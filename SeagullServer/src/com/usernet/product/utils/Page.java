package com.usernet.product.utils;

public class Page {

	public Page() {
	}
	
	/** 每页的数量 */
	private int everyPage = 20;
	/** 总页数 */
	private int totalPage;
	/** 当前页 */
	private int currentPage =1;
	/** 起始点 */
	private int beginIndex;
	/** 总记录数 */
	private int totalCount;

	public Page(int everyPage,
			int totalPage, int currentPage, int beginIndex, int totalCount) {
		super();
		this.everyPage = everyPage;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.beginIndex = beginIndex;
		this.totalCount = totalCount;
	}

	public int getEveryPage() {
		return everyPage;
	}

	public void setEveryPage(int everyPage) {
		this.everyPage = everyPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
