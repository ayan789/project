package com.ccic.salesapp.performance.domain.vo.dto;

public class PlanFeeDetailVO {	
	private String policyCreateDate;//保单生成日期
	private String licenseNo;//车牌号
	private String carOwnerName;//车主名称
	private String planFee;//手续费
	private String premium;
	private String policyNo;
	
	public String getPolicyCreateDate() {
		return policyCreateDate;
	}
	public void setPolicyCreateDate(String policyCreateDate) {
		this.policyCreateDate = policyCreateDate;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getCarOwnerName() {
		return carOwnerName;
	}
	public void setCarOwnerName(String carOwnerName) {
		this.carOwnerName = carOwnerName;
	}
	public String getPlanFee() {
		return planFee;
	}
	public void setPlanFee(String planFee) {
		this.planFee = planFee;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	
	
}
