package com.oopsw.model.DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.oopsw.model.DBCP;
import com.oopsw.model.VO.DefaultApprovalLineVO;
import com.oopsw.model.VO.EmployeeVO;
import com.oopsw.model.VO.FormVO;

public class ManagerDAO {
	public int getFormCount(FormVO formVO){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		String page = conn.selectOne("managerMapper.getFormCount", formVO);
		conn.close();
		
		return Integer.parseInt(page);
	}
	public int getEmployeeCount(EmployeeVO employeeVO){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		String page = conn.selectOne("managerMapper.getEmployeeCount", employeeVO);
		conn.close();
		
		return Integer.parseInt(page);
	}
	public List<EmployeeVO> getEmployees(EmployeeVO employeeVO){
		List<EmployeeVO> employees;
		
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		employees = conn.selectList("managerMapper.getEmployees", employeeVO);
		conn.close();
		
		return employees;
	}
	
	public boolean invertPermission(EmployeeVO employeeVO){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		boolean result = conn.update("managerMapper.invertPermission", employeeVO) == 1;
		conn.commit();
		conn.close();
		
		return result;
	}
	

	public List<FormVO> getForms(FormVO vo){
		List<FormVO> forms;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		forms = conn.selectList("managerMapper.getAllForms", vo);
		conn.close();
		
		return forms;
	}

	public List<FormVO> getFormsWithKeyword(FormVO vo){
		List<FormVO> forms;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		forms = conn.selectList("managerMapper.getFormsByKeyword", vo);
		conn.close();
		
		return forms;
	}
	
	public boolean addForm(FormVO formVO){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		boolean result = conn.insert("managerMapper.addForm", formVO) == 1;
		conn.commit();
		conn.close();
		
		return result;
	}
	
	public boolean addDefaultApprovalLine(List<DefaultApprovalLineVO> approvalList){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		boolean result = false;
		for(int i = 0; i < approvalList.size(); i++){
			 result = conn.insert("managerMapper.addApprovalLine", approvalList.get(i)) == 1;
			if(!result) break;
		}
		conn.commit();
		conn.close();
		
		return result;
	}
	
	public boolean invertFormUsage(FormVO formVO){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		boolean result = conn.update("managerMapper.invertFormUsage", formVO) == 1;
		conn.commit();
		conn.close();
		
		return result;
	}
}
