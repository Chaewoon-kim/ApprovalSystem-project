package com.oopsw.action.approve;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.ApproverListVO;
import com.oopsw.model.VO.GetListVO;

public class GetEndListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
          
        HttpSession session = request.getSession();
        String employeeId = (String) session.getAttribute("employeeId");
        if (employeeId == null) {
            employeeId = "E25-010"; // 테스트용
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
        
        ApproverDAO dao = new ApproverDAO();
        GetListVO vo = new GetListVO(employeeId, processStatus, page);
        List<ApproverListVO> endList = dao.getEndList(vo);

        // 전체 페이지 수 (임시)
        int totalPages = 3;

        // Gson 변환
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")   // 원하는 포맷 지정
                .create();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("currentPage", page);
        resultMap.put("totalPages", totalPages);
        resultMap.put("success", !endList.isEmpty());
        resultMap.put("list", endList);

        String json = gson.toJson(resultMap);
        request.setAttribute("result", json);

        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        if (isAjax) {
            // json을 JSP로
            return "webpage/approve/endListTable.jsp";
        }

        // 동기일 경우
        request.setAttribute("endList", endList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("processStatus", processStatus);

        return "webpage/approve/getApprovalEndList.jsp";

        
    }

}
