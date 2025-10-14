package com.oopsw.action.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.EmployeeVO;

public class GetEmployeeCount implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/manager/getEmployeeCount.jsp";
		String filter = request.getParameter("filter");
		
		EmployeeVO vo = new EmployeeVO();
		if(!filter.isEmpty())
			vo.setAccessPermission(filter.toCharArray()[0]);
		request.setAttribute("empCount", new ManagerDAO().getEmployeeCount(vo));
		
		return url;
	}

}
