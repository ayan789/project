package com.ccic.salesapp.noncar.dto.request;
import java.util.ArrayList;
import java.util.List;



public class PageVO  extends DataVO{

	private Integer page;//当前页
	private Integer size;  //每页数量
	private Integer pages;  //总页数
	private Integer rows;  //总条数




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

	//以总数量和每页数量单独传参
	public void initOutReq(Integer rowNum,Integer size)
	{
		rowNum = rowNum != null ? rowNum : 0;
		
		size=size!=null?size:20;
		
		this.setRows(rowNum);
		this.setPages((int)Math.ceil((rowNum*1.0)/size));
	}

	public static List<String> page(List<String> dataList, int pageSize,int currentPage) {
        List<String> currentPageList = new ArrayList<>();
        if (dataList != null && dataList.size() > 0) {
            int currIdx = (currentPage > 1 ? (currentPage - 1) * pageSize : 0);
            for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {
                String data = dataList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
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

