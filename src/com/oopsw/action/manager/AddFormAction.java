package com.oopsw.action.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.oopsw.action.Action;
import com.oopsw.model.DBCP;
import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.DefaultApprovalLineVO;
import com.oopsw.model.VO.DefaultLineVO;
import com.oopsw.model.VO.FormVO;

public class AddFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "webpage/manager/resultAsync.jsp";
		
		String ajax = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(ajax);
		if(!isAjax) 
			return "webpage/manager/addForm.jsp";
		
		Gson gson = new Gson();
		String json = request.getParameter("data");
		Map<String, Object> data = gson.fromJson(json, Map.class);
		
		FormVO formVO = new FormVO();
//		vo.setManagerId(request.getSession().getAttribute("employeeId").toString());
		formVO.setManagerId("E25-000");
		formVO.setFormCategory(data.get("formCategory").toString());
		formVO.setFormName(data.get("formName").toString());
		formVO.setFormContent(data.get("formContent").toString());
		formVO.setFormDescription(data.get("formDescription").toString());
		formVO.setFormUsage('Y');

		// 양식 등록
		SqlSession session = DBCP.getSqlSessionFactory().openSession(true);
		ManagerDAO dao = new ManagerDAO();
		boolean result = dao.addForm(formVO);

		// 기본 결재선 등록		
		List<String> confirmerList = (List<String>)data.get("confirmer");
		List<DefaultApprovalLineVO> approvalList = new ArrayList<>();
		for(int i = 0; i < confirmerList.size(); i++){
			DefaultApprovalLineVO defaultApprovalLineVO = new DefaultApprovalLineVO();
			defaultApprovalLineVO.setFormId(formVO.getFormId());
			defaultApprovalLineVO.setOrder(i+1);
			defaultApprovalLineVO.setRank(confirmerList.get(i));
			approvalList.add(defaultApprovalLineVO);
		}

		result = dao.addDefaultApprovalLine(approvalList);
		session.close();
		
		request.setAttribute("result", result);	
		
		return url;
	}

}
