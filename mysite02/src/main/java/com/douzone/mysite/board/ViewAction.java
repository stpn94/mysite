package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ViewAction start");

		Long no = Long.parseLong(request.getParameter("no"));

		BoardVo boardvo = new BoardDao().findByNo(no); // title , 내용 받아옴

		new BoardDao().updateHit(no);


		request.setAttribute("title", boardvo.getTitle()); 		// System.out.println("no:" + no);
		request.setAttribute("contents", boardvo.getContents());
		request.setAttribute("no", no);
		request.setAttribute("userNo", boardvo.getUserNo());

		MvcUtil.forward("board/view", request, response);

	}

}
