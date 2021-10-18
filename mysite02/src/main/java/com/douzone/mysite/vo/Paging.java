package com.douzone.mysite.vo;

import com.douzone.mysite.dao.BoardDao;

public class Paging {
	private final static int pageCount = 5;
	private int blockStartNum = 0;
	private int blockLastNum = 0;
	private int lastPageNum = 0;

	public static int getPagecount() {
		return pageCount;
	}

	public int getBlockStartNum() {
		return blockStartNum;
	}

	public void setBlockStartNum(int blockStartNum) {
		this.blockStartNum = blockStartNum;
	}

	public int getBlockLastNum() {
		return blockLastNum;
	}

	public void setBlockLastNum(int blockLastNum) {
		this.blockLastNum = blockLastNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public void makeBlock(int curPage) {
		int blockNum = 0;

		blockNum = (int) Math.floor((curPage - 1) / pageCount);
		blockStartNum = (pageCount * blockNum) + 1;
		blockLastNum = blockStartNum + (pageCount - 1);
	}

	public void makeLastPageNum() {

		int total = new BoardDao().findAllCount();

		if (total % pageCount == 0) {
			lastPageNum = (int) Math.floor(total / pageCount);
		} else {
			lastPageNum = (int) Math.floor(total / pageCount) + 1;
		}
	}

	// 검색을 했을 때 총 페이지의 마지막 번호
	public void makeLastPageNum(String kwd) {

		int total = new BoardDao().findAllCount();

		if (total % pageCount == 0) {
			lastPageNum = (int) Math.floor(total / pageCount);
		} else {
			lastPageNum = (int) Math.floor(total / pageCount) + 1;
		}
	}

}