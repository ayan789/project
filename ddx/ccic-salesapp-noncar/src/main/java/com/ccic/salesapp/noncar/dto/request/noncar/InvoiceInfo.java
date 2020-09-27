package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * 开票信息
 * 
 * @author Administrator
 *
 */
public class InvoiceInfo {
	private String accountNo;// 开户行账号
	private String bankCode;// 开户行/银行代码
	private String companyName;// 公司名称
	private String copyDataFromType;// 开票公司信息(同投保人/同被保人/第三方)
	private String customerType;// 客户类型
	private String depositeBankName;// 开户行名称代码
	private String email;// 邮件地址
	private String invoiceType;// 发票类型
	private String mobile;// 手机号码
	private String taxPayerNo;// 纳税人识别号
	private String taxPayerType;// 纳税人类型
	private String taxRegisterAddress;// 税务登记地址
	private String taxRegisterTel;// 税务登记电话
	private Map<String, String> tempData;// 临时数据
	private Long sequenceNumber;//序号
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCopyDataFromType() {
		return copyDataFromType;
	}

	public void setCopyDataFromType(String copyDataFromType) {
		this.copyDataFromType = copyDataFromType;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
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

	public String getTaxRegisterAddress() {
		return taxRegisterAddress;
	}

	public void setTaxRegisterAddress(String taxRegisterAddress) {
		this.taxRegisterAddress = taxRegisterAddress;
	}

	public String getTaxRegisterTel() {
		return taxRegisterTel;
	}

	public void setTaxRegisterTel(String taxRegisterTel) {
		this.taxRegisterTel = taxRegisterTel;
	}

	public Map<String, String> getTempData() {
		return tempData;
	}

	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

}
