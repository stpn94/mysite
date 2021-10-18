package com.douzone.mysite.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.Paging;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserVo authUser = null;
		Paging paging = new Paging();
		
		String keyword = request.getParameter("kwd");
		String cp = request.getParameter("page");
		
		int currentPage = 0;
		int first = 0;
		int second = 0;
		int groupStartNum = 0;
		int groupLastNum = 0;
		int lastPageNum = 0;
		int pagesize = Paging.getPagecount();


		if (keyword == null || keyword.isEmpty()) {
			keyword = "";
		}
		
		
		if (cp == null || "null".equals(cp)) {
			
		}else {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		paging.setGroup(currentPage);
		groupStartNum = paging.getGroupStartNum(); // 그룹 번호
		groupLastNum = paging.getGroupLastNum();
		
		paging.setLastPageNum(keyword);
		lastPageNum = paging.getLastPageNum();
	

		if (currentPage != 0) {
			first = (currentPage * pagesize) - pagesize;
		} else {
			first = (currentPage * pagesize);
		}
		second = (currentPage * pagesize) + (pagesize - 1);

		List<BoardVo> list = new BoardDao().findAllSearch(keyword, first, pagesize);
		
		request.setAttribute("curPageNum", currentPage);
		request.setAttribute("groupStartNum", groupStartNum);
		request.setAttribute("groupLastNum", groupLastNum);
		request.setAttribute("lastPageNum", lastPageNum); // lastPageNum = 6일 때, 7, 8, 9, 10는 링크를 활성화 하지 못함
		request.setAttribute("list", list);
		
		MvcUtil.forward("board/list", request, response);

		
	}

}

