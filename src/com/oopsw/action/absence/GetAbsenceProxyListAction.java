package com.oopsw.action.absence;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.AbsenceListVO;

public class GetAbsenceProxyListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "getAbsenceList.jsp"; // ��� ǥ���� JSP

        HttpSession session = request.getSession(true);
//      EmployeeVO loginUser = (EmployeeVO) session.getAttribute("loginUser");
     // String approverId = loginUser.getEmployeeId();
        
//      String employeeId = (String) session.getAttribute(loginId);
        String employeeId = "E25-007"; // �ӽ�

        ApproverDAO dao = new ApproverDAO();

        List<AbsenceListVO> absenceList = dao.getAbsenceList(employeeId);
        List<AbsenceListVO> proxyList = dao.getProxyList(employeeId);

        request.setAttribute("absenceList", absenceList);
        request.setAttribute("proxyList", proxyList);

        return url;
    
	}

}
