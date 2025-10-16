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
            approverId = "E25-000"; // �׽�Ʈ��
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
        
        // ����Ⱓ ��ȿ���˻�?
        if (startDate.before(today)) {
            request.setAttribute("message", "�������� ���� ���ķ� �����ؾ� �մϴ�.");
            return "webpage/absence/addAbsence.jsp";
        }
        if (endDate.before(startDate)) {
            request.setAttribute("message", "�������� ������ ���Ŀ��� �մϴ�.");
            return "webpage/absence/addAbsence.jsp";
        }
        
        // ���� ���� 
        String usage = "���";
        if (startDate.equals(today)) {
            usage = "����"; // �������� ������ ���
        }
        
        System.out.println(usage);
        AbsenceVO vo = new AbsenceVO();
        vo.setAbsenteeId(approverId);
        vo.setProxyId(proxyId);
        vo.setAbsenceStartDate(Date.valueOf(startDateStr));
        vo.setAbsenceEndDate(Date.valueOf(endDateStr));
        vo.setAbsenceReason(reason);
        vo.setAbsenceUsage(usage); 
        // noti_in_date, read_status�� DB���� sysdate/null �ڵ� ó��

        ApproverDAO dao = new ApproverDAO();
        boolean result = dao.addAbsence(vo);

        if (result) {
            request.setAttribute("message", "���� ����� �Ϸ�Ǿ����ϴ�.");
        } else {
            request.setAttribute("message", "���� ��� �� ������ �߻��߽��ϴ�.");
        }

        return url;
	}

}
