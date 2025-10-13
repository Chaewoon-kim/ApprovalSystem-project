package test.com.oopsw.model;

import java.sql.Connection;
import java.util.List;

import com.oopsw.model.CommentNotiVO;
import com.oopsw.model.CommentVO;
import com.oopsw.model.EmployeeDAO;
import com.oopsw.model.EmployeeVO;

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
	
	//로그인
	@Test
	public void loginTest(){ 
		    EmployeeVO vo = new EmployeeVO("E25-001" , "a1lb2@c3");
		    EmployeeVO result = dao.login(vo);       
		    assertEquals("김민준", result.getName()); 
	
	}
	
	
	//댓글 조회 테스트
	@Test
	public void getCommentTest(){
		List<CommentVO> resultList = dao.getComments(1);
		System.out.println(resultList);
			
	}

	//댓글 작성
	@Test 
	public void writeCommentTest(){
		CommentVO comment = new CommentVO(1 , "E25-013" ,"승인했습니다.");
		boolean result = dao.writeComment(comment);
		assertEquals(true , result);
		
	}
	
	//전체 사원 검색
	@Test
	public void getAllEmployeesTest(){
		 List<EmployeeVO> employeeList = dao.getAllEmployees();
		 
		 if (!employeeList.isEmpty()) {
	            EmployeeVO firstEmployee = employeeList.get(0);	            	         
	            assertEquals("E25-000", firstEmployee.getEmployeeId());
		 }
	}
	
	//특정 사원 검색
	@Test
	public void getEmployeeTest(){
		List<EmployeeVO> employeeList = dao.getEmployee("영");
		
		assertEquals(7 , employeeList.size());
	}
	
		//댓글 알림 보내기
		@Test
		public void sendNoti(){
			System.out.println(dao.sendNoti(new CommentNotiVO(9 , "E25-001" , 3)));
		}
		
		//대결 알림
		@Test
		public void getApprovalNoti(){
			System.out.println(dao.getApprovalNoti("E25-001"));
		}
		
		//댓글 알림 (받기)
		@Test
		public void getCommentsNoti(){
			System.out.println(dao.getCommentsNoti("E25-006"));
		}
		
		//결재선 테이블에서 가져오기
		@Test
		public void getApprovalTable(){
			System.out.println(dao.getApprvovalTable(2));
		}
		
		//결재 신청 문서 상세 조회
		@Test
		public void getDetailReport(){
			System.out.println(dao.getDetailReport(2));
		}
}
