package com.ccic.salesapp.noncar.dto.request;

public class AutoRenewalVO {

	//是否同意自动续保
		private String isAcceptAutoRenewal;
		//商业险专用自动续保积分
		private String autoRenewalPointBI;
		//交强险专用自动续保积分
		private String autoRenewalPointCI;
		//银行代码
		private String bankCode;
		//银行名称
		private String bankName;
		//帐户名
		private String accountName;
		//帐号
		private String accountNo;
		//绑定手机号码
		private String phoneNumber;
		//开户证件类型
		private String identifyType;
		//开户证件号码
		private String identifyNumber;

	public String getIsAcceptAutoRenewal() {
		return isAcceptAutoRenewal;
	}
	public void setIsAcceptAutoRenewal(String isAcceptAutoRenewal) {
		this.isAcceptAutoRenewal = isAcceptAutoRenewal;
	}
	public String getAutoRenewalPointBI() {
		return autoRenewalPointBI;
	}
	public void setAutoRenewalPointBI(String autoRenewalPointBI) {
		this.autoRenewalPointBI = autoRenewalPointBI;
	}
	public String getAutoRenewalPointCI() {
		return autoRenewalPointCI;
	}
	public void setAutoRenewalPointCI(String autoRenewalPointCI) {
		this.autoRenewalPointCI = autoRenewalPointCI;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

}
