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


		Gson gson = new Gson();
		String json = gson.toJson(commentList);


		HttpServletResponse response = (HttpServletResponse) request.getAttribute("javax.servlet.response");
		if (response == null) {
			throw new ServletException("HttpServletResponse를 찾을 수 없습니다.");
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;
	}
}
