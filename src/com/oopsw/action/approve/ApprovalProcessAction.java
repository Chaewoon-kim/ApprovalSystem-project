package com.oopsw.action.approve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.action.Action;
import com.oopsw.model.*;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.AbsenceVO;
import com.oopsw.model.VO.ApprovalLineVO;
import com.oopsw.model.VO.DocumentVO;

public class ApprovalProcessAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String approverId = (String) session.getAttribute("loginId");

        int documentNo = Integer.parseInt(request.getParameter("documentNo"));
        String approvalStatus = request.getParameter("approvalStatus"); // 승인 or 반려
        String opinion = request.getParameter("opinion");
        int lineOrder = Integer.parseInt(request.getParameter("lineOrder"));

        ApproverDAO dao = new ApproverDAO();
        String url = "approvalFail.jsp";

        try {
        	// 부재 여부 확인
            AbsenceVO absence = dao.checkAbsence(approverId);
            if (absence != null && !absence.getProxyId().equals(approverId)) {
                request.setAttribute("message", "현재 부재 설정 중, 대결자만 결재 가능.");
                return "approvalFail.jsp";
            }

            ApprovalLineVO vo = new ApprovalLineVO();
            vo.setDocumentNo(documentNo);
            vo.setApproverId(approverId);
            vo.setApprovalStatus(approvalStatus);
            vo.setOpinion(opinion);
            vo.setLineOrder(lineOrder);
            
            dao.processApproval(vo); 

            if (approvalStatus.equals("승인")) {
                Integer nextLineNo = dao.findNextApprovalLineNo(vo);

                if (nextLineNo != null) {
                    // 다음 결재자 존재 -> 결재대기 처리 + 알림
                    dao.setNextApproverToWait(vo);
                    vo.setApprovalLineNo(nextLineNo);
                    dao.sendRequestNoti(vo);
                } else {
                    // 마지막 결재자 -> 문서 완료 처리 + 알림
                    DocumentVO doc = new DocumentVO();
                    doc.setDocumentNo(documentNo);
                    dao.setDocComplete(doc);
                    dao.sendProcessNoti(vo);
                }
                request.setAttribute("message", "승인 완료");
                url = "controller?cmd=getApprovalWaitList";

            } else if (approvalStatus.equals("반려")) {
                // 반려 처리 -> 문서 반려 + 알림
                DocumentVO doc = new DocumentVO();
                doc.setDocumentNo(documentNo);
                dao.setDocReject(doc);
                dao.sendProcessNoti(vo);
                request.setAttribute("message", "반려 처리 완료");
                url = "controller?cmd=getApprovalWaitList";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "결재 실패");
        }

        return url;
    }
}
