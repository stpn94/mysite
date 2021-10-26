package com.douzone.mysite.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "/user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result , Model model) {
		if(result.hasErrors()) {
			// BindingResult result는 Valid(검사)하고 난 결과
//			List<ObjectError> errlist = result.getAllErrors();
//			for(ObjectError error : errlist) {
//				System.out.println(error);
//			}
			
			/* Map 형태로 UserVo 객체를 키
			 * "userVo" : Object 
			 * 이렇게 된 Map을 꺼내준다.*/
			
			Map<String, Object> map = result.getModel();
//			model.addAttribute("userVo", map.get("userVo"));
			model.addAllAttributes(map);
			return "user/join";
		}
		
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "redirect:/";
	}

	@Auth
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "email", required = true, defaultValue = "") String email, 
			@RequestParam(value = "password", required = true, defaultValue = "") String password, 
			Model model) {

		return "redirect:/";
	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(
			@AuthUser UserVo authUser, 
			Model model) {

		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);

		return "user/update";

	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(
			@AuthUser UserVo authUser, 
			UserVo userVo) {

		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);

		authUser.setName(userVo.getName());

		return "redirect:/user/update";

	}

	//	UserVo userVo = userService.getUser(email, password);
	//	if(userVo == null) {
	//		model.addAttribute("result", "fail");
	//		return "user/login";
	//	}
	//	
	//	/* 인증 처리 */
	//	session.setAttribute("authUser", userVo);
}