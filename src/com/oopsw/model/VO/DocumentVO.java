package com.oopsw.model.VO;

import java.sql.Date;

public class DocumentVO {
	private int documentNo;
	private String employeeId;
	private String formId;
	private String title;
	private String contents;
	private Date draftDate;
	private String approvedDocumentNo;
	private String processStatus;
	private Date completionDate;
	private Date temporarySaveDate;
	private Date deadline;
	
	public DocumentVO(){}
	
	public DocumentVO(String employeeId, String formId, String title, String contents, Date deadline) {
		setEmployeeId(employeeId);
		setFormId(formId);
		setTitle(title);
		setContents(contents);
		setDeadline(deadline);
	}
	
	public DocumentVO(int documentNo, String title, String contents, Date deadline) {
		setDocumentNo(documentNo);
		setTitle(title);
		setContents(contents);
		setDeadline(deadline);
	}

	public DocumentVO(int documentNo, String employeeId, String formId, String title, String contents, Date draftDate,
			String approvedDocumentNo, String processStatus, Date completionDate, Date temporarySaveDate,
			Date deadline) {
		super();
		setDocumentNo(documentNo);
		setEmployeeId(employeeId);
		setFormId(formId);
		setTitle(title);
		setContents(contents);
		setDraftDate(draftDate);
		setApprovedDocumentNo(approvedDocumentNo);
		setProcessStatus(processStatus);
		setCompletionDate(completionDate);
		setTemporarySaveDate(temporarySaveDate);
		setDeadline(deadline);
	}

	public int getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getDraftDate() {
		return draftDate;
	}

	public void setDraftDate(Date draftDate) {
		this.draftDate = draftDate;
	}

	public String getApprovedDocumentNo() {
		return approvedDocumentNo;
	}

	public void setApprovedDocumentNo(String approvedDocumentNo) {
		this.approvedDocumentNo = approvedDocumentNo;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public Date getTemporarySaveDate() {
		return temporarySaveDate;
	}

	public void setTemporarySaveDate(Date temporarySaveDate) {
		this.temporarySaveDate = temporarySaveDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "DocumentVO [documentNo=" + documentNo + ", employeeId=" + employeeId + ", formId=" + formId + ", title="
				+ title + ", contents=" + contents + ", draftDate=" + draftDate + ", approvedDocumentNo="
				+ approvedDocumentNo + ", processStatus=" + processStatus + ", completionDate=" + completionDate
				+ ", temporarySaveDate=" + temporarySaveDate + ", deadline=" + deadline + "]\n";
	};
	

	
	
	
	
}
