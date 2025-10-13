package  com.oopsw.model.VO;

import java.sql.Date;

public class CommentVO {

		
		private int documentNumber;
		private String writerName;
	    private String writerId;     
	    private String commentContents;
	    private Date commentInDate;  
	    private Date commentUpDate;
	    private Date deleteStatus;

	    
	    public CommentVO() {}
	    
	    public CommentVO(int documentNumber){
	    	setDocumentNumber(documentNumber);
	    }
	    
	    public CommentVO(int documentNumber , String writerId , String commentContents){
	    	setDocumentNumber(documentNumber);
	    	setwriterId(writerId);
	    	setCommentContents(commentContents);
	    }
	    
	    


		public String getWriterName() {
			return writerName;
		}

		public void setWriterName(String writerName) {
			this.writerName = writerName;
		}

		
		public int getDocumentNumber() {
			return documentNumber;
		}


		public void setDocumentNumber(int documentNumber) {
			this.documentNumber = documentNumber;
		}


		public String getwriterId() {
			return writerId;
		}


		public void setwriterId(String writerId) {
			this.writerId = writerId;
		}


		public String getCommentContents() {
			return commentContents;
		}


		public void setCommentContents(String commentContents) {
			this.commentContents = commentContents;
		}


		public Date getCommentInDate() {
			return commentInDate;
		}


		public void setCommentInDate(Date commentInDate) {
			this.commentInDate = commentInDate;
		}


		public Date getCommentUpDate() {
			return commentUpDate;
		}


		public void setCommentUpDate(Date commentUpDate) {
			this.commentUpDate = commentUpDate;
		}


		public Date getDeleteStatus() {
			return deleteStatus;
		}


		public void setDeleteStatus(Date deleteStatus) {
			this.deleteStatus = deleteStatus;
		}

		@Override
		public String toString() {
			return "CommentVO [writerName=" + writerName + ", writerId="
					+ writerId + ", commentContents=" + commentContents + ", commentInDate=" + commentInDate
					+ "]";
		}
	    
	    

	  
	
}
