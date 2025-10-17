package com.oopsw.action.approve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

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
        String approverId = (String) session.getAttribute("employeeId");
        if (approverId == null) {
            approverId = "E25-000";
        }
        int documentNo = Integer.parseInt(request.getParameter("documentNo"));
        String approvalStatus = request.getParameter("approvalStatus"); // �듅�씤 or 諛섎젮
        String opinion = request.getParameter("opinion");
        int lineOrder = Integer.parseInt(request.getParameter("lineOrder"));

        ApproverDAO dao = new ApproverDAO();
        
        SqlSession conn = DBCP.getSqlSessionFactory().openSession(false);
        String url = "webpage/approve/getApprovalWaitList.jsp";

        try {
            AbsenceVO absence = dao.checkAbsence(approverId);
            if (absence != null && !absence.getProxyId().equals(approverId)) {
                request.setAttribute("message", "현재 부재 설정 중, 대결자만 결재 가능.");
                return url;
            }

            ApprovalLineVO vo = new ApprovalLineVO();
            vo.setDocumentNo(documentNo);
            vo.setApproverId(approverId);
            vo.setApprovalStatus(approvalStatus);
            vo.setOpinion(opinion);
            vo.setLineOrder(lineOrder);
            
            dao.processApproval(conn, vo); 

            if (approvalStatus.equals("승인")) {
                Integer nextLineNo = dao.findNextApprovalLineNo(conn, vo);
                System.out.println(nextLineNo);
                if (nextLineNo != null) {
                    dao.setNextApproverToWait(conn,vo);
                    vo.setApprovalLineNo(nextLineNo);
                    dao.sendRequestNoti(conn,vo);
                    request.setAttribute("message", "다음결재자에게 전달");
                } else {
                    DocumentVO doc = new DocumentVO();
                    doc.setDocumentNo(documentNo);
                    dao.setDocComplete(conn,doc);
                    dao.sendProcessNoti(conn,vo);
                    request.setAttribute("message", "최종 승인 완료");

                }
                conn.commit();
//                url = "controller?cmd=getWaitList";
                return url;

            } else if (approvalStatus.equals("반려")) {
                // 반려 처리 -> 문서 반려 + 알림
                DocumentVO doc = new DocumentVO();
                doc.setDocumentNo(documentNo);
                dao.setDocReject(conn, doc);
                dao.sendProcessNoti(conn, vo);
                conn.commit();
                request.setAttribute("message", "결재 실패");
                return url;

            }
            
        } catch (Exception e) {
        	conn.rollback();
            e.printStackTrace();
            request.setAttribute("message", "寃곗옱 �떎�뙣");
        } finally {
        	conn.close();
        }
        return url;
    }
}
