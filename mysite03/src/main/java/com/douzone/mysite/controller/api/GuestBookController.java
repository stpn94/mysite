	package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller("guestbookControllerApi")
@RequestMapping("/api/guestbook")
public class GuestBookController {

	@Autowired
	GuestbookService guestbookService;

	//
	@ResponseBody
	@RequestMapping("/list")
	public JsonResult list(@RequestParam(value = "no", required = true, defaultValue="-1") Long no) {

//		List<GuestbookVo> list = guestbookService.getMessageList(guestbookService.getMaxNo() - Long.parseLong(no));
//		if (list.get(0) == null) {
//			list = guestbookService.getMessageList(guestbookService.getMaxNo() - (Long.parseLong(no) - 2L));
//		}
		
		List<GuestbookVo> list = guestbookService.getMessageList(no);
		return JsonResult.success(list);
	}

	@ResponseBody
	@RequestMapping("/add")
	public JsonResult add(@RequestBody GuestbookVo vo) {

		guestbookService.addMessage(vo);
		System.out.println(vo.toString());
		return JsonResult.success(vo);

	}

	@ResponseBody
	@RequestMapping("delete/{no}")
	public JsonResult delete(@PathVariable("no") Long no, String password) {
		// 삭제 작업 (guestbookService)
		Long data = 0L;

		if (!guestbookService.deleteMessage(no, password)) {
			data = -1L;
			return JsonResult.success(data);
		}

		//  1. 삭제가 안된 경우
		data = -1L;

		// 2 삭제가 된 경우
		data = no;
		return JsonResult.success(data);

	}
	
	
}
