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

        // 로그인한 사원
        HttpSession session = request.getSession();
        String employeeId = (String) session.getAttribute("employeeId");
        if (employeeId == null) {
            System.out.println("테스트 사원 로그인됨");
            employeeId = "E25-000";
        }

        // 페이지 파라미터 처리
        int page = 1;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // 상태 필터
        String processStatus = request.getParameter("processStatus");
        if (processStatus == null || processStatus.trim().isEmpty()) {
            processStatus = null;
        }

        // DAO 호출
        ApproverDAO dao = new ApproverDAO();
        GetListVO vo = new GetListVO(employeeId, processStatus, page);
        List<ApproverListVO> waitList = dao.getWaitList(vo);

        int totalPages = 3; // (임시)

        // Gson 변환 준비
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

        // AJAX 요청 확인
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        if (isAjax) {
            return "webpage/approve/waitListTable.jsp"; // JSON JSP
        }

        // 동기요청 (초기 페이지)
        request.setAttribute("waitList", waitList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("processStatus", processStatus);

        return "webpage/approve/getApprovalWaitList.jsp";
    }
}
