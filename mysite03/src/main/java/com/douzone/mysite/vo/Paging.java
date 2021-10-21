package com.douzone.mysite.vo;

public class Paging {

	private final static int pageCount = 5; //페이지당 개시물
	private int groupStartNum = 0; // 한 그룹의 시작번호
	private int groupLastNum = 0; // 한 그룹의 마지막 번
	private int lastPageNum = 0; // 그냥 마지막 번호

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

	public void setLastPageNum(double total) { //마지막 번호 세팅
		if (total % pageCount == 0) {
			lastPageNum = (int) Math.floor(total / pageCount);
		} else {
			lastPageNum = (int) Math.floor(total / pageCount) + 1;
		}
	}

	public void setLastPageNum(double total, String keyword) {

		if (total % pageCount == 0) {
			lastPageNum = (int) Math.floor(total / pageCount);
		} else {
			lastPageNum = (int) Math.floor(total / pageCount) + 1;
		}
	}

}