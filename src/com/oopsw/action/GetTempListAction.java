package com.oopsw.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.DrafterDAO;
import com.oopsw.model.TempDocumentVO;

public class GetTempListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeId = (String) session.getAttribute("employeeId");
		List<TempDocumentVO> list = new DrafterDAO().getTempList(employeeId);
		//setParameter
		return null;
	}

}
