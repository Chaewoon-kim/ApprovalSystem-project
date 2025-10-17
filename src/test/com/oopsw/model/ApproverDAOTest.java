package test.com.oopsw.model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oopsw.model.DBCP;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.AbsenceListVO;
import com.oopsw.model.VO.AbsenceVO;
import com.oopsw.model.VO.AlarmVO;
import com.oopsw.model.VO.ApprovalLineVO;
import com.oopsw.model.VO.ApproverListVO;
import com.oopsw.model.VO.DocumentVO;
import com.oopsw.model.VO.GetListVO;

public class ApproverDAOTest {
	
	static ApproverDAO dao;
	static ApprovalLineVO al; 
	static AbsenceVO ab;
	static DocumentVO doc;
	static SqlSession conn = DBCP.getSqlSessionFactory().openSession();
	
	@BeforeClass
	public static void start() throws Exception{
		dao = new ApproverDAO();
		al = new ApprovalLineVO();
		ab = new AbsenceVO();
		doc = new DocumentVO();
		
	}
	
	@Test
	public void setAbsenceStatusToActiveTest() {
		assertTrue(dao.setAbsenceStatusToActive());
	}
	
	@Test
	public void setAbsenceStatusToEndTest(){
		assertTrue(dao.setAbsenceStatusToEnd());
	}

	@Test
	public void processApprovalTest() {
		al.setDocumentNo(20);
		al.setApproverId("E25-014");
		al.setApprovalStatus("승인");
		al.setOpinion("테스트 의견");
		assertTrue(dao.processApproval(conn, al));
	}

	@Test
	public void setNextApproverToWaitTest() {
		al.setDocumentNo(20);
		al.setLineOrder(2);
		assertTrue(dao.setNextApproverToWait(conn, al));
	}

	@Test
	public void findNextApprovalLineNoTest() {
		al.setDocumentNo(20); 
	    al.setLineOrder(2);    
	    System.out.println(dao.findNextApprovalLineNo(conn, al));
	}

	@Test
	public void sendRequestNotiTest() {
		al.setApprovalLineNo(28);
		assertTrue(dao.sendRequestNoti(conn, al));
	}

	@Test
	public void sendProcessNotiTest() {
		al.setDocumentNo(20);
		assertTrue(dao.sendProcessNoti(conn, al));
	}

	@Test
	public void setDocRejectTest() {
		doc.setDocumentNo(2);
		assertTrue(dao.setDocReject(conn, doc));
	}

	@Test
	public void setDocCompleteTest() {
		doc.setDocumentNo(2);
		assertTrue(dao.setDocComplete(conn, doc));
	}

	@Test
	public void checkAbsenceTest() {
		System.out.println(dao.checkAbsence("E25-003"));
	}

	@Test
	public void getWaitListTest() {
		System.out.println(dao.getWaitList(new GetListVO("E25-000", null, 1)));
	}

	@Test
	public void getEndListTest() {
		System.out.println(dao.getEndList(new GetListVO("E25-010", "반려", 1)));
	}

	@Test
	public void getAbsenceListTest() {
		System.out.println(dao.getAbsenceList("E25-005"));
	}

	@Test
	public void getProxyListTest() {
		System.out.println(dao.getProxyList("E25-013"));
	}

	@Test
	public void addAbsenceTest() {
		ab.setAbsenteeId("E25-001");
		ab.setProxyId("E25-002");
		ab.setAbsenceStartDate(java.sql.Date.valueOf("2025-10-12"));
		ab.setAbsenceEndDate(java.sql.Date.valueOf("2025-10-20"));
		ab.setAbsenceReason("출장");
		ab.setAbsenceUsage("대기");
		assertTrue(dao.addAbsence(ab));
	}

	@Test
	public void modifyAbsenceTest() {
		ab.setAbsenceDateNo(1);
		ab.setProxyId("E25-020");
		ab.setAbsenceStartDate(java.sql.Date.valueOf("2025-10-12"));
		ab.setAbsenceEndDate(java.sql.Date.valueOf("2025-10-20"));
		ab.setAbsenceReason("휴가 일정 변경");
		ab.setAbsenceUsage("대기");
		assertTrue(dao.modifyAbsence(ab));
	}
	
	@Test
	public void endAbsenceTest() {
		assertTrue(dao.endAbsence(10));
	}
	
	@Test
	public void deleteAbsenceTest() {
		assertTrue(dao.deleteAbsence(13));
	}

	@Test
	public void getApprovalReqNotiTest() {
		System.out.println(dao.getApprovalReqNoti("E25-008"));
	}
	
	@Test
	public void getUnReadApprovalReqNotiTest() {
		System.out.println(dao.getUnReadApprovalReqNoti("E25-008"));
	}
	
	
	@Test
	public void DBCPtest(){
		System.out.println(DBCP.getSqlSessionFactory());
		System.out.println(DBCP.getSqlSessionFactory().openSession());
		System.out.println(DBCP.getSqlSessionFactory().openSession().getConnection());
	}


}

