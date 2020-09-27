package com.ccic.salesapp.noncar.dto;

import io.swagger.annotations.ApiModelProperty;

public class EInvoiceApplicationForAppDTO {
	
	@ApiModelProperty("保单号")
	private String policyNo;
	
	@ApiModelProperty("手机号")
	private String mobileNo;
	
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


	@Override
	public String toString() {
		return "EInvoiceApplicationForAppDTO [policyNo=" + policyNo + ", mobileNo=" + mobileNo + "]";
	}

	public EInvoiceApplicationForAppDTO(String policyNo, String mobileNo) {
		super();
		this.policyNo = policyNo;
		this.mobileNo = mobileNo;
	}

	public EInvoiceApplicationForAppDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
