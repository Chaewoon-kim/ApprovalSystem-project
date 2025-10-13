package  com.oopsw.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class EmployeeDAO {
	
	private Connection conn;
	
	public EmployeeDAO() throws ClassNotFoundException, SQLException{
		
		String className = "oracle.jdbc.driver.OracleDriver";
		Class.forName(className);
		System.out.println("Driver loading OK");
		
		
		String uri = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String id = "hr";
		String pw = "hr";
		conn = DriverManager.getConnection(uri, id, pw);
		System.out.println("2. 연결 ok");
	}
	
	
	//로그인
	public EmployeeVO login(EmployeeVO vo) {
	    EmployeeVO result = null;
	    String sql = "select employee_id, name from employee where employee_id = ? and password = ?";
	    
	    try {
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, vo.getEmployeeId());
	        pstmt.setString(2, vo.getPassword());
	        ResultSet rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	            result = new EmployeeVO();
	            result.setName(rs.getString("name"));
	        }
	        
	        rs.close();
	        pstmt.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	    return result; 
	}
	
	
	//댓글 조회
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
		
	}
	
	
	//댓글 작성
	public boolean writeComment(int documentNo, String writerId, String contents){
		boolean result = false;
		
		String sql = "insert into comments (comment_no, document_no, writer_id, comment_contents, comment_in_date, comment_up_date, del_status) "
				+ " values (comment_no_seq.nextval, ?, ?, ?, sysdate, null, null)";
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1 , documentNo);
			pstmt.setString(2, writerId);
			pstmt.setString(3, contents);
			
			if(pstmt.executeUpdate() == 1){
				result = true;
			}
			pstmt.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	

	//전체 사원 검색
	public List<EmployeeVO> getAllEmployees(){
		List<EmployeeVO> employeeList = new ArrayList<>();
		
		String sql = "select employee_id, name, department, rank from employee order by employee_id";

		try{
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
			
			EmployeeVO employee = new EmployeeVO(rs.getString("employee_id") , rs.getString("name") , rs.getString("department"),
					rs.getString("rank"));
			employeeList.add(employee);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	
		return employeeList;
	}
	
	//특정 사원 검색
	public List<EmployeeVO> getEmployee(String keyword){
		List<EmployeeVO> employeeList = new ArrayList<>();
		
		String sql = "select employee_id , name , department , rank from employee where "
				+ " name like '%' || ? || '%' or department like '%' || ? || '%' or rank like '%'|| ? || '%'";
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setString(3, keyword);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
                EmployeeVO employee = new EmployeeVO(
                    rs.getString("employee_id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getString("rank")
                );
                employeeList.add(employee);
			}
		}catch(SQLException e){
			e.printStackTrace();
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



