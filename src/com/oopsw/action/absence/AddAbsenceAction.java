package com.oopsw.action.absence;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.AbsenceVO;

public class AddAbsenceAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/absence/getAbsenceList.jsp"; // fail page

        HttpSession session = request.getSession();
        String approverId = (String) session.getAttribute("employeeId");
        if (approverId == null) {
            approverId = "E25-000"; // 테스트용
        }

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String reason = request.getParameter("reason");
        String proxyId = request.getParameter("proxyId");
        
        System.out.println(proxyId);
        System.out.println(reason);
        System.out.println(startDateStr);
        System.out.println(endDateStr);
        
        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);
        Date today = new Date(System.currentTimeMillis());
        
        // 부재기간 유효성검사?
        if (startDate.before(today)) {
            request.setAttribute("message", "시작일은 오늘 이후로 설정해야 합니다.");
            return "webpage/absence/addAbsence.jsp";
        }
        if (endDate.before(startDate)) {
            request.setAttribute("message", "종료일은 시작일 이후여야 합니다.");
            return "webpage/absence/addAbsence.jsp";
        }
        
        // 부재 상태 
        String usage = "대기";
        if (startDate.equals(today)) {
            usage = "위임"; // 시작일이 오늘일 경우
        }
        
        System.out.println(usage);
        AbsenceVO vo = new AbsenceVO();
        vo.setAbsenteeId(approverId);
        vo.setProxyId(proxyId);
        vo.setAbsenceStartDate(Date.valueOf(startDateStr));
        vo.setAbsenceEndDate(Date.valueOf(endDateStr));
        vo.setAbsenceReason(reason);
        vo.setAbsenceUsage(usage); 
        // noti_in_date, read_status는 DB에서 sysdate/null 자동 처리

        ApproverDAO dao = new ApproverDAO();
        boolean result = dao.addAbsence(vo);

        if (result) {
            request.setAttribute("message", "부재 등록이 완료되었습니다.");
        } else {
            request.setAttribute("message", "부재 등록 중 오류가 발생했습니다.");
        }

        return url;
	}

}
