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
import com.oopsw.model.VO.EmployeeVO;

// 결재하기(approvalProcess) 테스트용
public class GetDetailReportAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/employee/getDetailReport.jsp";
		
		HttpSession session = request.getSession(); 
//		EmployeeVO user = null;
        String employeeId = (String) session.getAttribute("employeeId");
        if (employeeId == null) {
            employeeId = "E25-000"; // 테스트용
        }

        
		EmployeeDAO employee = new EmployeeDAO();


		String documentParam = request.getParameter("documentNo");
		int documentNumber = 0;

		if(documentParam == null || documentParam.trim().isEmpty()){
			request.setAttribute("message", "조회할 문서 번호가 누락되었습니다.");
			return url;
		}

		try{
			documentNumber = Integer.parseInt(documentParam);			
		}catch(Exception e){
			request.setAttribute("message", "문서 번호 형식이 올바르지 않습니다.");
			return url;
		}
		
		
		// 현재 로그인한 결재자의 lineOrder 찾기
	    List<ApprovalLineEmployeeVO> approvalLines = employee.getApprvovalTable(documentNumber);
	    							
	    int lineOrder = -1;
	    for (ApprovalLineEmployeeVO line : approvalLines) {
	        if (line.getEmployeeId().equals(employeeId)) {
	            lineOrder = line.getLineOrder();
	            break;
	        }
	    }
	    System.out.println(lineOrder);
	    request.setAttribute("approvalLines", approvalLines);
	    request.setAttribute("approverLineOrder", lineOrder);
		
		
	    
		DocumentDetailVO detailDoc = null;
		try{
			detailDoc = employee.getDetailReport(documentNumber);

			if (detailDoc == null) {
				request.setAttribute("message", "문서 번호 " + documentNumber + "에 해당하는 결재 문서가 없습니다.");
				return url;
			}

			request.setAttribute("documentDetail", detailDoc);


			return url;

		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "문서 조회 중 오류 발생");

			return url;
		}


	}


}