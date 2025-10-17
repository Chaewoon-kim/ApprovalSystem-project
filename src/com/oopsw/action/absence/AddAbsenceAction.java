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

        

        String usage = "대기";
        if (start.equals(todayStr)) {
            usage = "위임"; 
        }
        
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
        
        Map<String, Object> jsonResult = new HashMap<>();
        
        if (start.compareTo(todayStr) < 0) {
            jsonResult.put("success", false);
            jsonResult.put("message", "시작일은 오늘 또는 이후로 설정해야 합니다.");
            return addAbsencePage;
        }

        if (endDate.before(startDate)) {
            jsonResult.put("success", false);
            jsonResult.put("message", "종료일은 시작일 이후여야 합니다.");
            return addAbsencePage;
        }
        
        if (result) {
            jsonResult.put("success", true);
            jsonResult.put("message", "부재 등록이 완료되었습니다.");
        } else {
            jsonResult.put("success", false);
            jsonResult.put("message", "부재 등록 중 오류가 발생했습니다.");
        }

        Gson gson = new Gson();
        String json = gson.toJson(jsonResult);

        request.setAttribute("result", json);
        return "webpage/absence/absenceResult.jsp";
	}

}
