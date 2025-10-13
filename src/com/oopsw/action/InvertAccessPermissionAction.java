package com.oopsw.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.EmployeeVO;
import com.oopsw.model.FormVO;
import com.oopsw.model.ManagerDAO;

public class InvertAccessPermissionAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {

		String employeeId = request.getParameter("employeeId");

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmployeeId(employeeId);
		
		boolean result = new ManagerDAO().invertPermission(employeeVO);
		request.setAttribute("result", result);
		
		return "invertAccessPermission.jsp";
	}

}
