package com.oopsw.action;

public class ActionFactory {
	private ActionFactory(){}
	public static Action getAction(String cmd){
		Action a = null;
		switch(cmd){
		
		case "deleteAbsence":
			a = new DeleteAbsenceAction();
			break;
			
		case "revokeAbsence":
			a = new RevokeAbsenceAction();
			break;
			
		case "updateAbsence":
			a = new UpdateAbsenceAction();
			break;
			
		case "addAbsence":
			a = new AddAbsenceAction();
			break;
		
		case "getAbsenceProxyListAction":
			a = new GetAbsenceProxyListAction();
			break;
		
		case "approvalProcess":
			a = new ApprovalProcessAction();
			break;
		
		case "getWaitList":
			a = new GetWaitListAction();
			break;
			
		case "getEndList":
			a = new GetEndListAction();
			break;
		
			
		case "getApprovalProcessNoti":
			a = new GetApprovalProcessNoti();
			break;
		case "getApprovalStatus":
			a = new GetApprovalStatusAction();
			break;
		case "submitTempDoc":
			a = new SubmitTempDocAction();
			break;
		case "getTempDoc":
			a = new GetTempDocAction();
		case "getTempList":
			a = new GetTempListAction();
			break;
		case "addDoc":
			a = new AddDocAction();
			break;
		case "getDefaultLine":
			a = new GetDefaultLineAction();
			break;
		case "getReqList":
			a = new GetReqListAction();
			break;
		case "setForm":
			a = new SetFormAction();
			break;
		}
		return a;
	}
}
