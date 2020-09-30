package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.math.BigDecimal;
import java.util.List;

import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;

public class AccidentQuoteResponse extends ResponseBody{
	//投保单号
			private String proposalNo;
			//业务来源属性
			private String businessAttribute;
			//保单性质（团单标记，区分个团）
			private String policyNature;
			//分户类型(被保险人清单类型)
			private String insuredListType;
			//产品代码
			private String productCode;
			//请求业务类型
			private String businessType;
			//归属机构代码
			private String orgCode;
			//出单机构代码
			private String issueOrgCode;
			//出单员代码
			private String issueUserCode;
			//出单点地址
			private String issueAddress;
			//业务来源1/BusinessNature1，对应归属经办人的业务来源，存放末级的业务原来
			private String businessSourceCode;
			//业务来源2/BusinessNature，对应归属经办人2的业务来源生成，非前台录入
			private String businessSource2Code;
			//经办人编码
			private String handlerCode;
			private String handlerName;   //经办人名称
			//代理人ID，仅在非直销业务中使用
			private String agentCode;
			//代理人协议号
			private String agreementNo;
			//业务编码(RepairChannelCode)/Q码
			private String repairChannelCode;
			//起保日期/起始生效日期,支持时分秒
			private String effectiveDate;
			//终保日期，保单止期/止保日期/失效日期，不包含这个时间点在内
			private String expiryDate;
			//投保日期
			private String proposalDate;
			//定期结算方式
			private String regularSettlementModeCode;
			//是否定期结算标识
			private String isRegularSettlement;
			//定期结算日期
			private String regularSettlementDate;
			//定期结算最晚结算日期
			private String latestRegularSettleDate;
			//保额币种
			private String siCurrencyCode;
			//保额
			private String sumInsured;
			//保费币种
			private String premiumCurrencyCode;
			//应收保费/真实保费/RealPremium,=调整保费
			private String duePremium;
			//总折扣率
			private BigDecimal totalDiscountRate;
			//短期费率
			private String shortRate;
			//共保标记（主共/从共）
			private String coInsuranceType;
			//联保标记（主联/从联）
			private String internalCoInsuranceType;
			//项目编码
			private String projectNo;
			//农业性质
			private String agricultureNatureCode;
			//是否涉农标识
			private String agricultureRelaType;
			//是否发送电子保单标志
			private String isSendEPolicy;
			//电子保单模板，意健险需要使用
			private Long electronicPolicyTemplateCode;
			//保单打印方式，电子保单/纸质保单
			private Long policyPrintType;
			//是否发送短信
			private String isSendSms;
			//短信发送对象
			private String smsSendCustRoleCode;
			//短信模板ID
			private Long smsTemplateId;
			//是否加载签证函
			private String isLoadVisaLetter;
			//是否招标投标
			private String isBid;
			//是否政府业务
			private String isGovernmentBiz;
			//是否大额团单
			private String isLargeGroupPolicy;
			//是否小额保险
			private String isSmallAmount;
			//境内外业务标识
			private String overseaBusinessType;
			//特殊标识代码(见费出单相关信息)
			private String specialFlagCode;
			//仲裁委员会代码
			private String arbitrationCommissionCode;
			//争议解决方式
			private String argueSolutionType;
			//司法管辖
			private String judicalScopeCode;
			//是否见费出单标志
			private String isIssueAfterPay;
			//单证流水号
			private String printNo;
			//主险保费
			private BigDecimal sumMainPrem;
			//附加险保费
			private BigDecimal sumSubPrem;
			//总折扣金额
			private BigDecimal sumDisCount;
			//手续费比例
			private BigDecimal commission;
			//最高手续费比例
			private BigDecimal topCommission;
			//短期费率方式
			private String shortRateType;
			private BigDecimal vat;//VAT金额(增值税)
			private BigDecimal beforeVatPremium;
			public BigDecimal getBeforeVatPremium() {
				return beforeVatPremium;
			}
			public void setBeforeVatPremium(BigDecimal beforeVatPremium) {
				this.beforeVatPremium = beforeVatPremium;
			}
			public String getProposalStatus() {
				return proposalStatus;
			}
			public void setProposalStatus(String proposalStatus) {
				this.proposalStatus = proposalStatus;
			}
			private String secondLine; //产品二级险类
			private String isPushMedicalInsurance; //是否推送医保通
			private String isSpecialGroup;//是否特定团体
		    private String policyNo;//保单号
		    private String agentName;//代理人姓名
		    private String orgname;       //归属机构名称
		    private String isSalesFla;    //是否是销售人员
		    private String issueusercode; //出单员
		    private String issueUserName; //出单员姓名
		    private String issueOrgName;  //出单机构名称
		    private String productName; //产品名称
		    private String belongToHandlerCode;//代表归属经办人
		    private String belongToHandlerName;//归属经办人名称
		    private String belongToHandler2Code;//代表业务风险分类
		    private String belongToHandler2Name;//业务风险分类名称
		    private String proposalStatus; //保单状态̬
	    
	    
		  //渠道信息
			private List<ChannelOpInfo> channelOpInfoList;
			//投保人信息
			private List<PolicyCustomer> policyCustomerList;
			//标的信息
			private List<PolicyLob> policyLobList;
			//农业信息
			private List<AgriSubsidy> agriSubsidyList;
			//发票信息
			private List<InvoiceInfo> invoiceInfoList;
			//
			private List<PolicyPaymentInfo> policyPaymentInfoList;
			//单证信息列表
			private List<VisaInfo> visaInfoList;
			//联保信息
			private List<InnerCoInsuranceInfo> innerCoInsuranceInfoList;
		
		public String getProposalNo() {
			return proposalNo;
		}
		public void setProposalNo(String proposalNo) {
			this.proposalNo = proposalNo;
		}
		public String getBusinessAttribute() {
			return businessAttribute;
		}
		public void setBusinessAttribute(String businessAttribute) {
			this.businessAttribute = businessAttribute;
		}
		public String getPolicyNature() {
			return policyNature;
		}
		public void setPolicyNature(String policyNature) {
			this.policyNature = policyNature;
		}
		public String getInsuredListType() {
			return insuredListType;
		}
		public void setInsuredListType(String insuredListType) {
			this.insuredListType = insuredListType;
		}
		public String getProductCode() {
			return productCode;
		}
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		public String getBusinessType() {
			return businessType;
		}
		public void setBusinessType(String businessType) {
			this.businessType = businessType;
		}
		public String getIssueOrgCode() {
			return issueOrgCode;
		}
		public void setIssueOrgCode(String issueOrgCode) {
			this.issueOrgCode = issueOrgCode;
		}
		public String getIssueAddress() {
			return issueAddress;
		}
		public void setIssueAddress(String issueAddress) {
			this.issueAddress = issueAddress;
		}
		public String getBusinessSourceCode() {
			return businessSourceCode;
		}
		public void setBusinessSourceCode(String businessSourceCode) {
			this.businessSourceCode = businessSourceCode;
		}
		public String getBusinessSource2Code() {
			return businessSource2Code;
		}
		public void setBusinessSource2Code(String businessSource2Code) {
			this.businessSource2Code = businessSource2Code;
		}
		
		public String getAgentCode() {
			return agentCode;
		}
		public void setAgentCode(String agentCode) {
			this.agentCode = agentCode;
		}
		public String getAgreementNo() {
			return agreementNo;
		}
		public void setAgreementNo(String agreementNo) {
			this.agreementNo = agreementNo;
		}
		public String getRepairChannelCode() {
			return repairChannelCode;
		}
		public void setRepairChannelCode(String repairChannelCode) {
			this.repairChannelCode = repairChannelCode;
		}
		
		public String getProposalDate() {
			return proposalDate;
		}
		public void setProposalDate(String proposalDate) {
			this.proposalDate = proposalDate;
		}
		public String getRegularSettlementModeCode() {
			return regularSettlementModeCode;
		}
		public void setRegularSettlementModeCode(String regularSettlementModeCode) {
			this.regularSettlementModeCode = regularSettlementModeCode;
		}
		public String getIsRegularSettlement() {
			return isRegularSettlement;
		}
		public void setIsRegularSettlement(String isRegularSettlement) {
			this.isRegularSettlement = isRegularSettlement;
		}
		public String getSiCurrencyCode() {
			return siCurrencyCode;
		}
		public String getRegularSettlementDate() {
			return regularSettlementDate;
		}
		public void setRegularSettlementDate(String regularSettlementDate) {
			this.regularSettlementDate = regularSettlementDate;
		}
		public String getLatestRegularSettleDate() {
			return latestRegularSettleDate;
		}
		public void setLatestRegularSettleDate(String latestRegularSettleDate) {
			this.latestRegularSettleDate = latestRegularSettleDate;
		}
		public void setSiCurrencyCode(String siCurrencyCode) {
			this.siCurrencyCode = siCurrencyCode;
		}
		public String getPremiumCurrencyCode() {
			return premiumCurrencyCode;
		}
		public void setPremiumCurrencyCode(String premiumCurrencyCode) {
			this.premiumCurrencyCode = premiumCurrencyCode;
		}
		public BigDecimal getTotalDiscountRate() {
			return totalDiscountRate;
		}
		public void setTotalDiscountRate(BigDecimal totalDiscountRate) {
			this.totalDiscountRate = totalDiscountRate;
		}
		public String getCoInsuranceType() {
			return coInsuranceType;
		}
		public void setCoInsuranceType(String coInsuranceType) {
			this.coInsuranceType = coInsuranceType;
		}
		public String getInternalCoInsuranceType() {
			return internalCoInsuranceType;
		}
		public void setInternalCoInsuranceType(String internalCoInsuranceType) {
			this.internalCoInsuranceType = internalCoInsuranceType;
		}
		public String getProjectNo() {
			return projectNo;
		}
		public void setProjectNo(String projectNo) {
			this.projectNo = projectNo;
		}
		public String getAgricultureNatureCode() {
			return agricultureNatureCode;
		}
		public void setAgricultureNatureCode(String agricultureNatureCode) {
			this.agricultureNatureCode = agricultureNatureCode;
		}
		public String getAgricultureRelaType() {
			return agricultureRelaType;
		}
		public void setAgricultureRelaType(String agricultureRelaType) {
			this.agricultureRelaType = agricultureRelaType;
		}
		public String getIsSendEPolicy() {
			return isSendEPolicy;
		}
		public void setIsSendEPolicy(String isSendEPolicy) {
			this.isSendEPolicy = isSendEPolicy;
		}
		public Long getElectronicPolicyTemplateCode() {
			return electronicPolicyTemplateCode;
		}
		public void setElectronicPolicyTemplateCode(Long electronicPolicyTemplateCode) {
			this.electronicPolicyTemplateCode = electronicPolicyTemplateCode;
		}
		public Long getPolicyPrintType() {
			return policyPrintType;
		}
		public void setPolicyPrintType(Long policyPrintType) {
			this.policyPrintType = policyPrintType;
		}
		public String getIsSendSms() {
			return isSendSms;
		}
		public void setIsSendSms(String isSendSms) {
			this.isSendSms = isSendSms;
		}
		public String getSmsSendCustRoleCode() {
			return smsSendCustRoleCode;
		}
		public void setSmsSendCustRoleCode(String smsSendCustRoleCode) {
			this.smsSendCustRoleCode = smsSendCustRoleCode;
		}
		public Long getSmsTemplateId() {
			return smsTemplateId;
		}
		public void setSmsTemplateId(Long smsTemplateId) {
			this.smsTemplateId = smsTemplateId;
		}
		public String getIsLoadVisaLetter() {
			return isLoadVisaLetter;
		}
		public void setIsLoadVisaLetter(String isLoadVisaLetter) {
			this.isLoadVisaLetter = isLoadVisaLetter;
		}
		public String getIsBid() {
			return isBid;
		}
		public void setIsBid(String isBid) {
			this.isBid = isBid;
		}
		public String getIsGovernmentBiz() {
			return isGovernmentBiz;
		}
		public void setIsGovernmentBiz(String isGovernmentBiz) {
			this.isGovernmentBiz = isGovernmentBiz;
		}
		public String getIsLargeGroupPolicy() {
			return isLargeGroupPolicy;
		}
		public void setIsLargeGroupPolicy(String isLargeGroupPolicy) {
			this.isLargeGroupPolicy = isLargeGroupPolicy;
		}
		public String getIsSmallAmount() {
			return isSmallAmount;
		}
		public void setIsSmallAmount(String isSmallAmount) {
			this.isSmallAmount = isSmallAmount;
		}
		public String getOverseaBusinessType() {
			return overseaBusinessType;
		}
		public void setOverseaBusinessType(String overseaBusinessType) {
			this.overseaBusinessType = overseaBusinessType;
		}
		public String getSpecialFlagCode() {
			return specialFlagCode;
		}
		public void setSpecialFlagCode(String specialFlagCode) {
			this.specialFlagCode = specialFlagCode;
		}
		public String getArbitrationCommissionCode() {
			return arbitrationCommissionCode;
		}
		public void setArbitrationCommissionCode(String arbitrationCommissionCode) {
			this.arbitrationCommissionCode = arbitrationCommissionCode;
		}
		public String getArgueSolutionType() {
			return argueSolutionType;
		}
		public void setArgueSolutionType(String argueSolutionType) {
			this.argueSolutionType = argueSolutionType;
		}
		public String getJudicalScopeCode() {
			return judicalScopeCode;
		}
		public void setJudicalScopeCode(String judicalScopeCode) {
			this.judicalScopeCode = judicalScopeCode;
		}
		public String getIsIssueAfterPay() {
			return isIssueAfterPay;
		}
		public void setIsIssueAfterPay(String isIssueAfterPay) {
			this.isIssueAfterPay = isIssueAfterPay;
		}
		public String getPrintNo() {
			return printNo;
		}
		public void setPrintNo(String printNo) {
			this.printNo = printNo;
		}
		public BigDecimal getSumMainPrem() {
			return sumMainPrem;
		}
		public void setSumMainPrem(BigDecimal sumMainPrem) {
			this.sumMainPrem = sumMainPrem;
		}
		public BigDecimal getSumSubPrem() {
			return sumSubPrem;
		}
		public void setSumSubPrem(BigDecimal sumSubPrem) {
			this.sumSubPrem = sumSubPrem;
		}
		public BigDecimal getSumDisCount() {
			return sumDisCount;
		}
		public void setSumDisCount(BigDecimal sumDisCount) {
			this.sumDisCount = sumDisCount;
		}
		public BigDecimal getCommission() {
			return commission;
		}
		public void setCommission(BigDecimal commission) {
			this.commission = commission;
		}
		public BigDecimal getTopCommission() {
			return topCommission;
		}
		public void setTopCommission(BigDecimal topCommission) {
			this.topCommission = topCommission;
		}
		public List<ChannelOpInfo> getChannelOpInfoList() {
			return channelOpInfoList;
		}
		public void setChannelOpInfoList(List<ChannelOpInfo> channelOpInfoList) {
			this.channelOpInfoList = channelOpInfoList;
		}
		public List<PolicyCustomer> getPolicyCustomerList() {
			return policyCustomerList;
		}
		public void setPolicyCustomerList(List<PolicyCustomer> policyCustomerList) {
			this.policyCustomerList = policyCustomerList;
		}
		public List<PolicyLob> getPolicyLobList() {
			return policyLobList;
		}
		public void setPolicyLobList(List<PolicyLob> policyLobList) {
			this.policyLobList = policyLobList;
		}
		public List<AgriSubsidy> getAgriSubsidyList() {
			return agriSubsidyList;
		}
		public void setAgriSubsidyList(List<AgriSubsidy> agriSubsidyList) {
			this.agriSubsidyList = agriSubsidyList;
		}
		public List<InvoiceInfo> getInvoiceInfoList() {
			return invoiceInfoList;
		}
		public void setInvoiceInfoList(List<InvoiceInfo> invoiceInfoList) {
			this.invoiceInfoList = invoiceInfoList;
		}
		public List<PolicyPaymentInfo> getPolicyPaymentInfoList() {
			return policyPaymentInfoList;
		}
		public void setPolicyPaymentInfoList(
				List<PolicyPaymentInfo> policyPaymentInfoList) {
			this.policyPaymentInfoList = policyPaymentInfoList;
		}
		public String getEffectiveDate() {
			return effectiveDate;
		}
		public void setEffectiveDate(String effectiveDate) {
			this.effectiveDate = effectiveDate;
		}
		public String getExpiryDate() {
			return expiryDate;
		}
		public void setExpiryDate(String expiryDate) {
			this.expiryDate = expiryDate;
		}
		public String getOrgCode() {
			return orgCode;
		}
		public void setOrgCode(String orgCode) {
			this.orgCode = orgCode;
		}
		public String getIssueUserCode() {
			return issueUserCode;
		}
		public void setIssueUserCode(String issueUserCode) {
			this.issueUserCode = issueUserCode;
		}
		public String getHandlerCode() {
			return handlerCode;
		}
		public void setHandlerCode(String handlerCode) {
			this.handlerCode = handlerCode;
		}
		
		public String getDuePremium() {
			return duePremium;
		}
		public void setDuePremium(String duePremium) {
			this.duePremium = duePremium;
		}
		public String getShortRate() {
			return shortRate;
		}
		public void setShortRate(String shortRate) {
			this.shortRate = shortRate;
		}
		public String getSumInsured() {
			return sumInsured;
		}
		public void setSumInsured(String sumInsured) {
			this.sumInsured = sumInsured;
		}
		public String getShortRateType() {
			return shortRateType;
		}
		public void setShortRateType(String shortRateType) {
			this.shortRateType = shortRateType;
		}
		public BigDecimal getVat() {
			return vat;
		}
		public void setVat(BigDecimal vat) {
			this.vat = vat;
		}
		public String getSecondLine() {
			return secondLine;
		}
		public void setSecondLine(String secondLine) {
			this.secondLine = secondLine;
		}
		public String getIsPushMedicalInsurance() {
			return isPushMedicalInsurance;
		}
		public void setIsPushMedicalInsurance(String isPushMedicalInsurance) {
			this.isPushMedicalInsurance = isPushMedicalInsurance;
		}
		public String getIsSpecialGroup() {
			return isSpecialGroup;
		}
		public void setIsSpecialGroup(String isSpecialGroup) {
			this.isSpecialGroup = isSpecialGroup;
		}
		public String getPolicyNo() {
			return policyNo;
		}
		public void setPolicyNo(String policyNo) {
			this.policyNo = policyNo;
		}
		public String getHandlerName() {
			return handlerName;
		}
		public void setHandlerName(String handlerName) {
			this.handlerName = handlerName;
		}
		public String getIssueUserName() {
			return issueUserName;
		}
		public void setIssueUserName(String issueUserName) {
			this.issueUserName = issueUserName;
		}
		public String getIssueOrgName() {
			return issueOrgName;
		}
		public void setIssueOrgName(String issueOrgName) {
			this.issueOrgName = issueOrgName;
		}
		public String getOrgname() {
			return orgname;
		}
		public void setOrgname(String orgname) {
			this.orgname = orgname;
		}
		public String getIsSalesFla() {
			return isSalesFla;
		}
		public void setIsSalesFla(String isSalesFla) {
			this.isSalesFla = isSalesFla;
		}
		public String getIssueusercode() {
			return issueusercode;
		}
		public void setIssueusercode(String issueusercode) {
			this.issueusercode = issueusercode;
		}
		public String getAgentName() {
			return agentName;
		}
		public void setAgentName(String agentName) {
			this.agentName = agentName;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public String getBelongToHandlerCode() {
			return belongToHandlerCode;
		}
		public void setBelongToHandlerCode(String belongToHandlerCode) {
			this.belongToHandlerCode = belongToHandlerCode;
		}
		public String getBelongToHandlerName() {
			return belongToHandlerName;
		}
		public void setBelongToHandlerName(String belongToHandlerName) {
			this.belongToHandlerName = belongToHandlerName;
		}
		public String getBelongToHandler2Code() {
			return belongToHandler2Code;
		}
		public void setBelongToHandler2Code(String belongToHandler2Code) {
			this.belongToHandler2Code = belongToHandler2Code;
		}
		public String getBelongToHandler2Name() {
			return belongToHandler2Name;
		}
		public void setBelongToHandler2Name(String belongToHandler2Name) {
			this.belongToHandler2Name = belongToHandler2Name;
		}
		public List<VisaInfo> getVisaInfoList() {
			return visaInfoList;
		}
		public void setVisaInfoList(List<VisaInfo> visaInfoList) {
			this.visaInfoList = visaInfoList;
		}
		public List<InnerCoInsuranceInfo> getInnerCoInsuranceInfoList() {
			return innerCoInsuranceInfoList;
		}
		public void setInnerCoInsuranceInfoList(
				List<InnerCoInsuranceInfo> innerCoInsuranceInfoList) {
			this.innerCoInsuranceInfoList = innerCoInsuranceInfoList;
		}
		
}	