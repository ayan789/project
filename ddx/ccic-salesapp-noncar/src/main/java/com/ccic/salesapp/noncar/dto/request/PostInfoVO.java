package com.ccic.salesapp.noncar.dto.request;


public class PostInfoVO {
	private String postManName;//收件人姓名
	private String mobile;//收件人联系电话
	private String addressInfo;//配送地址
	private String addressDetail;//详细地址
	private String addressCode;//省市区代码
	
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getPostManName() {
		return postManName;
	}
	public void setPostManName(String postManName) {
		this.postManName = postManName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddressInfo() {
		return addressInfo;
	}
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	
}
