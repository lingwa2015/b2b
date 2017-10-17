package com.b2b.page;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Page<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_PAGE_SIZE_STR = "15";
	public static final int DEFAULT_PAGE_SIZE = 15; // 默认单面记录数
	private Integer pageSize = Page.DEFAULT_PAGE_SIZE; // 首选页面记录数
	private List<T> result; // 结果列表
	private Integer pageNum = 1; // 当前页码
	private Long records = 0L; // 所有记录数
	private Integer pages = 0; // 总页数

	public Integer getPageFirstRecNum() {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = Page.DEFAULT_PAGE_SIZE;
		}
		return (pageNum - 1) * pageSize + 1;
	}

	public Integer getPageLastRecNum() {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = Page.DEFAULT_PAGE_SIZE;
		}
		return pageNum * pageSize;
	}

	public Page() {
		this(0, 0, Page.DEFAULT_PAGE_SIZE, new ArrayList<T>());
	}

	public Page(int pageNum, long records, int pageSize, List<T> result) {
		this.pageSize = pageSize;
		if (pageSize == 0) {
			pageSize = Page.DEFAULT_PAGE_SIZE;
		}
		if (result == null) {
			result = new ArrayList<T>();
		}
		this.pageNum = pageNum;
		this.records = records;
		this.result = result;
		if (records > 0) {// 根据记录数和页面大小, 得到页数
			this.pages = Integer.parseInt((records / this.pageSize + (records % this.pageSize > 0 ? 1 : 0)) + "");
		} else {
			this.pages = 0;
		}

	}

	public boolean getPreviousPage() {
		if (this.pageNum - 1 > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean getNextPage() {
		if (this.pageNum + 1 <= this.pages) {
			return true;
		} else {
			return false;
		}
	}

	public Long getCountsBefore() {
		return Long.parseLong(((this.pageNum - 1) * this.pageSize) + "");
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
		if (records != null && records.intValue() > 0) {// 根据记录数和页面大小, 得到页数
			this.pages = Integer.parseInt((records / this.pageSize + (records % this.pageSize > 0 ? 1 : 0)) + "");
		} else {
			this.pages = 0;
		}
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public static int calStartRow(int currentPage, int pageSize) {
		return (currentPage - 1) * pageSize;
	}
}