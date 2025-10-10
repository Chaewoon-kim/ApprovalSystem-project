package com.oopsw.action;

public class ActionFactory {
	private ActionFactory(){}
	public static Action getAction(String cmd){
		Action a = null;
		switch(cmd){
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
