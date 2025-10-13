package com.oopsw.model;

public class DefaultLineVO {
	private String name;
	private String department;
	private String rank;
	private int lineOrder;
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
	public int getLineOrder() {
		return lineOrder;
	}
	public void setLineOrder(int lineOrder) {
		this.lineOrder = lineOrder;
	}
	@Override
	public String toString() {
		return "DefaultLineVO [name=" + name + ", department=" + department + ", rank=" + rank + ", lineOrder="
				+ lineOrder + "]\n";
	}
	
	
	
	
}
