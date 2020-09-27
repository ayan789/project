package com.ccic.salesapp.noncar.dto;



/*
 * 客户保单列表查询接口
 * @author Administrator
 *
 */
public class EnPolicyListQueryEntity {
	
	private String staffCode;//业务员代码
	private String insuranceType;//险类大类
	private String custType;//客户类型 个人客户：10 企业客户：20
	private String contactorName;//联系人姓名",企业客户保单查询使用
	private String minPayStartDate;//投保日期查询下限
	private String maxPayStartDate;//投保日期查询上限	
	private String minRespEndDate;//到期日期查询下限
	private String maxRespEndDate;//到期日期查询上限
	private String policyNo;//保单号
	private String riskCode;//险种代码
	private String riskName;//险种名称
	private String premium;//保单保费
	private String custNo;//客户号
	private String custName;//客户姓名
	private String respEndDate;//责任终止日期
	private String riskType;//险类",
	private String status;//注销退保标志" 0-正常；1-全单退保；2-满期退保；3-部分退保；4-注销
	private String clsLevel;// 客户等级 20141103 增加 客户等级需求增加
	private String licenseNo;//车牌号码
	private String curHandleStatus;//续保任务状态
	private String applicantName;//投保人姓名
	private String insuredName;//被保人姓名
	private String winBack;//是否赢回字段
	private String appDate;//投保日期
	private String trackStatus ;//续保状态	
	private int   riskTypeCode;
	private String agentCode;//代理人代码
	private String productCode;//产品代码
	private String proposalNo;//投保单号
	
	public int getRiskTypeCode() {
		return riskTypeCode;
	}
	public void setRiskTypeCode(int riskTypeCode) {
		this.riskTypeCode = riskTypeCode;
	}
	public String getTrackStatus() {
		return trackStatus;
	}
	public void setTrackStatus(String trackStatus) {
		this.trackStatus = trackStatus;
	}
	public String getWinBack() {
		return winBack;
	}
	public void setWinBack(String winBack) {
		this.winBack = winBack;
	}
	public String getMinRespEndDate() {
		return minRespEndDate;
	}
	public void setMinRespEndDate(String minRespEndDate) {
		this.minRespEndDate = minRespEndDate;
	}
	public String getMaxRespEndDate() {
		return maxRespEndDate;
	}
	public void setMaxRespEndDate(String maxRespEndDate) {
		this.maxRespEndDate = maxRespEndDate;
	}
	
	public String getCurHandleStatus() {
		return curHandleStatus;
	}
	public void setCurHandleStatus(String curHandleStatus) {
		this.curHandleStatus = curHandleStatus;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getRespEndDate() {
		return respEndDate;
	}
	public void setRespEndDate(String respEndDate) {
		this.respEndDate = respEndDate;
	}
	public String getRiskType() {
		return riskType;
	}
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClsLevel() {
		return clsLevel;
	}
	public void setClsLevel(String clsLevel) {
		this.clsLevel = clsLevel;
	}
	
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
    public String getLicenseNo() {
		return licenseNo;
	}
    public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
     public String getMinPayStartDate() {
		return minPayStartDate;
	}
	public void setMinPayStartDate(String minPayStartDate) {
		this.minPayStartDate = minPayStartDate;
	}
	public String getMaxPayStartDate() {
		return maxPayStartDate;
	}
	public void setMaxPayStartDate(String maxPayStartDate) {
		this.maxPayStartDate = maxPayStartDate;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	
	public String getContactorName() {
		return contactorName;
	}
	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

}