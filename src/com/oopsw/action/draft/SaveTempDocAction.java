package com.oopsw.action.draft;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.oopsw.action.Action;
import com.oopsw.model.DBCP;
import com.oopsw.model.DAO.DrafterDAO;
import com.oopsw.model.VO.ApprovalLineVO;
import com.oopsw.model.VO.DocumentVO;

public class SaveTempDocAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/draft/addReport.jsp";
		DrafterDAO d = new DrafterDAO();
		HttpSession session = request.getSession();
		String documentNoStr = request.getParameter("documentNo");
		String employeeId = (String) session.getAttribute("employeeId");
		String formId = request.getParameter("formId");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents")
				.replace("\\", "\\\\")   
			    .replace("\"", "\\\"")   
			    .replace("\t", "\\t")    
			    .replace("\r", "\\r")    
			    .replace("\n", "\\n");
		String year = request.getParameter("deadlineYear");
		String month = request.getParameter("deadlineMonth");
		String day = request.getParameter("deadlineDay");
		String formattedMonth = String.format("%02d", Integer.parseInt(month));
		String formattedDay = String.format("%02d", Integer.parseInt(day));
		Date deadline = java.sql.Date.valueOf(year + "-" + formattedMonth+ "-" + formattedDay);
		String[] approverIds = request.getParameterValues("approverId");
	
		SqlSession conn = DBCP.getSqlSessionFactory().openSession(false);
		try {
			int documentNo = 0;
			boolean isUpdate = false;
			if (documentNoStr != null && !documentNoStr.trim().isEmpty()) {
                documentNo = Integer.parseInt(documentNoStr);
                isUpdate = true;
            }
			if(documentNo != 0){
				d.editTempDoc(new DocumentVO(documentNo, title, contents, deadline), conn);
			}else{
				documentNo = d.saveTempDoc(new DocumentVO(employeeId, formId, title, contents, deadline), conn);
			}
			
			if(isUpdate){
				int count = d.removeApprovers(documentNo, conn);
			}

			for (int i = 0; i < approverIds.length; i++) {
				d.addApprovers(new ApprovalLineVO(documentNo, approverIds[i], i+1, "�����"), conn);
			}
			
			conn.commit();
			url = "webpage/draft/getSaveList.jsp";
		} catch (Exception e) {
			request.setAttribute("message", "�ӽ������� �����Ͽ����ϴ�.");
			conn.rollback();
		}finally{
			conn.close();
		}
		
		return url;
	}

}
