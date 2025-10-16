package com.oopsw.action.absence;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.AbsenceVO;

public class AddAbsenceAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String absenceListPage = "webpage/absence/getAbsenceList.jsp"; // fail page
		String addAbsencePage = "webpage/absence/addAbsence.jsp";
		
        HttpSession session = request.getSession();
        String approverId = (String) session.getAttribute("employeeId");
        if (approverId == null) {
            approverId = "E25-000"; // �׽�Ʈ��
        }

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String reason = request.getParameter("reason");
        String proxyId = request.getParameter("proxyId");
        
//        System.out.println(proxyId);
//        System.out.println(reason);
//        System.out.println(startDateStr);
//        System.out.println(endDateStr);
        
        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);
        Date today = new Date(System.currentTimeMillis());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String start = sdf.format(startDate); 
        String todayStr = sdf.format(today); 

        if (start.compareTo(todayStr) < 0) {
            request.setAttribute("message", "�������� ���� �Ǵ� ���ķ� �����ؾ� �մϴ�.");
            return addAbsencePage;
        }

        if (endDate.before(startDate)) {
            request.setAttribute("message", "�������� ������ ���Ŀ��� �մϴ�.");
            return addAbsencePage;
        }
        
//        if(reason == null){
//        	request.setAttribute("message", "���� ������ �ۼ����ּ���.");
//        	return url;
//        }
//        if(proxyId == null){
//        	request.setAttribute("message", "����ڸ� �������ּ���.");
//        	return url;
//        }
        
        // ���� ���� 
        String usage = "���";
        if (start.equals(todayStr)) {
            usage = "����"; // �������� ������ ���
        }
        
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

        return absenceListPage;
	}

}
