package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {
	
	@Autowired
	SiteRepository siteRepository;

	public SiteVo findOne() {
		return siteRepository.findOne();
	}

	public void update(SiteVo vo) {
		
		siteRepository.update(vo);
		
	}

	
	

}
