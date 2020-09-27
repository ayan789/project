package com.ccic.salesapp.performance.domain.vo.request;
import io.swagger.annotations.ApiModelProperty;

public class PageVO{
	
	@ApiModelProperty("当前页")
	private Integer page;
	
	@ApiModelProperty("每页数量")
	private Integer size;  
	
	@ApiModelProperty("总页数")
	private Integer pages; 
	
	@ApiModelProperty("总条数")
	private Integer rows;  
	
	//根据行数初始化对象
	public void init(Integer rowNum)
	{
		rowNum = rowNum != null ? rowNum : 0;
		if(this.getSize() == null)
		{
			this.setSize(20);
		}
		this.setRows(rowNum);
		this.setPages((int)Math.ceil((rowNum*1.0)/this.getSize()));
	}
	
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
}
