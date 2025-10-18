package com.oopsw.action.approve;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import com.oopsw.action.Action;
import com.oopsw.model.DBCP;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.ApprovalLineVO;
import com.oopsw.model.VO.DocumentVO;

public class ApprovalProcessAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String approverId = (String) session.getAttribute("employeeId");

        int documentNo = Integer.parseInt(request.getParameter("documentNo"));
        String approvalStatus = request.getParameter("approvalStatus");
        String opinion = request.getParameter("opinion");
        int lineOrder = Integer.parseInt(request.getParameter("lineOrder"));

        ApproverDAO dao = new ApproverDAO();
        SqlSession conn = DBCP.getSqlSessionFactory().openSession(false);
        String url = "webpage/approve/getApprovalWaitList.jsp";

        try {
            // 현재 결재자가 위임 상태인지 확인
            if (dao.isAbsentToday(approverId)) {
                request.setAttribute("message", "위임중이라 대결자만 결재 가능합니다.");
                return url;
            }
            if("반려".equals(approvalStatus) && (opinion == null || opinion.trim().isEmpty())){
            	request.setAttribute("message", "반려사유를 입력해주세요. ");
            	return url;
            }

            ApprovalLineVO vo = new ApprovalLineVO();
            vo.setDocumentNo(documentNo);
            vo.setApproverId(approverId);
            vo.setApprovalStatus(approvalStatus);
            vo.setOpinion(opinion);
            vo.setLineOrder(lineOrder);

            dao.processApproval(conn, vo);

            // 승인
            if (approvalStatus.equals("승인")) {
                Integer nextLineNo = dao.findNextApprovalInfo(conn, vo).getApprovalLineNo();

                ApprovalLineVO nextInfo = dao.findNextApprovalInfo(conn, vo);
                if (nextInfo != null && nextInfo.getApprovalLineNo() != 0) {

                    String nextApproverId = nextInfo.getApproverId();

                    // 다음 결재자가 부재중인지 확인 
                    String proxyId = dao.checkAbsence(nextApproverId).getProxyId();

                    if (proxyId != null) { 
                        // 부재중이라면 다음 결재자를 대결자로 교체 
                        dao.updateNextApproverToProxy(conn, vo.getDocumentNo(), nextApproverId, proxyId);
                        nextApproverId = proxyId;
                    }

                    // 다음 결재자(or 대결자) 결재대기로 전환 + 알림 
                    vo.setApproverId(nextApproverId);
                    dao.setNextApproverToWait(conn, vo);
                    vo.setApprovalLineNo(nextLineNo);
                    dao.sendRequestNoti(conn, vo);

                    conn.commit();
                    request.setAttribute("message", "승인 완료, 다음 결재자에게 전달하였습니다.");

                } else {
                    // 마지막 결재자 -> 문서 완료 처리 + 알림
                    DocumentVO doc = new DocumentVO();
                    doc.setDocumentNo(documentNo);
                    dao.setDocComplete(conn, doc);
                    dao.sendProcessNoti(conn, vo);
                    request.setAttribute("message", "최종 승인 완료하였습니다.");
                }

                conn.commit();
                return url;
            }

            // 반려
            else if (approvalStatus.equals("반려")) {
                DocumentVO doc = new DocumentVO();
                doc.setDocumentNo(documentNo);
                dao.setDocReject(conn, doc);
                dao.sendProcessNoti(conn, vo);

                conn.commit();
                request.setAttribute("message", "반려 처리하였습니다.");
                return url;
            }

        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            request.setAttribute("message", "결재 실패하였습니다.");
        } finally {
            conn.close();
        }

        return url;
    }
}
