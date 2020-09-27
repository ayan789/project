package com.ccic.salesapp.noncar.dto.request;

import com.ccic.salesapp.noncar.dto.DataVO;

public class UploadSignBookReqVO extends DataVO{
	private String signBytes;	//签名图片码
	private String insuranceNo;	//投保单号
	private String verificationCode;	//短信验证码
	
	public String getSignBytes() {
		return signBytes;
	}
	public void setSignBytes(String signBytes) {
		this.signBytes = signBytes;
	}
	public String getInsuranceNo() {
		return insuranceNo;
	}
	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	
}
