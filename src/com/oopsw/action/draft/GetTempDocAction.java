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

/* �����ϱ�(approvalProcess) �׽�Ʈ��  Action */
public class GetTempDocAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/draft/getSaveList.jsp";
		
		HttpSession session = request.getSession(); 

        String employeeId = (String) session.getAttribute("employeeId");
        String documentParam = request.getParameter("documentNo");
		EmployeeDAO employee = new EmployeeDAO();

		if(documentParam == null || documentParam.trim().isEmpty()){
			request.setAttribute("message", "��ȸ�� ���� ��ȣ�� �����Ǿ����ϴ�.");
			return url;
		}
		
		int documentNumber = 0;
		try{
			documentNumber = Integer.parseInt(documentParam);			
		}catch(Exception e){
			request.setAttribute("message", "���� ��ȣ ������ �ùٸ��� �ʽ��ϴ�.");
			return url;
		}
		
	    List<ApprovalLineEmployeeVO> approvalLines = employee.getApprvovalTable(documentNumber);
	    
	    request.setAttribute("approvalLines", approvalLines);

		DocumentDetailVO detailDoc = null;
		try{
			detailDoc = employee.getDetailReport(documentNumber);

			if (detailDoc == null) {
				request.setAttribute("message", "���� ��ȣ " + documentNumber + "�� �ش��ϴ� ������ �����ϴ�.");
				return url;
			}

			request.setAttribute("documentDetail", detailDoc);

			url = "webpage/draft/addReport.jsp";
			return url;

		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "���� ��ȸ �� ���� �߻�");

			return url;
		}


	}


}