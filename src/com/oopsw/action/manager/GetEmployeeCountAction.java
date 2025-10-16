package com.oopsw.action.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.EmployeeVO;

public class GetEmployeeCountAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/manager/resultAsync.jsp";
		String filter = request.getParameter("filter");
		
		EmployeeVO vo = new EmployeeVO();
		if(!filter.isEmpty())
			vo.setAccessPermission(filter.toCharArray()[0]);
		request.setAttribute("result", new ManagerDAO().getEmployeeCount(vo));
		
		return url;
	}

}
