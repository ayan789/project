package com.ccic.salesapp.noncar.dto.request;



/**
 * *联合销售信息
 * @author Administrator
 *
 */
public class JointSaleVO {

	private String joinRiskCode;//联合销售险种代码	
	
	private String formulaCode;//方案代码
	
	private String formulaName;//方案名称
	
	private String mergePrint;//合并打印标示
	
	private int buyNum;//购买份数
	
	private InsuredJointVO insuredJointVO;//被保险人
	
	private String applicationNo;//投保单号
	
	private String policyNo;//保单号
	
	private double sumPremium;//保费总和
	
	private double sumAmount;//保额总和
	
	private String isAgentMode;//是否代理
	private String businessNature;//业务来源
	
	private String btbChannelCode;//代理人代码

	private String businessNature2;//业务来源2
	
	private String handler2Code;//业务风险分类
	
	private String agreementNo;//代理协议
	
	private String agreementName;//代理协议名称
	private String handler2Name;//业务风险分类名称
	private String btbChannelName;//代理人名称
	private String saleChannel ;//直销/直团渠道
	
	public String getBusinessNature2() {
		return businessNature2;
	}

	public void setBusinessNature2(String businessNature2) {
		this.businessNature2 = businessNature2;
	}

	public String getHandler2Code() {
		return handler2Code;
	}

	public void setHandler2Code(String handler2Code) {
		this.handler2Code = handler2Code;
	}

	public String getIsAgentMode() {
		return isAgentMode;
	}

	public void setIsAgentMode(String isAgentMode) {
		this.isAgentMode = isAgentMode;
	}

	public String getBusinessNature() {
		return businessNature;
	}

	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}

	public String getBtbChannelCode() {
		return btbChannelCode;
	}

	public void setBtbChannelCode(String btbChannelCode) {
		this.btbChannelCode = btbChannelCode;
	}

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	public String getJoinRiskCode() {
		return joinRiskCode;
	}

	public void setJoinRiskCode(String joinRiskCode) {
		this.joinRiskCode = joinRiskCode;
	}

	public String getFormulaCode() {
		return formulaCode;
	}

	public void setFormulaCode(String formulaCode) {
		this.formulaCode = formulaCode;
	}

	public String getMergePrint() {
		return mergePrint;
	}

	public void setMergePrint(String mergePrint) {
		this.mergePrint = mergePrint;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public InsuredJointVO getInsuredJointVO() {
		return insuredJointVO;
	}

	public void setInsuredJointVO(InsuredJointVO insuredJointVO) {
		this.insuredJointVO = insuredJointVO;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public double getSumPremium() {
		return sumPremium;
	}

	public void setSumPremium(double sumPremium) {
		this.sumPremium = sumPremium;
	}

	public double getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(double sumAmount) {
		this.sumAmount = sumAmount;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getHandler2Name() {
		return handler2Name;
	}

	public void setHandler2Name(String handler2Name) {
		this.handler2Name = handler2Name;
	}

	public String getBtbChannelName() {
		return btbChannelName;
	}

	public void setBtbChannelName(String btbChannelName) {
		this.btbChannelName = btbChannelName;
	}

	public String getSaleChannel() {
		return saleChannel;
	}

	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}
	
	
}
