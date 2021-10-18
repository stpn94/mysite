package com.douzone.mysite.vo;

import com.douzone.mysite.dao.BoardDao;

public class Paging {
	private final static int pageCount = 5;
	private int groupStartNum = 0;
	private int groupLastNum = 0;
	private int lastPageNum = 0;

	public static int getPagecount() {
		return pageCount;
	}

	public int getGroupStartNum() {
		return groupStartNum;
	}

	public void setGroupStartNum(int groupStartNum) {
		this.groupStartNum = groupStartNum;
	}

	public int getGroupLastNum() {
		return groupLastNum;
	}

	public void setGroupLastNum(int groupLastNum) {
		this.groupLastNum = groupLastNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public void setGroup(int curPage) {
		int groupNum = 0;

		groupNum = (int) Math.floor((curPage - 1) / pageCount);
		groupStartNum = (pageCount * groupNum) + 1;
		groupLastNum = groupStartNum + (pageCount - 1);
	}

	public void setLastPageNum() {

		int total = new BoardDao().findAllCount();

		if (total % pageCount == 0) {
			lastPageNum = (int) Math.floor(total / pageCount);
		} else {
			lastPageNum = (int) Math.floor(total / pageCount) + 1;
		}
	}

	// 검색을 했을 때 총 페이지의 마지막 번호
	public void setLastPageNum(String kwd) {

		int total = new BoardDao().findAllCount();

		if (total % pageCount == 0) {
			lastPageNum = (int) Math.floor(total / pageCount);
		} else {
			lastPageNum = (int) Math.floor(total / pageCount) + 1;
		}
	}

}