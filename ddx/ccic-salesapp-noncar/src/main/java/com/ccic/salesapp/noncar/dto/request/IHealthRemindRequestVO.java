package com.ccic.salesapp.noncar.dto.request;
 
public class IHealthRemindRequestVO extends DataVO {
	
	private String curStaffCode;	//业务员代码
	private String busiType;		//业务类型 1业务员 2渠道
	private String productCate;		//产品类型：1、百万医疗2、团意险
	
	private String queryType;		//查询类型:1续保清单2已续保清单（新单）;  2018/11/27	新增
	private String isRenewal;		//是否续保:0未续保1已续保	2018/11/27	新增
	private String startDate;		//保单终止日期起		2018/11/27	新增
	private String endDate;			//保单终止日期止		2018/11/27	新增
	private String uwStartDate;		//核保通过日期起		2018/11/27	新增
	private String uwEndDate;		//核保通过日期止		2018/11/27	新增
	
	
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getIsRenewal() {
		return isRenewal;
	}
	public void setIsRenewal(String isRenewal) {
		this.isRenewal = isRenewal;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getUwStartDate() {
		return uwStartDate;
	}
	public void setUwStartDate(String uwStartDate) {
		this.uwStartDate = uwStartDate;
	}
	public String getUwEndDate() {
		return uwEndDate;
	}
	public void setUwEndDate(String uwEndDate) {
		this.uwEndDate = uwEndDate;
	}
	public String getCurStaffCode() {
		return curStaffCode;
	}
	public void setCurStaffCode(String curStaffCode) {
		this.curStaffCode = curStaffCode;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	public String getProductCate() {
		return productCate;
	}
	public void setProductCate(String productCate) {
		this.productCate = productCate;
	}

}
