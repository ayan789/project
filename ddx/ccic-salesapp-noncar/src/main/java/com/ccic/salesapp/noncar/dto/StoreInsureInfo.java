package com.ccic.salesapp.noncar.dto;
import java.util.Date;

import com.ccic.salesapp.noncar.dto.request.DataVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreInsureInfo extends DataVO{

	@ApiModelProperty("物理主键")
	private String  id;

	@ApiModelProperty("投保单号")
	private String insureNo;
	
	@ApiModelProperty("订单号")
	private String orderNo;

	@ApiModelProperty("保单号")
	private String policyNo;

	@ApiModelProperty("险种代码")
	private String insuranceCode;

	@ApiModelProperty("险种名称")
	private String insuranceName;

	@ApiModelProperty("记录创建日期")
	private Date createDate;

	@ApiModelProperty("保单起期")
	private Date startDate;

	@ApiModelProperty("保单止期")
	private Date  endDate;

	@ApiModelProperty("保单起时")
	private String startTime;

	@ApiModelProperty("保单止时")
	private String endTime;

	@ApiModelProperty("保险期限")
	private String insurancePeriod;

	@ApiModelProperty("核保日期")
	private Date   underwriteDate;

	@ApiModelProperty("总保费")
	private String sumPremium;

	@ApiModelProperty("总保额")
	private String sumAmount;

	@ApiModelProperty("业务归属")
	private String businessType;

	@ApiModelProperty("投保单状态")
	private String status;

	@ApiModelProperty("所属用户")
	private String userId;

	@ApiModelProperty("投保份数")
	private int insuranceCount;

	@ApiModelProperty("车牌号")
	private String licensePlateNo;

	@ApiModelProperty("业务归属机构")
	private String comCode;
	
	@ApiModelProperty("业务归属机构所属分公司")
	private String pComCode;

	@ApiModelProperty("经办人代码")
	private String handlerCode;

	@ApiModelProperty("操作员代码")
	private String operatorCode;

	@ApiModelProperty("操作员名称")
	private String operatorName;

	@ApiModelProperty("支付号")
	private String payApplyNo;

	@ApiModelProperty("支付验证码")
	private String identityCode;

	@ApiModelProperty("投保人姓名")
	private String applicantName;

	@ApiModelProperty("投保人身份证号码")
	private String appliIdentify;

	@ApiModelProperty("投保人证件类型")
	private String appliIdentifyType;

	@ApiModelProperty("被保人姓名")
	private String insuredName;

	@ApiModelProperty("被保人身份证号码")
	private String insuredIdentify;

	@ApiModelProperty("被保险人证件类型")
	private String insuredIdentifyType;

	@ApiModelProperty("出生日期")
	private Date birthDate;

	@ApiModelProperty("手机号")
	private String mobileNo;

	@ApiModelProperty("地址")
	private String address;

	@ApiModelProperty("邮箱")
	private String email;

	@ApiModelProperty("方案代码")
	private String formulaCode;

	@ApiModelProperty("方案名称")
	private String formulaName;

	@ApiModelProperty("与被保险人的关系")
	private String relateInsured;

	@ApiModelProperty("设备号")
	private String imei;

	@ApiModelProperty("代理人代码")
	private String agentCode;	

	@ApiModelProperty("代理人协议号")
	private String agreementNo;

	@ApiModelProperty("代理人协议名称")
	private String agreementName;

	@ApiModelProperty("保险类别")
	private String insuranceType;

	@ApiModelProperty("签名状态")
	private String signStatus;

	@ApiModelProperty("影像ID")
	private String imgId;

	@ApiModelProperty("附加险是否选中代码以_分割")
	private String additionalCode;

	@ApiModelProperty("经办人2 ")
	private String handlerCode2;

	@ApiModelProperty("加密后的投保单号")
	private String desProposalNo;

	@ApiModelProperty("是否app出单")
	private String isRunApp;

	@ApiModelProperty("平台标识 1-新华出单")
	private String platformFlag;

	@ApiModelProperty("新华用户标识 ")
	private String nclUserCode;
	
	@ApiModelProperty("投保人生日 ")
	private Date applicantBirthDate;


	//监护人责任险
	@ApiModelProperty("被监护人性别")
	private String beGuardianSex;

	@ApiModelProperty("被监护人生日")
	private Date beGuardianBirthDate;

	@ApiModelProperty("产品代码")
	private String productCode;

	@ApiModelProperty("露天展台 0露天 1非露天")
	private String isOpenair;

	@ApiModelProperty("展位编号")
	private String showNo;

	@ApiModelProperty("展会名称")
	private String showName;

	@ApiModelProperty("能否支付：1是0否")
	private String payFlag;

	@ApiModelProperty("省份代码")
	private String provinceCode;

	@ApiModelProperty("辖区代码")
	private String prefectureCode;

	@ApiModelProperty("国家代码")
	private String countyCode;

	@ApiModelProperty("产品名称")
	private String productName;

	@ApiModelProperty("展会面积")
	private String exhibitionArea;

	@ApiModelProperty("被保人手机号码")
	private String insuredMobile;

	@ApiModelProperty("关联投保单ID")
	private Long associatedInsureID;
	
	@ApiModelProperty("客户号")
	private String custNo ; 
	
	@ApiModelProperty("客户标识  1本地客户,2保单客户")
	private final String custSourceType="2"; 
	
	@ApiModelProperty("客户类型")
	private String custType;
	
	@ApiModelProperty("社保类型")
	private String insuredSheBaoType;
	
	@ApiModelProperty("1:见费  0：非见费")
	private String isSeeFee;
	
	@ApiModelProperty("保单创建时间")
	private Date policyCreateDate;
	
	@ApiModelProperty("订单来源类型")
	private String orderSource;
	
	@ApiModelProperty("是否需要签名")
	private String isNeedSign;
	
	@ApiModelProperty("是否需要上传影像")
	private String isNeedUploadImg;
	
	@ApiModelProperty("产品id")
	private String strategyId;
	
	@ApiModelProperty("历史保单号")
	private String relationPolicyNo;
	
	@ApiModelProperty("上架方案数量")
	private Integer onlinePlanNum;
	
	@ApiModelProperty("产品销售代码")
	private String strategyCode;
	
	
	@ApiModelProperty("代理人名称")
	private String agentName;
	@ApiModelProperty("业务来源1")
	private String businessNature;
	@ApiModelProperty("业务来源2")
	private String businessNature2;
	@ApiModelProperty("经办人2名称")
	private String handler2Name;
	@ApiModelProperty("第三方业务员工号")
	private String thirdPartyHandlerNo;
	@ApiModelProperty("第三方业务员姓名")
	private String thirdPartyHandlerName;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getInsureNo() {
		return insureNo;
	}

	public void setInsureNo(String insureNo) {
		this.insureNo = insureNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getInsuranceCode() {
		return insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getInsurancePeriod() {
		return insurancePeriod;
	}

	public void setInsurancePeriod(String insurancePeriod) {
		this.insurancePeriod = insurancePeriod;
	}

	public Date getUnderwriteDate() {
		return underwriteDate;
	}

	public void setUnderwriteDate(Date underwriteDate) {
		this.underwriteDate = underwriteDate;
	}

	public String getSumPremium() {
		return sumPremium;
	}

	public void setSumPremium(String sumPremium) {
		this.sumPremium = sumPremium;
	}

	public String getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getInsuranceCount() {
		return insuranceCount;
	}

	public void setInsuranceCount(int insuranceCount) {
		this.insuranceCount = insuranceCount;
	}

	public String getLicensePlateNo() {
		return licensePlateNo;
	}

	public void setLicensePlateNo(String licensePlateNo) {
		this.licensePlateNo = licensePlateNo;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getHandlerCode() {
		return handlerCode;
	}

	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getAppliIdentify() {
		return appliIdentify;
	}

	public void setAppliIdentify(String appliIdentify) {
		this.appliIdentify = appliIdentify;
	}

	public String getAppliIdentifyType() {
		return appliIdentifyType;
	}

	public void setAppliIdentifyType(String appliIdentifyType) {
		this.appliIdentifyType = appliIdentifyType;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getInsuredIdentify() {
		return insuredIdentify;
	}

	public void setInsuredIdentify(String insuredIdentify) {
		this.insuredIdentify = insuredIdentify;
	}

	public String getInsuredIdentifyType() {
		return insuredIdentifyType;
	}

	public void setInsuredIdentifyType(String insuredIdentifyType) {
		this.insuredIdentifyType = insuredIdentifyType;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFormulaCode() {
		return formulaCode;
	}

	public void setFormulaCode(String formulaCode) {
		this.formulaCode = formulaCode;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public String getRelateInsured() {
		return relateInsured;
	}

	public void setRelateInsured(String relateInsured) {
		this.relateInsured = relateInsured;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
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

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getAdditionalCode() {
		return additionalCode;
	}

	public void setAdditionalCode(String additionalCode) {
		this.additionalCode = additionalCode;
	}

	public String getHandlerCode2() {
		return handlerCode2;
	}

	public void setHandlerCode2(String handlerCode2) {
		this.handlerCode2 = handlerCode2;
	}

	public String getDesProposalNo() {
		return desProposalNo;
	}

	public void setDesProposalNo(String desProposalNo) {
		this.desProposalNo = desProposalNo;
	}

	public String getIsRunApp() {
		return isRunApp;
	}

	public void setIsRunApp(String isRunApp) {
		this.isRunApp = isRunApp;
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

	public String getBeGuardianSex() {
		return beGuardianSex;
	}

	public void setBeGuardianSex(String beGuardianSex) {
		this.beGuardianSex = beGuardianSex;
	}

	public Date getBeGuardianBirthDate() {
		return beGuardianBirthDate;
	}

	public void setBeGuardianBirthDate(Date beGuardianBirthDate) {
		this.beGuardianBirthDate = beGuardianBirthDate;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getIsOpenair() {
		return isOpenair;
	}

	public void setIsOpenair(String isOpenair) {
		this.isOpenair = isOpenair;
	}

	public String getShowNo() {
		return showNo;
	}

	public void setShowNo(String showNo) {
		this.showNo = showNo;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getPrefectureCode() {
		return prefectureCode;
	}

	public void setPrefectureCode(String prefectureCode) {
		this.prefectureCode = prefectureCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getExhibitionArea() {
		return exhibitionArea;
	}

	public void setExhibitionArea(String exhibitionArea) {
		this.exhibitionArea = exhibitionArea;
	}

	public String getInsuredMobile() {
		return insuredMobile;
	}

	public void setInsuredMobile(String insuredMobile) {
		this.insuredMobile = insuredMobile;
	}

	public Long getAssociatedInsureID() {
		return associatedInsureID;
	}

	public void setAssociatedInsureID(Long associatedInsureID) {
		this.associatedInsureID = associatedInsureID;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getInsuredSheBaoType() {
		return insuredSheBaoType;
	}

	public void setInsuredSheBaoType(String insuredSheBaoType) {
		this.insuredSheBaoType = insuredSheBaoType;
	}

	public String getIsSeeFee() {
		return isSeeFee;
	}

	public void setIsSeeFee(String isSeeFee) {
		this.isSeeFee = isSeeFee;
	}

	public String getCustSourceType() {
		return custSourceType;
	}
	
	
	
	
	
}
