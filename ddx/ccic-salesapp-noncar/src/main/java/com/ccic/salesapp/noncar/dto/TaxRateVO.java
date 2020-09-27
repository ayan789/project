package com.ccic.salesapp.noncar.dto;

/**
 * 税率信息
 * @author Administrator
 *
 */
public class TaxRateVO extends DataVO {

	private String appliedArea  ;//适用区域   国标行政区划代码                  
	private String taxRateIdentifier ;//税率方案代码                            
	private String taxItemdetailCode ;//税务机关交通工具分类代码详见代码2.2.33  
	private String taxUnittypeCode   ;//计税单位代码详见代码2.2.32              
	private String unitRate ;//单位计税金额                                     
	private String taxRateStartDate  ;//适用期限起期格式：YYYY-MM-DD            
	private String taxRateEndDate;//适用期限止期格式：YYYY-MM-DD                
	public String getAppliedArea() {
		return appliedArea;
	}
	public void setAppliedArea(String appliedArea) {
		this.appliedArea = appliedArea;
	}
	public String getTaxRateIdentifier() {
		return taxRateIdentifier;
	}
	public void setTaxRateIdentifier(String taxRateIdentifier) {
		this.taxRateIdentifier = taxRateIdentifier;
	}
	public String getTaxItemdetailCode() {
		return taxItemdetailCode;
	}
	public void setTaxItemdetailCode(String taxItemdetailCode) {
		this.taxItemdetailCode = taxItemdetailCode;
	}
	public String getTaxUnittypeCode() {
		return taxUnittypeCode;
	}
	public void setTaxUnittypeCode(String taxUnittypeCode) {
		this.taxUnittypeCode = taxUnittypeCode;
	}
	public String getUnitRate() {
		return unitRate;
	}
	public void setUnitRate(String unitRate) {
		this.unitRate = unitRate;
	}
	public String getTaxRateStartDate() {
		return taxRateStartDate;
	}
	public void setTaxRateStartDate(String taxRateStartDate) {
		this.taxRateStartDate = taxRateStartDate;
	}
	public String getTaxRateEndDate() {
		return taxRateEndDate;
	}
	public void setTaxRateEndDate(String taxRateEndDate) {
		this.taxRateEndDate = taxRateEndDate;
	}

}
