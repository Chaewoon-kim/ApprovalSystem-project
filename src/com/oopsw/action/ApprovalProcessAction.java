package com.oopsw.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.model.*;

public class ApprovalProcessAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String approverId = (String) session.getAttribute("loginId");

        int documentNo = Integer.parseInt(request.getParameter("documentNo"));
        String approvalStatus = request.getParameter("approvalStatus"); // ���� or �ݷ�
        String opinion = request.getParameter("opinion");
        int lineOrder = Integer.parseInt(request.getParameter("lineOrder"));

        ApproverDAO dao = new ApproverDAO();
        String url = "approvalFail.jsp";

        try {
        	// ���� ���� Ȯ��
            AbsenceVO absence = dao.checkAbsence(approverId);
            if (absence != null) {
                approverId = absence.getProxyId(); // ����ڰ� �����ϵ���
            }

            ApprovalLineVO vo = new ApprovalLineVO();
            vo.setDocumentNo(documentNo);
            vo.setApproverId(approverId);
            vo.setApprovalStatus(approvalStatus);
            vo.setOpinion(opinion);
            vo.setLineOrder(lineOrder);
            dao.updateApproval(vo);

            if (approvalStatus.equals("����")) {
                int nextLineNo = dao.findNextApprovalLineNo(vo);

                if (nextLineNo != 0) {
                    // ���� ������ ���� -> ������ ó�� + �˸�
                    dao.updateNextApproverToWait(vo);
                    vo.setApprovalLineNo(nextLineNo);
                    dao.sendRequestNoti(vo);
                } else {
                    // ������ ������ -> ���� �Ϸ� ó�� + �˸�
                    DocumentVO doc = new DocumentVO();
                    doc.setDocumentNo(documentNo);
                    dao.updateDocComplete(doc);
                    dao.sendProcessNoti(vo);
                }

            } else if (approvalStatus.equals("�ݷ�")) {
                // �ݷ� ó�� -> ���� �ݷ� + �˸�
                DocumentVO doc = new DocumentVO();
                doc.setDocumentNo(documentNo);
                dao.updateDocReject(doc);
                dao.sendProcessNoti(vo);
                request.setAttribute("message", "�ݷ� ó�� �Ϸ�");
                url ="controller?cmd=getApprovalWaitList";
            }
            
            request.setAttribute("message", "���� �Ϸ�");
            url = "controller?cmd=getApprovalWaitList";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "���� ����");
        }

        return url;
    }
}
