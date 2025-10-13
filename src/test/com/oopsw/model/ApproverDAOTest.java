package test.com.oopsw.model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.oopsw.model.AbsenceListVO;
import com.oopsw.model.AbsenceVO;
import com.oopsw.model.AlarmVO;
import com.oopsw.model.ApprovalLineVO;
import com.oopsw.model.ApproverDAO;
import com.oopsw.model.ApproverListVO;
import com.oopsw.model.DBCP;
import com.oopsw.model.DocumentVO;

public class ApproverDAOTest {
	
	static ApproverDAO dao;
	static ApprovalLineVO al; 
	static AbsenceVO ab;
	static DocumentVO doc;
	
	@BeforeClass
	public static void start() throws Exception{
		dao = new ApproverDAO();
		al = new ApprovalLineVO();
		ab = new AbsenceVO();
		doc = new DocumentVO();
	}

//	@Test
	public void processApprovalTest() {
		al.setDocumentNo(20);
		al.setApproverId("E25-014");
		al.setApprovalStatus("승인");
		al.setOpinion("테스트 의견");
		assertTrue(dao.processApproval(al));
	}

//	@Test
	public void setNextApproverToWaitTest() {
		al.setDocumentNo(20);
		al.setLineOrder(2);
		assertTrue(dao.setNextApproverToWait(al));
	}

//	@Test
	public void findNextApprovalLineNoTest() {
		al.setDocumentNo(20); 
	    al.setLineOrder(2);    
	    System.out.println(dao.findNextApprovalLineNo(al));
	}

//	@Test
	public void sendRequestNotiTest() {
		al.setApprovalLineNo(28);
		assertTrue(dao.sendRequestNoti(al));
	}

//	@Test
	public void sendProcessNotiTest() {
		al.setDocumentNo(20);
		assertTrue(dao.sendProcessNoti(al));
	}

//	@Test
	public void setDocRejectTest() {
		doc.setDocumentNo(2);
		assertTrue(dao.setDocReject(doc));
	}

//	@Test
	public void setDocCompleteTest() {
		doc.setDocumentNo(2);
		assertTrue(dao.setDocComplete(doc));
	}

//	@Test
	public void checkAbsenceTest() {
		System.out.println(dao.checkAbsence("E25-003"));
	}

//	@Test
	public void getWaitListTest() {
		System.out.println(dao.getWaitList("E25-000"));
	}

//	@Test
	public void getEndListTest() {
		System.out.println(dao.getEndList("E25-010"));
	}

//	@Test
	public void getAbsenceListTest() {
		System.out.println(dao.getAbsenceList("E25-005"));
	}

//	@Test
	public void getProxyListTest() {
		System.out.println(dao.getProxyList("E25-013"));
	}

//	@Test
	public void addAbsenceTest() {
		ab.setAbsenteeId("E25-001");
		ab.setProxyId("E25-002");
		ab.setAbsenceStartDate(java.sql.Date.valueOf("2025-10-12"));
		ab.setAbsenceEndDate(java.sql.Date.valueOf("2025-10-20"));
		ab.setAbsenceReason("출장");
		ab.setAbsenceUsage("대기");
		assertTrue(dao.addAbsence(ab));
	}

//	@Test
	public void modifyAbsenceTest() {
		ab.setAbsenceDateNo(1);
		ab.setProxyId("E25-020");
		ab.setAbsenceStartDate(java.sql.Date.valueOf("2025-10-12"));
		ab.setAbsenceEndDate(java.sql.Date.valueOf("2025-10-20"));
		ab.setAbsenceReason("휴가 일정 변경");
		ab.setAbsenceUsage("대기");
		assertTrue(dao.modifyAbsence(ab));
	}
	
	// 부재 조기종료 (위임중일때만)
	@Test
	public void endAbsenceTest() {
		assertTrue(dao.endAbsence(10));
	}
	
	// 부재 삭제 (대기중일때만)
//	@Test
	public void deleteAbsenceTest() {
		assertTrue(dao.deleteAbsence(13));
	}

//	@Test
	public void getApprovalReqNotiTest() {
		System.out.println(dao.getApprovalReqNoti("E25-013"));
	}
	
	
	//@Test
	public void DBCPtest(){
		System.out.println(DBCP.getSqlSessionFactory());
		System.out.println(DBCP.getSqlSessionFactory().openSession());
		System.out.println(DBCP.getSqlSessionFactory().openSession().getConnection());
	}


}

