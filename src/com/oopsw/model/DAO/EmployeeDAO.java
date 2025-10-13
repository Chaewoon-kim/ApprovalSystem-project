package  com.oopsw.model.DAO;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.oopsw.model.DBCP;
import com.oopsw.model.VO.AbsenceVO;
import com.oopsw.model.VO.ApprovalLineEmployeeVO;
import com.oopsw.model.VO.CommentNotiVO;
import com.oopsw.model.VO.CommentVO;
import com.oopsw.model.VO.DocumentDetailVO;
import com.oopsw.model.VO.EmployeeVO;

public class EmployeeDAO {
	
	

	
	//�α���
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
	
	
	//��� ��ȸ
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

	
	
	//��� �ۼ�
	public boolean writeComment(CommentVO comment) {
	    SqlSession session = DBCP.getSqlSessionFactory().openSession(true); // �ڵ� Ŀ�� true
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
	

	//��ü ��� �˻�
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
	
	//Ư�� ��� �˻�
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
	
	
	
	//��� �˸� ������
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
	
	//��� �˸�
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

	//��� �˸� (�ޱ�)
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
	
	//���� ��û ���� �� ��ȸ - ���缱 ���̺��� ��������
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
	
	//���� ��û ���� �� ��ȸ
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
	
}



