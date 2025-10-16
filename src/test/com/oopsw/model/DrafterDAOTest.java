package test.com.oopsw.model;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oopsw.exception.DatabaseTransactionException;
import com.oopsw.model.DBCP;
import com.oopsw.model.DAO.DrafterDAO;
import com.oopsw.model.VO.ApprovalLineVO;
import com.oopsw.model.VO.DocumentVO;
import com.oopsw.model.VO.GetDefaultLineVO;
import com.oopsw.model.VO.GetListVO;

public class DrafterDAOTest {
	static DrafterDAO d;
	static SqlSession conn = DBCP.getSqlSessionFactory().openSession();
	
	@BeforeClass
	public static void start() throws Exception{
		d = new DrafterDAO();
	}

	@Test
	public void getAllReqListTest() {
		System.out.println(d.getReqList(new GetListVO("E25-001", null, 1)));
	}
	
	@Test
	public void getStatusReqListTest() {
		System.out.println(d.getReqList(new GetListVO("E25-001", "�Ϸ�", 1)));
	}
	
	@Test
	public void setFormTest(){
		System.out.println(d.setForm("D1"));
	}
	
	@Test
	public void getDefaultLineTest(){
		System.out.println(d.getDefaultLine(new GetDefaultLineVO("E25-002", "D2")));
	}
	
	@Test
	public void addDocTest() throws DatabaseTransactionException{
		System.out.println(d.addDoc(new DocumentVO("E25-001", "D2", "�׽�Ʈ����", "�׽�Ʈ����", java.sql.Date.valueOf("2025-10-05")), conn));
	}
	
	@Test
	public void addApproverTest() throws DatabaseTransactionException{
		System.out.println(d.addApprovers(new ApprovalLineVO(41, "E25-008", 1, "�����"), conn));
	}
	
	@Test
	public void sendFirstReqNotiTest() throws DatabaseTransactionException{
		assertTrue(d.sendFirstReqNoti(46, conn));
	}
	
	@Test
	public void saveTempDocTest() throws DatabaseTransactionException{
		System.out.println(d.saveTempDoc(new DocumentVO("E25-001", "D2", "�ӽ������׽�Ʈ����", "�ӽ������׽�Ʈ����",java.sql.Date.valueOf("2025-10-05")), conn));
	}
	
	@Test
	public void getTempListTest(){
		System.out.println(d.getTempList("E25-001"));
	}
	
	@Test
	public void editTempDocTest() throws DatabaseTransactionException{
		assertTrue(d.editTempDoc(new DocumentVO(41, "�������", "�ӽ����峻��", java.sql.Date.valueOf("2025-10-09")), conn));
	}
	
	@Test
	public void submitTempDocTest() throws DatabaseTransactionException{
		assertTrue(d.submitTempDoc(new DocumentVO(41, "�ӽ���������", "�ӽ����峻��", java.sql.Date.valueOf("2025-10-09")), conn));
	}
	
	@Test
	public void removeApproversTest() throws DatabaseTransactionException{
		assertEquals(2, d.removeApprovers(53, conn));
	}
	
	@Test
	public void getApprovalStatusTest(){
		System.out.println(d.getApprovalStatus(20));
	}
	
	@Test
	public void getAllApprovalProcessNotiTest() {
		System.out.println(d.getApprovalProcessNoti(new GetListVO("E25-004", null, 0)));
	}
	
	@Test
	public void getStatusApprovalProcessNotiTest() {
		System.out.println(d.getApprovalProcessNoti(new GetListVO("E25-004", "�Ϸ�", 0)));
	}
	
	@Test
	public void getUnReadStatusApprovalProcessNotiTest() {
		System.out.println(d.getUnReadApprovalProcessNoti(new GetListVO("E25-004", null, 0)));
	}
	
}
