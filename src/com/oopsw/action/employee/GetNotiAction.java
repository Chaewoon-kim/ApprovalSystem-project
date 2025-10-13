package com.oopsw.action.employee;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.DrafterDAO;
import com.oopsw.model.VO.AlarmVO;
import com.oopsw.model.VO.GetListVO;

public class GetNotiAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = null;
		HttpSession session = request.getSession();
		String employeeId = (String) session.getAttribute("employeeId");
		String processStatus = request.getParameter("processStatus");
		
		List<AlarmVO> list = new DrafterDAO().getApprovalProcessNoti(new GetListVO(employeeId, processStatus, 0));
		//setParameter« ø‰
		return url;
	}

}
