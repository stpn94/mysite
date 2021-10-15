package com.douzone.mysite.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateAction 들어옴");

		// 접근 제어 (인증이 필요한 접근에 대한 체크)
		HttpSession session = request.getSession();
		if (session == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}

		Long userNo = authUser.getNo();
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");

		new UserDao().updateUserNamePassword(name, password, gender, userNo);
		if (session != null) {
			session.removeAttribute("authUser");
		}
		authUser.setNo(userNo);
		authUser.setName(name);
		session.setAttribute("authUser", authUser);

		MvcUtil.forward("user/updatesuccess", request, response);

	}

}
