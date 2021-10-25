package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	@RequestMapping({"", "/main"})
	public String index() {
		return "main/index";
	}
	
	
//	@RequestMapping("/hello")
//	public void message(HttpServletResponse resp) throws Exception {
//		resp.setContentType("application/json; charset=UTF-8");
//		resp.getWriter().print("{\"message\":\"Hello World\"}");
//	}
	@ResponseBody
	@RequestMapping("/hello")
	public UserVo hello() {
		UserVo vo = new UserVo();
		vo.setNo(10L);
		vo.setEmail("whddbs2855@naver.com");
		vo.setName("이종윤");
		return vo;
	}

	@ResponseBody
	@RequestMapping("/msg1")
	public String message1() {
		return "안녕~~";
	}

	@ResponseBody
	@RequestMapping("/msg2")
	public UserVo message2() {
		UserVo vo = new UserVo();
		vo.setNo(1L);
		vo.setEmail("dd");
		vo.setName("dd");
		vo.setPassword("dd");
		return vo;
	}
}
