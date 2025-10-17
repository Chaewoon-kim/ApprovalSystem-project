package com.oopsw.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.FormVO;

public class GetDraftForm implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		
		FormVO vo = new FormVO();
		vo.setKeyword(request.getParameter("keyword"));

		ManagerDAO dao = new ManagerDAO();
		List<FormVO> list = dao.getForms(vo);

		request.setAttribute("result", list);
		return "webpage/result.jsp";
	}

}
