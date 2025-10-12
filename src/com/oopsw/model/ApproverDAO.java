package com.oopsw.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class ApproverDAO{
	/// 결재 처리
	// 1. 결재 하기
    public boolean processApproval(ApprovalLineVO vo) {
    	boolean result = false;
    	
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		int count = conn.update("approverMapper.processApproval", vo);
        	result = count == 1;
//        	conn.commit();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
 // 2. 다음 결재자 상태를 '결재대기'로 변경
    public boolean setNextApproverToWait(ApprovalLineVO vo) {
        boolean result = false;
        
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try {
            int count = conn.update("approverMapper.setNextApproverToWait", vo);
            result = count == 1;
        } finally {
            conn.close();
        }
        return result;
    }

    // 3. 다음 결재자 line_no 조회
    public Integer findNextApprovalLineNo(ApprovalLineVO vo) {
    	Integer nextLineNo;
    	
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try {
            nextLineNo = conn.selectOne("approverMapper.findNextApprovalLineNo", vo);
        } finally {
            conn.close();
        }
        return nextLineNo;
    }


    // 4. 다음 결재자 결재요청알림 insert 
    public boolean sendRequestNoti(ApprovalLineVO vo) {
        boolean result = false;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try {
            int count = conn.insert("approverMapper.sendRequestNoti", vo);
            result = count == 1;
        } finally {
            conn.close();
        }
        return result;
    }
    
    // 5. 마지막 결재자일시, 결재처리알림 insert
    public boolean sendProcessNoti(ApprovalLineVO vo) {
    	boolean result = false;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try{
        	int count = conn.insert("approverMapper.sendProcessNoti", vo);
        	result = count == 1;
        } finally{
        	conn.close();
        }
        return result;
    }
    
    /// 문서 상태변경
    // 6. 문서 반려 처리
    public boolean setDocReject(DocumentVO doc) {
    	boolean result = false;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		int count = conn.update("approverMapper.setDocReject", doc);
    		result = count == 1;
    	} finally{
    		conn.close();
    	}
    	return result;
    }

    // 7. 문서 완료 처리 (마지막 결재자 승인 시)
    public boolean setDocComplete(DocumentVO doc) {
    	boolean result = false;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		//doc.setApprovedDocumentNo(generateApprovedDocNo()); // 문서번호 생성
    		int count = conn.update("approverMapper.setDocComplete", doc);
    		result = count == 1;
    	} finally{
    		conn.close();
    	}
    	
        return result;
    }
    
    // 8. 부재 여부 확인
    public AbsenceVO checkAbsence(String approverId) {
    	AbsenceVO vo = null;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try{
        	vo = conn.selectOne("approverMapper.checkAbsence", approverId);
        } finally{
        	conn.close();
        }
        return vo;
    }
    
    
 // 결재 대기 목록 조회
    public List<ApproverListVO> getWaitList(String approverId) {
        List<ApproverListVO> list = null;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try {
            list = conn.selectList("approverMapper.getWaitList", approverId);
        } finally {
            conn.close();
        }
        return list;
    }
    
 // 결재 처리 목록 조회 (내가 반려한 문서 + 내가 승인한 완료 문서)
    public List<ApproverListVO> getEndList(String approverId) {
        List<ApproverListVO> list = null;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try {
            list = conn.selectList("approverMapper.getEndList", approverId);
        } finally {
            conn.close();
        }
        return list;
    }
    
    // 부재 목록 조회
    public List<AbsenceListVO> getAbsenceList(String absenteeId){
    	List<AbsenceListVO> list = null;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	 try {
             list = conn.selectList("approverMapper.getAbsenceList", absenteeId);
         } finally {
             conn.close();
         }
    	return list;
    }
    
    // 대결 목록 조회
    public List<AbsenceListVO> getProxyList(String proxyId){
    	List<AbsenceListVO> list = null;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	 try {
             list = conn.selectList("approverMapper.getProxyList", proxyId);
         } finally {
             conn.close();
         }
    	return list;
    }
    
    // 부재 추가
    public boolean addAbsence(AbsenceVO vo) {
        boolean result = false;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try {
            int count = conn.insert("approverMapper.addAbsence", vo);
            result = count == 1;
        } finally {
            conn.close();
        }
        return result;
    }
    
    // 부재 수정
    public boolean modifyAbsence(AbsenceVO vo){
    	boolean result = false;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		int count = conn.update("approverMapper.modifyAbsence", vo);
        	result = count == 1;
//        	conn.commit();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
    // 부재 조기종료
    public boolean endAbsence(int absenceDateNo) {
    	boolean result = false;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		int count = conn.update("approverMapper.endAbsence", absenceDateNo);
        	result = count == 1;
//        	conn.commit();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
    // 부재 삭제
    public boolean deleteAbsence(int absenceDateNo) {
    	boolean result = false;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		int count = conn.update("approverMapper.deleteAbsence", absenceDateNo);
        	result = count == 1;
//        	conn.commit();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
    
    // 알림 수신 목록 조회 (결재 요청)
    public List<AlarmVO> getApprovalReqNoti(String approverId){
    	List<AlarmVO> list = null;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try {
    		list = conn.selectList("approverMapper.getApprovalReqNoti", approverId);
    	} finally {
    		conn.close();
    	}
    	return list;
    }

    
    


    
}


