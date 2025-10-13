package com.oopsw.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.ApproverDAO;
import com.oopsw.model.ApproverListVO;

public class GetEndListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		 // ���ǿ��� �α����� ����� ���� ��������
          HttpSession session = request.getSession();
//        EmployeeVO loginUser = (EmployeeVO) session.getAttribute("loginUser");
       // String approverId = loginUser.getEmployeeId();
          String approverId = request.getParameter("employeeId");
          
        ApproverDAO dao = new ApproverDAO();
        List<ApproverListVO> endList = dao.getEndList(approverId);

        request.setAttribute("endList", endList);

        return "approvalEndList.jsp";
	}

}
