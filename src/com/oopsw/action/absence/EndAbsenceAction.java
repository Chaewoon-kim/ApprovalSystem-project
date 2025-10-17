package com.oopsw.action.absence;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;

public class EndAbsenceAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "getAbsenceList.jsp";

		int absenceDateNo = Integer.parseInt(request.getParameter("absenceDateNo"));

		ApproverDAO dao = new ApproverDAO();
		boolean result = dao.endAbsence(absenceDateNo);

		if (result) {
			request.setAttribute("message", "위임이 성공적으로 철회되었습니다.");
		} else {
			request.setAttribute("message", "위임 철회 불가 (이미 종료되었거나 존재하지 않습니다.)");
		}

		return url;
	}

}
