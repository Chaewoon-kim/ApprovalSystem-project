package com.oopsw.action.employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;

public class readNotiAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "";

		String notiType = request.getParameter("notiType");
		String notiStr = request.getParameter("notiNo");
		
		int notiNo = Integer.parseInt(notiStr);
		EmployeeDAO dao = new EmployeeDAO();
		boolean result = false;
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
		System.out.println(result);
		request.setAttribute("result", result);
		return "webpage/manager/resultAsync.jsp";
	}

}
