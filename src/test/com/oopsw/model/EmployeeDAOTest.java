package test.com.oopsw.model;

import java.sql.Connection;
import java.util.List;

import com.oopsw.model.CommentVO;
import com.oopsw.model.EmployeeDAO;
import com.oopsw.model.EmployeeVO;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;


public class EmployeeDAOTest {
	
	static EmployeeDAO dao;
	static Connection conn;
	static EmployeeVO vo;
	
	@BeforeClass
	public static void start() throws Exception{
		dao = new EmployeeDAO();
	}
	
	
	@Test
	public void loginTest(){ 
		    EmployeeVO vo = new EmployeeVO();
		    vo.setEmployeeId("E25-001");
		    vo.setPassword("a1lb2@c3");
		    EmployeeVO result = dao.login(vo);       
		    assertEquals("김채운", result.getName()); 
	
	}
	
	
	@Test
	public void getCommentTest(){
		List<CommentVO> resultList = dao.getComments(1);
		
		CommentVO firstComment = resultList.get(0);
		assertEquals("E25-007", firstComment.getWriterName().getEmployeeId());
	    assertEquals("조은서", firstComment.getWriterName().getName());
			
	}

	
	@Test 
	public void writeCommentTest(){
		CommentVO comment = new CommentVO(1 , "E25-013" ,"승인했습니다.");
		boolean result = dao.writeComment(comment.getDocumentNumber(),comment.getwriterId(), comment.getCommentContents());
		assertEquals(true , result);
		
	}
	
	@Test
	public void getAllEmployeesTest(){
		 List<EmployeeVO> employeeList = dao.getAllEmployees();
		 
		 if (!employeeList.isEmpty()) {
	            EmployeeVO firstEmployee = employeeList.get(0);	            	         
	            assertEquals("E25-000", firstEmployee.getEmployeeId());
		 }
	}
	
	@Test
	public void getEmployeeTest(){
		List<EmployeeVO> employeeList = dao.getEmployee("영");
		
		assertEquals(7 , employeeList.size());
		
	}
}
