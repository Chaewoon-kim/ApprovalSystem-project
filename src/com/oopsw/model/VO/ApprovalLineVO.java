package com.oopsw.model.VO;

import java.sql.Date;

public class ApprovalLineVO {
	private int approvalLineNo;
	private int documentNo;
	private String approverId;
	private int lineOrder;
	private String opinion;
	private Date approvalDate;
	private String approvalStatus;
	
	public ApprovalLineVO(){};
	
	public ApprovalLineVO(int documentNo, String approverId, int lineOrder, String approvalStatus) {
		setDocumentNo(documentNo);
		setApproverId(approverId);
		setLineOrder(lineOrder);
		setApprovalStatus(approvalStatus);
	}
	
	public ApprovalLineVO(int approvalLineNo, int documentNo, String approverId, int lineOrder, String opinion,
			Date approvalDate, String approvalStatus) {
		super();
		setApprovalLineNo(approvalLineNo);
		setDocumentNo(documentNo);
		setApproverId(approverId);
		setLineOrder(lineOrder);
		setOpinion(opinion);
		setApprovalDate(approvalDate);
		setApprovalStatus(approvalStatus);
	}

	public int getApprovalLineNo() {
		return approvalLineNo;
	}
	public void setApprovalLineNo(int approvalLineNo) {
		this.approvalLineNo = approvalLineNo;
	}
	public int getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public int getLineOrder() {
		return lineOrder;
	}
	public void setLineOrder(int lineOrder) {
		this.lineOrder = lineOrder;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
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
	@Override
	public String toString() {
		return "ApprovalLineVO [approvalLineNo=" + approvalLineNo + ", documentNo=" + documentNo + ", approverId="
				+ approverId + ", lineOrder=" + lineOrder + ", opinion=" + opinion + ", approvalDate=" + approvalDate
				+ ", approvalStatus=" + approvalStatus + "]\n";
	}
	
	
}
