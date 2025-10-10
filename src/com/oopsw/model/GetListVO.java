package com.oopsw.model;

public class GetListVO {
	private String employeeId;
	private String processStatus;
	private int page;
	
	public GetListVO(String employeeId, String processStatus, int page) {
		super();
		setEmployeeId(employeeId);
		setProcessStatus(processStatus);
		setPage(page);
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}
