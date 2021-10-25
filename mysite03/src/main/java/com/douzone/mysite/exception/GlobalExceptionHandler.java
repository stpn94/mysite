package com.douzone.mysite.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douzone.mysite.dto.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public void HandlerException(
		HttpServletRequest request,
		HttpServletResponse response,
		Exception e) throws Exception {
		
		// 1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		/**
		 * 1. appender
		 *    - file appender /log-mysite/exception.log 
		 *      10M (Archiving 정책)
		 *      1-10(rolling)
		 *    - console appender
		 *    
		 * 2. logger - com.douzone.mysite.exception, level(error), (console+file) appender
		 *    logger - Root, level(debug), console appender    
		 */
		LOGGER.error(errors.toString());

				

		
		//2. 요청 구분
		// 만약 , JSON 요청인 경우이면 request header 의 Accept에 application/json
		// 만약 , HTML 요청인 경우이면 request header 의 Accept에 text/html
		
		String accept = request.getHeader("accept");
		
		// 정규 표현식 " .* " = 모든것
		if(accept.matches(".*application/json.*")) {
			// 3. json 응답
			
			JsonResult result  = JsonResult.fail(errors.toString());
			//jackson
			String jsonString = new ObjectMapper().writeValueAsString(result);
			//200 OK Header
			response.setStatus(HttpServletResponse.SC_OK);
			
			// byte로 보내버리자.
			OutputStream os = response.getOutputStream();
			
			//printWriter로 보내도 된다. 하지만 그대로 보내줄려고 byte로 한다.
			os.write(jsonString.getBytes("UTF-8"));
			os.close();
			
			
		} else {
			// 3. 사과페이지(정상종료)
			 request.setAttribute("exception",errors.toString());
			 request.getRequestDispatcher("/WEB-INF/views/error/exception");
		}
		
		// 3. 사과페이지(정상종료)
		request.setAttribute("exception", errors.toString());
		request
			.getRequestDispatcher("/WEB-INF/views/error/exception.jsp")
			.forward(request, response);
	}
}
