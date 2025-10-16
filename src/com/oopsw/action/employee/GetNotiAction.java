package com.oopsw.action.employee;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.DrafterDAO;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.AlarmVO;
import com.oopsw.model.VO.GetListVO;

public class GetNotiAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/manager/resultAsync.jsp";

		// Ajax 비동기 통신이 아닌 경우
		String ajax = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(ajax);
		if(!isAjax) 
			return "webpage/noti/getNotificationList.jsp";
		
		HttpSession session = request.getSession(true);
		String empId = (String) session.getAttribute("employeeId");
		String page = request.getParameter("page");
		String filter = request.getParameter("filter");
		
		EmployeeDAO dao = new EmployeeDAO();
		AlarmVO vo = new AlarmVO();
		vo.setEmpId(empId);
		vo.setNotiType(filter);
		vo.setPage(Integer.parseInt(page));
		
		
		List<AlarmVO> list = dao.getAllNoti(vo);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String result = gson.toJson(list);
		
		request.setAttribute("result", result);
//		HttpSession session = request.getSession();
//		String employeeId = (String) session.getAttribute("employeeId");
//		String processStatus = request.getParameter("processStatus");
		
//		List<AlarmVO> list = new DrafterDAO().getApprovalProcessNoti(new GetListVO(employeeId, processStatus, 0));
		//setParameter필요
		return url;
	}

}
