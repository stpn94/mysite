package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class PagingAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PagingAction start");
		int currentPage = Integer.parseInt(request.getParameter("page"));

		System.out.println(currentPage);

		MvcUtil.redirect(request.getContextPath() + "/board?a=board", request, response);

	}

}
