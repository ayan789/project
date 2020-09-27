package com.ccic.salesapp.noncar.dto.request;

public class CommissionVO {

	private Double commissionRateUpper;//手续费支付比例上限
	private Double companyCommissionRateUpper;//公司总体上限
	private String claimEffectReason;//理赔因素影响原因
	private String newVehicleEffectReason;//车龄因素影响原因
	private String producerEffectReason;//渠道因素影响原因
	public Double getCommissionRateUpper() {
		return commissionRateUpper;
	}
	public void setCommissionRateUpper(Double commissionRateUpper) {
		this.commissionRateUpper = commissionRateUpper;
	}
	public Double getCompanyCommissionRateUpper() {
		return companyCommissionRateUpper;
	}
	public void setCompanyCommissionRateUpper(Double companyCommissionRateUpper) {
		this.companyCommissionRateUpper = companyCommissionRateUpper;
	}
	public String getClaimEffectReason() {
		return claimEffectReason;
	}
	public void setClaimEffectReason(String claimEffectReason) {
		this.claimEffectReason = claimEffectReason;
	}
	public String getNewVehicleEffectReason() {
		return newVehicleEffectReason;
	}
	public void setNewVehicleEffectReason(String newVehicleEffectReason) {
		this.newVehicleEffectReason = newVehicleEffectReason;
	}
	public String getProducerEffectReason() {
		return producerEffectReason;
	}
	public void setProducerEffectReason(String producerEffectReason) {
		this.producerEffectReason = producerEffectReason;
	}
}
