package com.oopsw.action;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.ApprovalLineVO;
import com.oopsw.model.DocumentVO;
import com.oopsw.model.DrafterDAO;

public class SubmitDocAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = null;
		DrafterDAO d = new DrafterDAO();
		HttpSession session = request.getSession();
		String documentNoStr = request.getParameter("documentNo");//임시저장 안했었으면 null
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
		//1. 문서등록
		int documentNo = (documentNoStr != null) ? Integer.parseInt(documentNoStr) : 0;
		
		if(documentNoStr != null){
			//임시저장했던 문서인 경우
			result = d.submitTempDoc(new DocumentVO(documentNo, title, contents, deadline));
		}else{
			//처음 작성하는 문서인 경우
			documentNo = d.addDoc(new DocumentVO(employeeId, formId, title, contents, deadline));
		}
		//2. 결재자 등록
		int firstApprovalLineNo = 0;
		
		//임시저장된 문서였던 경우 기존 결재자 삭제
		if(documentNoStr != null){
			int count = d.removeApprovers(documentNo);
		}
		
		for (int i = 1; i <= approverIds.length; i++) {
			if(i == 1){
				firstApprovalLineNo = d.addApprovers(new ApprovalLineVO(documentNo, approverIds[i], i, "결재대기"));
			}else{
				d.addApprovers(new ApprovalLineVO(documentNo, approverIds[i], i, "대기중"));
			}
		}
		
		//첫번째 결재자 알림
		int count = 0; 
		if(firstApprovalLineNo != 0){
			count = d.sendFirstReqNoti(firstApprovalLineNo);
			if(count == 1) request.setAttribute("message", "결재요청되었습니다.");
		}
		return url;
	}

}
