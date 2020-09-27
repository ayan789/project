package com.ccic.salesapp.performance.domain.vo.dto;
import java.util.Date;
 

public class PlanFeePay {
	private String certiId;//业务id
	private String policyNo;//保单号
	private String certiNo;//业务号
	private String agentCode;//代理人代码
	private String riskCName;//险种名称
	private Double realPoliFee;//不含税保费
	private Double disRate;//手续费率
	private Double planFee;//手续费
	private String insuredName;//被保险人名称
	private String itemAttribute;//记录属性
	private String withdrawalsFlag;//提现状态
	private String withdrawalsMsg;//提现结果信息
	private Date withdrawalsDate;//提现日期
	private String packNo;//结算包号

	private String agreementNo;//协议号
	private String agentOrigin;//代理人来源
	private String branchCode;//账套机构
	private String handler1code;//业务归属人员
	private String billStatus;//结算状态:0 初始状态 1 已生成结算单 2 已打包 3 送费控 4 已支付 5 报销单审核通过
	private Date packageDate;//打包日期

	private String certiType;//业务类型
	private String commisionType;//手续费类型
	private String riskCode;//险种
	private String contractno;//合同号
	private String appliCode;//投保人代码
	private String appliName;//投保人名称
	private String insuredCode;//被保险人代码
	private Date startDate;//起保日期
	private Date endDate;//终保日期
	private Date underWriteDate;//保单核保通过日期
	private Date validDate;//生效日期（保单为起保日期、批单为生效日期
	private String comCode;//归属机构
	private String makeCom;//出单机构
	private String agentName;//代理人名称
	private String handler1Name;//业务归属人员名称
	private String handlerCode;//经办人
	private String businessNature;//业务渠道
	private String shareholderFlag;//业务性质
	private String carNatureCode;//交强险车辆九大类明细 
	private String writeOffFlag;//是否注销标识
	private String mainPolicyFlag;//是否组合产品标识
	private String maxDisRate;//最大手续费比例（修改上限）
	private String accBookCode;//帐套代码
	private String packFlag;//是否允许生成结算单
	private String packPrintFee;//已生成结算单金额(应收付币种)
	private String attribute5;//销售人员名称
	private String hD1ExhibitionNo;//业务员展业证号
	private String certifiCateNo;//中介机构许可证号/个人代理资格证号
	private String proJectSerialNo;//项目编码
	private String businessNature2;//业务渠道2代码
	private String businessNature2Name;//业务渠道2名称
	private String handler2Code;//经办人2代码
	private String handler2Name;//经办人2名称
	private String billNo;//结算单号
	private String reimNo;//报销单号
	private String payInfoStatus;//支付信息上传平台状态
	private String callBackFlag;//是否回访成功
	private String needCallBack;//是否需要回访
	private String agencyCode;//中介组织机构代码
	private String realPremium;//含税保费
	private Date auditDate;//报销单审核通过时间
	private String classCode;//险类代码
	private Integer start; 
	private Integer pageSize;
	private int count;
	 

	public String getCertiId() {
		return certiId;
	}
	public void setCertiId(String certiId) {
		this.certiId = certiId;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getCertiNo() {
		return certiNo;
	}
	public void setCertiNo(String certiNo) {
		this.certiNo = certiNo;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getRiskCName() {
		return riskCName;
	}
	public void setRiskCName(String riskCName) {
		this.riskCName = riskCName;
	}

	public Double getRealPoliFee() {
		return realPoliFee;
	}
	public void setRealPoliFee(Double realPoliFee) {
		this.realPoliFee = realPoliFee;
	}
	public Double getDisRate() {
		return disRate;
	}
	public void setDisRate(Double disRate) {
		this.disRate = disRate;
	}
	public Double getPlanFee() {
		return planFee;
	}
	public void setPlanFee(Double planFee) {
		this.planFee = planFee;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getItemAttribute() {
		return itemAttribute;
	}
	public void setItemAttribute(String itemAttribute) {
		this.itemAttribute = itemAttribute;
	}
	public String getWithdrawalsFlag() {
		return withdrawalsFlag;
	}
	public void setWithdrawalsFlag(String withdrawalsFlag) {
		this.withdrawalsFlag = withdrawalsFlag;
	}
	public String getWithdrawalsMsg() {
		return withdrawalsMsg;
	}
	public void setWithdrawalsMsg(String withdrawalsMsg) {
		this.withdrawalsMsg = withdrawalsMsg;
	}
	public Date getWithdrawalsDate() {
		return withdrawalsDate;
	}
	public void setWithdrawalsDate(Date withdrawalsDate) {
		this.withdrawalsDate = withdrawalsDate;
	}
	public String getPackNo() {
		return packNo;
	}
	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getAgentOrigin() {
		return agentOrigin;
	}
	public void setAgentOrigin(String agentOrigin) {
		this.agentOrigin = agentOrigin;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getHandler1code() {
		return handler1code;
	}
	public void setHandler1code(String handler1code) {
		this.handler1code = handler1code;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public Date getPackageDate() {
		return packageDate;
	}
	public void setPackageDate(Date packageDate) {
		this.packageDate = packageDate;
	}
	public String getCertiType() {
		return certiType;
	}
	public void setCertiType(String certiType) {
		this.certiType = certiType;
	}
	public String getCommisionType() {
		return commisionType;
	}
	public void setCommisionType(String commisionType) {
		this.commisionType = commisionType;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getContractno() {
		return contractno;
	}
	public void setContractno(String contractno) {
		this.contractno = contractno;
	}
	public String getAppliCode() {
		return appliCode;
	}
	public void setAppliCode(String appliCode) {
		this.appliCode = appliCode;
	}
	public String getAppliName() {
		return appliName;
	}
	public void setAppliName(String appliName) {
		this.appliName = appliName;
	}
	public String getInsuredCode() {
		return insuredCode;
	}
	public void setInsuredCode(String insuredCode) {
		this.insuredCode = insuredCode;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getUnderWriteDate() {
		return underWriteDate;
	}
	public void setUnderWriteDate(Date underWriteDate) {
		this.underWriteDate = underWriteDate;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getMakeCom() {
		return makeCom;
	}
	public void setMakeCom(String makeCom) {
		this.makeCom = makeCom;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getHandler1Name() {
		return handler1Name;
	}
	public void setHandler1Name(String handler1Name) {
		this.handler1Name = handler1Name;
	}
	public String getHandlerCode() {
		return handlerCode;
	}
	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode;
	}
	public String getBusinessNature() {
		return businessNature;
	}
	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}
	public String getShareholderFlag() {
		return shareholderFlag;
	}
	public void setShareholderFlag(String shareholderFlag) {
		this.shareholderFlag = shareholderFlag;
	}
	public String getCarNatureCode() {
		return carNatureCode;
	}
	public void setCarNatureCode(String carNatureCode) {
		this.carNatureCode = carNatureCode;
	}
	public String getWriteOffFlag() {
		return writeOffFlag;
	}
	public void setWriteOffFlag(String writeOffFlag) {
		this.writeOffFlag = writeOffFlag;
	}
	public String getMainPolicyFlag() {
		return mainPolicyFlag;
	}
	public void setMainPolicyFlag(String mainPolicyFlag) {
		this.mainPolicyFlag = mainPolicyFlag;
	}
	public String getMaxDisRate() {
		return maxDisRate;
	}
	public void setMaxDisRate(String maxDisRate) {
		this.maxDisRate = maxDisRate;
	}
	public String getAccBookCode() {
		return accBookCode;
	}
	public void setAccBookCode(String accBookCode) {
		this.accBookCode = accBookCode;
	}
	public String getPackFlag() {
		return packFlag;
	}
	public void setPackFlag(String packFlag) {
		this.packFlag = packFlag;
	}
	public String getPackPrintFee() {
		return packPrintFee;
	}
	public void setPackPrintFee(String packPrintFee) {
		this.packPrintFee = packPrintFee;
	}
	public String getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	public String gethD1ExhibitionNo() {
		return hD1ExhibitionNo;
	}
	public void sethD1ExhibitionNo(String hD1ExhibitionNo) {
		this.hD1ExhibitionNo = hD1ExhibitionNo;
	}
	public String getCertifiCateNo() {
		return certifiCateNo;
	}
	public void setCertifiCateNo(String certifiCateNo) {
		this.certifiCateNo = certifiCateNo;
	}
	public String getProJectSerialNo() {
		return proJectSerialNo;
	}
	public void setProJectSerialNo(String proJectSerialNo) {
		this.proJectSerialNo = proJectSerialNo;
	}
	public String getBusinessNature2() {
		return businessNature2;
	}
	public void setBusinessNature2(String businessNature2) {
		this.businessNature2 = businessNature2;
	}
	public String getBusinessNature2Name() {
		return businessNature2Name;
	}
	public void setBusinessNature2Name(String businessNature2Name) {
		this.businessNature2Name = businessNature2Name;
	}
	public String getHandler2Code() {
		return handler2Code;
	}
	public void setHandler2Code(String handler2Code) {
		this.handler2Code = handler2Code;
	}
	public String getHandler2Name() {
		return handler2Name;
	}
	public void setHandler2Name(String handler2Name) {
		this.handler2Name = handler2Name;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getReimNo() {
		return reimNo;
	}
	public void setReimNo(String reimNo) {
		this.reimNo = reimNo;
	}
	public String getPayInfoStatus() {
		return payInfoStatus;
	}
	public void setPayInfoStatus(String payInfoStatus) {
		this.payInfoStatus = payInfoStatus;
	}
	public String getCallBackFlag() {
		return callBackFlag;
	}
	public void setCallBackFlag(String callBackFlag) {
		this.callBackFlag = callBackFlag;
	}
	public String getNeedCallBack() {
		return needCallBack;
	}
	public void setNeedCallBack(String needCallBack) {
		this.needCallBack = needCallBack;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getRealPremium() {
		return realPremium;
	}
	public void setRealPremium(String realPremium) {
		this.realPremium = realPremium;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	 


}
