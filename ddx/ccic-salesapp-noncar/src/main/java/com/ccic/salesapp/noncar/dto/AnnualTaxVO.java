package com.ccic.salesapp.noncar.dto;





/**
 * 年度税金信息
 * @author Administrator
 *
 */
public class AnnualTaxVO extends DataVO {

	private String taxType ;//类型Current：本年纳税，Delinquent：欠税信息      
	private String serialNo;//序号欠款信息会有多条，当年信息默认为1            
	private String taxLocationCode;//纳税地区代码  国标地区代码,全国税上平台， 
	private String taxStartDate;//税款所属始期Y 格式：YYYY-MM-DD               
	private String taxEndDate ;//税款所属止期格式：YYYY-MM-DD                  
	private String taxUnitTypeCode;//计税单位代码详见代码2.2.32                
	private String unitRate;//单位计税金额                                     
	private String declareDate;//申报日期格式：YYYY-MM-DD                      
	private String annualTaxAmount;//当期年单位税额                            
	private String exceedDate ;//逾期时间 格式：YYYY-MM-DD                     
	private String exceedDaysCount;//逾期天数                                  
	private PaidVO paid;//完税信息
	private TaxRateVO taxRate;//税率信息
	private DeRateVO deRate;//减免税信息
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getTaxLocationCode() {
		return taxLocationCode;
	}
	public void setTaxLocationCode(String taxLocationCode) {
		this.taxLocationCode = taxLocationCode;
	}
	public String getTaxStartDate() {
		return taxStartDate;
	}
	public void setTaxStartDate(String taxStartDate) {
		this.taxStartDate = taxStartDate;
	}
	public String getTaxEndDate() {
		return taxEndDate;
	}
	public void setTaxEndDate(String taxEndDate) {
		this.taxEndDate = taxEndDate;
	}
	public String getTaxUnitTypeCode() {
		return taxUnitTypeCode;
	}
	public void setTaxUnitTypeCode(String taxUnitTypeCode) {
		this.taxUnitTypeCode = taxUnitTypeCode;
	}
	public String getUnitRate() {
		return unitRate;
	}
	public void setUnitRate(String unitRate) {
		this.unitRate = unitRate;
	}
	public String getDeclareDate() {
		return declareDate;
	}
	public void setDeclareDate(String declareDate) {
		this.declareDate = declareDate;
	}
	public String getAnnualTaxAmount() {
		return annualTaxAmount;
	}
	public void setAnnualTaxAmount(String annualTaxAmount) {
		this.annualTaxAmount = annualTaxAmount;
	}
	public String getExceedDate() {
		return exceedDate;
	}
	public void setExceedDate(String exceedDate) {
		this.exceedDate = exceedDate;
	}
	public String getExceedDaysCount() {
		return exceedDaysCount;
	}
	public void setExceedDaysCount(String exceedDaysCount) {
		this.exceedDaysCount = exceedDaysCount;
	}
	public PaidVO getPaid() {
		return paid;
	}
	public void setPaid(PaidVO paid) {
		this.paid = paid;
	}
	public TaxRateVO getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(TaxRateVO taxRate) {
		this.taxRate = taxRate;
	}
	public DeRateVO getDeRate() {
		return deRate;
	}
	public void setDeRate(DeRateVO deRate) {
		this.deRate = deRate;
	}
	
	
}
