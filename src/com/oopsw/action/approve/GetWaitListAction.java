package com.oopsw.action.approve;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.ApproverListVO;
import com.oopsw.model.VO.GetListVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class GetWaitListAction implements Action {

	@Override
	
	public String execute(HttpServletRequest request) throws ServletException, IOException {
			
		 	// 로그인한 사원의 사번 가져오기
		 	HttpSession session = request.getSession();
	        String employeeId = (String) session.getAttribute("employeeId");
	        if (employeeId == null) {
	            employeeId = "E25-000"; // 테스트용
	        }
	        
	        int page = 1;
	        if (request.getParameter("page") != null) {
	            try {
	                page = Integer.parseInt(request.getParameter("page"));
	            } catch (NumberFormatException e) {
	                page = 1;
	            }
	        }
	        
	        String processStatus = request.getParameter("processStatus");
	        if (processStatus == null || processStatus.trim().isEmpty()) {
	            processStatus = null; 
	        }
	        
	        GetListVO vo = new GetListVO(employeeId, processStatus, page);

	        ApproverDAO dao = new ApproverDAO();
	        List<ApproverListVO> waitList = dao.getWaitList(vo);
	        request.setAttribute("success", !waitList.isEmpty());

	        // 전체페이지수 일단 고정 -> 추후 리팩토링
	        int totalPages = 3;
	        
	        request.setAttribute("currentPage", page);
	        request.setAttribute("totalPages", totalPages);
	        request.setAttribute("processStatus", processStatus);
	        
//	        Map<String, Object> responseMap = new HashMap<>();
//	        responseMap.put("currentPage", page);
//	        responseMap.put("totalPages", totalPages);
//	        responseMap.put("success", !waitList.isEmpty());
//	        responseMap.put("list", waitList);
	        
//	        Gson gson = new GsonBuilder();
//            String waitListGson = gson.toJson(waitList);
            
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")   // 원하는 포맷 지정
                    .create();
            String waitListGson = gson.toJson(waitList);
            
            // 리스트
            request.setAttribute("waitListGson", waitListGson);
            request.setAttribute("success", !waitList.isEmpty());

	        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

	        if (isAjax) {
	            return "webpage/approve/waitListTable.jsp";
	        } 
	        
	        return "webpage/approve/getApprovalWaitList.jsp";

	}

}

