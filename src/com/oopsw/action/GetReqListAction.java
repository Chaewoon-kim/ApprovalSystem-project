package com.oopsw.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.DocumentVO;
import com.oopsw.model.DrafterDAO;
import com.oopsw.model.GetListVO;

public class GetReqListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeId = (String) session.getAttribute("employeeId");
		String processStatus = request.getParameter("processStatus");
		int page = Integer.parseInt(request.getParameter("page"));
		
		List<DocumentVO> list = new DrafterDAO().getReqList(new GetListVO(employeeId, processStatus, page));
		//setParameter로 넘겨줘야함
		return "getReport.jsp";
	}

}
