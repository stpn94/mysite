package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
@Service
public class BoardService {
	
	@Autowired
	BoardRepository boardRepository;
	
	public Boolean insertReply(BoardVo vo) {
		 
		return boardRepository.insertReply(vo);
	}
	
	public BoardVo findByNo(Long BoardNo) {
		 
		return boardRepository.findByNo(BoardNo);
	}
	
	public Boolean replyUpdate(int groupNo , int orderNo) {
		return boardRepository.replyUpdate(groupNo , orderNo);
	}
	
	public Boolean insert(BoardVo vo) {
		 
		return boardRepository.insert(vo);
	}
	
	public Boolean delete(Long no) {
		 
		return boardRepository.delete(no);
	}
	
	public double findAllCount() {
		return boardRepository.findAllCount();
	}

	
	public List<BoardVo> findAllSearch(String searchValue , int first , int second) {
		   return boardRepository.findAllSearch(searchValue,first, second);
	}
	
	public void updateView(Long no , String title , String contents) {
		    boardRepository.updateView(no,title,contents);
	}
	
	public int findMaxGroupNo() {
		return boardRepository.findMaxGroupNo();
	}
	
	public int findMaxOrderNo() {
		return boardRepository.findMaxOrderNo();
	}
	
	public void updateHit(Long no) {
		 boardRepository.updateHit(no);
	}
	
}
	
	
	