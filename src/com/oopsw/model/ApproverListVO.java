package com.oopsw.model;

import java.util.Date;

public class ApproverListVO {
	private int documentNo;
	private Date deadline;
	private Date draftDate;
	private Date completionDate;
	private String title;
	private String department;
	private String approvedDocumentNo;
	private String approvalStatus;
	private String name;
	private String processStatus;
	
	public ApproverListVO(){};
	
	
	public ApproverListVO(int documentNo, Date deadline, Date draftDate, Date completionDate, String title,
			String department, String approvedDocumentNo, String approvalStatus, String name, String processStatus) {
		this.documentNo = documentNo;
		this.deadline = deadline;
		this.draftDate = draftDate;
		this.completionDate = completionDate;
		this.title = title;
		this.department = department;
		this.approvedDocumentNo = approvedDocumentNo;
		this.approvalStatus = approvalStatus;
		this.name = name;
		this.processStatus = processStatus;
	}


	public int getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Date getDraftDate() {
		return draftDate;
	}
	public void setDraftDate(Date draftDate) {
		this.draftDate = draftDate;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getApprovedDocumentNo() {
		return approvedDocumentNo;
	}
	public void setApprovedDocumentNo(String approvedDocumentNo) {
		this.approvedDocumentNo = approvedDocumentNo;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	
	

}
