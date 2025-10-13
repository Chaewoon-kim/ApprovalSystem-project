package com.oopsw.action;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.ApprovalLineVO;
import com.oopsw.model.DocumentVO;
import com.oopsw.model.DrafterDAO;

public class SaveTempDocAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = null;
		DrafterDAO d = new DrafterDAO();
		HttpSession session = request.getSession();
		String documentNoStr = request.getParameter("documentNo");//�ӽ����� ���߾����� null
		String employeeId = (String) session.getAttribute("employeeId");
		String formId = request.getParameter("formId");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String year = request.getParameter("deadlineYear");
		String month = request.getParameter("deadlineMonth");
		String day = request.getParameter("deadlineDay");
		String formattedMonth = String.format("%02d", Integer.parseInt(month));
		String formattedDay = String.format("%02d", Integer.parseInt(day));
		Date deadline = java.sql.Date.valueOf(year + "-" + formattedMonth+ "-" + formattedDay);
		String[] approverIds = request.getParameterValues("approverId");
		boolean result = false;
		//�ӽ�����
		int documentNo = (documentNoStr != null) ? Integer.parseInt(documentNoStr) : 0;
		if(documentNoStr != null){
			//�ӽ����� ������ �ٽ� ����
			result = d.editTempDoc(new DocumentVO(documentNo, title, contents, deadline));
		}else{
			//ó�� �ӽ�����
			documentNo = d.saveTempDoc(new DocumentVO(employeeId, formId, title, contents, deadline));
		}
		
		//�ӽ������ �������� ��� ���� ������ ����
		if(documentNoStr != null){
			int count = d.removeApprovers(documentNo);
		}
		//�����ڵ��
		for (int i = 1; i <= approverIds.length; i++) {
			d.addApprovers(new ApprovalLineVO(documentNo, approverIds[i], i, "�����"));
		}
		
		return url;
	}

}
