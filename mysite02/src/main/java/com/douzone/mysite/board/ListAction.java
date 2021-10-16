package com.douzone.mysite.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ListAction start");

		int page = 1;
		int limit = 5;
		
		// 검색어
		String searchValue = request.getParameter("kwd");
		
		if (searchValue == null || searchValue.isEmpty()) {
			searchValue = "";
		}
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//총 리스트 개수
		int listCount = new BoardDao().findAllCount();
		
		//21.0/5 = 5
		int maxPage = (int) Math.ceil((double)listCount/limit);
		
		// 1 page 1~5, 2page 6~10, 11~15,..
		// 11page 51~55,
		// [이전] [1][2][3][4][5][6][7][8][9][10] 다음
		// [이전] [11][12][13][14][15][16][17][18][19][20] 다음
		// [이전] [21][22][23] 다음
		int startPage = (((int)((double)page/limit +0.9)) -1)*5+1;
		int endPage = startPage +5 -1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		List<BoardVo> list = new BoardDao().findAllSearch(searchValue, startPage, limit);
		
//		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);		
		
		request.setAttribute("articleList", list);
		request.setAttribute("currentPage", page);
		request.setAttribute("startNum", startPage);
		request.setAttribute("lastNum", endPage);
		request.setAttribute("lastPageNum", maxPage); // lastPageNum = 6일 때, 7, 8, 9, 10는 링크를 활성화 하지 못함
		
		MvcUtil.forward("board/list", request, response);
	}

}

class PageInfo {
	private int page;
	private int maxPage;
	private int startPage;
	private int endPage;
	private int listCount;

	public PageInfo() {
		// TODO Auto-generated constructor stub
	}

	public PageInfo(int page, int maxPage, int startPage, int endPage, int listCount) {
		super();
		this.page = page;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.listCount = listCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	@Override
	public String toString() {
		return String.format("PageInfo [page=%s, maxPage=%s, startPage=%s, endPage=%s, listCount=%s]", page, maxPage,
				startPage, endPage, listCount);
	}

}
