package  com.oopsw.model.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.oopsw.exception.DatabaseTransactionException;
import com.oopsw.model.DBCP;
import com.oopsw.model.VO.AlarmVO;
import com.oopsw.model.VO.ApprovalLineVO;
import com.oopsw.model.VO.ApprovalStatusVO;
import com.oopsw.model.VO.DefaultLineVO;
import com.oopsw.model.VO.FormVO;
import com.oopsw.model.VO.DocumentVO;
import com.oopsw.model.VO.GetDefaultLineVO;
import com.oopsw.model.VO.GetListVO;
import com.oopsw.model.VO.TempDocumentVO;

public class DrafterDAO {

	public List<DocumentVO> getReqList(GetListVO getListVO) {
		List<DocumentVO> list = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		list = conn.selectList("drafterMapper.getReqList", getListVO);
		conn.close();
		return list;
	}

	public FormVO setForm(String formId) {
		FormVO result = null;
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

	public int addDoc(DocumentVO documentVO, SqlSession conn) throws DatabaseTransactionException {
		int count = conn.insert("drafterMapper.addDoc", documentVO);
		if(count != 1) throw new DatabaseTransactionException("문서 등록 실패: 예상과 다른 행 수(" + count + ")가 반영됨.");
		return documentVO.getDocumentNo();
	}

	public int addApprovers(ApprovalLineVO approvalLineVO, SqlSession conn) throws DatabaseTransactionException {
		int count = conn.insert("drafterMapper.addApprovers", approvalLineVO);
		if(count != 1) throw new DatabaseTransactionException("결재자 등록 실패: 예상과 다른 행 수(" + count + ")가 반영됨.");
		return approvalLineVO.getApprovalLineNo();
	}

	public boolean sendFirstReqNoti(int firstApprovalLineNo, SqlSession conn) throws DatabaseTransactionException {
		int count = conn.insert("drafterMapper.sendFirstReqNoti", firstApprovalLineNo);
		if(count != 1) throw new DatabaseTransactionException("알림 등록 실패: 예상과 다른 행 수(" + count + ")가 반영됨.");
		return true;
	}

	public int saveTempDoc(DocumentVO documentVO, SqlSession conn) throws DatabaseTransactionException {
		int count = conn.insert("drafterMapper.saveTempDoc", documentVO);
		if(count != 1) throw new DatabaseTransactionException("문서 임시저장 실패: 예상과 다른 행 수(" + count + ")가 반영됨.");
		return documentVO.getDocumentNo();
	}

	public List<TempDocumentVO> getTempList(GetListVO getListVO) {
		List<TempDocumentVO> list = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		list = conn.selectList("drafterMapper.getTempList", getListVO);
		conn.close();
		return list;
	}

	public boolean editTempDoc(DocumentVO documentVO, SqlSession conn) throws DatabaseTransactionException {
		int count = conn.update("drafterMapper.editTempDoc", documentVO);
		if(count != 1) throw new DatabaseTransactionException("문서 임시저장 실패: 예상과 다른 행 수(" + count + ")가 반영됨.");
			
		return true;
	}

	public boolean submitTempDoc(DocumentVO documentVO, SqlSession conn) throws DatabaseTransactionException {
		int count = conn.update("drafterMapper.submitTempDoc", documentVO);
		if(count != 1) throw new DatabaseTransactionException("문서 등록 실패: 예상과 다른 행 수(" + count + ")가 반영됨.");
		return true;
	}

	public int removeApprovers(int documentNo, SqlSession conn) throws DatabaseTransactionException {
		int count = conn.delete("drafterMapper.removeApprovers", documentNo);
		if(count == 0) throw new DatabaseTransactionException("결재자 삭제 실패: 예상과 다른 행 수(" + count + ")가 반영됨.");
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
	
	public List<AlarmVO> getUnReadApprovalProcessNoti(GetListVO getListVO) {
		List<AlarmVO> list = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		list = conn.selectList("drafterMapper.getUnReadApprovalProcessNoti", getListVO);
		conn.close();
		return list;
	}

}
