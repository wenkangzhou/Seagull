package com.usernet.product.utils;

public class PageUtils {
	public static Page createPage(Page page, int totalRecords) {
		return createPage(page.getEveryPage(), page.getCurrentPage(),
				totalRecords);
	}

	public static Page createPage(int everyPage, int currentPage,
			int totalRecords) {
		everyPage = getEveryPage(everyPage);
		currentPage = getCurrentPage(currentPage);
		int beginIndex = getBeginIndex(everyPage, currentPage,totalRecords);
		int totalPage = getTotalPage(everyPage, totalRecords);
		if(beginIndex==0)
			currentPage=1;
		return new Page(everyPage, totalPage,
				currentPage, beginIndex, totalRecords);
	}

	private static int getEveryPage(int everyPage) {
		return everyPage == 0 ? 10 : everyPage;
	}

	private static int getCurrentPage(int currentPage) {
		return currentPage == 0 ? 1 : currentPage;
	}

	private static int getBeginIndex(int everyPage, int currentPage,int totalRecords) {
		if((currentPage - 1) * everyPage>totalRecords)
			return 0;
		else
			return (currentPage - 1) * everyPage;
	}

	private static int getTotalPage(int everyPage, int totalRecords) {
		int totalPage = 0;

		if (totalRecords % everyPage == 0)
			totalPage = totalRecords / everyPage;
		else
			totalPage = totalRecords / everyPage + 1;

		return totalPage;
	}
}
