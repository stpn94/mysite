package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		HttpSession session = request.getSession();
		if(no != session.getAttribute("authUser") ) {
		response.setContentType("text/html;charset=UTF-8");
		SendMessage.sendMessage(response, "수정할 권한이 없습니다");
		}

		request.setAttribute("no", no);
		MvcUtil.forward("board/modify", request, response);

	}

}