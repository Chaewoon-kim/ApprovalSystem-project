package com.oopsw.action.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;

public class GetEmployeesAction implements Action {

	@Override 
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "getEmployees.jsp";
	}

}
