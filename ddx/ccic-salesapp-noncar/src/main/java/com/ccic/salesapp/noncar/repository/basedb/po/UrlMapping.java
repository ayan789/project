package com.ccic.salesapp.noncar.repository.basedb.po;

import java.util.Date;

public class UrlMapping {

	private String id;//主键
	private String urlCode;//url代码
	private String urlMapping;//映射url
	private Date createDate;//创建日期
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrlCode() {
		return urlCode;
	}
	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}
	public String getUrlMapping() {
		return urlMapping;
	}
	public void setUrlMapping(String urlMapping) {
		this.urlMapping = urlMapping;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
  	
}
