package com.oopsw.action.draft;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.DrafterDAO;
import com.oopsw.model.VO.ApprovalStatusVO;

public class GetApprovalStatusAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		int documentNo = Integer.parseInt(request.getParameter("documentNo"));
		List<ApprovalStatusVO> status = new DrafterDAO().getApprovalStatus(documentNo);
		System.out.println("½ÇÇà");
		System.out.println(status);
		request.setAttribute("result", status);
		return "webpage/result.jsp";
	}

}
