package com.ccic.salesapp.noncar.repository.basedb.po;

import java.math.BigDecimal;
import java.util.Date;

public class Order {

	private Long id;
	private String orderNo;
	private String orderStatus;
	private String isDelete;
	private String productCode;
	private String insureNo;
	private String policyNo;
	private String productName;
	private String userCode;
	private Date startDate;
	private Date endDate;
	private String identityCode;
	private String payApplyNo;
	private Long planId;
	private String comCode;
	private String pComCode;
	private Date underwriteDate;
	private BigDecimal sumPremium;
	private BigDecimal sumAmount;
	private Integer numberOfCopys;
	private Integer insuranceCount;
	private String handlerCode;
	private String operatorCode;
	private String operatorName;
	private Integer isAgent;
	private String agentCode;
	private String agentName;
	private String agreementNo;
	private String agreementName;
	private String belongToHandlerCode;
	private String handlerCode2;
	private String orderSource;
	private String signStatus;
	private String platformFlag;
	private String platformUserCode;
	private Date policyCreateDate;
	private Long relationOrderId;
	private Date payDate;
	private String isIssueAfterPay;
	private String mobile;
	private String applicantName;
	private Long strategyId;
	private String handlerName;
	private String businessSourceCode;
	private String businessSource2Code;
	private String businessAtureName;
	private String businessAttribute;
	private String policyNature;
	private String isNeedUploadImg;
	private String isNeedSign;
	private Double vat;
	private Double beforeVatPremium;
	private String strategyCode;
	private String planCode;
	private String insuranceCategory;
	private String policyS3Path;
	private String custNo;
	private String custSourceType;
	private String custType;
	private String invoiceFlag;
	private String relationPolicyNo;
	private String thirdPartyHandlerNo;
	private String thirdPartyHandlerName;
	private String dataTag;
	private String isGroupPlan;
	private String productLine;
	private BigDecimal premiumBeforeDiscount;
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;
	private String businessNatureName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus == null ? null : orderStatus.trim();
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete == null ? null : isDelete.trim();
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode == null ? null : productCode.trim();
	}

	public String getInsureNo() {
		return insureNo;
	}

	public void setInsureNo(String insureNo) {
		this.insureNo = insureNo == null ? null : insureNo.trim();
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo == null ? null : policyNo.trim();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode == null ? null : userCode.trim();
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

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode == null ? null : identityCode.trim();
	}

	public String getPayApplyNo() {
		return payApplyNo;
	}

	public void setPayApplyNo(String payApplyNo) {
		this.payApplyNo = payApplyNo == null ? null : payApplyNo.trim();
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode == null ? null : comCode.trim();
	}

	public String getpComCode() {
		return pComCode;
	}

	public void setpComCode(String pComCode) {
		this.pComCode = pComCode == null ? null : pComCode.trim();
	}

	public Date getUnderwriteDate() {
		return underwriteDate;
	}

	public void setUnderwriteDate(Date underwriteDate) {
		this.underwriteDate = underwriteDate;
	}

	public BigDecimal getSumPremium() {
		return sumPremium;
	}

	public void setSumPremium(BigDecimal sumPremium) {
		this.sumPremium = sumPremium;
	}

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public Integer getNumberOfCopys() {
		return numberOfCopys;
	}

	public void setNumberOfCopys(Integer numberOfCopys) {
		this.numberOfCopys = numberOfCopys;
	}

	public Integer getInsuranceCount() {
		return insuranceCount;
	}

	public void setInsuranceCount(Integer insuranceCount) {
		this.insuranceCount = insuranceCount;
	}

	public String getHandlerCode() {
		return handlerCode;
	}

	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode == null ? null : handlerCode.trim();
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode == null ? null : operatorCode.trim();
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName == null ? null : operatorName.trim();
	}

	public Integer getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(Integer isAgent) {
		this.isAgent = isAgent;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode == null ? null : agentCode.trim();
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName == null ? null : agentName.trim();
	}

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo == null ? null : agreementNo.trim();
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName == null ? null : agreementName.trim();
	}

	public String getBelongToHandlerCode() {
		return belongToHandlerCode;
	}

	public void setBelongToHandlerCode(String belongToHandlerCode) {
		this.belongToHandlerCode = belongToHandlerCode;
	}

	public String getHandlerCode2() {
		return handlerCode2;
	}

	public void setHandlerCode2(String handlerCode2) {
		this.handlerCode2 = handlerCode2 == null ? null : handlerCode2.trim();
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource == null ? null : orderSource.trim();
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus == null ? null : signStatus.trim();
	}

	public String getPlatformFlag() {
		return platformFlag;
	}

	public void setPlatformFlag(String platformFlag) {
		this.platformFlag = platformFlag == null ? null : platformFlag.trim();
	}

	public String getPlatformUserCode() {
		return platformUserCode;
	}

	public void setPlatformUserCode(String platformUserCode) {
		this.platformUserCode = platformUserCode == null ? null : platformUserCode.trim();
	}

	public Date getPolicyCreateDate() {
		return policyCreateDate;
	}

	public void setPolicyCreateDate(Date policyCreateDate) {
		this.policyCreateDate = policyCreateDate;
	}

	public Long getRelationOrderId() {
		return relationOrderId;
	}

	public void setRelationOrderId(Long relationOrderId) {
		this.relationOrderId = relationOrderId;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getIsIssueAfterPay() {
		return isIssueAfterPay;
	}

	public void setIsIssueAfterPay(String isIssueAfterPay) {
		this.isIssueAfterPay = isIssueAfterPay == null ? null : isIssueAfterPay.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName == null ? null : applicantName.trim();
	}

	public Long getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(Long strategyId) {
		this.strategyId = strategyId;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName == null ? null : handlerName.trim();
	}

	public String getBusinessSourceCode() {
		return businessSourceCode;
	}

	public void setBusinessSourceCode(String businessSourceCode) {
		this.businessSourceCode = businessSourceCode == null ? null : businessSourceCode.trim();
	}

	public String getBusinessSource2Code() {
		return businessSource2Code;
	}

	public void setBusinessSource2Code(String businessSource2Code) {
		this.businessSource2Code = businessSource2Code == null ? null : businessSource2Code.trim();
	}

	public String getBusinessAtureName() {
		return businessAtureName;
	}

	public void setBusinessAtureName(String businessAtureName) {
		this.businessAtureName = businessAtureName == null ? null : businessAtureName.trim();
	}

	public String getBusinessAttribute() {
		return businessAttribute;
	}

	public void setBusinessAttribute(String businessAttribute) {
		this.businessAttribute = businessAttribute == null ? null : businessAttribute.trim();
	}

	public String getPolicyNature() {
		return policyNature;
	}

	public void setPolicyNature(String policyNature) {
		this.policyNature = policyNature == null ? null : policyNature.trim();
	}

	public String getIsNeedUploadImg() {
		return isNeedUploadImg;
	}

	public void setIsNeedUploadImg(String isNeedUploadImg) {
		this.isNeedUploadImg = isNeedUploadImg == null ? null : isNeedUploadImg.trim();
	}

	public String getIsNeedSign() {
		return isNeedSign;
	}

	public void setIsNeedSign(String isNeedSign) {
		this.isNeedSign = isNeedSign == null ? null : isNeedSign.trim();
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public Double getBeforeVatPremium() {
		return beforeVatPremium;
	}

	public void setBeforeVatPremium(Double beforeVatPremium) {
		this.beforeVatPremium = beforeVatPremium;
	}

	public String getStrategyCode() {
		return strategyCode;
	}

	public void setStrategyCode(String strategyCode) {
		this.strategyCode = strategyCode == null ? null : strategyCode.trim();
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode == null ? null : planCode.trim();
	}

	public String getInsuranceCategory() {
		return insuranceCategory;
	}

	public void setInsuranceCategory(String insuranceCategory) {
		this.insuranceCategory = insuranceCategory == null ? null : insuranceCategory.trim();
	}

	public String getPolicyS3Path() {
		return policyS3Path;
	}

	public void setPolicyS3Path(String policyS3Path) {
		this.policyS3Path = policyS3Path == null ? null : policyS3Path.trim();
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo == null ? null : custNo.trim();
	}

	public String getCustSourceType() {
		return custSourceType;
	}

	public void setCustSourceType(String custSourceType) {
		this.custSourceType = custSourceType == null ? null : custSourceType.trim();
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType == null ? null : custType.trim();
	}

	public String getInvoiceFlag() {
		return invoiceFlag;
	}

	public void setInvoiceFlag(String invoiceFlag) {
		this.invoiceFlag = invoiceFlag == null ? null : invoiceFlag.trim();
	}

	public String getRelationPolicyNo() {
		return relationPolicyNo;
	}

	public void setRelationPolicyNo(String relationPolicyNo) {
		this.relationPolicyNo = relationPolicyNo == null ? null : relationPolicyNo.trim();
	}

	public String getThirdPartyHandlerNo() {
		return thirdPartyHandlerNo;
	}

	public void setThirdPartyHandlerNo(String thirdPartyHandlerNo) {
		this.thirdPartyHandlerNo = thirdPartyHandlerNo == null ? null : thirdPartyHandlerNo.trim();
	}

	public String getThirdPartyHandlerName() {
		return thirdPartyHandlerName;
	}

	public void setThirdPartyHandlerName(String thirdPartyHandlerName) {
		this.thirdPartyHandlerName = thirdPartyHandlerName == null ? null : thirdPartyHandlerName.trim();
	}

	public String getDataTag() {
		return dataTag;
	}

	public void setDataTag(String dataTag) {
		this.dataTag = dataTag == null ? null : dataTag.trim();
	}

	public String getIsGroupPlan() {
		return isGroupPlan;
	}

	public void setIsGroupPlan(String isGroupPlan) {
		this.isGroupPlan = isGroupPlan == null ? null : isGroupPlan.trim();
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine == null ? null : productLine.trim();
	}

	public BigDecimal getPremiumBeforeDiscount() {
		return premiumBeforeDiscount;
	}

	public void setPremiumBeforeDiscount(BigDecimal premiumBeforeDiscount) {
		this.premiumBeforeDiscount = premiumBeforeDiscount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser == null ? null : updateUser.trim();
	}

	public String getBusinessNatureName() {
		return businessNatureName;
	}

	public void setBusinessNatureName(String businessNatureName) {
		this.businessNatureName = businessNatureName == null ? null : businessNatureName.trim();
	}

}