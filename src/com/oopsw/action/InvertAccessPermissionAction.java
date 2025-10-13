package com.oopsw.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.EmployeeVO;
import com.oopsw.model.VO.FormVO;

public class InvertAccessPermissionAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {

		String employeeId = request.getParameter("employeeId");

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmployeeId(employeeId);
		
		boolean result = new ManagerDAO().invertPermissioin(employeeVO);
		request.setAttribute("result", result);
		
		return "invertAccessPermission.jsp";
	}

}
