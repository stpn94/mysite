package com.douzone.mysite.main;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}
