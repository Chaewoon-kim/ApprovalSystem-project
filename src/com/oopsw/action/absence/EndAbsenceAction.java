package com.oopsw.action.absence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;

public class EndAbsenceAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/absence/getAbsenceList.jsp";
		
		
		int absenceDateNo = Integer.parseInt(request.getParameter("absenceDateNo"));
		
		ApproverDAO dao = new ApproverDAO();
		boolean result = dao.endAbsence(absenceDateNo);
		
		String message = result ? "������ ���������� öȸ�Ǿ����ϴ�." : "���� öȸ �Ұ� (�̹� ����Ǿ��ų� �������� �ʽ��ϴ�.)";

	        Map<String, Object> map = new HashMap<>();
	        map.put("success", result);
	        map.put("message", message);

	        String json = new Gson().toJson(map);
	        request.setAttribute("result", json);
	        
	        
	        	return "webpage/absence/endAbsenceResult.jsp";
	       
	}

}
