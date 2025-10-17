package com.oopsw.action.employee;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.ApprovalLineEmployeeVO;
import com.oopsw.model.VO.DocumentDetailVO;

public class GetDetailReportAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		String url = "webpage/employee/getDetailReport.jsp";

		
		if (session == null || session.getAttribute("User") == null) {
			request.setAttribute("message", "로그인이 필요합니다.");
			return "webpage/employee/login.jsp";
		}

		
		EmployeeDAO employee = new EmployeeDAO();

		String documentParam = request.getParameter("documentNo");
		int documentNumber = 0;

	
		if (documentParam == null || documentParam.trim().isEmpty()) {
			request.setAttribute("message", "조회할 문서 번호가 누락되었습니다.");
			return url;
		}

		
		try {
			documentNumber = Integer.parseInt(documentParam);
		} catch (NumberFormatException e) {
			request.setAttribute("message", "문서 번호 형식이 올바르지 않습니다.");
			return url;
		}

		
		 try {
		        DocumentDetailVO detailDoc = employee.getDetailReport(documentNumber);
		        if (detailDoc == null) {
		            request.setAttribute("message", "문서 번호 " + documentNumber + "에 해당하는 결재 문서가 없습니다.");
		            return url;
		        }

		        List<ApprovalLineEmployeeVO> getApprovalTable = employee.getApprovalTable(documentNumber);
		        request.setAttribute("documentDetail", detailDoc);
		        request.setAttribute("approvalTable", getApprovalTable);

		        
		        //GetCommentListAction commentAction = new GetCommentListAction();
		        //commentAction.execute(request); 

		        return url;

		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("message", "문서 조회 중 오류가 발생했습니다.");
		        return url;
		    }
		}
}