package com.oopsw.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.model.DrafterDAO;
import com.oopsw.model.DocumentFormVO;

public class SetFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String formId = request.getParameter("formId");
		DocumentFormVO form = new DrafterDAO().setForm("D1");
		//setParameter ÇØ¾ßÇÔ
		return "addReport.jsp";
	}

}
