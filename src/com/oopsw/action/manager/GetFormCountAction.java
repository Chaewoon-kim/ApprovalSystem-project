package com.oopsw.action.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.EmployeeVO;
import com.oopsw.model.VO.FormVO;

public class GetFormCountAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/manager/resultAsync.jsp";
		String keyword = request.getParameter("keyword");
		FormVO vo = new FormVO();
		if(!keyword.isEmpty())
			vo.setKeyword(keyword);
		
		int page = new ManagerDAO().getFormCount(vo);
		request.setAttribute("result", page);
		
		return url;
	}

}
