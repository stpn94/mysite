package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// 새로운 객체 생성하지 말고 주입받은거 가져오기
		
		UserVo authUser = userService.getUser(email, password);
		
		if (authUser == null) {
			
			request.setAttribute("email", email);
			request.setAttribute("result", "fail");
			request
				.getRequestDispatcher("/WEB-INF/views/user/login.jsp")
				.forward(request, response);
			
			System.out.println("[Loginininter] : " + authUser + " === 이메일,비밀번호 확인실패 로그인 폼으로");
			
			return false;
		}

		// session 처리
		System.out.println("[Loginininter] : " + authUser + " === 이메일,비밀번호 확인하고 session처리 완료");

		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());
		return false;
	}

}
