package com.douzone.mysite.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.dao.GuestBookDao;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class IndexAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* default request(action) */
		// 1. 요청처리
		List<GuestBookVo> list = new GuestBookDao().findAll();
		// 2. request범위에 데이터(객체) 저장
		request.setAttribute("list", list);
		// 3. view로 포워딩
		MvcUtil.forward("guestbook/list", request, response);

	}
}
