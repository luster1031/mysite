package com.poscoict.mysite.mvc.board;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("writeform".equals(actionName)) {
			action = new writeFormAction();
		}else if("write".equals(actionName)) {
			action = new writeAction();
		}else if("view".equals(actionName)) {
			action = new viewAction();
		}else if("delete".equals(actionName)) {
			action = new deleteAction();
		}else if("updateform".equals(actionName)) {
			action = new updateFormAction();
		}else if("update".equals(actionName)) {
			action = new updateAction();
		}else if("commentform".equals(actionName)) {
			action = new commenFormtAction();
		}else if("comment".equals(actionName)) {
			action = new commenAction();
		}else {
			action = new ListAction();
		}
		
		return action;
	}

}