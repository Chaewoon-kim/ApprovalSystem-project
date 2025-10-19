package com.oopsw.action.draft;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

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
		String contents = request.getParameter("contents");
		String year = request.getParameter("deadlineYear");
		String month = request.getParameter("deadlineMonth");
		String day = request.getParameter("deadlineDay");
		String formattedMonth = String.format("%02d", Integer.parseInt(month));
		String formattedDay = String.format("%02d", Integer.parseInt(day));
		Date deadline = java.sql.Date.valueOf(year + "-" + formattedMonth+ "-" + formattedDay);
		String[] approverIds = request.getParameterValues("approverId");
		
		Map<String, Object> jsonResult = new HashMap<>();
		
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
				d.addApprovers(new ApprovalLineVO(documentNo, approverIds[i], i+1, "대기중"), conn);
			}
			
			conn.commit();
			jsonResult.put("success", true);
	        jsonResult.put("message", "임시 저장되었습니다.");
	        jsonResult.put("url", "controller?cmd=getTempListUI");
		} catch (Exception e) {
			jsonResult.put("success", false);
	        jsonResult.put("message", "임시 저장이 실패하였습니다.");
			conn.rollback();
		}finally{
			conn.close();
		}
		request.setAttribute("result", jsonResult);
		return "webpage/result.jsp";
	}

}
