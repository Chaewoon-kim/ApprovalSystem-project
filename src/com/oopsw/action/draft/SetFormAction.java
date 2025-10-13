package com.oopsw.action.draft;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.DrafterDAO;
import com.oopsw.model.VO.DefaultLineVO;
import com.oopsw.model.VO.DocumentFormVO;
import com.oopsw.model.VO.GetDefaultLineVO;

public class SetFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeId = (String) session.getAttribute("employeeId");
		String formId = request.getParameter("formId");
		DocumentFormVO form = new DrafterDAO().setForm(formId);
		List<DefaultLineVO> lines = new DrafterDAO().getDefaultLine(new GetDefaultLineVO(employeeId, formId));
		//setParameter ÇØ¾ßÇÔ
		return "addReport.jsp";
	}

}
