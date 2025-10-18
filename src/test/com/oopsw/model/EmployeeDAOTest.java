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
	
	@Test
	public void loginTest(){ 
		    EmployeeVO vo = new EmployeeVO("E25-021" , "asdf123!");
		    EmployeeVO result = dao.login(vo);       
		    System.out.println(result);
	}
	

	@Test
	public void getCommentTest(){
		List<CommentVO> resultList = dao.getComments(1);
		
		CommentVO firstComment = resultList.get(0);
		assertEquals("E25-007", firstComment.getwriterId());
	    assertEquals("������", firstComment.getWriterName());
			
	}

	@Test 
	public void writeCommentTest(){
		CommentVO comment = new CommentVO(1 , "E25-013" ,"�����߽��ϴ�.");
		boolean result = dao.addComment(comment);
		assertEquals(true , result);
		
	}
	
	@Test
	public void getAllEmployeesTest(){
		 List<EmployeeVO> employeeList = dao.getAllEmployees();
		 System.out.println(employeeList);
	}
	

	@Test
	public void getEmployeeTest(){
		List<EmployeeVO> employeeList = dao.getEmployee("��");
		System.out.println(employeeList);
	}
	
	@Test
	public void sendNoti(){
		System.out.println(dao.sendNoti(new CommentNotiVO(9 , "E25-001" , 3)));
	}
	
	//대결 알림 조회
	@Test
	public void getApprovalNoti(){
		System.out.println(dao.getApprovalNoti("E25-001"));
	}
	
	//댓글 알림 (받기)
	@Test
	public void getCommentsNoti(){
		System.out.println(dao.getCommentsNoti("E25-006"));
	}
	
	//안읽은 대결 알림 조회
	@Test
	public void getUnReadApprovalNoti(){
		System.out.println(dao.getUnReadApprovalNoti("E25-001"));
	}
	
	//안읽은 댓글 알림 (받기)
	@Test
	public void getUnReadCommentsNoti(){
		System.out.println(dao.getUnReadCommentsNoti("E25-002"));
	}
	
	@Test
	public void getApprovalTable(){
		System.out.println(dao.getApprvovalTable(2));
	}
	
	@Test
	public void getDetailReport(){
		System.out.println(dao.getDetailReport(2));
	}
	
	@Test
	public void readProcessNotiTest(){
		assertTrue(dao.readProcessNoti(12));
	}
	
	@Test
	public void readRequestNotiTest(){
		assertTrue(dao.readRequestNoti(42));
	}
	
	@Test
	public void readCommentNotiTest(){
		assertTrue(dao.readCommentNoti(18));
	}
	
	@Test
	public void readAbsenceNotiTest(){
		assertTrue(dao.readAbsenceNoti(7));
	}

}
