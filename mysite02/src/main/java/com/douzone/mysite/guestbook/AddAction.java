package com.douzone.mysite.guestbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestBookDao;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class AddAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String message = request.getParameter("message");
		String regdate = format1.format(time);

		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		vo.setRegDate(regdate);
		new GuestBookDao().insert(vo);

		// 2 redirect 응답
		MvcUtil.redirect(request.getContextPath() + "/guestbook?a=guestbook", request, response);
		
	}
}
