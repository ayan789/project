package com.ccic.salesapp.noncar.repository.basedb.po;

public class PolicyCustomer {
    private Long customerId;

	private Long orderId;

	private String customerRoleCode;

	private String isContactPolHolder;

	private String polHolderInsuredRelaCode;

	private String insuredVehicleRelaCode;

	private String permanentAddress;

	private String partyCategory;

	private String customerName;

	private Short indiAge;

	private String indiGenderCode;

	private String indiDateOfBirth;

	private String idType;

	private String idNo;

	private String indiMobile;

	private String email;

	private String addInsuredInsuredRelaCode;

	private String customerNo;

	private String socialSecurityFlag;

	private String medicalInsuranceCode;

	private String fullAddress;

	private String address;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCustomerRoleCode() {
		return customerRoleCode;
	}

	public void setCustomerRoleCode(String customerRoleCode) {
		this.customerRoleCode = customerRoleCode == null ? null : customerRoleCode.trim();
	}

	public String getIsContactPolHolder() {
		return isContactPolHolder;
	}

	public void setIsContactPolHolder(String isContactPolHolder) {
		this.isContactPolHolder = isContactPolHolder == null ? null : isContactPolHolder.trim();
	}

	public String getPolHolderInsuredRelaCode() {
		return polHolderInsuredRelaCode;
	}

	public void setPolHolderInsuredRelaCode(String polHolderInsuredRelaCode) {
		this.polHolderInsuredRelaCode = polHolderInsuredRelaCode == null ? null : polHolderInsuredRelaCode.trim();
	}

	public String getInsuredVehicleRelaCode() {
		return insuredVehicleRelaCode;
	}

	public void setInsuredVehicleRelaCode(String insuredVehicleRelaCode) {
		this.insuredVehicleRelaCode = insuredVehicleRelaCode == null ? null : insuredVehicleRelaCode.trim();
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress == null ? null : permanentAddress.trim();
	}

	public String getPartyCategory() {
		return partyCategory;
	}

	public void setPartyCategory(String partyCategory) {
		this.partyCategory = partyCategory == null ? null : partyCategory.trim();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName == null ? null : customerName.trim();
	}

	public Short getIndiAge() {
		return indiAge;
	}

	public void setIndiAge(Short indiAge) {
		this.indiAge = indiAge;
	}

	public String getIndiGenderCode() {
		return indiGenderCode;
	}

	public void setIndiGenderCode(String indiGenderCode) {
		this.indiGenderCode = indiGenderCode == null ? null : indiGenderCode.trim();
	}

	public String getIndiDateOfBirth() {
		return indiDateOfBirth;
	}

	public void setIndiDateOfBirth(String indiDateOfBirth) {
		this.indiDateOfBirth = indiDateOfBirth == null ? null : indiDateOfBirth.trim();
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType == null ? null : idType.trim();
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo == null ? null : idNo.trim();
	}

	public String getIndiMobile() {
		return indiMobile;
	}

	public void setIndiMobile(String indiMobile) {
		this.indiMobile = indiMobile == null ? null : indiMobile.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getAddInsuredInsuredRelaCode() {
		return addInsuredInsuredRelaCode;
	}

	public void setAddInsuredInsuredRelaCode(String addInsuredInsuredRelaCode) {
		this.addInsuredInsuredRelaCode = addInsuredInsuredRelaCode == null ? null : addInsuredInsuredRelaCode.trim();
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo == null ? null : customerNo.trim();
	}

	public String getSocialSecurityFlag() {
		return socialSecurityFlag;
	}

	public void setSocialSecurityFlag(String socialSecurityFlag) {
		this.socialSecurityFlag = socialSecurityFlag == null ? null : socialSecurityFlag.trim();
	}

	public String getMedicalInsuranceCode() {
		return medicalInsuranceCode;
	}

	public void setMedicalInsuranceCode(String medicalInsuranceCode) {
		this.medicalInsuranceCode = medicalInsuranceCode == null ? null : medicalInsuranceCode.trim();
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress == null ? null : fullAddress.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

}