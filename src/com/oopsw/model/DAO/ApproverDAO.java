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
        	result = count == 1;
//        	conn.commit();
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

    
}


