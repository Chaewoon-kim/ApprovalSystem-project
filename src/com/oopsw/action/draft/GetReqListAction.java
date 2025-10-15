package com.oopsw.action.draft;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.DrafterDAO;
import com.oopsw.model.VO.DocumentVO;
import com.oopsw.model.VO.GetListVO;

public class GetReqListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeId = (String) session.getAttribute("employeeId");
		String processStatus = request.getParameter("processStatus").isEmpty() ? null : request.getParameter("processStatus");
		int page = Integer.parseInt(request.getParameter("page"));		
		List<DocumentVO> list = new DrafterDAO().getReqList(new GetListVO(employeeId, processStatus, page));
		
		request.setAttribute("result", list);
		
		return "webpage/result.jsp";
	}

}
