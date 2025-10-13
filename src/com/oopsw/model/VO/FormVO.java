package com.oopsw.model.VO;

public class FormVO {
	private String formId;
	private String managerId;
	private String formCategory;
	private String formName;
	private String formContent;
	private String formDescription;
	private char formUsage;
	private int page;
	private String keyword;
	
	public FormVO() {
		setPage(1);
	}
	
	public FormVO(String formId, String managerId, String formCategory, String formName, String formContent,
			String formDescription, char formUsage) {
		this();
		setFormId(formId);
		setManagerId(managerId);
		setFormCategory(formCategory);
		setFormName(formName);
		setFormContent(formContent);
		setFormDescription(formDescription);
		setFormUsage(formUsage);
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getFormCategory() {
		return formCategory;
	}

	public void setFormCategory(String formCategory) {
		this.formCategory = formCategory;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormContent() {
		return formContent;
	}

	public void setFormContent(String formContent) {
		this.formContent = formContent;
	}

	public String getFormDescription() {
		return formDescription;
	}

	public void setFormDescription(String formDescription) {
		this.formDescription = formDescription;
	}

	public char getFormUsage() {
		return formUsage;
	}

	public void setFormUsage(char formUsage) {
		this.formUsage = formUsage;
	}

	@Override
	public String toString() {
		return "FormVO [formId=" + formId + ", managerId=" + managerId + ", formCategory=" + formCategory
				+ ", formName=" + formName + ", formContent=" + formContent + ", formDescription=" + formDescription
				+ ", formUsage=" + formUsage + "]";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
