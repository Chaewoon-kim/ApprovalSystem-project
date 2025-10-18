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

public class GetWaitListAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String employeeId = (String) session.getAttribute("employeeId");

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        String processStatus = request.getParameter("processStatus");
        if (processStatus == null) {processStatus = null;}

        ApproverDAO dao = new ApproverDAO();
        GetListVO vo = new GetListVO(employeeId, processStatus, page);
        List<ApproverListVO> waitList = dao.getWaitList(vo);
        
        int totalPages = 1;
        if(waitList.size() != 0){
        	double totalCount = waitList.get(0).getTotalCount();
            totalPages = (int) Math.ceil(totalCount / 8.0);
        }
        

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("currentPage", page);
        resultMap.put("totalPages", totalPages);
        resultMap.put("success", !waitList.isEmpty());
        resultMap.put("list", waitList);

        String json = gson.toJson(resultMap);
        request.setAttribute("result", json);
        
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        if (isAjax) {
            return "webpage/approve/listTable.jsp"; 
        }

        request.setAttribute("waitList", waitList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("processStatus", processStatus);

        return "webpage/approve/getApprovalWaitList.jsp";
    }
}
