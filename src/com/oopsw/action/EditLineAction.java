package com.oopsw.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class EditLineAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		return "webpage/draft/updateLine.jsp";
	}

}
