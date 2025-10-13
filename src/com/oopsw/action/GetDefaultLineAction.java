package com.oopsw.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.DefaultLineVO;
import com.oopsw.model.DrafterDAO;
import com.oopsw.model.GetDefaultLineVO;

public class GetDefaultLineAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeId = (String) session.getAttribute("employeeId");
		String formId = request.getParameter("formId");
		List<DefaultLineVO> lines = new DrafterDAO().getDefaultLine(new GetDefaultLineVO(employeeId, formId));
		return "defaultLine.jsp";
	}

}
