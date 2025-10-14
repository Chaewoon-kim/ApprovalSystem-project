package com.oopsw.action.approve;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        
        GetListVO vo = new GetListVO(employeeId, processStatus, page);

        ApproverDAO dao = new ApproverDAO();
        List<ApproverListVO> endList = dao.getEndList(vo);

        // 전체 문서 수로 전체 페이지 계산
//        int totalCount = dao.getEndListCount(vo); // count 쿼리 필요,, 리팩토링하기
//        int pageSize = 8; // 한 페이지당 문서 개수
//        int totalPages = (int) Math.ceil(totalCount / (double) pageSize);
        int totalPages = 3;
        
        request.setAttribute("endList", endList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("processStatus", processStatus);

        // AJAX 요청인지 확인
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        
        if (isAjax) {
            return "webpage/approve/endListTable.jsp";
        } 
        
        return "webpage/approve/getApprovalEndList.jsp";

        
    }

}
