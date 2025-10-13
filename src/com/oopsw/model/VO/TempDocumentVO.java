package com.oopsw.model.VO;

import java.sql.Date;

public class TempDocumentVO {
	private int documentNo;
	private String formName;
	private String title;
	private String processStatus;
	private Date temporarySaveDate;
	public int getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public Date getTemporarySaveDate() {
		return temporarySaveDate;
	}
	public void setTemporarySaveDate(Date temporarySaveDate) {
		this.temporarySaveDate = temporarySaveDate;
	}
	@Override
	public String toString() {
		return "TempDocumentVO [documentNo=" + documentNo + ", formName=" + formName + ", title=" + title
				+ ", processStatus=" + processStatus + ", temporarySaveDate=" + temporarySaveDate + "]\n";
	}
	
	
}
