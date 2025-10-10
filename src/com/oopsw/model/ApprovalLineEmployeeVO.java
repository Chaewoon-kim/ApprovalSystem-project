package com.oopsw.model;

import java.sql.Date;

public class ApprovalLineEmployeeVO {
	private int lineOrder;
	private Date approvalDate;
	private String approvalStatus;


	private String employeeId;
	private String name;
	private String department;
	private String rank;

	public ApprovalLineEmployeeVO() {}

	public int getLineOrder() {
		return lineOrder;
	}

	public void setLineOrder(int lineOrder) {
		this.lineOrder = lineOrder;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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

	@Override
	public String toString() {
		return "ApprovalLineEmployeeVO [lineOrder=" + lineOrder + ", approvalDate=" + approvalDate + ", approvalStatus="
				+ approvalStatus + ", employeeId=" + employeeId + ", name=" + name + ", department=" + department
				+ ", rank=" + rank + "]";
	}


	
	
	
	}
