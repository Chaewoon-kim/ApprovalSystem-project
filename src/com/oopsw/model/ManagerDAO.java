package com.oopsw.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class ManagerDAO {
	public List<Map<String, Object>> getEmployees(EmployeeVO vo){
		List<Map<String, Object>> employees;
		
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		employees = conn.selectList("managerMapper.getEmployees", vo);
		conn.close();
		
		return employees;
	}
	
	public boolean invertPermissioin(EmployeeVO employeeVO){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		boolean result = conn.update("invertPermission", employeeVO) == 1;
		conn.close();
		
		return result;
	}
	
	public List<Map<String, Object>> getEmployeesByPermission(EmployeeVO employeeVO){
		List<Map<String, Object>> employees;
		
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		employees = conn.selectList("getEmployeesByPermission", employeeVO);
		conn.close();
		
		return employees;
	}

	public List<Map<String, Object>> getForms(){
		List<Map<String, Object>> forms;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		forms = conn.selectList("getForms");
		conn.close();
		
		return forms;
	}
	
	public boolean addForm(FormVO formVO){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		boolean result = conn.insert("addForm", formVO) == 1;
		conn.close();
		
		return result;
	}
	
	public boolean setDefaultApprovalLine(List<DefaultApprovalLineVO> approvalList){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		boolean result = false;
		for(int i = 0; i < approvalList.size(); i++){
			 result = conn.insert("addApprovalLine", approvalList.get(i)) == 1;
			if(!result) break;
		}
		conn.close();
		
		return result;
	}
	
	public boolean invertFormUsage(FormVO formVO){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		boolean result = conn.update("invertFormUsage", formVO) == 1;
		conn.close();
		
		return result;
	}
	
	public List<Map<String, Object>> getFormsByKeyword(FormVO formVO){
		List<Map<String, Object>> forms;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		forms = conn.selectList("getFormsByKeyword", formVO);
		conn.close();
		
		return forms;
	}
}
