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
	    String approverId = "E25-003"; // �����ϴ� ������ ID�� �׽�Ʈ

	    AbsenceVO vo = dao.checkAbsence(approverId);

	    if (vo != null) {
	        System.out.println("������ ID: " + vo.getAbsenteeId());
	        System.out.println("����� ID: " + vo.getProxyId());
	        System.out.println("���� ������: " + vo.getAbsenceStartDate());
	        System.out.println("���� ������: " + vo.getAbsenceEndDate());
	        System.out.println("���� ����: " + vo.getAbsenceReason());
	        System.out.println("��� ����: " + vo.getAbsenceUsage());
	    } else {
	        System.out.println("���� ���� ���� �ƴմϴ�.");
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
            System.out.println("������ȣ: " + first.getDocumentNo());
            System.out.println("��������: " + first.getTitle());
            System.out.println("�������: " + first.getStatus());
            System.out.println("��������: " + first.getReadStatus());
            System.out.println("�˸���¥: " + first.getNotiInDate());
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
        vo.setAbsenceReason("���� ���� ����");

        Date today = new Date(System.currentTimeMillis());
        if (today.before(vo.getAbsenceStartDate())) {
            vo.setAbsenceUsage("���");
        } else if (today.after(vo.getAbsenceEndDate())) {
            vo.setAbsenceUsage("����");
        } else {
            vo.setAbsenceUsage("����");
        }

        assertTrue(dao.updateAbsence(vo));
    }
	
	//@Test
    public void testAddAbsence() {
        AbsenceVO vo = new AbsenceVO();

        vo.setAbsenteeId("E25-007"); // ������
        vo.setProxyId("E25-004");    // �����
        vo.setAbsenceStartDate(Date.valueOf("2025-10-14"));
        vo.setAbsenceEndDate(Date.valueOf("2025-10-16"));
        vo.setAbsenceReason("�������� ���� ����");
        vo.setAbsenceUsage("�����");

        assertTrue(dao.addAbsence(vo));
    }
	
	//@Test
    public void testGetAbsenceList() {
        String absenteeId = "E25-007"; 
        List<AbsenceListVO> list = dao.getAbsenceList(absenteeId);

        assertNotNull(list);
        
        if (!list.isEmpty()) {
            AbsenceListVO first = list.get(0);
            System.out.println("������: " + first.getAbsenceStartDate());
            System.out.println("������: " + first.getAbsenceEndDate());
            System.out.println("����� �̸�: " + first.getProxyName());
            System.out.println("����� ����: " + first.getProxyRank());
            System.out.println("���� ����: " + first.getAbsenceReason());
            System.out.println("��뿩��: " + first.getAbsenceUsage());
        }
    }

    //@Test
    public void testGetProxyList() {
        String proxyId = "E25-004"; 
        List<AbsenceListVO> list = dao.getProxyList(proxyId);

        assertNotNull(list);
        if (!list.isEmpty()) {
            AbsenceListVO first = list.get(0);
            System.out.println("������: " + first.getAbsenceStartDate());
            System.out.println("������: " + first.getAbsenceEndDate());
            System.out.println("������ �̸�: " + first.getAbsenteeName());
            System.out.println("������ ����: " + first.getAbsenteeRank());
            System.out.println("���� ����: " + first.getAbsenceReason());
        }
    }
    
	//@Test
    public void testGetEndList() {
        String approverId = "E25-008"; 

        List<ApproverListVO> list = dao.getEndList(approverId);

        assertNotNull("��� ����Ʈ�� null�̸� �� ��", list);
        assertTrue("����Ʈ ũ��� 0 �̻��̾�� ��", list.size() >= 0);

        if (!list.isEmpty()) {
            ApproverListVO first = list.get(0);
            System.out.println("������ȣ: " + first.getDocumentNo());
            System.out.println("����: " + first.getTitle());
            System.out.println("��Ⱥμ�: " + first.getDepartment());
            System.out.println("�������: " + first.getProcessStatus());
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
            System.out.println("������ȣ: " + first.getDocumentNo());
            System.out.println("����: " + first.getTitle());
            System.out.println("�����: " + first.getName());
            System.out.println("�������: " + first.getApprovalStatus());
        }
    }
	
	//@Test
	public void updateApprovalTest(){
		System.out.println(new ApproverDAO().updateApproval(new ApprovalLineVO(0, 20, "E25-014", 0, "�ݷ� test", null, "�ݷ�")));
	}
	
	//@Test
	public void updateNextApproverToWaitTest() {
		ApprovalLineVO vo = new ApprovalLineVO();
		vo.setDocumentNo(20);
		vo.setLineOrder(2); 

		boolean result = dao.updateNextApproverToWait(vo);
		System.out.println("updateNextApproverToWait ��� : " + result);
		// ����� row�� ���� ���� �����Ƿ� assertTrue ���, ���
	}

	//@Test
	public void findNextApprovalLineNoTest() {
		ApprovalLineVO vo = new ApprovalLineVO();
		vo.setDocumentNo(20);
		vo.setLineOrder(1);

		int nextLineNo = dao.findNextApprovalLineNo(vo);
		System.out.println("findNextApprovalLineNo ��� : " + nextLineNo);
		assertTrue(nextLineNo >= -1);
	}

	//@Test
	public void sendRequestNotiTest() {
		ApprovalLineVO vo = new ApprovalLineVO();
		vo.setApprovalLineNo(27); 
		boolean result = dao.sendRequestNoti(vo);
		System.out.println("sendRequestNoti ��� : " + result);
		assertTrue(result);
	}

	//@Test      
	public void sendProcessNotiTest() {
		ApprovalLineVO vo = new ApprovalLineVO();
		vo.setDocumentNo(20); 
		boolean result = dao.sendProcessNoti(vo);
		System.out.println("sendProcessNoti ��� : " + result);
		assertTrue(result);
	}
	
	//@Test
    public void updateDocRejectTest() {
        DocumentVO doc = new DocumentVO();
        doc.setDocumentNo(4);
        boolean result = dao.updateDocReject(doc);

        System.out.println("�ݷ� ó�� ���: " + result);
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

