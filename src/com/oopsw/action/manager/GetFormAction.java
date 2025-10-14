package com.oopsw.action.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.oopsw.action.Action;
import com.oopsw.model.DBCP;
import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.DocumentFormVO;
import com.oopsw.model.VO.FormVO;

public class GetFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "GetForm.jsp";
		
		FormVO vo = new FormVO();
		vo.setKeyword(null);

		ManagerDAO dao = new ManagerDAO();
		List<FormVO> list = dao.getForms(vo);
		
		request.setAttribute("formList", list);
		
		return url;
	}

}
