package com.ccic.salesapp.noncar.dto.request;

import com.ccic.salesapp.noncar.dto.DataVO;

public class ISendMessageRequestVO extends DataVO {
	
	private String url;//续保地址
	private String mobile;//投保人手机号
	private String username;//投保人姓名	
	private String endDate;//终止日期
	private String policyNo;//保单号
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	
}
