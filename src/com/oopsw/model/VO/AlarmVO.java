package com.oopsw.model.VO;

import java.sql.Date;

public class AlarmVO {
	private int notiNo;
	private int documentNo;
	private String title;
	private String approvedDocumentNo;
	private String status;
	private Date notiInDate;
	private Date readStatus;
	public int getNotiNo() {
		return notiNo;
	}
	public void setNotiNo(int notiNo) {
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
	@Override
	public String toString() {
		return "AlarmVO [notiNo=" + notiNo +",documentNo=" + documentNo + ", title=" + title + ", approvedDocumentNo=" + approvedDocumentNo
				+ ", status=" + status + ", notiInDate=" + notiInDate + ", readStatus=" + readStatus + "]\n";
	}
	
}
