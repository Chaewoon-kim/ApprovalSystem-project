package  com.oopsw.model.DAO;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.oopsw.model.DBCP;
import com.oopsw.model.VO.AlarmVO;
import com.oopsw.model.VO.ApprovalLineEmployeeVO;
import com.oopsw.model.VO.CommentNotiVO;
import com.oopsw.model.VO.CommentVO;
import com.oopsw.model.VO.DocumentDetailVO;
import com.oopsw.model.VO.EmployeeVO;

public class EmployeeDAO {
	
	//댓글 삭제
	   public boolean deleteComment(int commentNumber){
	      SqlSession session = DBCP.getSqlSessionFactory().openSession();
	      boolean result = false;
	      
	      try{
	         int count = session.update("employeeMapper.deleteComment" , commentNumber);
	         if(count == 1){
	            result = true;
	            session.commit();
	         }
	      }finally{
	         session.close();
	      }
	      
	      return result;
	   }
	   
	   //댓글 수정 
	   public boolean updateComment(CommentVO comment){
	      SqlSession session = DBCP.getSqlSessionFactory().openSession();
	      boolean result = false;
	      
	      try{
	         int count = session.update("employeeMapper.updateComment" , comment);
	         if(count == 1){
	            result = true;
	            session.commit();
	         }
	      }finally{
	         session.close();
	      }
	      
	      return result;
	   }
	   
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

	public String getAllNotiCount(AlarmVO vo){
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		String result = null;
		try{
			result = session.selectOne("employeeMapper.getAllNotiCount", vo);
		}finally{
			session.close();
		}
		return result;
	}
	public List<AlarmVO> getAllNoti(AlarmVO vo){
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		List<AlarmVO> result = null;
		try{
			result = session.selectList("employeeMapper.getAllNoti", vo);
		}finally{
			session.close();
		}
		return result;
	}
	
	//댓글 조회
	public List<CommentVO> getComments(int documentNumber) {
	    SqlSession session = DBCP.getSqlSessionFactory().openSession();
	    List<CommentVO> commentList = null;

	    try {
	        commentList = session.selectList("employeeMapper.getComments", documentNumber);
	    } finally {
	        session.close();
	    }

	    return commentList;
	}

	public boolean addComment(CommentVO comment) {
	    SqlSession session = DBCP.getSqlSessionFactory().openSession(true);
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
	
	public List<AlarmVO> getApprovalNoti(String proxyId) {
	    List<AlarmVO> result = null;
	    SqlSession conn = DBCP.getSqlSessionFactory().openSession();
	    try {
	        result = conn.selectList("employeeMapper.getApprovalNoti", proxyId);
	    } finally {
	        conn.close();
	    }
	    return result;
	}


	public List<AlarmVO> getCommentsNoti(String recipientId){
		List<AlarmVO> result = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		try{
			result = conn.selectList("employeeMapper.getCommentsNoti" , recipientId);
		}finally{
			conn.close();
		}
		return result;
	}
	
		public List<AlarmVO> getUnReadApprovalNoti(String proxyId) {
		    List<AlarmVO> result = null;
		    SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		    try {
		        result = conn.selectList("employeeMapper.getUnReadApprovalNoti", proxyId);
		    } finally {
		        conn.close();
		    }
		    return result;
		}

	public List<AlarmVO> getUnReadCommentsNoti(String recipientId){
		List<AlarmVO> result = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		
		try{
			result = conn.selectList("employeeMapper.getUnReadCommentsNoti" , recipientId);
		}finally{
			conn.close();
		}
		return result;
	}
	
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
	
	public boolean readProcessNoti(int notiNo){
		boolean result = false;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		result = conn.update("employeeMapper.readProcessNoti", notiNo) == 1;
		if(result)
			conn.commit();
		return result;
	}
	
	public boolean readRequestNoti(int notiNo){
		boolean result = false;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		result = conn.update("employeeMapper.readRequestNoti", notiNo) == 1;
		if(result)
			conn.commit();
		return result;
	}
	
	public boolean readCommentNoti(int notiNo){
		boolean result = false;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		result = conn.update("employeeMapper.readCommentNoti", notiNo) == 1;
		if(result)
			conn.commit();
		return result;
	}


	public boolean readAbsenceNoti(int notiNo) {
		boolean result = false;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		result = conn.update("employeeMapper.readAbsenceNoti", notiNo) == 1;
		if(result)
			conn.commit();
		return result;
	}
	
}



