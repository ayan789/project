/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.salesplan;
import java.util.List;

import com.ccic.salesapp.noncar.dto.request.planelement.HealthNotice;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditions;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Auto-generated: 2020-09-09 14:2:59
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@ApiModel("子方案信息")
public class PlanChild {
	@ApiModelProperty("主键id")
	private int childPlanId;
	@ApiModelProperty("子方案代码")
    private String childPlanCode;
	@ApiModelProperty("子方案名称")
    private String childPlanName;
	@ApiModelProperty("产品代码")
    private String productCode;
	private String isDefault;
	@ApiModelProperty("产品名称")
    private String productName;
	@ApiModelProperty("产品线 secondLine非车/意健/车")
    private String productLine;
	@ApiModelProperty("是否主产品")
    private String masterProduct;
	@ApiModelProperty("是否使用")
    private String isUsed;
	@ApiModelProperty("险类 主险/附加险/赠险")
    private String insuranceType;
	@ApiModelProperty("销售方案id")
    private String salesPlanId;
	@ApiModelProperty("特别约定")
    private String policyform;
	@ApiModelProperty("方案责任信息")
    private List<PlanCoverage> salesChildPlanCts;
    //private TermsAndConditions termsAndConditions = new TermsAndConditions();//条款须知
    //private boolean isNeedHealthNotice;
    //private HealthNotice healthNotice = new HealthNotice();//健康须知
	public int getChildPlanId() {
		return childPlanId;
	}
	public void setChildPlanId(int childPlanId) {
		this.childPlanId = childPlanId;
	}
	public String getChildPlanCode() {
		return childPlanCode;
	}
	public void setChildPlanCode(String childPlanCode) {
		this.childPlanCode = childPlanCode;
	}
	public String getChildPlanName() {
		return childPlanName;
	}
	public void setChildPlanName(String childPlanName) {
		this.childPlanName = childPlanName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getMasterProduct() {
		return masterProduct;
	}
	public void setMasterProduct(String masterProduct) {
		this.masterProduct = masterProduct;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getSalesPlanId() {
		return salesPlanId;
	}
	public void setSalesPlanId(String salesPlanId) {
		this.salesPlanId = salesPlanId;
	}
	public String getPolicyform() {
		return policyform;
	}
	public void setPolicyform(String policyform) {
		this.policyform = policyform;
	}
	public List<PlanCoverage> getSalesChildPlanCts() {
		return salesChildPlanCts;
	}
	public void setSalesChildPlanCts(List<PlanCoverage> salesChildPlanCts) {
		this.salesChildPlanCts = salesChildPlanCts;
	}
    
    
	
}