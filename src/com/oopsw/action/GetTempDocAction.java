package com.oopsw.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class GetTempDocAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String documentNo = request.getParameter("documentNo");
		//���λ���ȸ�� ������ ���߿�
		return null;
	}

}
