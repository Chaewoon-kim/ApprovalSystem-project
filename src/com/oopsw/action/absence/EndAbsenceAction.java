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
			request.setAttribute("message", "������ ���������� öȸ�Ǿ����ϴ�.");
		} else {
			request.setAttribute("message", "���� öȸ �Ұ� (�̹� ����Ǿ��ų� �������� �ʽ��ϴ�.)");
		}

		return url;
	}

}
