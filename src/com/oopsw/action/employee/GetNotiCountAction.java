package com.oopsw.action.employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.AlarmVO;
import com.oopsw.model.VO.EmployeeVO;

public class GetNotiCountAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/manager/resultAsync.jsp";
		System.out.println("hit");

		HttpSession session = request.getSession(); 
        String employeeId = (String) session.getAttribute("employeeId");
		
		String filter = request.getParameter("filter");

		System.out.println("hit");
		String result = null;
		AlarmVO vo = new AlarmVO();
		vo.setEmpId(employeeId);
		vo.setNotiType(filter);
		
		if(!employeeId.isEmpty())
			result = new EmployeeDAO().getAllNotiCount(vo);
		
		request.setAttribute("result", result);
		
		return url;
	}

}
