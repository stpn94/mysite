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
		String authUser = (String) session.getAttribute("authUser");
		
		System.out.println("[Loginininter] : " + authUser + " === 이메일,비밀번호 확인하고 session처리 완료");
		
		session.removeAttribute("authUser");
		session.invalidate();
		response.sendRedirect(request.getContextPath());
		return false;
	}

}
