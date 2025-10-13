package com.oopsw.model.VO;

import java.sql.Date;

public class ApprovalStatusVO {
	private int approvalLineNo;
	private String approverId;
	private String name;
	private String department;
	private String rank;
	private int lineOrder;
	private Date approvalDate;
	private String approvalStatus;
	private String opinion;
	private Date requestReadStatus;
	
	public int getApprovalLineNo() {
		return approvalLineNo;
	}
	public void setApprovalLineNo(int approvalLineNo) {
		this.approvalLineNo = approvalLineNo;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
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
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public Date getRequestReadStatus() {
		return requestReadStatus;
	}
	public void setRequestReadStatus(Date requestReadStatus) {
		this.requestReadStatus = requestReadStatus;
	}
	@Override
	public String toString() {
		return "ApprovalStatusVO [approvalLineNo=" + approvalLineNo + ", approverId=" + approverId + ", name=" + name
				+ ", department=" + department + ", rank=" + rank + ", lineOrder=" + lineOrder + ", approvalDate="
				+ approvalDate + ", approvalStatus=" + approvalStatus + ", opinion=" + opinion + ", requestReadStatus="
				+ requestReadStatus + "]\n";
	}
	
}
