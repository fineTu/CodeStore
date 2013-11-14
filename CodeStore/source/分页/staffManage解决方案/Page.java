package cn.pms.pojo;

import cn.pms.pojo.Page;

public final class Page
{
	public final static int DEFAULT_PAGE_NUMBER = 5;
	private final int currentPage, eachPageNumber;
	private int totalPageNum;
	
	public Page(int currentPage,int totalPageNum)
	{
		this.totalPageNum = totalPageNum;
		this.currentPage = currentPage;
		this.eachPageNumber = DEFAULT_PAGE_NUMBER;
	}
	
	public Page(int currentPage, int eachPageNumber,int totalPageNum)
	{
		this.currentPage = currentPage;
		this.eachPageNumber = eachPageNumber;
		this.totalPageNum = totalPageNum;
	}
	
	public Page getHomePage()
	{
		return new Page(1,this.getTotalPageNum());
	}
	
	public int getFirstIndex()
	{
		return (currentPage - 1) * eachPageNumber;
	}
	
	public int getCurrentPage()
	{
		return currentPage;
	}
	
	public int getEachPageNumber()
	{
		return eachPageNumber;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	
}
