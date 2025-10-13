package com.oopsw.model.VO;

import java.sql.Date;

public class CommentNotiVO {

	private int commentNo;
	private String recipientId;
	private Date commentReadStatus;
	private int documentNo;
	
	
	public CommentNotiVO(){}
	
	public CommentNotiVO(int commentNo , String recipientId , int documentNo){
		setCommentNo(commentNo);
		setRecipientId(recipientId);
		setDocumentNo(documentNo);
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}


	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public Date getCommentReadStatus() {
		return commentReadStatus;
	}

	public void setCommentReadStatus(Date commentReadStatus) {
		this.commentReadStatus = commentReadStatus;
	}

	public int getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}


}
