package test.com.oopsw.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import com.oopsw.model.DBCP;
import com.oopsw.model.DAO.ManagerDAO;
import com.oopsw.model.VO.DefaultApprovalLineVO;
import com.oopsw.model.VO.EmployeeVO;
import com.oopsw.model.VO.FormVO;

public class ManagerDAOTest {
		
	public static ManagerDAO managerDAO;
	@Before
	public void DBCPTest(){
		SqlSession connection = DBCP.getSqlSessionFactory().openSession();
		assertNotNull(connection);
		
		managerDAO = new ManagerDAO();
	}
	
	@Test
	public void getEmployeesTest(){
		EmployeeVO vo = new EmployeeVO();
		vo.setPage(1);
		
		List<EmployeeVO> employees = managerDAO.getEmployees(vo);
		
		assertNotNull(employees);

//		for(Object tmp : employees){
//			System.out.println(tmp);
//		}
	}
	
	@Test
	public void getFormsTest(){
		List<FormVO> forms = managerDAO.getForms(new FormVO());
		
		assertNotNull(forms);

//		for(Object tmp : forms){
//			System.out.println(tmp);
//		}
	}
	
	@Test
	public void invertPermissionTest(){
		EmployeeVO vo = new EmployeeVO();
		vo.setEmployeeId("E25-021");
		
		assertTrue(managerDAO.invertPermission(vo));
	}

	@Test
	public void getEmployeesByPermissionTest(){
		EmployeeVO vo = new EmployeeVO();
		vo.setAccessPermission('Y');
		
		List<EmployeeVO> employees = managerDAO.getEmployees(vo);
		
		assertNotNull(employees);

//		for(Object tmp : employees){
//			System.out.println(tmp);
//		}
	}
	
	@Test
	public void addFormTest(){
		FormVO vo = new FormVO(null, "E25-000", "업무", "테스트제목", "테스트양식", "테스트설명", 'Y');
		assertTrue(managerDAO.addForm(vo));
		
//		System.out.println(vo.getFormId()); // insert 이후 pk 받아올 수 있음
	}
	
	@Test
	public void setDefaultApprovalLineTest(){
		List<DefaultApprovalLineVO> voList = new ArrayList<>();
		voList.add(new DefaultApprovalLineVO(0, "D2", "대리", 1));
		voList.add(new DefaultApprovalLineVO(0, "D2", "과장", 2));
		
		
		managerDAO.setDefaultApprovalLine(voList);
	}

	@Test
	public void invertFormUsageTest(){
		FormVO vo = new FormVO();
		vo.setFormId("D2");
		
		assertTrue(managerDAO.invertFormUsage(vo));
	}
	
	@Test
	public void getFormsByKeywordTest(){
		FormVO vo = new FormVO();
		vo.setKeyword("업무");
		List<FormVO> forms = managerDAO.getForms(vo);
		
		assertNotNull(forms);

//		for(Object tmp : forms){
//			System.out.println(tmp);
//		}
	}
}
