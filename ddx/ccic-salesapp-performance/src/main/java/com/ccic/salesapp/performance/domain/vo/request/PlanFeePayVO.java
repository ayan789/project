package com.ccic.salesapp.performance.domain.vo.request;



public class PlanFeePayVO {
	
	private String certiId;//业务id
	private String certiNo;//业务号
	private String planFee;//手续费
	
	public String getCertiId() {
		return certiId;
	}
	public void setCertiId(String certiId) {
		this.certiId = certiId;
	}
	public String getCertiNo() {
		return certiNo;
	}
	public void setCertiNo(String certiNo) {
		this.certiNo = certiNo;
	}
	public String getPlanFee() {
		return planFee;
	}
	public void setPlanFee(String planFee) {
		this.planFee = planFee;
	}


}