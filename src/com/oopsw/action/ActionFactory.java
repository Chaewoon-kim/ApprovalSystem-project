package com.oopsw.action;

import com.oopsw.action.absence.AddAbsenceAction;
import com.oopsw.action.absence.DeleteAbsenceAction;
import com.oopsw.action.absence.EndAbsenceAction;
import com.oopsw.action.absence.GetAbsenceProxyListAction;
import com.oopsw.action.absence.ModifyAbsenceAction;
import com.oopsw.action.approve.ApprovalProcessAction;
import com.oopsw.action.approve.GetEndListAction;
import com.oopsw.action.approve.GetWaitListAction;
import com.oopsw.action.draft.GetApprovalStatusAction;
import com.oopsw.action.draft.GetReqListAction;
import com.oopsw.action.draft.GetTempDocAction;
import com.oopsw.action.draft.GetTempListAction;
import com.oopsw.action.draft.SaveTempDocAction;
import com.oopsw.action.draft.SetFormAction;
import com.oopsw.action.draft.SubmitDocAction;
import com.oopsw.action.employee.GetAllEmployeesAction;
import com.oopsw.action.employee.GetDetailReportAction;
import com.oopsw.action.employee.GetDetailReportUIAction;
import com.oopsw.action.employee.GetNotiAction;
import com.oopsw.action.employee.LoginUIAction;
import com.oopsw.action.manager.GetEmployeesAction;
import com.oopsw.action.manager.InvertAccessPermissionAction;

public class ActionFactory {
	private ActionFactory(){}
	public static Action getAction(String cmd){
		Action a = null;
		switch(cmd){
		
		case "loginUI":
		case "mainUI":
			a = new LoginUIAction();
			break;
		
		case "getAllEmployees":
			a = new GetAllEmployeesAction();
			break;
		case "getEmployees": 
			a = new GetEmployeesAction();
			break;
		case "invertAccessPermission":
			a = new InvertAccessPermissionAction();
			break;
		case "getDetailReport":
			a = new GetDetailReportAction();
			break;
		
		case "getDetailReportUIAction":
			a = new GetDetailReportUIAction();
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
			a = new GetNotiAction();
			break;
		case "getApprovalStatus":
			a = new GetApprovalStatusAction();
			break;
		case "saveTempDoc":
			a = new SaveTempDocAction();
			break;
		case "getTempDoc":
			a = new GetTempDocAction();
			break;
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
		if(a == null){
			a = new LoginUIAction();
			
		}
		return a;
	}
}
