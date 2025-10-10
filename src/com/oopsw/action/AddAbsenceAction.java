package com.oopsw.action;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.AbsenceVO;
import com.oopsw.model.ApproverDAO;

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

        // 부재 상태 결정
        String usage;
        if (today.before(startDate)) {
            usage = "대기중";
        } else if (today.after(endDate)) {
            usage = "종료";
        } else {
            usage = "위임중";
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
