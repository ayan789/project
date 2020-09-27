package com.ccic.salesapp.noncar.dto;

import java.util.Date;

public class OrderListEntity {
	private String id;
	private String operatorEmpno;//操作员工号
    private String startDate;//报价起期
    private String endDate;//报价止期
    private String quotationMode;//报价模式 
    private String status;//订单状态
    private String licenseNo;//车牌号
    private String applicantName;//投保人姓名
    
    private String minStartDate;//起保日期起期
    private String maxStartDate;//起保日期止期
    
	private String orderNo;//订单号
	private String proposalNo;//投保单号
	private String policyNo;//保单号
	private String operateDate;//报价日期
	private String payApplyNo;//支付号
	private String identityCode;//支付验证码
	private Double sumPremium;//车险保费
	private Double sumTax;//车船税
	private String riskCode;//险种
	private String jointSales;//联合销售标识
	private String EYZapplicationNo;//意外险投保单号
	private String EYZpolicyNo;//意外险保单号
	private Double EYZpremium;//意外险保费
	private String YEJapplicationNo;//随车险投保单号
	private String YEJpolicyNo;//随车险保单号
	private Double YEJpremium;//随车险保费
	private String WAEapplicationNo;//健康险投保单号
	private String WAEpolicyNo ;//健康险保单号
	private Double WAEpremium;//健康险保费
	private String insuredName;//被保险人姓名
	private String businessNature;//业务来源
	private String agentCode;//代理人代码
	private String isSuccess;//是否报价成功

	private String currentDate;//查询月份
	private String platformFlag;//平台标识  1-新华出单
	private String nclUserCode;//新华用户标识
	private Double commisionamntVci;//商业险佣金  新华
	private Double commisionamntTci;//交强险佣金  新华
	private Double managementfeeVci;//商业险手续费 新华
	private Double managementfeeTci;//交强险手续费 新华
	private Double premiumTci;
	private Double premiumVci;
	private String comeCode; //出单机构
	private String appliyInsuredNature; //投保人的性质
	private String appliyIdType; //投保人证件类型
	private String insuredNature;//被 保人的性质
	private String insuredIdType; //被保人证件类型
	private String machineCode;//机器编码
	private String querySequenceNo;//交强险查询码
	private String querySequenceBzNo;//商业险查询码
    private String electronicInsurance ;//是否是电子投保     
    private String machinecodeStatus;//验证码
    
    private String carOwnerNature;//车主性质
    private String carUseNatureCode;//车辆使用性质
    private String carKindCode;//车辆种类
    private String seatCount;//座位数
    private Date applyMessageDate;//短信验证码申请时间
    private String quotationType;//报价类型 1-单交2-单商3交商混保
    private String projectCode;//项目编码
    private String huataiPaymentStatus;//华泰分期申请状态
    private String huataiPaymentStatusName;//华泰分期申请状态

	private String newcoreFlag;//新核心出单标志 1代表新核心
	
	private Date effectDate;//起保日期
	private String carOwner;
	   
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
	public String getMachinecodeStatus() {
		return machinecodeStatus;
	}
	public void setMachinecodeStatus(String machinecodeStatus) {
		this.machinecodeStatus = machinecodeStatus;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getQuerySequenceNo() {
		return querySequenceNo;
	}
	public void setQuerySequenceNo(String querySequenceNo) {
		this.querySequenceNo = querySequenceNo;
	}
	public String getQuerySequenceBzNo() {
		return querySequenceBzNo;
	}
	public void setQuerySequenceBzNo(String querySequenceBzNo) {
		this.querySequenceBzNo = querySequenceBzNo;
	}
	public String getElectronicInsurance() {
		return electronicInsurance;
	}
	public void setElectronicInsurance(String electronicInsurance) {
		this.electronicInsurance = electronicInsurance;
	}
	public String getAppliyInsuredNature() {
		return appliyInsuredNature;
	}
	public void setAppliyInsuredNature(String appliyInsuredNature) {
		this.appliyInsuredNature = appliyInsuredNature;
	}
	public String getInsuredNature() {
		return insuredNature;
	}
	public void setInsuredNature(String insuredNature) {
		this.insuredNature = insuredNature;
	}
	public String getOperatorEmpno() {
		return operatorEmpno;
	}
	public void setOperatorEmpno(String operatorEmpno) {
		this.operatorEmpno = operatorEmpno;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getQuotationMode() {
		return quotationMode;
	}
	public void setQuotationMode(String quotationMode) {
		this.quotationMode = quotationMode;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
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
	public Double getSumPremium() {
		return sumPremium;
	}
	public void setSumPremium(Double sumPremium) {
		this.sumPremium = sumPremium;
	}
	public Double getSumTax() {
		return sumTax;
	}
	public void setSumTax(Double sumTax) {
		this.sumTax = sumTax;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
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
	public Double getEYZpremium() {
		return EYZpremium;
	}
	public void setEYZpremium(Double eYZpremium) {
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
	public Double getYEJpremium() {
		return YEJpremium;
	}
	public void setYEJpremium(Double yEJpremium) {
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
	public Double getWAEpremium() {
		return WAEpremium;
	}
	public void setWAEpremium(Double wAEpremium) {
		WAEpremium = wAEpremium;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getBusinessNature() {
		return businessNature;
	}
	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getComeCode() {
		return comeCode;
	}
	public void setComeCode(String comeCode) {
		this.comeCode = comeCode;
	}	

	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getPlatformFlag() {
		return platformFlag;
	}
	public void setPlatformFlag(String platformFlag) {
		this.platformFlag = platformFlag;
	}
	public String getNclUserCode() {
		return nclUserCode;
	}
	public void setNclUserCode(String nclUserCode) {
		this.nclUserCode = nclUserCode;
	}
	public Double getCommisionamntVci() {
		return commisionamntVci;
	}
	public Double getCommisionamntTci() {
		return commisionamntTci;
	}
	public void setCommisionamntTci(Double commisionamntTci) {
		this.commisionamntTci = commisionamntTci;
	}
	public Double getManagementfeeVci() {
		return managementfeeVci;
	}
	public void setManagementfeeVci(Double managementfeeVci) {
		this.managementfeeVci = managementfeeVci;
	}
	public Double getManagementfeeTci() {
		return managementfeeTci;
	}
	public void setManagementfeeTci(Double managementfeeTci) {
		this.managementfeeTci = managementfeeTci;
	}
	public void setCommisionamntVci(Double commisionamntVci) {
		this.commisionamntVci = commisionamntVci;
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
	public String getCarOwnerNature() {
		return carOwnerNature;
	}
	public void setCarOwnerNature(String carOwnerNature) {
		this.carOwnerNature = carOwnerNature;
	}
	public String getCarUseNatureCode() {
		return carUseNatureCode;
	}
	public void setCarUseNatureCode(String carUseNatureCode) {
		this.carUseNatureCode = carUseNatureCode;
	}
	public Date getApplyMessageDate() {
		return applyMessageDate;
	}
	public void setApplyMessageDate(Date applyMessageDate) {
		this.applyMessageDate = applyMessageDate;
	}
	public String getAppliyIdType() {
		return appliyIdType;
	}
	public void setAppliyIdType(String appliyIdType) {
		this.appliyIdType = appliyIdType;
	}
	public String getInsuredIdType() {
		return insuredIdType;
	}
	public void setInsuredIdType(String insuredIdType) {
		this.insuredIdType = insuredIdType;
	}
	public String getQuotationType() {
		return quotationType;
	}
	public void setQuotationType(String quotationType) {
		this.quotationType = quotationType;
	}
	public String getCarKindCode() {
		return carKindCode;
	}
	public void setCarKindCode(String carKindCode) {
		this.carKindCode = carKindCode;
	}
	public String getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
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
	public String getMinStartDate() {
		return minStartDate;
	}
	public void setMinStartDate(String minStartDate) {
		this.minStartDate = minStartDate;
	}
	public String getMaxStartDate() {
		return maxStartDate;
	}
	public void setMaxStartDate(String maxStartDate) {
		this.maxStartDate = maxStartDate;
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
	public Date getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	
}