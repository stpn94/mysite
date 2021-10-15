package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ReplyFormAction start");
		Long no = Long.valueOf(request.getParameter("no"));

		BoardVo vo = new BoardDao().findByNo(no);

		System.out.println("vo값 출력 : " + vo);

		request.setAttribute("vo", vo);

		MvcUtil.forward("board/reply", request, response);

	}

}
