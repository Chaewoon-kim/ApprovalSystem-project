// src/main/java/com/oopsw/model/ApproverDAOImpl.java
package com.oopsw.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class ApproverDAO{
	/// ���� ó��
	// 1. ���� �ϱ�
    public boolean updateApproval(ApprovalLineVO vo) {
    	boolean result = false;
    	
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		int count = conn.update("approverMapper.updateApproval", vo);
        	result = count == 1;
//        	conn.commit();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
 // 2. ���� ������ ���¸� '������'�� ����
    public boolean updateNextApproverToWait(ApprovalLineVO vo) {
        boolean result = false;
        
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try {
            int count = conn.update("approverMapper.updateNextApproverToWait", vo);
            result = count == 1;
        } finally {
            conn.close();
        }
        return result;
    }

    // 3. ���� ������ line_no ��ȸ
    public int findNextApprovalLineNo(ApprovalLineVO vo) {
        int nextLineNo = -1; 

        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        try {
            nextLineNo = conn.selectOne("approverMapper.findNextApprovalLineNo", vo);
            if(nextLineNo == 0) nextLineNo = -1;   
        } finally {
            conn.close();
        }
        return nextLineNo;
    }


    // 4. ���� ������ �����û�˸� insert 
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
    
    // 5. ������ �������Ͻ�, ����ó���˸� insert
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
    
    /// ���� ���º���
    // 6. ���� �ݷ� ó��
    public boolean updateDocReject(DocumentVO doc) {
    	boolean result = false;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		int count = conn.update("approverMapper.updateDocReject", doc);
    		result = count == 1;
    	} finally{
    		conn.close();
    	}
    	return result;
    }

    // 7. ���� �Ϸ� ó�� (������ ������ ���� ��)
    public boolean updateDocComplete(DocumentVO doc) {
    	boolean result = false;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		doc.setApprovedDocumentNo(generateApprovedDocNo()); // ������ȣ ����
    		int count = conn.update("approverMapper.updateDocComplete", doc);
    		result = count == 1;
    	} finally{
    		conn.close();
    	}
    	
        return result;
    }
    
    // 8. ���� ���� Ȯ��
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
    

    // ���繮����ȣ ���� ��Ģ (ex: D25-0001)
    private String generateApprovedDocNo() {
        String year = String.valueOf(java.time.LocalDate.now().getYear()).substring(2); // "25"
        int random = (int)(Math.random() * 9000) + 1000;
        return "D" + year + "-" + random;
    }
    
    
    
    
 // ���� ��� ��� ��ȸ
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
    
 // ���� ó�� ��� ��ȸ (���� �ݷ��� ���� + ���� ������ �Ϸ� ����)
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
    
    // ���� ��� ��ȸ
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
    
    // ��� ��� ��ȸ
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
    
    // ���� �߰�
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
    
    // ���� ����
    public boolean updateAbsence(AbsenceVO vo){
    	boolean result = false;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		int count = conn.update("approverMapper.updateAbsence", vo);
        	result = count == 1;
//        	conn.commit();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
    // ���� öȸ
    public boolean revokeAbsence(int absenceDateNo) {
    	boolean result = false;
    	SqlSession conn = DBCP.getSqlSessionFactory().openSession();
    	try{
    		int count = conn.update("approverMapper.revokeAbsence", absenceDateNo);
        	result = count == 1;
//        	conn.commit();
    	} finally{
    		conn.close();
    	}
    	return result;
    }
    
    // ���� ����
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
    
    
    // �˸� ���� ��� ��ȸ (���� ��û)
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


