package com.oopsw.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.FormVO;
import com.oopsw.model.ManagerDAO;

public class InvertAccessPermissionAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {

		String formId = request.getParameter("formId");
		
		FormVO formVO = new FormVO();
		formVO.setFormId(formId);
		
		boolean result = new ManagerDAO().invertFormUsage(formVO);
		request.setAttribute("result", result);
		
		return "invertAccessPermission.jsp";
	}

}
