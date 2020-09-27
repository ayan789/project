package com.ccic.salesapp.noncar.dto;


/**
 * 减免税信息
 * @author Administrator
 *
 */
public class DeRateVO extends DataVO {

	private String noTaxCarType  ;//免税车辆类型          
	private String deDuctiondueCode  ;//减免税原因代码    
	private String deDuctiondueType  ;//减免税方案代码    
	private String deDuctiondueProportion;//减免比例      
	private String deDuction;//减免金额                   
	private String deDuctionDocumentNumber;//减免税凭证号 
	private String taxDepartmentDode ;//税务机关代码      
	private String taxDepartment ;//税务机关名称          
	public String getNoTaxCarType() {
		return noTaxCarType;
	}
	public void setNoTaxCarType(String noTaxCarType) {
		this.noTaxCarType = noTaxCarType;
	}
	public String getDeDuctiondueCode() {
		return deDuctiondueCode;
	}
	public void setDeDuctiondueCode(String deDuctiondueCode) {
		this.deDuctiondueCode = deDuctiondueCode;
	}
	public String getDeDuctiondueType() {
		return deDuctiondueType;
	}
	public void setDeDuctiondueType(String deDuctiondueType) {
		this.deDuctiondueType = deDuctiondueType;
	}
	public String getDeDuctiondueProportion() {
		return deDuctiondueProportion;
	}
	public void setDeDuctiondueProportion(String deDuctiondueProportion) {
		this.deDuctiondueProportion = deDuctiondueProportion;
	}
	public String getDeDuction() {
		return deDuction;
	}
	public void setDeDuction(String deDuction) {
		this.deDuction = deDuction;
	}
	public String getDeDuctionDocumentNumber() {
		return deDuctionDocumentNumber;
	}
	public void setDeDuctionDocumentNumber(String deDuctionDocumentNumber) {
		this.deDuctionDocumentNumber = deDuctionDocumentNumber;
	}
	public String getTaxDepartmentDode() {
		return taxDepartmentDode;
	}
	public void setTaxDepartmentDode(String taxDepartmentDode) {
		this.taxDepartmentDode = taxDepartmentDode;
	}
	public String getTaxDepartment() {
		return taxDepartment;
	}
	public void setTaxDepartment(String taxDepartment) {
		this.taxDepartment = taxDepartment;
	}

}
