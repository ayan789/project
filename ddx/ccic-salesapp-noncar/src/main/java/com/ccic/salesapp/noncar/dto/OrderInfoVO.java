package com.ccic.salesapp.noncar.dto;

public class OrderInfoVO {
	private String id;
	private String orderNo;//订单号
	private String proposalNo;//投保单号
	private String policyNo;//保单号
	private String operateDate;//报价日期
	private String quotationType;//报价类型
	private String licenseNo;//车牌号
	private String payApplyNo;//支付号
	private String identityCode;//支付验证码
	private String sumPremium;//合计保费
	private String underWriteStatus;//核保状态
	private String riskCode;//险种
	private String jointSales;//联合销售标识
	private String EYZapplicationNo;//意外险投保单号
	private String EYZpolicyNo;//意外险保单号
	private double EYZpremium;//意外险保费
	private String YEJapplicationNo;//随车险投保单号
	private String YEJpolicyNo;//随车险保单号
	private double YEJpremium;//随车险保费
	private String WAEapplicationNo;//健康险投保单号
	private String WAEpolicyNo ;//健康险保单号
	private double WAEpremium;//健康险保费
	private String insuredName;//被保险人姓名
	private String desProposalNo;//des加密后的投保单号
	private String comeCode;//归属机构
    private String flag;

	private double commisionamntVci;//商业险佣金  新华
	private double commisionamntTci;//交强险佣金  新华
	private double managementfeeVci;//商业险手续费 新华
	private double managementfeeTci;//交强险手续费 新华
	private Double premiumTci;
	private Double premiumVci;
	
	private String projectCode;//项目编码
	private String huataiPaymentStatus;//华泰分期申请状态
	private String huataiPaymentStatusName;//华泰分期申请状态
	
	private String newcoreFlag;//新核心出单标志
	
	private String payFlag;//能否进行支付：1是0否
	
	private String desPolicyNo;//des加密后的保单号
	
	private String carOwner;//车主姓名
	private String yzmAreaFlag;//验证码标志
	
	public String getCarOwner() {
		return carOwner;
	}
	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getComeCode() {
		return comeCode;
	}
	public void setComeCode(String comeCode) {
		this.comeCode = comeCode;
	}
	public String getJointSales() {
		return jointSales;
	}
	public void setJointSales(String jointSales) {
		this.jointSales = jointSales;
	}
	public String getEYZapplicationNo() {
		return EYZapplicationNo;
	}
	public void setEYZapplicationNo(String eYZapplicationNo) {
		EYZapplicationNo = eYZapplicationNo;
	}
	public String getEYZpolicyNo() {
		return EYZpolicyNo;
	}
	public void setEYZpolicyNo(String eYZpolicyNo) {
		EYZpolicyNo = eYZpolicyNo;
	}
	public double getEYZpremium() {
		return EYZpremium;
	}
	public void setEYZpremium(double eYZpremium) {
		EYZpremium = eYZpremium;
	}
	public String getYEJapplicationNo() {
		return YEJapplicationNo;
	}
	public void setYEJapplicationNo(String yEJapplicationNo) {
		YEJapplicationNo = yEJapplicationNo;
	}
	public String getYEJpolicyNo() {
		return YEJpolicyNo;
	}
	public void setYEJpolicyNo(String yEJpolicyNo) {
		YEJpolicyNo = yEJpolicyNo;
	}
	public double getYEJpremium() {
		return YEJpremium;
	}
	public void setYEJpremium(double yEJpremium) {
		YEJpremium = yEJpremium;
	}
	public String getWAEapplicationNo() {
		return WAEapplicationNo;
	}
	public void setWAEapplicationNo(String wAEapplicationNo) {
		WAEapplicationNo = wAEapplicationNo;
	}
	public String getWAEpolicyNo() {
		return WAEpolicyNo;
	}
	public void setWAEpolicyNo(String wAEpolicyNo) {
		WAEpolicyNo = wAEpolicyNo;
	}
	public double getWAEpremium() {
		return WAEpremium;
	}
	public void setWAEpremium(double wAEpremium) {
		WAEpremium = wAEpremium;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	public String getQuotationType() {
		return quotationType;
	}
	public void setQuotationType(String quotationType) {
		this.quotationType = quotationType;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getPayApplyNo() {
		return payApplyNo;
	}
	public void setPayApplyNo(String payApplyNo) {
		this.payApplyNo = payApplyNo;
	}
	public String getIdentityCode() {
		return identityCode;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public String getSumPremium() {
		return sumPremium;
	}
	public void setSumPremium(String sumPremium) {
		this.sumPremium = sumPremium;
	}
	public String getUnderWriteStatus() {
		return underWriteStatus;
	}
	public void setUnderWriteStatus(String underWriteStatus) {
		this.underWriteStatus = underWriteStatus;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getDesProposalNo() {
		return desProposalNo;
	}
	public void setDesProposalNo(String desProposalNo) {
		this.desProposalNo = desProposalNo;
	}
	public double getCommisionamntVci() {
		return commisionamntVci;
	}
	public void setCommisionamntVci(double commisionamntVci) {
		this.commisionamntVci = commisionamntVci;
	}
	public double getCommisionamntTci() {
		return commisionamntTci;
	}
	public void setCommisionamntTci(double commisionamntTci) {
		this.commisionamntTci = commisionamntTci;
	}
	public double getManagementfeeVci() {
		return managementfeeVci;
	}
	public void setManagementfeeVci(double managementfeeVci) {
		this.managementfeeVci = managementfeeVci;
	}
	public double getManagementfeeTci() {
		return managementfeeTci;
	}
	public void setManagementfeeTci(double managementfeeTci) {
		this.managementfeeTci = managementfeeTci;
	}
	public Double getPremiumTci() {
		return premiumTci;
	}
	public void setPremiumTci(Double premiumTci) {
		this.premiumTci = premiumTci;
	}
	public Double getPremiumVci() {
		return premiumVci;
	}
	public void setPremiumVci(Double premiumVci) {
		this.premiumVci = premiumVci;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getHuataiPaymentStatus() {
		return huataiPaymentStatus;
	}
	public void setHuataiPaymentStatus(String huataiPaymentStatus) {
		this.huataiPaymentStatus = huataiPaymentStatus;
	}
	public String getHuataiPaymentStatusName() {
		return huataiPaymentStatusName;
	}
	public void setHuataiPaymentStatusName(String huataiPaymentStatusName) {
		this.huataiPaymentStatusName = huataiPaymentStatusName;
	}
	public String getNewcoreFlag() {
		return newcoreFlag;
	}
	public void setNewcoreFlag(String newcoreFlag) {
		this.newcoreFlag = newcoreFlag;
	}
	public String getPayFlag() {
		return payFlag;
	}
	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}
	public String getDesPolicyNo() {
		return desPolicyNo;
	}
	public void setDesPolicyNo(String desPolicyNo) {
		this.desPolicyNo = desPolicyNo;
	}
	public String getYzmAreaFlag() {
		return yzmAreaFlag;
	}
	public void setYzmAreaFlag(String yzmAreaFlag) {
		this.yzmAreaFlag = yzmAreaFlag;
	}

}
