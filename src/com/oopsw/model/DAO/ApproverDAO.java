package com.oopsw.model.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	// approvalProcess
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
    
    // 대결자 id
    public ApprovalLineVO findNextApprovalInfo(SqlSession conn, ApprovalLineVO vo) {
        return conn.selectOne("approverMapper.findNextApprovalInfo", vo);
    }
    // 부재자 -> 대결자
    public boolean updateNextApproverToProxy(SqlSession conn, int documentNo, String absenteeId, String proxyId) {
        boolean result = false;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("documentNo", documentNo);
            map.put("absenteeId", absenteeId);
            map.put("proxyId", proxyId);

            int count = conn.update("approverMapper.updateNextApproverToProxy", map);
            result = count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
    
    public boolean isAbsentToday(String approverId) {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try {
            Integer cnt = conn.selectOne("approverMapper.countActiveAbsenceFor", approverId);
            return cnt != null && cnt > 0;
        } finally {
            conn.close();
        }
    } //
    

    
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
    
    public boolean hasOverlapAbsence(AbsenceVO vo) {
        boolean result = false;
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try { 
            int count = conn.selectOne("approverMapper.checkOverlapAbsence", vo);
            result = count > 0;
        } finally {
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
            if(result = count == 1) conn.commit();
        } catch(Exception e){
    		conn.rollback();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
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


