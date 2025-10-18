package com.oopsw.action.employee;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.CommentVO;

public class GetCommentsAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {

		int documentNo = Integer.parseInt(request.getParameter("documentNo"));        
		EmployeeDAO dao = new EmployeeDAO();
		List<CommentVO> commentList = dao.getComments(documentNo);


		request.setAttribute("result",commentList);

		return "webpage/result.jsp";
	}
}