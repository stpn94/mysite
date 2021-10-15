package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ReplyAction start");
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		int depth = Integer.parseInt(request.getParameter("depth"));

//		Long parentNo = Long.parseLong(request.getParameter("no"));
		System.out.println("orderNo: " + orderNo);
		// Boolean result= new BoardRepository().replyUpdate(groupNo, (orderNo+1));

		String title = request.getParameter("title");
		String content = request.getParameter("contents");

		BoardVo vo = new BoardVo();

		System.out.println("orderNo: " + orderNo);
		vo.setTitle(title);
		vo.setContents(content);
		vo.setHit(0);
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo + 1);
		vo.setDepth(depth + 1);
		vo.setUserNo(authUser.getNo());

		System.out.println("vo값 이후 값 출력 : " + vo);
		new BoardDao().insert(vo);

		MvcUtil.redirect(request.getContextPath() + "/board?a=board", request, response);
	}

}
