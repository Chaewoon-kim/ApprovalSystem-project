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
        
        // 현재 페이지 번호 파라미터 처리
        int page = 1;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // 결재 상태 필터링 (전체/완료/반려)
        String processStatus = request.getParameter("processStatus");
        if (processStatus == null || processStatus.trim().isEmpty()) {
            processStatus = null; 
        }

        GetListVO vo = new GetListVO(employeeId, processStatus, page);

        
        ApproverDAO dao = new ApproverDAO();
        List<ApproverListVO> endList = dao.getEndList(vo);

        request.setAttribute("endList", endList); 
        request.setAttribute("currentPage", page);
        request.setAttribute("processStatus", processStatus); 
        
        /*// 페이지네이션, 상태필터링 비동기 
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return "webpage/approve/endListTable.jsp"; // partial만 리턴
        } else {
        */	
        
        return "webpage/approve/getApprovalEndList.jsp";

	}

}
