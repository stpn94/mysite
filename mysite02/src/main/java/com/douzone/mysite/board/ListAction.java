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
		int cureentPage = 0;
		int first = 0;
		int second = 0;
		int blockStartNum = 0;
		int blockLastNum = 0;
		int lastPageNum = 0;
		int pagesize = Paging.getPagecount();
		Paging paging = new Paging();

		String searchValue = request.getParameter("kwd");

		if (searchValue == null || searchValue.isEmpty()) {
			searchValue = "";
		}
		String cp = request.getParameter("page");

		
		if (cp == null || "null".equals(cp)) {
			/* 최초 홈페이지 입장 */
			paging.makeBlock(cureentPage);
			paging.makeLastPageNum(searchValue);
			blockStartNum = paging.getBlockStartNum(); // 그룹 번호
			blockLastNum = paging.getBlockLastNum();
			lastPageNum = paging.getLastPageNum();
			request.setAttribute("curPageNum", cureentPage);
		} else {
			 /* 페이징 버튼을 눌렸을때 */
			cureentPage = Integer.parseInt(request.getParameter("page"));
			paging.makeBlock(cureentPage);
			paging.makeLastPageNum(searchValue);
			blockStartNum = paging.getBlockStartNum(); // 그룹 번호
			blockLastNum = paging.getBlockLastNum();
			lastPageNum = paging.getLastPageNum();
			request.setAttribute("curPageNum", cureentPage);
		}
		request.setAttribute("blockStartNum", blockStartNum);
		request.setAttribute("blockLastNum", blockLastNum);
		request.setAttribute("lastPageNum", lastPageNum); // lastPageNum = 6일 때, 7, 8, 9, 10는 링크를 활성화 하지 못함

		if (cureentPage != 0) {
			first = (cureentPage * pagesize) - pagesize;
		} else {
			first = (cureentPage * pagesize);
		}
		second = (cureentPage * pagesize) + (pagesize - 1);

		List<BoardVo> list = new BoardDao().findAllSearch(searchValue, first, pagesize);

		request.setAttribute("list", list);
		MvcUtil.forward("board/list", request, response);

	}

}

