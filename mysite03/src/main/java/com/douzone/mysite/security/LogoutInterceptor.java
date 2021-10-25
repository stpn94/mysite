package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogoutInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("authUser");
		session.invalidate();
		System.out.println("[LogoutIntercepter] : authUser === 이메일,비밀번호 확인하고 session삭제 완료");		
		response.sendRedirect(request.getContextPath());
		return false;
	}

}
