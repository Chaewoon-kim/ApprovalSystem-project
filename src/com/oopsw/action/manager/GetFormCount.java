package com.oopsw.action.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.EmployeeVO;
import com.oopsw.model.VO.FormVO;

public class GetFormCount implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/manager/resultAsync.jsp";
		String keyword = request.getParameter("keyword");
		System.out.println(keyword);
		FormVO vo = new FormVO();
		if(!keyword.isEmpty())
			vo.setKeyword(keyword);
		
		System.out.println(vo.getKeyword());
		
		int page = new ManagerDAO().getFormCount(vo);
		request.setAttribute("result", page);
		
		return url;
	}

}
