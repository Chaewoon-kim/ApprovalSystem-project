package com.oopsw.model.VO;

import java.sql.Date;

public class AlarmVO {
	private String notiType;
	private String notiNo;
	private String title;
	private Date notiInDate;
	private Date readStatus;
	private int documentNo;
	private String status;
	private String approvedDocumentNo;
	private String empId;
	private int page;
	
	public AlarmVO(){
		setPage(1);
	}
	
	public String getNotiNo() {
		return notiNo;
	}
	public void setNotiNo(String notiNo) {
		this.notiNo = notiNo;
	}
	public int getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getApprovedDocumentNo() {
		return approvedDocumentNo;
	}
	public void setApprovedDocumentNo(String approvedDocumentNo) {
		this.approvedDocumentNo = approvedDocumentNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getNotiInDate() {
		return notiInDate;
	}
	public void setNotiInDate(Date notiInDate) {
		this.notiInDate = notiInDate;
	}
	public Date getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(Date readStatus) {
		this.readStatus = readStatus;
	}
	public String getNotiType() {
		return notiType;
	}
	public void setNotiType(String notiType) {
		this.notiType = notiType;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "AlarmVO [empId=" + empId + ", notiType=" + notiType + ", notiNo=" + notiNo + ", documentNo="
				+ documentNo + ", title=" + title + ", approvedDocumentNo=" + approvedDocumentNo + ", status=" + status
				+ ", notiInDate=" + notiInDate + ", readStatus=" + readStatus + ", page=" + page + "]";
	}
	
}
