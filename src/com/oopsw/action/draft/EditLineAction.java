package com.oopsw.action.draft;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;

public class EditLineAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		return "webpage/draft/updateLine.jsp";
	}

}
