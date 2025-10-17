package com.oopsw.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.action.absence.AddAbsenceAction;
import com.oopsw.action.absence.DeleteAbsenceAction;
import com.oopsw.action.absence.EndAbsenceAction;
import com.oopsw.action.absence.GetAbsenceProxyListAction;
import com.oopsw.action.absence.GetAddAbsenceUIAction;
import com.oopsw.action.absence.ModifyAbsenceAction;
import com.oopsw.action.approve.ApprovalProcessAction;
import com.oopsw.action.approve.GetEndListAction;
import com.oopsw.action.approve.GetWaitListAction;
import com.oopsw.action.draft.EditLineAction;
import com.oopsw.action.draft.GetApprovalProcessNoti;
import com.oopsw.action.draft.GetApprovalStatusAction;
import com.oopsw.action.draft.GetReqListAction;
import com.oopsw.action.draft.GetReqListUIAction;
import com.oopsw.action.draft.GetTempDocAction;
import com.oopsw.action.draft.GetTempListAction;
import com.oopsw.action.draft.GetTempListUIAction;
import com.oopsw.action.draft.SaveTempDocAction;
import com.oopsw.action.draft.SetFormAction;
import com.oopsw.action.draft.SubmitDocAction;
import com.oopsw.action.employee.GetDetailReportAction;
import com.oopsw.action.employee.GetNotiAction;
import com.oopsw.action.employee.LoginUIAction;
import com.oopsw.action.employee.LoginAction;
import com.oopsw.action.employee.LoginAction;
import com.oopsw.action.employee.LoginUIAction;

import com.oopsw.action.manager.AddFormAction;
import com.oopsw.action.manager.GetAllEmployeesAction;
import com.oopsw.action.manager.GetEmployeeCount;
import com.oopsw.action.manager.GetEmployeesAction;
import com.oopsw.action.manager.GetFormAction;
import com.oopsw.action.manager.InvertAccessPermissionAction;
import com.oopsw.action.manager.InvertFormUsageAction;

public class ActionFactory {
	private ActionFactory(){}
	public static Action getAction(String cmd){
		Action a = null;
		switch(cmd){
		
		case "getDetailReport":
			a = new GetDetailReportAction();
			break;
		
			
		case "getModifyUI":
			a = new GetModifyUIAction();
			break;
		
		case "getAddAbsenceUI":
			a = new GetAddAbsenceUIAction();
			break;
			
			
		case "editLine":
			a = new EditLineAction();
			break;
		case "loginAction":
			a = new LoginAction();
			break;
		case "loginUI":
		case "mainUI":
			a = new LoginUIAction();
			break;
		case "getAllEmployees":
			a = new GetAllEmployeesAction();
			break;
		case "getEmployeeCount":
			a = new GetEmployeeCount();
			break;
		case "invertFormUsage":
			a = new InvertFormUsageAction();
			break;
		case "getForms":
			a = new GetFormAction();
			break;
		case "addForms":
			a = new AddFormAction();
			break;
		case "getEmployees": 
			a = new GetEmployeesAction();
			break;
		case "invertAccessPermission":
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
		case "getAbsenceProxyList":
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
		case "getTempListUI":
			a = new GetTempListUIAction();
			break;
		case "getTempList":
			a = new GetTempListAction();
			break;
		case "submitDoc":
			a = new SubmitDocAction();
			break;
		case "getReqListUI":
			a = new GetReqListUIAction();
			break;
		case "getReqList":
			a = new GetReqListAction();
			break;
		case "setForm":
			a = new SetFormAction();
			break;
			
		default:
			a = new LoginUIAction();
			break;
		}
		return a;
	}
}

