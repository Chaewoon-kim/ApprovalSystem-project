package com.oopsw.action.draft;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.ApprovalLineEmployeeVO;
import com.oopsw.model.VO.DocumentDetailVO;
import com.oopsw.model.VO.EmployeeVO;


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
		
		int documentNo = 0;
		try{
			documentNo = Integer.parseInt(documentParam);			
		}catch(Exception e){
			request.setAttribute("message", "���� ��ȣ ������ �ùٸ��� �ʽ��ϴ�.");
			return url;
		}
		
	    List<ApprovalLineEmployeeVO> approvalLines = employee.getApprvovalTable(documentNo);
	    
	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    request.setAttribute("defaultlines", gson.toJson(approvalLines));

		DocumentDetailVO detailDoc = null;
		try{
			detailDoc = employee.getDetailReport(documentNo);

			if (detailDoc == null) {
				request.setAttribute("message", "���� ��ȣ " + documentNo + "�� �ش��ϴ� ������ �����ϴ�.");
				return url;
			}
			detailDoc.setDocumentNo(documentNo);
			request.setAttribute("documentDetail", gson.toJson(detailDoc));
			request.setAttribute("add", true);
			url = "webpage/draft/addReport.jsp";
			return url;

		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "���� ��ȸ �� ���� �߻�");

			return url;
		}


	}


}