package com.oopsw.model.VO;

import java.sql.Date;

public class DocumentDetailVO {
	//document
    private int documentNo;
    private String employeeId;
    private String title;
    private String contents;
    private Date draftDate;
    private Date completionDate;
    private Date deadline;

    // employee
    private String employeeName;
    private String employeeDepartment;
    private String employeeRank;

    // document_form
    private String formName;

    public DocumentDetailVO() {}

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

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public String getEmployeeRank() {
		return employeeRank;
	}

	public void setEmployeeRank(String employeeRank) {
		this.employeeRank = employeeRank;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	@Override
	public String toString() {
		return "DocumentDetailVO [employeeId=" + employeeId  + ", employeeName=" + employeeName + ", employeeRank=" + employeeRank + ", employeeDepartment=" + employeeDepartment + ", draftDate=" + draftDate + ", completionDate=" + completionDate	
				+ ", deadline=" + deadline  + "]";
	}


}
