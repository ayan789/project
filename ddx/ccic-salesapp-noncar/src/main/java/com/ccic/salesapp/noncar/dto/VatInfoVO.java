package com.ccic.salesapp.noncar.dto;
import java.io.Serializable;

/*
 * ��Ʊ��Ϣ
  */
public class VatInfoVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String depositeBankName;//���������ƴ���
	private String clientType;//客户类型
	private String taxPayerType;//纳税人类型
	private String invoiceType;//发票类型
	private String taxPayerNo;//纳税人识别号
	private String taxRegistryAddress;//税务登记地址
	private String bankName;//开户行名称
	private String accountNumber;//开户行帐号
	private String companyName;//公司名称
	private String companyNameType;//公司名称类型
	
	private String taxRegistryPhone;//税务登记电话
	private String mobile;//手机号码
	private String email;//邮件地址
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getTaxRegistryAddress() {
		return taxRegistryAddress;
	}
	public void setTaxRegistryAddress(String taxRegistryAddress) {
		this.taxRegistryAddress = taxRegistryAddress;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyNameType() {
		return companyNameType;
	}
	public void setCompanyNameType(String companyNameType) {
		this.companyNameType = companyNameType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDepositeBankName() {
		return depositeBankName;
	}
	public void setDepositeBankName(String depositeBankName) {
		this.depositeBankName = depositeBankName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTaxPayerNo() {
		return taxPayerNo;
	}
	public void setTaxPayerNo(String taxPayerNo) {
		this.taxPayerNo = taxPayerNo;
	}
	public String getTaxPayerType() {
		return taxPayerType;
	}
	public void setTaxPayerType(String taxPayerType) {
		this.taxPayerType = taxPayerType;
	}
	
	public String getTaxRegistryPhone() {
		return taxRegistryPhone;
	}
	public void setTaxRegistryPhone(String taxRegistryPhone) {
		this.taxRegistryPhone = taxRegistryPhone;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}


}
