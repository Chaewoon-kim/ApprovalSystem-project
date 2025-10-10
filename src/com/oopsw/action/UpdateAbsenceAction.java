package com.oopsw.action;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.model.AbsenceVO;
import com.oopsw.model.ApproverDAO;

public class UpdateAbsenceAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "getAbsenceList.jsp"; 

        int absenceDateNo = Integer.parseInt(request.getParameter("absenceDateNo"));
        String proxyId = request.getParameter("proxyId");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String reason = request.getParameter("reason");

        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);
        Date today = new Date(System.currentTimeMillis());

        String usage;
        if (today.before(startDate)) {
            usage = "대기중";
        } else if (today.after(endDate)) {
            usage = "종료";
        } else {
            usage = "위임중";
        }

        AbsenceVO vo = new AbsenceVO();
        vo.setAbsenceDateNo(absenceDateNo);
        vo.setProxyId(proxyId);
        vo.setAbsenceStartDate(startDate);
        vo.setAbsenceEndDate(endDate);
        vo.setAbsenceReason(reason);
        vo.setAbsenceUsage(usage);

        ApproverDAO dao = new ApproverDAO();
        boolean result = dao.updateAbsence(vo);

        if (result) {
            request.setAttribute("message", "부재 정보가 성공적으로 수정되었습니다.");
        } else {
            request.setAttribute("message", "부재 수정 중 오류가 발생했습니다.");
        }

        return url;
	}

}
