package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.douzone.mysite.vo.UserVo;

/* ArgumentResolver
 * 아규먼트리졸버는
 * @RequestParam 이라는 것이 어떻게 작동하는지 확인가능하고
 * 그걸 알면 @RequestParam 같은 걸 커스텀 해서 만들수 있다.*/

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer, 
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) 
					throws Exception {
		
		if (supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED; //아규먼트 해결되지않음
		}

		//톰켓에 있는 request 내놔라
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		//세션 불러오기
		HttpSession session = request.getSession();
		// 세션이 없으면 null 이다
		if (session == null) {
			return null;
		}

		return session.getAttribute("authUser");
	}

	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);

		// @AuthUser가 안붙어 있으면,
		// null이면 안붙어있는거
		if (authUser == null) {
			return false;
		}
		
		// Type 체크
		// 파라미터 타입()이 UserVo가 아니면,
		// parameter.getParameterType()와 UserVo.class 다르면
		if (parameter.getParameterType().equals(UserVo.class) == false) {
			return false;
		}

		return true;
	}
}
