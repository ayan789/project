package com.example.tlyannoif;


public class UploadSignBookReqVO{
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
