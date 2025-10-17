package com.oopsw.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.AbsenceVO;

public class GetModifyUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		int absenceDateNo = Integer.parseInt(request.getParameter("absenceDateNo"));
        ApproverDAO dao = new ApproverDAO();
        AbsenceVO vo = dao.getAbsenceDetail(absenceDateNo); 
        
        request.setAttribute("absence", vo); 
        return "webpage/absence/addAbsence.jsp";
	}

}
