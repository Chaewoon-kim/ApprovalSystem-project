package com.oopsw.action.employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.AlarmVO;

public class ReadNotiAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "";

		String notiListJson = request.getParameter("notiList");
		
		Gson gson = new Gson();
		List<Map<String, Object>> voList = gson.fromJson(notiListJson, List.class);
				
		boolean result = false;
		for(Map<String, Object> vo : voList){
			EmployeeDAO dao = new EmployeeDAO();
			int notiNo = ((Number)vo.get("notiNo")).intValue();
			String notiType = (String)vo.get("notiType");
						
			switch (notiType) {
			case "C":
				result = dao.readCommentNoti(notiNo);
				break;
			case "A":
				result = dao.readAbsenceNoti(notiNo);	
				break;
			case "R":
				result = dao.readRequestNoti(notiNo);
				break;
			case "P":
				result = dao.readProcessNoti(notiNo);
				break;
			}
		}
		
		request.setAttribute("result", result);
		return "webpage/manager/resultAsync.jsp";
	}

}
