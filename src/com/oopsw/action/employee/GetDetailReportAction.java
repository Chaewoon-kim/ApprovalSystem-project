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

// �����ϱ�(approvalProcess) �׽�Ʈ��
public class GetDetailReportAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/employee/getDetailReport.jsp";
		
		HttpSession session = request.getSession(); 
//		EmployeeVO user = null;
        String employeeId = (String) session.getAttribute("employeeId");
        if (employeeId == null) {
            employeeId = "E25-000"; // �׽�Ʈ��
        }

        
		EmployeeDAO employee = new EmployeeDAO();


		String documentParam = request.getParameter("documentNo");
		int documentNumber = 0;

		if(documentParam == null || documentParam.trim().isEmpty()){
			request.setAttribute("message", "��ȸ�� ���� ��ȣ�� �����Ǿ����ϴ�.");
			return url;
		}

		try{
			documentNumber = Integer.parseInt(documentParam);			
		}catch(Exception e){
			request.setAttribute("message", "���� ��ȣ ������ �ùٸ��� �ʽ��ϴ�.");
			return url;
		}
		
		
		// ���� �α����� �������� lineOrder ã��
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
				request.setAttribute("message", "���� ��ȣ " + documentNumber + "�� �ش��ϴ� ���� ������ �����ϴ�.");
				return url;
			}

			request.setAttribute("documentDetail", detailDoc);


			return url;

		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "���� ��ȸ �� ���� �߻�");

			return url;
		}


	}


}