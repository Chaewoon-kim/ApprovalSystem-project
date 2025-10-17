package com.oopsw.model.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.oopsw.model.DBCP;
import com.oopsw.model.VO.AbsenceListVO;
import com.oopsw.model.VO.AbsenceVO;
import com.oopsw.model.VO.AlarmVO;
import com.oopsw.model.VO.ApprovalLineVO;
import com.oopsw.model.VO.ApproverListVO;
import com.oopsw.model.VO.DocumentVO;
import com.oopsw.model.VO.GetListVO;

public class ApproverDAO{
    public boolean processApproval(SqlSession conn, ApprovalLineVO vo) {
    	boolean result = false;
    	int count = conn.update("approverMapper.processApproval", vo);
    	result = count == 1;
    	return result;
    }
    
    public boolean setNextApproverToWait(SqlSession conn, ApprovalLineVO vo) {
    	boolean result = false;
    	int count = conn.update("approverMapper.setNextApproverToWait", vo);
    	result = count == 1;
    	return result;
    }

    public Integer findNextApprovalLineNo(SqlSession conn, ApprovalLineVO vo) {
    	Integer nextLineNo;
    	nextLineNo = conn.selectOne("approverMapper.findNextApprovalLineNo", vo);
    	return nextLineNo;
    }

    public boolean sendRequestNoti(SqlSession conn, ApprovalLineVO vo) {
    	boolean result = false;
    	int count = conn.insert("approverMapper.sendRequestNoti", vo);
    	result = count == 1;
    	return result;
    }
    
    public boolean sendProcessNoti(SqlSession conn, ApprovalLineVO vo) {
    	boolean result = false;
    	int count = conn.insert("approverMapper.sendProcessNoti", vo);
    	result = count == 1;
    	return result;
    }
    
    public boolean setDocReject(SqlSession conn, DocumentVO doc) {
    	boolean result = false;
    	int count = conn.update("approverMapper.setDocReject", doc);
    	result = count == 1;
    	return result;
    }

    public boolean setDocComplete(SqlSession conn, DocumentVO doc) {
    	boolean result = false;
    	int count = conn.update("approverMapper.setDocComplete", doc);
    	result = count == 1;
    	return result;
    }
    
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
    
    // 현재(오늘) 위임 상태인지 여부
    public boolean isAbsentToday(String approverId) {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try {
            Integer cnt = conn.selectOne("approverMapper.countActiveAbsenceFor", approverId);
            return cnt != null && cnt > 0;
        } finally {
            conn.close();
        }
    }

    
    public List<ApproverListVO> getWaitList(GetListVO vo) {
        List<ApproverListVO> list = null;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession(true);
        try {
            list = conn.selectList("approverMapper.getWaitList", vo);
        } finally {
            conn.close();
        }
        return list;
    }
    
    public List<ApproverListVO> getEndList(GetListVO vo) {
    	List<ApproverListVO> list = null;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try{
        	 list = conn.selectList("approverMapper.getEndList", vo);
        } finally{
        	conn.close();
        }
        return list;
    }
    
    // 부재 목록 조회
    public List<AbsenceListVO> getAbsenceList(GetListVO vo){
    	List<AbsenceListVO> list = null;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	 try {
             list = conn.selectList("approverMapper.getAbsenceList", vo);
         } finally {
             conn.close();
         }
    	return list;
    }
    
    // 대결 목록 조회
    public List<AbsenceListVO> getProxyList(GetListVO vo){
    	List<AbsenceListVO> list = null;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	 try {
             list = conn.selectList("approverMapper.getProxyList", vo);
         } finally {
             conn.close();
         }
    	return list;
    }
    
    public boolean addAbsence(AbsenceVO vo) {
        boolean result = false;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try {
            int count = conn.insert("approverMapper.addAbsence", vo);
            if(result = count == 1) conn.commit();
        } catch(Exception e){
    		conn.rollback();
    	} finally{
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
        	if(result = count == 1) conn.commit();
    	} catch(Exception e){
    		conn.rollback();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
    public boolean endAbsence(int absenceDateNo) {
    	boolean result = false;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		int count = conn.update("approverMapper.endAbsence", absenceDateNo);
        	if(result = count == 1) conn.commit();
    	} catch(Exception e){
    		conn.rollback();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
    public boolean deleteAbsence(int absenceDateNo) {
    	boolean result = false;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		int count = conn.update("approverMapper.deleteAbsence", absenceDateNo);
        	if(result = count == 1) conn.commit();
    	} catch(Exception e){
    		conn.rollback();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
    
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

    
    public List<AlarmVO> getUnReadApprovalReqNoti(String approverId){
    	List<AlarmVO> list = null;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try {
    		list = conn.selectList("approverMapper.getUnReadApprovalReqNoti", approverId);
    	} finally {
    		conn.close();
    	}
    	return list;
    }


    public boolean setAbsenceStatusToActive() {
    	boolean result = false;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try{
    		int count = conn.update("approverMapper.setAbsenceStatusToActive");
        	if(result = count == 1) conn.commit();
    	} catch(Exception e){
    		conn.rollback();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
    public boolean setAbsenceStatusToEnd() {
    	boolean result = false;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        int count = 0;
        try {
            count = conn.update("approverMapper.setAbsenceStatusToEnd");
            result = count == 1;
//            conn.commit();
        } finally {
            conn.close();
        }
        return result;
    }
    
    // 부재수정 상세 조회
    public AbsenceVO getAbsenceDetail(int absenceDateNo) {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        AbsenceVO vo = null;
        try {
            vo = conn.selectOne("approverMapper.getAbsenceDetail", absenceDateNo);
        } finally {
            conn.close();
        }
        return vo;
    }


    
}


