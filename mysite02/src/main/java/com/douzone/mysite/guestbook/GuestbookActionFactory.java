package com.douzone.mysite.guestbook;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("delete".equals(actionName)) {
			action = new DeleteAction();
			System.out.println("delete");
		} else if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
			System.out.println("deleteform");
		} else if ("add".equals(actionName)) {
			action = new AddAction();
			System.out.println("add");
		} else if ("guestbook".equals(actionName)) {
			action = new IndexAction();
			System.out.println("guestbook");
		}
		return action;
	}

}
