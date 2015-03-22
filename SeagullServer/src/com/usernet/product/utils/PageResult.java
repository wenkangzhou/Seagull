package com.usernet.product.utils;

import java.util.List;

/**
 * 
 * 分页信息
 */
public class PageResult<E> {
	private Page page; // 分页信息
	private List<E> content = null; // 每页显示的集合
	private int[] size; // 分页大小集合
	
	public int[] getSize() {
		return size;
	}

	public void setSize(int[] size) {
		this.size = size;
	}

	public PageResult(Page page, List<E> content) {
		this.page = page;
		this.content = content;
		this.size = new int[20];
		for (int i = 0; i < this.size.length; i++) {
			this.size[i] = (i + 1) * 5;
		}
	}

	public PageResult() {
		super();
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<E> getContent() {
		return content;
	}

	public void setContent(List<E> content) {
		this.content = content;
	}

}
