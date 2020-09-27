package com.ccic.salesapp.noncar.utils;



import java.util.List;

/**
 * 分页对象
 * 
 */
@SuppressWarnings("unused")
public class Page implements java.io.Serializable {

	private static final long serialVersionUID = -7129370623545540245L;

	/** 总记录数 */
	private int totalCount;

	/** 每页的记录数 */
	private int pageSize;

	/** 分页的页数 */
	private int totalPage;

	/** 当前页数据集合 */
	private List<? extends Object> items;

	/** 当前页索引 */
	private int currentPageIndex;

	/** 上一页索引 */
	private int previousPageIndex;

	/** 下一页索引 */
	private int nextPageIndex;

	/** 最后一页的索引 */
	private int lastPageIndex;
	
	
	public Page() {
	}

	/**
	 * Creates a new Page object. 构造方法
	 * 
	 * @param totalCount
	 *            总记录数
	 * @param pageSize
	 *            页面大小
	 * @param currentPageIndex
	 *            当前索引
	 * @param items
	 *            数据
	 */
	public Page(int totalCount, int pageSize, int currentPageIndex, List<? extends Object> items) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		this.lastPageIndex = getLastPageIndex();
		setCurrentPageIndex(currentPageIndex);
		this.nextPageIndex = getNextPageIndex();
		this.previousPageIndex = getPreviousPageIndex();
		setItems(items);
		setTotalPage(getTotalPage());
	}

	public Page(List<? extends Object> items, int totalPage, int pageSize, int currentPageIndex) {
		setItems(items);
		setTotalPage(totalPage);
		setPageSize(pageSize);
		this.currentPageIndex = currentPageIndex;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return 总记录数
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 获取每页的记录数
	 * 
	 * @return 记录数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取当前页索引
	 * 
	 * @return 索引
	 */
	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	/**
	 * 设置当前页索引
	 * 
	 * @param currentPageIndex
	 */
	public void setCurrentPageIndex(int currentPageIndex) {
		if (totalCount <= 0) {
			this.currentPageIndex = 1;
		} else if (currentPageIndex >= this.lastPageIndex) {
			this.currentPageIndex = this.lastPageIndex;
		} else if (currentPageIndex < 1) {
			this.currentPageIndex = 1;
		} else {
			this.currentPageIndex = currentPageIndex;
		}
	}

	/**
	 * 获取上一页索引
	 * 
	 * @return 索引
	 */
	public int getPreviousPageIndex() {
		int previousPageIndex = getCurrentPageIndex() - 1;

		if (previousPageIndex <= 0) {
			return 1;
		} else {
			return previousPageIndex;
		}
	}

	/**
	 * 获取下一页索引
	 * 
	 * @return 索引
	 */
	public int getNextPageIndex() {
		int nextPageIndex = getCurrentPageIndex() + 1;

		if (nextPageIndex >= lastPageIndex) {
			return lastPageIndex;
		} else {
			return nextPageIndex;
		}
	}

	/**
	 * 获取最后一页的索引
	 * 
	 * @return 索引
	 */
	public int getLastPageIndex() {
		if ((totalCount % pageSize) == 0) {
			return totalCount / pageSize;
		} else {
			return (totalCount / pageSize) + 1;
		}
	}

	/**
	 * 获取当前页数据项集合
	 * 
	 * @return Collection
	 */
	public List<? extends Object> getItems() {
		return items;
	}

	/**
	 * 设置当前页数据项集合
	 * 
	 * @param items
	 */
	public void setItems(List<? extends Object> items) {
		if(items==null){
			return;
		}
		if(items.size()>this.pageSize){
			this.items = items.subList(this.pageSize*(this.currentPageIndex-1), this.pageSize*this.currentPageIndex>this.totalCount?this.totalCount:this.pageSize*this.currentPageIndex);
		}else{
			this.items = items;
		}
	}

	/**
	 * 返回总分页数
	 * 
	 * @return 分页数
	 */
	public final int getTotalPage() {
		if(this.totalCount==0){
			return this.totalPage;
		}
		
		if ((this.totalCount % this.pageSize) == 0) {
			return this.totalCount / this.pageSize;
		} else {
			return (this.totalCount / this.pageSize) + 1;
		}
	}

	/**
	 * 设置总分页数
	 * 
	 * @param totalPage
	 *            分页数F
	 */
	public final void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}

