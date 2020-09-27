package com.ccic.salesapp.performance.domain.vo.dto;
 


//
import java.util.Date;

import com.ccic.salesapp.performance.domain.vo.dto.DataVO;
 

public class StoreInsureInfo extends DataVO{
	private Long  id;                          //物理主键
	 private String insureNo;                	//投保单号
	 private String policyNo;                	//保单号
	 private String insuranceCode;        		//险种代码
	 private String insuranceName;       		//险种名称
	 private Date   createDate;             	//记录创建日期
	 private Date   startDate;                	//保单起期
	 private Date   endDate;                 	//保单止期
	 private String startTime;                	//保单起时
	 private String endTime;                 	//保单起时
	 private String insurancePeriod;      		//保险期限
	 private Date   underwriteDate;       		//核保日期
	 private String sumPremium;           		//总保费
	 private String sumAmount;            		//总保额
	 private String businessType;          		//业务归属
	 private String status;                     //投保单状态
	 private String userId;                     //所属用户
	 private int insuranceCount;        		//投保份数
	 private String licensePlateNo;         	//车牌号
	 private String comCode;                	//业务归属机构
	 private String handlerCode;           		//经办人代码
	 private String operatorCode;         		//操作员代码
	 private String operatorName;        		//操作员名称
	 private String payApplyNo;            		//支付号
	 private String identityCode;            	//支付验证码
	 private String applicantName;         		//投保人姓名
	 private String appliIdentify;            	//投保人身份证号码
	 private String appliIdentifyType;			//投保人证件类型
	 private String insuredName;           		//被保人姓名
	 private String insuredIdentify;         	//被保人身份证号码
	 private String insuredIdentifyType;			//被保险人证件类型
	 private Date   birthDate;                	//出生日期
	 private String mobileNo;               	//手机号
	 private String address;                	//地址
	 private String email;                      //邮箱
	 private String formulaCode;				//方案代码
	 private String formulaName;				//方案名称
	 private String relateInsured; 				//与被保险人的关系
	 private String imei;						//设备号
	 private String agentCode;//代理人代码	
	 private String agreementNo;//代理人协议号
	 private String agreementName;//代理人协议名称
	 private String insuranceType;//保险类别
	 private String signStatus;//签名状态
	 private String imgId;//影像ID
	 private String additionalCode;//附加险是否选中代码以_分割
	 
	 private String handlerCode2;//经办人2 
	 private String desProposalNo;//加密后的投保单号
	 private String isRunApp;//是否app出单
	private String platformFlag;				//平台标识 1-新华出单
	private String nclUserCode;					//新华用户标识 
	 
   //监护人责任险
	private String beGuardianSex;				//被监护人性别
	private Date beGuardianBirthDate;			//被监护人生日
	private String productCode;			//产品代码
	//露天展台 0露天 1非露天
	private String isOpenair;
	//展位编号
	private String showNo;
	//展会名称
	private String showName;
	
	private String payFlag;//能否支付：1是0否
	private String provinceCode;//省份代码
	private String prefectureCode;//辖区代码
	private String countyCode; //县级代码
	
	private String productName;//产品名称
	
	//展会面积
	private String exhibitionArea;
	//被保人手机号码
	private String insuredMobile;
	
	private Long associatedInsureID;//关联投保单ID
		
	public Long getAssociatedInsureID() {
		return associatedInsureID;
	}
	public void setAssociatedInsureID(Long associatedInsureID) {
		this.associatedInsureID = associatedInsureID;
	}
	public String getInsuredMobile() {
		return insuredMobile;
	}
	public void setInsuredMobile(String insuredMobile) {
		this.insuredMobile = insuredMobile;
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
	public String getAdditionalCode() {
		return additionalCode;
	}
	public void setAdditionalCode(String additionalCode) {
		this.additionalCode = additionalCode;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
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
	public String getAppliIdentifyType() {
		return appliIdentifyType;
	}
	public void setAppliIdentifyType(String appliIdentifyType) {
		this.appliIdentifyType = appliIdentifyType;
	}
	public String getInsuredIdentifyType() {
		return insuredIdentifyType;
	}
	public void setInsuredIdentifyType(String insuredIdentifyType) {
		this.insuredIdentifyType = insuredIdentifyType;
	}
	public String getDesProposalNo() {
		return desProposalNo;
	}
	public void setDesProposalNo(String desProposalNo) {
		this.desProposalNo = desProposalNo;
	} 
	public String getHandlerCode2() {
		return handlerCode2;
	}
	public void setHandlerCode2(String handlerCode2) {
		this.handlerCode2 = handlerCode2;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getRelateInsured() {
		return relateInsured;
	}
	public void setRelateInsured(String relateInsured) {
		this.relateInsured = relateInsured;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInsureNo() {
		return insureNo;
	}
	public void setInsureNo(String insureNo) {
		this.insureNo = insureNo;
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
	public String getIsRunApp() {
		return isRunApp;
	}
	public void setIsRunApp(String isRunApp) {
		this.isRunApp = isRunApp;
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

}
