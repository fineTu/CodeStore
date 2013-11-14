package cn.cafebabe.autodao.pojo;

import cn.cafebabe.autodao.util.Assert;

public final class Page
{
	private static int defaultPageNumber = 7;
	private final static long MAX_INT = 2147483647l;
	private int currentPage = 1;
	private int eachPageNumber = defaultPageNumber, pageNumber = 0;
	
	public Page()
	{}
	
	public Page(long totalNumber)
	{
		this(1, defaultPageNumber, totalNumber);
	}
	
	public Page(int eachPageNumber, long totalNumber)
	{
		this(1, eachPageNumber, totalNumber);
	}
	
	public Page(int currentPage, int eachPageNumber, long totalNumber)
	{
		Assert.isTrue(eachPageNumber > 0 && totalNumber > -1, "Page初始化信息有问题!");
		this.currentPage = currentPage;
		this.eachPageNumber = eachPageNumber;
		if(totalNumber == 0)
		{
			this.pageNumber = 1;
		}
		else
		{
			long newPageNumber = totalNumber / eachPageNumber;
			Assert.isTrue(newPageNumber < MAX_INT, "总数量太多,分页会出错!");
			if(totalNumber % eachPageNumber != 0)
			{
				newPageNumber += 1;
			}
			this.pageNumber = (int) newPageNumber;
		}
	}
	
	public static Page getPage(int currentPage, int eachPageNumber,
			long pageNumber)
	{
		return new Page(currentPage, eachPageNumber, pageNumber
				* eachPageNumber);
	}
	
	public int getFirstIndex()
	{
		return (currentPage - 1) * eachPageNumber;
	}
	
	public int getCurrentPage()
	{
		return (currentPage < 1 ? 1 : currentPage > pageNumber ? pageNumber
				: currentPage);
	}
	
	public int getEachPageNumber()
	{
		return eachPageNumber;
	}
	
	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}
	
	public int getPageNumber()
	{
		return pageNumber;
	}
	
	public static void setDefaultPageNumber(int defaultPageNumber)
	{
		Assert.isTrue(defaultPageNumber > 0, "每页条数不能小于1!");
		Page.defaultPageNumber = defaultPageNumber;
	}
	
	public static int getDefaultPageNumber()
	{
		return defaultPageNumber;
	}
	
	public void setEachPageNumber(int eachPageNumber)
	{
		Assert.isTrue(eachPageNumber > 0, "每页条数要大于0!");
		this.eachPageNumber = eachPageNumber;
	}
	
	public void setPageNumber(int pageNumber)
	{
		Assert.isTrue(pageNumber > 0, "总页数数要大于0!");
		this.pageNumber = pageNumber;
	}
}
