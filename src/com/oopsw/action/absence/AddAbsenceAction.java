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
		String url = "getAbsenceList.jsp"; // 부재/대결 목록 페이지로 이동

        HttpSession session = request.getSession(true);

        // 로그인 정보 가져오기 (임시)
        // 로그인 연동 후 session에서 가져오기
        // String absenteeId = (String) session.getAttribute("loginId");
        String absenteeId = "E25-007";  // 테스트용

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String reason = request.getParameter("reason");
        String proxyId = request.getParameter("proxyId");
        
        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);
        Date today = new Date(System.currentTimeMillis());
        
        // 부재기간 유효성검사?
        if (startDate.before(today)) {
            request.setAttribute("message", "시작일은 오늘 이후로 설정해야 합니다.");
            return url;
        }
        if (endDate.before(startDate)) {
            request.setAttribute("message", "종료일은 시작일 이후여야 합니다.");
            return url;
        }
        
        // 부재 상태 
        String usage;
        if (startDate.equals(today)) {
            usage = "위임"; // 시작일이 오늘일 경우
        } else {
            usage = "대기중";
        }
        
        AbsenceVO vo = new AbsenceVO();
        vo.setAbsenteeId(absenteeId);
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
