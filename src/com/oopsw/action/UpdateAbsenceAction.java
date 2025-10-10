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
            usage = "�����";
        } else if (today.after(endDate)) {
            usage = "����";
        } else {
            usage = "������";
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
            request.setAttribute("message", "���� ������ ���������� �����Ǿ����ϴ�.");
        } else {
            request.setAttribute("message", "���� ���� �� ������ �߻��߽��ϴ�.");
        }

        return url;
	}

}
