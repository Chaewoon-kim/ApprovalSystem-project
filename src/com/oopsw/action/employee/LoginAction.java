package com.oopsw.action.employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.EmployeeVO;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/employee/login.html";//����������
		String employeeId = request.getParameter("employeeId");
		String password = request.getParameter("password");
		
		EmployeeVO vo = new EmployeeDAO().login(new EmployeeVO(employeeId, password));
		if (vo != null){//�α��� ������
			HttpSession session = request.getSession(true);
			session.setAttribute("employeeId", employeeId);
			session.setAttribute("name", vo.getName());
			session.setAttribute("department", vo.getDepartment());
			session.setAttribute("rank", vo.getRank());
			session.setAttribute("isManager", vo.getManagerPermission());
		
			url = "webpage/approve/getApprovalWaitList.html";
			if(vo.getRank().equals("���")){
				url = "webpage/draft/getReport.html";
			}
		}
		return url;
	}

}
