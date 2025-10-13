package com.oopsw.action.approve;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.ApproverListVO;

public class GetWaitListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		 	HttpSession session = request.getSession();
		 	// 로그인한 사원의 사번 가져오기
	        // EmployeeVO loginUser = (EmployeeVO) session.getAttribute("loginUser");
	        // String approverId = loginUser.getEmployeeId();

	        String approverId = request.getParameter("employeeId");

	        ApproverDAO dao = new ApproverDAO();
	        List<ApproverListVO> waitList = dao.getWaitList(approverId);

	        request.setAttribute("waitList", waitList);

	        return "getApprovalWaitList.jsp";
	}

}
