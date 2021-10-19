package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public void HandlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		// 1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		// LOGGER.error(errors.toString()); 지금은 못해
		
		// 2. 요청 구분(지금은 안함)
		
		// 3. 사과 페이지 (정상종료)
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp");
	}

}
