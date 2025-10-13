package test.com.oopsw.model;

import java.sql.Connection;

import java.util.List;

import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.CommentNotiVO;
import com.oopsw.model.VO.CommentVO;
import com.oopsw.model.VO.EmployeeVO;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;


public class EmployeeDAOTest {
	
	static EmployeeDAO dao;
	static Connection conn;
	
	
	@BeforeClass
	public static void start() throws Exception{
		dao = new EmployeeDAO();
	}
	

	//�α���
//	@Test
	public void loginTest(){ 
		    EmployeeVO vo = new EmployeeVO("E25-001" , "a1lb2@c3");
		    EmployeeVO result = dao.login(vo);       
		    assertEquals("�����", result.getName()); 
	
	}
	
	
	//��� ��ȸ �׽�Ʈ
	//@Test
	public void getCommentTest(){
		List<CommentVO> resultList = dao.getComments(1);
		
		CommentVO firstComment = resultList.get(0);
		assertEquals("E25-007", firstComment.getwriterId());
	    assertEquals("������", firstComment.getWriterName());
			
	}

	//��� �ۼ�
	//@Test 
	public void writeCommentTest(){
		CommentVO comment = new CommentVO(1 , "E25-013" ,"�����߽��ϴ�.");
		boolean result = dao.writeComment(comment);
		assertEquals(true , result);
		
	}
	
	//��ü ��� �˻�
//	@Test
	public void getAllEmployeesTest(){
		 List<EmployeeVO> employeeList = dao.getAllEmployees();
		 
		 if (!employeeList.isEmpty()) {
	            EmployeeVO firstEmployee = employeeList.get(0);	            	         
	            assertEquals("E25-000", firstEmployee.getEmployeeId());
		 }
	}
	

	//Ư�� ��� �˻�
	//@Test
	public void getEmployeeTest(){
		List<EmployeeVO> employeeList = dao.getEmployee("��");
		
		assertEquals(7 , employeeList.size());
	}
	
	//��� �˸� ������
//	@Test
	public void sendNoti(){
		System.out.println(dao.sendNoti(new CommentNotiVO(9 , "E25-001" , 3)));
	}
	
	//��� �˸� ��ȸ
//	@Test
	public void getApprovalNoti(){
		System.out.println(dao.getApprovalNoti("E25-001"));
	}
	
	//��� �˸� (�ޱ�)
//	@Test
	public void getCommentsNoti(){
		System.out.println(dao.getCommentsNoti("E25-006"));
	}
	
	//������ ��� �˸� ��ȸ
	@Test
	public void getUnReadApprovalNoti(){
		System.out.println(dao.getUnReadApprovalNoti("E25-001"));
	}
	
	//������ ��� �˸� (�ޱ�)
	@Test
	public void getUnReadCommentsNoti(){
		System.out.println(dao.getUnReadCommentsNoti("E25-002"));
	}
	
	//���缱 ���̺��� ��������
//	@Test
	public void getApprovalTable(){
		System.out.println(dao.getApprvovalTable(2));
	}
	
	//���� ��û ���� �� ��ȸ
//	@Test
	public void getDetailReport(){
		System.out.println(dao.getDetailReport(2));
	}
	
//	@Test
	public void readProcessNotiTest(){
		assertTrue(dao.readProcessNoti(12));
	}
	
//	@Test
	public void readRequestNotiTest(){
		assertTrue(dao.readRequestNoti(42));
	}
	
//	@Test
	public void readCommentNotiTest(){
		assertTrue(dao.readCommentNoti(18));
	}
	
//	@Test
	public void readAbsenceNotiTest(){
		assertTrue(dao.readAbsenceNoti(7));
	}

}
