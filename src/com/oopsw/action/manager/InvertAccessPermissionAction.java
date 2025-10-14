package com.oopsw.action.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.EmployeeVO;
import com.oopsw.model.VO.FormVO;

public class InvertAccessPermissionAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {

		String employeeId = request.getParameter("empId");

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmployeeId(employeeId);
		
		boolean result = new ManagerDAO().invertPermission(employeeVO);
		request.setAttribute("result", result);
		System.out.println(result);
		return "webpage/manager/resultAsync.jsp";
	}

}
