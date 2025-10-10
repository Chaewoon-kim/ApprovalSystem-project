package com.oopsw.model;

public class DefaultApprovalLineVO {
	private int defaultApprovalLineNo;
	private String formId;
	private String rank;
	private int order;
	
	public DefaultApprovalLineVO(){}
	
	public DefaultApprovalLineVO(int defaultApprovalLineNo, String formId, String rank, int order) {
		setDefaultApprovalLineNo(defaultApprovalLineNo);
		setFormId(formId);
		setRank(rank);
		setOrder(order);
	}
	public int getDefaultApprovalLineNo() {
		return defaultApprovalLineNo;
	}
	public void setDefaultApprovalLineNo(int defaultApprovalLineNo) {
		this.defaultApprovalLineNo = defaultApprovalLineNo;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "DefaultApprovalLine [defaultApprovalLineNo=" + defaultApprovalLineNo + ", formId=" + formId + ", rank="
				+ rank + ", order=" + order + "]";
	}
}
