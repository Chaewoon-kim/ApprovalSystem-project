package  com.oopsw.model.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.oopsw.model.DBCP;
import com.oopsw.model.GetListVO;
import com.oopsw.model.VO.AlarmVO;
import com.oopsw.model.VO.ApprovalLineVO;
import com.oopsw.model.VO.ApprovalStatusVO;
import com.oopsw.model.VO.DefaultLineVO;
import com.oopsw.model.VO.DocumentFormVO;
import com.oopsw.model.VO.DocumentVO;
import com.oopsw.model.VO.GetDefaultLineVO;
import com.oopsw.model.VO.TempDocumentVO;

public class DrafterDAO {

	public List<DocumentVO> getReqList(GetListVO getListVO) {
		List<DocumentVO> list = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		list = conn.selectList("drafterMapper.getReqList", getListVO);
		conn.close();
		return list;
	}

	public DocumentFormVO setForm(String formId) {
		DocumentFormVO result = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		result = conn.selectOne("drafterMapper.setForm", formId);
		conn.close();
		return result;
	}

	public List<DefaultLineVO> getDefaultLine(GetDefaultLineVO getDefaultLineVO) {
		List<DefaultLineVO> line = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		line = conn.selectList("drafterMapper.getDefaultLine", getDefaultLineVO);
		conn.close();
		return line;
	}

	public int addDoc(DocumentVO documentVO) {
		int newDocumentNo = 0;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		int count = conn.insert("drafterMapper.addDoc", documentVO);
		if(count == 1){
			 newDocumentNo = documentVO.getDocumentNo();
			 conn.commit();
		}else{
			conn.rollback();
		}
		conn.close();
		return newDocumentNo;
	}

	public int addApprovers(ApprovalLineVO approvalLineVO) {
		int newApprovalLineNo = 0;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		int count = conn.insert("drafterMapper.addApprovers", approvalLineVO);
		if(count == 1){
			newApprovalLineNo = approvalLineVO.getApprovalLineNo();
			conn.commit();
		}else{
			conn.rollback();
		}
		conn.close();
		return newApprovalLineNo;
	}

	public int sendFirstReqNoti(int firstApprovalLineNo) {
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		int count = conn.insert("drafterMapper.sendFirstReqNoti", firstApprovalLineNo);
		if(count == 1){
			conn.commit();
		}else{
			conn.rollback();
		}
		conn.close();
		return count;
	}

	public int saveTempDoc(DocumentVO documentVO) {
		int newDocumentNo = 0;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		int count = conn.insert("drafterMapper.saveTempDoc", documentVO);
		if(count == 1){
			 newDocumentNo = documentVO.getDocumentNo();
			 conn.commit();
		}else{
			conn.rollback();
		}
		conn.close();
		return newDocumentNo;
	}

	public List<TempDocumentVO> getTempList(String employeeId) {
		List<TempDocumentVO> list = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		list = conn.selectList("drafterMapper.getTempList", employeeId);
		conn.close();
		return list;
	}

	public boolean editTempDoc(DocumentVO documentVO) {
		boolean result = false;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		int count = conn.update("drafterMapper.editTempDoc", documentVO);
		if(count == 1){
			result = true;
			conn.commit();
		}else{
			conn.rollback();
		}
		conn.close();
		return result;
	}

	public boolean submitTempDoc(DocumentVO documentVO) {
		boolean result = false;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		int count = conn.update("drafterMapper.submitTempDoc", documentVO);
		if(count == 1){
			result = true;
			conn.commit();
		}else{
			conn.rollback();
		}
		conn.close();
		return result;
	}

	public int removeApprovers(int documentNo) {
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		int count = conn.delete("drafterMapper.removeApprovers", documentNo);
		if(count > 0){
			conn.commit();
		}else{
			conn.rollback();
		}
		conn.close();
		return count;
		
	}

	public List<ApprovalStatusVO> getApprovalStatus(int documentNo) {
		List<ApprovalStatusVO> status = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		status = conn.selectList("drafterMapper.getApprovalStatus", documentNo);
		conn.close();
		return status;
	}

	public List<AlarmVO> getApprovalProcessNoti(GetListVO getListVO) {
		List<AlarmVO> list = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		list = conn.selectList("drafterMapper.getApprovalProcessNoti", getListVO);
		conn.close();
		return list;
	}

}
