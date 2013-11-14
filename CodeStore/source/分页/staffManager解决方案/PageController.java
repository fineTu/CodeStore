package com.neusoft.util;

import java.util.ArrayList;

import com.neusoft.exception.PageControlException;

public class PageController {

	private ArrayList resultList; // 全部记录
	private int currentPageIndex = 1; // 当前页号，外界传入
	private int countPerpage = 5; // 每页条数，外界可以设定
	private ArrayList pageList; // 分页集合，返回
	private int pageCount; // 总页数
	private int recordCount; // 总记录条数
	private int prePageIndex; // 上一页序号
	private int nextPageIndex; // 下一页序号

	public void setResultList(ArrayList resultList) {
		this.resultList = resultList;
		recordCount = resultList.size();
		pageCount = recordCount % countPerpage == 0 ? recordCount
				/ countPerpage : recordCount / countPerpage + 1;
	}

	public void setCurrentPageIndex(int currentPageIndex) { // 每当页数改变，都会调用这个函数，筛选代码可以写在这里
		this.currentPageIndex = currentPageIndex;

		// 上一页，下一页确定
		prePageIndex = currentPageIndex - 1;
		nextPageIndex = currentPageIndex + 1;
		// 是否第一页，最后一页
	}

	public void setPageList() {
		// 筛选工作
		if (currentPageIndex > 0) {
			if (resultList != null) {
				if (recordCount > countPerpage) {
					pageList = new ArrayList();
					for (int i = (currentPageIndex - 1) * countPerpage; i < currentPageIndex
							* countPerpage
							&& i < recordCount; i++) {
						pageList.add(resultList.get(i));
					}
				} else {
					pageList = resultList;
				}
			} else {
				throw new PageControlException("未设置结果集");
			}
		} else {
			throw new PageControlException("未设置当前页");
		}

	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCountPerpage(int countPerpage) {
		this.countPerpage = countPerpage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public ArrayList getPageList() {
		return pageList;
	}

	public int getNextPageIndex() {
		return nextPageIndex;
	}

	public int getPrePageIndex() {
		return prePageIndex;
	}
	
	public int getRecordCount(){
		return recordCount;
	}
	public int getCountPerpage(){
		return countPerpage;
	}
	
}
