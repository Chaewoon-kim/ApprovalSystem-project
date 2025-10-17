package com.oopsw.action.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.EmployeeVO;
import com.oopsw.model.VO.FormVO;

public class InvertFormUsageAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String ajax = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(ajax);
		if(!isAjax) return "";
		
		String formId = request.getParameter("formId");

		FormVO vo = new FormVO();
		vo.setFormId(formId);
		
		boolean result = new ManagerDAO().invertFormUsage(vo);
		request.setAttribute("result", result);
		return "webpage/manager/resultAsync.jsp";
	}

}
