package test.com.oopsw.model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.oopsw.model.AbsenceListVO;
import com.oopsw.model.AbsenceVO;
import com.oopsw.model.AlarmVO;
import com.oopsw.model.ApprovalLineVO;
import com.oopsw.model.ApproverDAO;
import com.oopsw.model.ApproverListVO;
import com.oopsw.model.DBCP;
import com.oopsw.model.DocumentVO;

public class ApproverDAOTest {
	
	static ApproverDAO dao;
	
	@BeforeClass
	public static void start() throws Exception{
		dao = new ApproverDAO();
	}
	
	//@Test
	public void testCheckAbsence() {
	    String approverId = "E25-003"; // 존재하는 결재자 ID로 테스트

	    AbsenceVO vo = dao.checkAbsence(approverId);

	    if (vo != null) {
	        System.out.println("부재자 ID: " + vo.getAbsenteeId());
	        System.out.println("대결자 ID: " + vo.getProxyId());
	        System.out.println("부재 시작일: " + vo.getAbsenceStartDate());
	        System.out.println("부재 종료일: " + vo.getAbsenceEndDate());
	        System.out.println("부재 사유: " + vo.getAbsenceReason());
	        System.out.println("사용 여부: " + vo.getAbsenceUsage());
	    } else {
	        System.out.println("현재 부재 중이 아닙니다.");
	    }

	}

	
	//@Test
	public void testGetApprovalReqNoti() {
        String approverId = "E25-013"; 

        List<AlarmVO> list = dao.getApprovalReqNoti(approverId);

        assertNotNull(list);
        assertTrue(list.size() >= 0);

        if (!list.isEmpty()) {
            AlarmVO first = list.get(0);
            System.out.println("문서번호: " + first.getDocumentNo());
            System.out.println("문서제목: " + first.getTitle());
            System.out.println("결재상태: " + first.getStatus());
            System.out.println("읽음상태: " + first.getReadStatus());
            System.out.println("알림날짜: " + first.getNotiInDate());
        } 
    }
	
	
	//@Test
    public void testDeleteAbsence() {
        int absenceDateNo = 13; 
        assertTrue(dao.deleteAbsence(absenceDateNo));
    }
	
	
	//@Test
    public void testRevokeAbsence() {
        int absenceDateNo = 10; 
        assertTrue(dao.revokeAbsence(absenceDateNo));
    }
	
	//@Test
	public void testUpdateAbsence() {
        AbsenceVO vo = new AbsenceVO();

        vo.setAbsenceDateNo(3);
        vo.setProxyId("E25-004");
        vo.setAbsenceStartDate(Date.valueOf("2025-10-14"));
        vo.setAbsenceEndDate(Date.valueOf("2025-10-16"));
        vo.setAbsenceReason("출장 일정 변경");

        Date today = new Date(System.currentTimeMillis());
        if (today.before(vo.getAbsenceStartDate())) {
            vo.setAbsenceUsage("대기");
        } else if (today.after(vo.getAbsenceEndDate())) {
            vo.setAbsenceUsage("종료");
        } else {
            vo.setAbsenceUsage("위임");
        }

        assertTrue(dao.updateAbsence(vo));
    }
	
	//@Test
    public void testAddAbsence() {
        AbsenceVO vo = new AbsenceVO();

        vo.setAbsenteeId("E25-007"); // 부재자
        vo.setProxyId("E25-004");    // 대결자
        vo.setAbsenceStartDate(Date.valueOf("2025-10-14"));
        vo.setAbsenceEndDate(Date.valueOf("2025-10-16"));
        vo.setAbsenceReason("출장으로 인한 부재");
        vo.setAbsenceUsage("대기중");

        assertTrue(dao.addAbsence(vo));
    }
	
	//@Test
    public void testGetAbsenceList() {
        String absenteeId = "E25-007"; 
        List<AbsenceListVO> list = dao.getAbsenceList(absenteeId);

        assertNotNull(list);
        
        if (!list.isEmpty()) {
            AbsenceListVO first = list.get(0);
            System.out.println("시작일: " + first.getAbsenceStartDate());
            System.out.println("종료일: " + first.getAbsenceEndDate());
            System.out.println("대결자 이름: " + first.getProxyName());
            System.out.println("대결자 직급: " + first.getProxyRank());
            System.out.println("부재 사유: " + first.getAbsenceReason());
            System.out.println("사용여부: " + first.getAbsenceUsage());
        }
    }

    //@Test
    public void testGetProxyList() {
        String proxyId = "E25-004"; 
        List<AbsenceListVO> list = dao.getProxyList(proxyId);

        assertNotNull(list);
        if (!list.isEmpty()) {
            AbsenceListVO first = list.get(0);
            System.out.println("시작일: " + first.getAbsenceStartDate());
            System.out.println("종료일: " + first.getAbsenceEndDate());
            System.out.println("위임자 이름: " + first.getAbsenteeName());
            System.out.println("위임자 직급: " + first.getAbsenteeRank());
            System.out.println("부재 사유: " + first.getAbsenceReason());
        }
    }
    
	//@Test
    public void testGetEndList() {
        String approverId = "E25-008"; 

        List<ApproverListVO> list = dao.getEndList(approverId);

        assertNotNull("결과 리스트가 null이면 안 됨", list);
        assertTrue("리스트 크기는 0 이상이어야 함", list.size() >= 0);

        if (!list.isEmpty()) {
            ApproverListVO first = list.get(0);
            System.out.println("문서번호: " + first.getDocumentNo());
            System.out.println("제목: " + first.getTitle());
            System.out.println("기안부서: " + first.getDepartment());
            System.out.println("결재상태: " + first.getProcessStatus());
        }
    }
	
	//@Test
    public void testGetWaitList() {
        String approverId = "E25-013"; 

        List<ApproverListVO> list = dao.getWaitList(approverId);

        assertNotNull(list);
        assertTrue(list.size() >= 0);

        if (!list.isEmpty()) {
            ApproverListVO first = list.get(0);
            System.out.println("문서번호: " + first.getDocumentNo());
            System.out.println("제목: " + first.getTitle());
            System.out.println("기안자: " + first.getName());
            System.out.println("결재상태: " + first.getApprovalStatus());
        }
    }
	
	//@Test
	public void updateApprovalTest(){
		System.out.println(new ApproverDAO().updateApproval(new ApprovalLineVO(0, 20, "E25-014", 0, "반려 test", null, "반려")));
	}
	
	//@Test
	public void updateNextApproverToWaitTest() {
		ApprovalLineVO vo = new ApprovalLineVO();
		vo.setDocumentNo(20);
		vo.setLineOrder(2); 

		boolean result = dao.updateNextApproverToWait(vo);
		System.out.println("updateNextApproverToWait 결과 : " + result);
		// 변경될 row가 없을 수도 있으므로 assertTrue 대신, 출력
	}

	//@Test
	public void findNextApprovalLineNoTest() {
		ApprovalLineVO vo = new ApprovalLineVO();
		vo.setDocumentNo(20);
		vo.setLineOrder(1);

		int nextLineNo = dao.findNextApprovalLineNo(vo);
		System.out.println("findNextApprovalLineNo 결과 : " + nextLineNo);
		assertTrue(nextLineNo >= -1);
	}

	//@Test
	public void sendRequestNotiTest() {
		ApprovalLineVO vo = new ApprovalLineVO();
		vo.setApprovalLineNo(27); 
		boolean result = dao.sendRequestNoti(vo);
		System.out.println("sendRequestNoti 결과 : " + result);
		assertTrue(result);
	}

	//@Test      
	public void sendProcessNotiTest() {
		ApprovalLineVO vo = new ApprovalLineVO();
		vo.setDocumentNo(20); 
		boolean result = dao.sendProcessNoti(vo);
		System.out.println("sendProcessNoti 결과 : " + result);
		assertTrue(result);
	}
	
	//@Test
    public void updateDocRejectTest() {
        DocumentVO doc = new DocumentVO();
        doc.setDocumentNo(4);
        boolean result = dao.updateDocReject(doc);

        System.out.println("반려 처리 결과: " + result);
        assertTrue(result); 
    }

    @Test
    public void updateDocCompleteTest() {
        DocumentVO doc = new DocumentVO();
        doc.setDocumentNo(1); 
        boolean result = dao.updateDocComplete(doc);
        assertTrue(result);
    }



	//@Test
	public void DBCPtest(){
		System.out.println(DBCP.getSqlSessionFactory());
		System.out.println(DBCP.getSqlSessionFactory().openSession());
		System.out.println(DBCP.getSqlSessionFactory().openSession().getConnection());
	}


}

