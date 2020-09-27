package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;



/**
 * 关系人信息
 * @author Administrator
 *
 */
public class PrivyVO extends DataVO {

	private String insuredFlag;//关系人标志
	private String insuredName;//关系人名称
	private String insuredAddress;//关系人地址
	private String postCode;//邮政编码
	private String insuredNature;//关系人性质
	private String unitType;//单位性质
	private String identifyType ;//证件类型
	private String identifyNumber;//证件号码
	private String mobile  ;//移动电话
	private String sex ;//性别
	private String drivingLicenseNo ;//驾驶证号
	private String driverAge ;//年龄
	private String acceptLicenseDate;//初次领证日期 格式：YYYY-MM-DD
	private String mainDriverFlag;//主驾驶员标志
	
	private String occupation;//职业
	private String identifyStartDate;//证件有效期起期
	private String identifyEndDate;//证件有效期止期
	
    private  String birthDate;
    private  String appNation;
    private  String appIdentifyCom;
    private  String email; 
    
    private  String agentName; //客户经办人名称
    private  String agentIdCardType; //客户经办人证件类型
    private  String agentIdCard; //客户经办人证件号码
    
    private String payerAccountName;//付款人账户名称
    private String payerReType;//投保人与付款人关系类型
    private String payerReTypeRemark;//投保人与付款人关系类型备注
    
    private String custNo;//客户号
    
    private String nationality;//国籍
    
    private String isPhoneHolder;//是否手机持有人本人
    private String phoneHolderName;//手机持有人姓名
	private String phoneHolderIdNo;//手机持有人身份证号
	private String businessRelation;//企业经办人也车辆关系
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getAppNation() {
		return appNation;
	}
	public void setAppNation(String appNation) {
		this.appNation = appNation;
	}
	public String getAppIdentifyCom() {
		return appIdentifyCom;
	}
	public void setAppIdentifyCom(String appIdentifyCom) {
		this.appIdentifyCom = appIdentifyCom;
	}
	public String getInsuredFlag() {
		return insuredFlag;
	}
	public void setInsuredFlag(String insuredFlag) {
		this.insuredFlag = insuredFlag;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getInsuredAddress() {
		return insuredAddress;
	}
	public void setInsuredAddress(String insuredAddress) {
		this.insuredAddress = insuredAddress;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getInsuredNature() {
		return insuredNature;
	}
	public void setInsuredNature(String insuredNature) {
		this.insuredNature = insuredNature;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getIdentifyType() {
		return identifyType;
	}
	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}
	public String getIdentifyNumber() {
		return identifyNumber;
	}
	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}
	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}
	public String getDriverAge() {
		return driverAge;
	}
	public void setDriverAge(String driverAge) {
		this.driverAge = driverAge;
	}
	public String getAcceptLicenseDate() {
		return acceptLicenseDate;
	}
	public void setAcceptLicenseDate(String acceptLicenseDate) {
		this.acceptLicenseDate = acceptLicenseDate;
	}
	public String getMainDriverFlag() {
		return mainDriverFlag;
	}
	public void setMainDriverFlag(String mainDriverFlag) {
		this.mainDriverFlag = mainDriverFlag;
	}
	public String getIdentifyStartDate() {
		return identifyStartDate;
	}
	public void setIdentifyStartDate(String identifyStartDate) {
		this.identifyStartDate = identifyStartDate;
	}
	public String getIdentifyEndDate() {
		return identifyEndDate;
	}
	public void setIdentifyEndDate(String identifyEndDate) {
		this.identifyEndDate = identifyEndDate;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentIdCardType() {
		return agentIdCardType;
	}
	public void setAgentIdCardType(String agentIdCardType) {
		this.agentIdCardType = agentIdCardType;
	}
	public String getAgentIdCard() {
		return agentIdCard;
	}
	public void setAgentIdCard(String agentIdCard) {
		this.agentIdCard = agentIdCard;
	}
	public String getPayerAccountName() {
		return payerAccountName;
	}
	public void setPayerAccountName(String payerAccountName) {
		this.payerAccountName = payerAccountName;
	}
	public String getPayerReType() {
		return payerReType;
	}
	public void setPayerReType(String payerReType) {
		this.payerReType = payerReType;
	}
	public String getPayerReTypeRemark() {
		return payerReTypeRemark;
	}
	public void setPayerReTypeRemark(String payerReTypeRemark) {
		this.payerReTypeRemark = payerReTypeRemark;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getIsPhoneHolder() {
		return isPhoneHolder;
	}
	public void setIsPhoneHolder(String isPhoneHolder) {
		this.isPhoneHolder = isPhoneHolder;
	}
	public String getPhoneHolderName() {
		return phoneHolderName;
	}
	public void setPhoneHolderName(String phoneHolderName) {
		this.phoneHolderName = phoneHolderName;
	}
	public String getPhoneHolderIdNo() {
		return phoneHolderIdNo;
	}
	public void setPhoneHolderIdNo(String phoneHolderIdNo) {
		this.phoneHolderIdNo = phoneHolderIdNo;
	}
	public String getBusinessRelation() {
		return businessRelation;
	}
	public void setBusinessRelation(String businessRelation) {
		this.businessRelation = businessRelation;
	}
	
}
