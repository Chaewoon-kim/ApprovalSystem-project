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
		String url = "getAbsenceList.jsp"; // ����/��� ��� �������� �̵�

        HttpSession session = request.getSession(true);

        // �α��� ���� �������� (�ӽ�)
        // �α��� ���� �� session���� ��������
        // String absenteeId = (String) session.getAttribute("loginId");
        String absenteeId = "E25-007";  // �׽�Ʈ��

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String reason = request.getParameter("reason");
        String proxyId = request.getParameter("proxyId");
        
        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);
        Date today = new Date(System.currentTimeMillis());
        
        // ����Ⱓ ��ȿ���˻�?
        if (startDate.before(today)) {
            request.setAttribute("message", "�������� ���� ���ķ� �����ؾ� �մϴ�.");
            return url;
        }
        if (endDate.before(startDate)) {
            request.setAttribute("message", "�������� ������ ���Ŀ��� �մϴ�.");
            return url;
        }
        
        // ���� ���� 
        String usage;
        if (startDate.equals(today)) {
            usage = "����"; // �������� ������ ���
        } else {
            usage = "�����";
        }
        
        AbsenceVO vo = new AbsenceVO();
        vo.setAbsenteeId(absenteeId);
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
