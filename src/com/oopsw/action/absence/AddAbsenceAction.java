package com.oopsw.action.absence;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.AbsenceVO;

public class AddAbsenceAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String addAbsencePage = "webpage/absence/addAbsence.jsp";
		
        HttpSession session = request.getSession();
        String approverId = (String) session.getAttribute("employeeId");
        if (approverId == null) {
            approverId = "E25-000"; 
        }

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String reason = request.getParameter("reason");
        String proxyId = request.getParameter("proxyId");
        
        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);
        Date today = new Date(System.currentTimeMillis());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String start = sdf.format(startDate); 
        String todayStr = sdf.format(today); 

        

        String usage = "���";
        if (start.equals(todayStr)) {
            usage = "����"; 
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
        
        Map<String, Object> jsonResult = new HashMap<>();
        
        if (start.compareTo(todayStr) < 0) {
            jsonResult.put("success", false);
            jsonResult.put("message", "�������� ���� �Ǵ� ���ķ� �����ؾ� �մϴ�.");
            return addAbsencePage;
        }

        if (endDate.before(startDate)) {
            jsonResult.put("success", false);
            jsonResult.put("message", "�������� ������ ���Ŀ��� �մϴ�.");
            return addAbsencePage;
        }
        
        if (result) {
            jsonResult.put("success", true);
            jsonResult.put("message", "���� ����� �Ϸ�Ǿ����ϴ�.");
        } else {
            jsonResult.put("success", false);
            jsonResult.put("message", "���� ��� �� ������ �߻��߽��ϴ�.");
        }

        Gson gson = new Gson();
        String json = gson.toJson(jsonResult);

        request.setAttribute("result", json);
        return "webpage/absence/absenceResult.jsp";
	}

}
