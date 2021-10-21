package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	
	@Autowired
	GalleryService galleryService;
	
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping("")
	public String index(Model model) {
		
		List<GalleryVo> list = galleryService.findAllList();		
		model.addAttribute("list",list);
		return "gallery/index";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable(value ="no") int no) {		
		
		
		galleryService.deleteNo(no);
		return "redirect:/gallery";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file,  GalleryVo galleryVo) {
		String url = fileUploadService.restore(file);
		galleryVo.setUrl(url);

		galleryService.insert(galleryVo);
		
		return "redirect:/gallery";
	}
	
	

}
