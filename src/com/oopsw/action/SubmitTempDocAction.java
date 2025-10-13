package com.oopsw.action;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.model.DAO.DrafterDAO;
import com.oopsw.model.VO.ApprovalLineVO;
import com.oopsw.model.VO.DocumentVO;

public class SubmitTempDocAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		DrafterDAO d = new DrafterDAO();
		int documentNo = Integer.parseInt(request.getParameter("documentNo"));
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String deadLineStr = request.getParameter("deadLine");
		String isTemp = request.getParameter("isTemp");
		Date deadLine = java.sql.Date.valueOf(deadLineStr);
		String[] approverIds = request.getParameterValues("approverId");
		boolean result = false;
		if(isTemp != null){
			result = d.submitTempDoc(new DocumentVO(documentNo, title, contents, deadLine));
		}else{
			result = d.editTempDoc(new DocumentVO(documentNo, title, contents, deadLine));
		}
		//���缱 ����(���� �� �ٽ� �Է�)
		int firstApprovalLineNo = 0;
		if(approverIds != null && approverIds.length > 0){
			int count = d.removeApprovers(documentNo);
			for (int i = 1; i <= approverIds.length; i++) {
				if(isTemp == null && i == 1){
					firstApprovalLineNo = d.addApprovers(new ApprovalLineVO(documentNo, approverIds[i], i, "������"));
				}else{
					d.addApprovers(new ApprovalLineVO(documentNo, approverIds[i], i, "�����"));
				}
			}
		}
		
		
		//ù��° ������ �˸�
		int count = 0; 
		if(firstApprovalLineNo != 0){
			count = d.sendFirstReqNoti(firstApprovalLineNo);
			if(count == 1) request.setAttribute("message", "�����û�Ǿ����ϴ�.");
		}
		
		return null;
	}

}
