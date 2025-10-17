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
		
		HttpSession session = request.getSession(false);
		System.out.println(request);
		if(session == null) {
			return "webpage/employee/login.html";
		}

		String empId = (String) session.getAttribute("employeeId");
		String filter = request.getParameter("filter");
		
		String result = null;
		AlarmVO vo = new AlarmVO();
		vo.setEmpId(empId);
		vo.setNotiType(filter);
		
		if(!empId.isEmpty())
			result = new EmployeeDAO().getAllNotiCount(vo);
		
		request.setAttribute("result", result);
		
		return url;
	}

}
