package com.oopsw.action.absence;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.AbsenceVO;

public class ModifyAbsenceAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		 int absenceDateNo = Integer.parseInt(request.getParameter("absenceDateNo"));
	        String proxyId = request.getParameter("proxyId");
	        String startDateStr = request.getParameter("startDate");
	        String endDateStr = request.getParameter("endDate");
	        String reason = request.getParameter("reason");

	        Date startDate = Date.valueOf(startDateStr);
	        Date endDate = Date.valueOf(endDateStr);
	        Date today = new Date(System.currentTimeMillis());

	        String usage = "위임";
	        if (today.before(startDate)) {
	            usage = "대기";
	        } else if (today.after(endDate)) {
	            usage = "종료";
	        }

	        AbsenceVO vo = new AbsenceVO();
	        vo.setAbsenceDateNo(absenceDateNo);
	        vo.setProxyId(proxyId);
	        vo.setAbsenceStartDate(startDate);
	        vo.setAbsenceEndDate(endDate);
	        vo.setAbsenceReason(reason);
	        vo.setAbsenceUsage(usage);

	        ApproverDAO dao = new ApproverDAO();
	        boolean result = dao.modifyAbsence(vo);

	        Map<String, Object> jsonResult = new HashMap<>();
	        if (result) {
	            jsonResult.put("success", true);
	            jsonResult.put("message", "부재 정보가 성공적으로 수정되었습니다.");
	        } else {
	            jsonResult.put("success", false);
	            jsonResult.put("message", "부재 수정 중 오류가 발생했습니다.");
	        }
	        
	        Gson gson = new Gson();
	        String json = gson.toJson(jsonResult);

	        request.setAttribute("result", json);
	        return "webpage/absence/absenceResult.jsp";
	    }
	}