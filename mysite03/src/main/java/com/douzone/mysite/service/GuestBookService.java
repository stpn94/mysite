package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookRepository guestBookRepository;

	public List<GuestBookVo> getGuestBookList() {
		return guestBookRepository.findAll();
	}

	public void addGuestBook(GuestBookVo vo) {
		guestBookRepository.insert(vo);

	}

	public boolean deleteGuestBook(long no, String password) {
		return deleteGuestBook(no, password);
	}

}
