package com.douzone.mysite.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {
	/*
	 * /board 딱 오면 여기로 오고
	 */
	@Override
	public Action getAction(String actionName) {
		Action action = null;

		/* 하나하나씩 */
		if ("view".equals(actionName)) { //글 내용 보기
			action = new ViewAction();
		} else if ("writeform".equals(actionName)) { //글쓰기 누르면 write로 보내준다.
			action = new WriteFormAction();
		} else if ("write".equals(actionName)) { //Data들 모두 넣고 입력하면 DB에 입력하는 Action으로 간다.
			action = new WriteAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if ("replyform".equals(actionName)) {
			action = new ReplyFormAction();
		} else if ("reply".equals(actionName)) {
			action = new ReplyAction();
		} else if ("page".equals(actionName)) {
			action = new PagingAction();
		} else if ("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if ("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if ("filedown".equals(actionName)) {
			action = new FileDownAction();
		}

		else {

			/* 게시판 리스트 메인화면 */
			action = new ListAction();
		}
		return action;
	}

}
