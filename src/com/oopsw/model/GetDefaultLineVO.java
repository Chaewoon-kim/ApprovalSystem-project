package com.oopsw.model;

public class GetDefaultLineVO {
	private String employeeId;
	private String formId;
	
	public GetDefaultLineVO(String employeeId, String formId) {
		super();
		setEmployeeId(employeeId);
		setFormId(formId);
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
	
	
}
