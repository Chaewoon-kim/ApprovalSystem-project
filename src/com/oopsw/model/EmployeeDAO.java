package  com.oopsw.model;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.SqlSession;

public class EmployeeDAO {
	
	

	
	//로그인
	public EmployeeVO login(EmployeeVO vo) {
	    SqlSession session = DBCP.getSqlSessionFactory().openSession();
	    EmployeeVO result = null;
	    try {
	        result = session.selectOne("employeeMapper.login", vo);
	    } finally {
	        session.close();
	    }
	    return result;
	}
	
	
	//댓글 조회
<<<<<<< HEAD
	public List<CommentVO> getComments(int documentNumber) {
	    SqlSession session = DBCP.getSqlSessionFactory().openSession();
	    List<CommentVO> commentList = null;

	    try {
	        commentList = session.selectList("employeeMapper.getComments", documentNumber);
	    } finally {
	        session.close();
	    }

	    return commentList;
=======
	public List<CommentVO> getComments(int documentNumber){
		List<CommentVO> commentList = new ArrayList<>();
		
		
		 String sql = "select e.employee_id , e.name, c.comment_contents, c.comment_in_date " +
                 "from comments c " +
                 "join employee e on c.writer_id = e.employee_id " +
                 "where c.document_no = ? and c.del_status is null " +
                 "order by c.comment_no";
		 
		 try {
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, documentNumber); 
	            ResultSet rs = pstmt.executeQuery();
	            
	            while(rs.next()){
	            	EmployeeVO writer = new EmployeeVO();
	            	writer.setEmployeeId(rs.getString("employee_id"));
	            	writer.setName(rs.getString("name"));
	            	
	            	CommentVO comment = new CommentVO();
	                comment.setCommentContents(rs.getString("comment_contents"));
	                comment.setCommentInDate(rs.getDate("comment_in_date"));
	                
	                //comment.setWriterName(writer);
	                commentList.add(comment);
	           
	            }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		
		return commentList;
		
>>>>>>> f8dce0232f82c7d5874bb166b2be45dcff177c56
	}

	
	
	//댓글 작성
	public boolean writeComment(CommentVO comment) {
	    SqlSession session = DBCP.getSqlSessionFactory().openSession(true); // 자동 커밋 true
	    boolean result = false;

	    try {
	        int count = session.insert("employeeMapper.writeComment", comment);
	        if (count == 1) {
	            result = true;
	        }
	    } finally {
	        session.close();
	    }

	    return result;
	}
	

	//전체 사원 검색
	public List<EmployeeVO> getAllEmployees() {
        SqlSession session = DBCP.getSqlSessionFactory().openSession();
        List<EmployeeVO> employeeList = null;

        try {
            employeeList = session.selectList("employeeMapper.getAllEmployees");
        } finally {
            session.close();
        }

        return employeeList;
    }
	
	//특정 사원 검색
	public List<EmployeeVO> getEmployee(String keyword) {
        SqlSession session = DBCP.getSqlSessionFactory().openSession();
        List<EmployeeVO> employeeList = null;

        try {
            employeeList = session.selectList("employeeMapper.getEmployee", keyword);
        } finally {
            session.close();
        }

        return employeeList;
    }
	
	
	
	//댓글 알림 보내기
	public int sendNoti(CommentNotiVO notiVo){
		int result = 0;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		try{
			result = conn.insert("employeeMapper.sendNoti",notiVo);
			conn.commit();
		}finally{
			conn.close();
		}
		return result;
	}
	
	//대결 알림
	public List<AbsenceVO> getApprovalNoti(String proxyId) {
	    List<AbsenceVO> result = null;
	    SqlSession conn = DBCP.getSqlSessionFactory().openSession();
	    try {
	        result = conn.selectList("employeeMapper.getApprovalNoti", proxyId);
	    } finally {
	        conn.close();
	    }
	    return result;
	}

	//댓글 알림 (받기)
	public List<CommentVO> getCommentsNoti(String recipientId){
		List<CommentVO> result = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		try{
			result = conn.selectList("employeeMapper.getCommentsNoti" , recipientId);
		}finally{
			conn.close();
		}
		return result;
	}
	
	//결재 신청 문서 상세 조회 - 결재선 테이블에서 가져오기
	public List<ApprovalLineEmployeeVO> getApprvovalTable(int documentNumber){
		List<ApprovalLineEmployeeVO> result = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		try{
			result = conn.selectList("employeeMapper.getApprovalTable" , documentNumber);			
		}finally{
			conn.close();
		}
		
		return result;
	}
	
	//결재 신청 문서 상세 조회
	public DocumentDetailVO getDetailReport(int documentNumber){
		DocumentDetailVO result = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		try{
			result = conn.selectOne("employeeMapper.getDetailReport" , documentNumber);
		}finally{
			conn.close();
		}
		
		return result;
	}
	
	//댓글 알림 보내기
	public int sendNoti(CommentNotiVO notiVo){
		int result = 0;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		try{
			result = conn.insert("employeeMapper.sendNoti",notiVo);
			conn.commit();
		}finally{
			conn.close();
		}
		return result;
	}
	
	//대결 알림
	public List<AbsenceVO> getApprovalNoti(String proxyId) {
	    List<AbsenceVO> result = null;
	    SqlSession conn = DBCP.getSqlSessionFactory().openSession();
	    try {
	        result = conn.selectList("employeeMapper.getApprovalNoti", proxyId);
	    } finally {
	        conn.close();
	    }
	    return result;
	}

	//댓글 알림 (받기)
	public List<CommentVO> getCommentsNoti(String recipientId){
		List<CommentVO> result = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		try{
			result = conn.selectList("employeeMapper.getCommentsNoti" , recipientId);
		}finally{
			conn.close();
		}
		return result;
	}
	
	//결재 신청 문서 상세 조회 - 결재선 테이블에서 가져오기
	public List<ApprovalLineEmployeeVO> getApprvovalTable(int documentNumber){
		List<ApprovalLineEmployeeVO> result = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		try{
			result = conn.selectList("employeeMapper.getApprovalTable" , documentNumber);			
		}finally{
			conn.close();
		}
		
		return result;
	}
	
	public List<DocumentVO> getDetailReport(int documentNumber){
		List<DocumentVO> result = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		try{
			result = conn.selectList("employeeMapper.getDetailReport" , documentNumber);
		}finally{
			conn.close();
		}
		
		return result;
	}
	
}



