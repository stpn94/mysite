package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Controller
@RequestMapping("/admin")
@Auth(role = "ADMIN")
public class AdminController {

	@Autowired
	SiteService siteSevice;

	@Autowired
	private GalleryService galleryService;

	@RequestMapping("")
	public String main(Model model) {

		SiteVo siteVo = siteSevice.findOne();
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(
			@RequestParam("file") MultipartFile file, 
			@RequestParam(value = "email", required = true, defaultValue = "") String email, 
			Model model) {

		String url = galleryService.restore(file);

		model.addAttribute("url", url);

		return "redirect:/admin";
	}

	@RequestMapping(value = "/main/update", method = RequestMethod.POST)
	public String updateMain(
			@ModelAttribute SiteVo vo, 
			@RequestParam("file") MultipartFile file) {

		String url = galleryService.restore(file);
		vo.setProfile(url);
		siteSevice.update(vo);
		return "redirect:/admin";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}

}
