package com.oopsw.action.draft;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;

public class GetTempDocAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String documentNo = request.getParameter("documentNo");
		//세부상세조회와 같으니 나중에
		return null;
	}

}
