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
            employeeId = "E25-010"; // �׽�Ʈ��
        }
        
        // ���� ������ ��ȣ �Ķ���� ó��
        int page = 1;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // ���� ���� ���͸� (��ü/�Ϸ�/�ݷ�)
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
        
        /*// ���������̼�, �������͸� �񵿱� 
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return "webpage/approve/endListTable.jsp"; // partial�� ����
        } else {
        */	
        
        return "webpage/approve/getApprovalEndList.jsp";

	}

}
