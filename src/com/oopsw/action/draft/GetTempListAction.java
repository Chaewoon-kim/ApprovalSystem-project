package com.oopsw.action.draft;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.DrafterDAO;
import com.oopsw.model.VO.GetListVO;
import com.oopsw.model.VO.TempDocumentVO;

public class GetTempListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeId = (String) session.getAttribute("employeeId");
		int page = Integer.parseInt(request.getParameter("page"));	
		List<TempDocumentVO> list = new DrafterDAO().getTempList(new GetListVO(employeeId, null, page));
		request.setAttribute("result", list);
		
		return "webpage/result.jsp";
	}

}
