package com.oopsw.action.absence;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
		HttpSession session = request.getSession();
	    String approverId = (String) session.getAttribute("employeeId");

	    String startDateStr = request.getParameter("startDate");
	    String endDateStr = request.getParameter("endDate");
	    String reason = request.getParameter("reason");
	    String proxyId = request.getParameter("proxyId");
	    Date startDate = Date.valueOf(startDateStr);
	    Date endDate = Date.valueOf(endDateStr);
	    Date today = Date.valueOf(LocalDate.now()); 
	    
	    Map<String, Object> jsonResult = new HashMap<>();

	    // 날짜 유효성 검증
	    if (endDate.before(startDate)) {
	        jsonResult.put("success", false);
	        jsonResult.put("message", "종료일은 시작일 이후여야 합니다.");
	        return jsonResponse(request, jsonResult);
	    }
	    if (startDate.before(today)) {
	        jsonResult.put("success", false);
	        jsonResult.put("message", "시작일은 오늘 또는 이후로 설정해야 합니다.");
	        return jsonResponse(request, jsonResult);
	    }

	    AbsenceVO vo = new AbsenceVO();
	    vo.setAbsenteeId(approverId);
	    vo.setProxyId(proxyId);
	    vo.setAbsenceStartDate(startDate);
	    vo.setAbsenceEndDate(endDate);
	    vo.setAbsenceReason(reason);
	    vo.setAbsenceUsage(startDate.equals(today) ? "위임" : "대기");

	    ApproverDAO dao = new ApproverDAO();
	    
	    // 부재기간 중복체크
	    boolean overlap = dao.hasOverlapAbsence(vo);
	    if (overlap) {
	        jsonResult.put("success", false);
	        jsonResult.put("message", "해당 기간에 이미 등록된 부재가 있습니다.");
	        return jsonResponse(request, jsonResult);
	    }
	    
	    if (approverId.equals(proxyId)) {
	        jsonResult.put("success", false);
	        jsonResult.put("message", "자기 자신을 대결자로 지정할 수 없습니다.");
	        return jsonResponse(request, jsonResult);
	    }
	
	    boolean result = dao.addAbsence(vo);
	    jsonResult.put("success", true);
	    jsonResult.put("message", "부재 등록이 완료되었습니다.");
	    
	    if(!result) {
	        jsonResult.put("success", false);
	        jsonResult.put("message", "부재 등록 중 오류가 발생했습니다.");
	    } 

	    return jsonResponse(request, jsonResult);
	}

	private String jsonResponse(HttpServletRequest request, Map<String, Object> result) {
	    Gson gson = new Gson();
	    request.setAttribute("result", gson.toJson(result));
	    return "webpage/absence/absenceResult.jsp";
	}

}
