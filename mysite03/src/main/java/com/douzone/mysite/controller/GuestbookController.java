package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	GuestBookService guestBookService;

	@RequestMapping("")
	public String index(Model model) {
		List<GuestBookVo> list = guestBookService.getGuestBookList();
		model.addAttribute("list", list);
		return "/guestbook/list";
	}

	@RequestMapping("add")
	public String add(GuestBookVo vo) {

		guestBookService.addGuestBook(vo);
		return "redirect:/guestbook";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") long no, Model model) {

		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public String delete(@PathVariable("no") long no, String password) {

		if (guestBookService.deleteGuestBook(no, password)) {
			return "redirect:/guestbook/";
		} else {
			return "/delete/{no}";
		}
	}
}