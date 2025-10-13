package com.oopsw.model.VO;

public class EmployeeVO {
	private String employeeId;
	private String name;
	private String password;
	private String department;
	private String rank;
	private char accessPermission;
	private char managerPermission;
	private int page;
	
	public EmployeeVO(){
		setPage(1);
	}
	
	public EmployeeVO(String employeeId, String name, String password, String department, String rank,
			char access_permission, char manager_permission) {
		this();
		setEmployeeId(employeeId);
		setName(name);
		setPassword(password);
		setDepartment(department);
		setRank(rank);
		setAccessPermission(access_permission);
		setManagerPermission(manager_permission);
	}
	
	public EmployeeVO(String employeeId, String password) {
		setEmployeeId(employeeId);
        setPassword(password);
    }
    
    public EmployeeVO(String employeeId, String name, String password) {
    	setEmployeeId(employeeId);
    	setName(name);
        setPassword(password);
    }
    
    public EmployeeVO(String employeeId , String name , String department , String rank){
    	setEmployeeId(employeeId);
    	setName(name);
    	setDepartment(department);
    	setRank(rank);
    }
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public char getAccessPermission() {
		return accessPermission;
	}
	public void setAccessPermission(char accessPermission) {
		this.accessPermission = accessPermission;
	}
	public char getManagerPermission() {
		return managerPermission;
	}
	public void setManagerPermission(char managerPermission) {
		this.managerPermission = managerPermission;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "EmployeeVO [employeeId=" + employeeId + ", name=" + name + ", password=" + password + ", department="
				+ department + ", rank=" + rank + ", access_permission=" + accessPermission + ", manager_permission="
				+ managerPermission + "]";
	}
}
