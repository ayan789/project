package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;


/**
 * 附加责任信息
 * 
 * @author Administrator
 *
 */
public class SharedCoverageGroup {

	private Double avgPremium;// 平均保费，录入责任分组时用
	private Double avgSumInsured;// 平均保额
	private Double benchRiskPremium;// 基准纯风险保费(仅CT级别)
	private Double duePremium;// 应收保费/真实保费/RealPremium,=调整保费
	private String groupDescription;// 分组描述
	private String isIncludedInSumInsured;// 是否计入保额
	private Double sumInsured;// 保额
	private Double vat;// VAT金额(增值税)
	private Double vatRate;// 增值税率
	private Long sequenceNumber;//序号
	private Map<String, String> tempData;// 临时数据
	private List<PolicyCoverage> policyCoverageList;// 附加责任信息

	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public List<PolicyCoverage> getPolicyCoverageList() {
		return policyCoverageList;
	}

	public void setPolicyCoverageList(List<PolicyCoverage> policyCoverageList) {
		this.policyCoverageList = policyCoverageList;
	}

	public Double getAvgPremium() {
		return avgPremium;
	}

	public void setAvgPremium(Double avgPremium) {
		this.avgPremium = avgPremium;
	}

	public Double getAvgSumInsured() {
		return avgSumInsured;
	}

	public void setAvgSumInsured(Double avgSumInsured) {
		this.avgSumInsured = avgSumInsured;
	}

	public Double getBenchRiskPremium() {
		return benchRiskPremium;
	}

	public void setBenchRiskPremium(Double benchRiskPremium) {
		this.benchRiskPremium = benchRiskPremium;
	}

	public Double getDuePremium() {
		return duePremium;
	}

	public void setDuePremium(Double duePremium) {
		this.duePremium = duePremium;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getIsIncludedInSumInsured() {
		return isIncludedInSumInsured;
	}

	public void setIsIncludedInSumInsured(String isIncludedInSumInsured) {
		this.isIncludedInSumInsured = isIncludedInSumInsured;
	}

	public Double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public Double getVatRate() {
		return vatRate;
	}

	public void setVatRate(Double vatRate) {
		this.vatRate = vatRate;
	}

	public Map<String, String> getTempData() {
		return tempData;
	}

	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

}
