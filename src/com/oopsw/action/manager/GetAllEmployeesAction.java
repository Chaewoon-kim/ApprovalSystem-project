package com.oopsw.action.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.EmployeeVO;

public class GetAllEmployeesAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		List<EmployeeVO> list = new EmployeeDAO().getAllEmployees();
		request.setAttribute("result", list);
		return "webpage/result.jsp";
	}

}
