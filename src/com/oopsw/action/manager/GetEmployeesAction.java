package com.oopsw.action.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.EmployeeVO;
import com.google.gson.Gson;

public class GetEmployeesAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/manager/employeeAccess.jsp";
		Gson gson = new Gson();
		ManagerDAO dao = new ManagerDAO();
		EmployeeVO vo = new EmployeeVO();
		
		// ������ ����
		String pageParam = request.getParameter("page");
		String permission = request.getParameter("filter");
		
		// ������ ����
		if(pageParam != null && !pageParam.equals("")) 
			vo.setPage(Integer.parseInt(pageParam));
		// ���� ����
		if(permission != null && !permission.equals("")) 
			vo.setAccessPermission(permission.toCharArray()[0]);
		
		List<EmployeeVO> employeeList = dao.getEmployees(vo);
		String empJson = gson.toJson(employeeList);
		String ajax = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(ajax);

		// ����� ����Ʈ
		request.setAttribute("result", empJson);
		
		if(isAjax){
			url = "webpage/manager/resultAsync.jsp";
		}
		return url;
	}

}
