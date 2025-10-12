package com.oopsw.action;

public class ActionFactory {
	private ActionFactory(){}
	public static Action getAction(String cmd){
		Action a = null;
		switch(cmd){
		
		case "getEmployees":
			a = new GetEmployeesAction();
			break;
		case "InvertAccessPermission":
			a = new InvertAccessPermissionAction();
			break;
		
			
		case "deleteAbsence":
			a = new DeleteAbsenceAction();
			break;
			
		case "endAbsence":
			a = new EndAbsenceAction();
			break;
			
		case "modifyAbsence":
			a = new ModifyAbsenceAction();
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
		case "saveTempDoc":
			a = new SaveTempDocAction();
			break;
		case "getTempDoc":
			a = new GetTempDocAction();
		case "getTempList":
			a = new GetTempListAction();
			break;
		case "submitDoc":
			a = new SubmitDocAction();
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
