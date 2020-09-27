package com.ccic.salesapp.noncar.dto;

import io.swagger.annotations.ApiModelProperty;

public class ElectronicInvoicingDTO {
	
	@ApiModelProperty("保单号")
	private String policyNo;
	
	@ApiModelProperty("手机号")
	private String mobileNo;
	
	@ApiModelProperty("邮箱")
	private String email;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
}
