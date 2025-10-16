package com.oopsw.action.draft;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.ApprovalLineEmployeeVO;
import com.oopsw.model.VO.DocumentDetailVO;
import com.oopsw.model.VO.EmployeeVO;

/* 결재하기(approvalProcess) 테스트용  Action */
public class GetTempDocAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/draft/getSaveList.jsp";
		
		HttpSession session = request.getSession(); 

        String employeeId = (String) session.getAttribute("employeeId");
        String documentParam = request.getParameter("documentNo");
		EmployeeDAO employee = new EmployeeDAO();

		if(documentParam == null || documentParam.trim().isEmpty()){
			request.setAttribute("message", "조회할 문서 번호가 누락되었습니다.");
			return url;
		}
		
		int documentNumber = 0;
		try{
			documentNumber = Integer.parseInt(documentParam);			
		}catch(Exception e){
			request.setAttribute("message", "문서 번호 형식이 올바르지 않습니다.");
			return url;
		}
		
	    List<ApprovalLineEmployeeVO> approvalLines = employee.getApprvovalTable(documentNumber);
	    
	    request.setAttribute("approvalLines", approvalLines);

		DocumentDetailVO detailDoc = null;
		try{
			detailDoc = employee.getDetailReport(documentNumber);

			if (detailDoc == null) {
				request.setAttribute("message", "문서 번호 " + documentNumber + "에 해당하는 문서가 없습니다.");
				return url;
			}

			request.setAttribute("documentDetail", detailDoc);

			url = "webpage/draft/addReport.jsp";
			return url;

		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "문서 조회 중 오류 발생");

			return url;
		}


	}


}