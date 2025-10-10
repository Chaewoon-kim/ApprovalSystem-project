package com.oopsw.action;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.ApprovalLineVO;
import com.oopsw.model.DocumentVO;
import com.oopsw.model.DrafterDAO;

public class AddDocAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = null;
		DrafterDAO d = new DrafterDAO();
		HttpSession session = request.getSession();
		String employeeId = (String) session.getAttribute("employeeId");
		String formId = request.getParameter("formId");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String deadlineStr = request.getParameter("deadline");
		Date deadline = java.sql.Date.valueOf(deadlineStr);
		String isTemp = request.getParameter("isTemp");
		String[] approverIds = request.getParameterValues("approverId");
		//�������
		int newDocumentNo = 0;
		if(isTemp != null){
			newDocumentNo = d.saveTempDoc(new DocumentVO(employeeId, formId, title, contents, deadline));
		}else{
			newDocumentNo = d.addDoc(new DocumentVO(employeeId, formId, title, contents, deadline));
		}
		int firstApprovalLineNo = 0;
		//�����ڵ��
		for (int i = 1; i <= approverIds.length; i++) {
			if(isTemp == null && i == 1){
				firstApprovalLineNo = d.addApprovers(new ApprovalLineVO(newDocumentNo, approverIds[i], i, "������"));
			}else{
				d.addApprovers(new ApprovalLineVO(newDocumentNo, approverIds[i], i, "�����"));
			}
		}
		
		//ù��° ������ �˸�
		int count = 0; 
		if(firstApprovalLineNo != 0){
			count = d.sendFirstReqNoti(firstApprovalLineNo);
			if(count == 1) request.setAttribute("message", "�����û�Ǿ����ϴ�.");
		}
		return url;
	}

}
