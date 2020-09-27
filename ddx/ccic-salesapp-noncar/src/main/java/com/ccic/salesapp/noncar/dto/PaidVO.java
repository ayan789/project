package com.ccic.salesapp.noncar.dto;



/**
 * 完税信息
 * @author Administrator
 *
 */
public class PaidVO extends DataVO {

	private String taxDepartmentCode;//开具完税凭证的税务机关代码 
	private String taxDepartment;//开具完税凭证的税务机关名称     
	private String taxDocumentNumber;//完税凭证号码               
	private String taxDocumentDate;//完税凭证填发日期
	private String taxPaidAreaCode;//开具完税凭证地区代码
	public String getTaxDepartmentCode() {
		return taxDepartmentCode;
	}
	public void setTaxDepartmentCode(String taxDepartmentCode) {
		this.taxDepartmentCode = taxDepartmentCode;
	}
	public String getTaxDepartment() {
		return taxDepartment;
	}
	public void setTaxDepartment(String taxDepartment) {
		this.taxDepartment = taxDepartment;
	}
	public String getTaxDocumentNumber() {
		return taxDocumentNumber;
	}
	public void setTaxDocumentNumber(String taxDocumentNumber) {
		this.taxDocumentNumber = taxDocumentNumber;
	}
	public String getTaxDocumentDate() {
		return taxDocumentDate;
	}
	public void setTaxDocumentDate(String taxDocumentDate) {
		this.taxDocumentDate = taxDocumentDate;
	}
	public String getTaxPaidAreaCode() {
		return taxPaidAreaCode;
	}
	public void setTaxPaidAreaCode(String taxPaidAreaCode) {
		this.taxPaidAreaCode = taxPaidAreaCode;
	}

}
