package test.com.oopsw.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import com.oopsw.model.DBCP;
import com.oopsw.model.DefaultApprovalLineVO;
import com.oopsw.model.EmployeeVO;
import com.oopsw.model.FormVO;
import com.oopsw.model.ManagerDAO;

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
		
		List<Map<String, Object>> employees = managerDAO.getEmployees(vo);
		
		assertNotNull(employees);

//		for(Object tmp : employees){
//			System.out.println(tmp);
//		}
	}
	
	@Test
	public void getFormsTest(){
		List<Map<String, Object>> forms = managerDAO.getForms();
		
		assertNotNull(forms);

//		for(Object tmp : forms){
//			System.out.println(tmp);
//		}
	}
	
	@Test
	public void invertPermissionTest(){
		EmployeeVO vo = new EmployeeVO();
		vo.setEmployeeId("E25-021");
		
		assertTrue(managerDAO.invertPermissioin(vo));
	}

	@Test
	public void getEmployeesByPermissionTest(){
		EmployeeVO vo = new EmployeeVO();
		vo.setAccessPermission('Y');
		
		List<Map<String, Object>> employees = managerDAO.getEmployeesByPermission(vo);
		
		assertNotNull(employees);

//		for(Object tmp : employees){
//			System.out.println(tmp);
//		}
	}
	
	@Test
	public void addFormTest(){
		FormVO vo = new FormVO(null, "E25-000", "����", "�׽�Ʈ����", "�׽�Ʈ���", "�׽�Ʈ����", 'Y');
		assertTrue(managerDAO.addForm(vo));
		
//		System.out.println(vo.getFormId()); // insert ���� pk �޾ƿ� �� ����
	}
	
	@Test
	public void setDefaultApprovalLineTest(){
		List<DefaultApprovalLineVO> voList = new ArrayList<>();
		voList.add(new DefaultApprovalLineVO(0, "23", "�븮", 1));
		voList.add(new DefaultApprovalLineVO(0, "23", "����", 2));
		
		
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
		vo.setKeyword("����");
		List<Map<String, Object>> forms = managerDAO.getFormsByKeyword(vo);
		
		assertNotNull(forms);

		for(Object tmp : forms){
			System.out.println(tmp);
		}
	}
}
