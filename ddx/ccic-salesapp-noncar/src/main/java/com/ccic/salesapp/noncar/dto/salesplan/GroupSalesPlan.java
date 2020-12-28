/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.salesplan;
import java.util.List;

import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditions;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Auto-generated: 2020-09-09 14:2:59
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@ApiModel("组合方案详细信息")
public class GroupSalesPlan {
	@ApiModelProperty("主键id")
	private int salesPlanId;
	@ApiModelProperty("销售方案代码")
    private String salesPlanCode;
	@ApiModelProperty("销售方案名称")
    private String salesPlanName;
	@ApiModelProperty("销售方案类型")
    private String salesType;
	@ApiModelProperty("组合方案代码")
    private String groupPlanCode;
	@ApiModelProperty("组合方案名称")
    private String groupPlanName;
	@ApiModelProperty("方案状态")
    private String status;
	@ApiModelProperty("生效日期")
    private String effectiveDate;
	@ApiModelProperty("截至日期")
    private String expiryDate;
	@ApiModelProperty("子方案信息")
    private List<PlanChild> salesChildPlans;
	@ApiModelProperty("是否默认")
	private String isDefault;
	@ApiModelProperty("机构")
	private String orgCode;
	@ApiModelProperty("从人渠道")
	private String channelByPerson;
	private String smsTemplateId;
	private String salesPlanType;
	private String groupCode;
	private String groupName;
	private String discountType;
	private String discountName;
	@ApiModelProperty("个人无忧家庭版条款")
	private TermsAndConditions termsAndConditions = new TermsAndConditions();
	
	public int getSalesPlanId() {
		return salesPlanId;
	}
	public void setSalesPlanId(int salesPlanId) {
		this.salesPlanId = salesPlanId;
	}
	public String getSalesPlanCode() {
		return salesPlanCode;
	}
	public void setSalesPlanCode(String salesPlanCode) {
		this.salesPlanCode = salesPlanCode;
	}
	public String getSalesPlanName() {
		return salesPlanName;
	}
	public void setSalesPlanName(String salesPlanName) {
		this.salesPlanName = salesPlanName;
	}
	public String getSalesType() {
		return salesType;
	}
	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}
	public String getGroupPlanCode() {
		return groupPlanCode;
	}
	public void setGroupPlanCode(String groupPlanCode) {
		this.groupPlanCode = groupPlanCode;
	}
	public String getGroupPlanName() {
		return groupPlanName;
	}
	public void setGroupPlanName(String groupPlanName) {
		this.groupPlanName = groupPlanName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public List<PlanChild> getSalesChildPlans() {
		return salesChildPlans;
	}
	public void setSalesChildPlans(List<PlanChild> salesChildPlans) {
		this.salesChildPlans = salesChildPlans;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getChannelByPerson() {
		return channelByPerson;
	}
	public void setChannelByPerson(String channelByPerson) {
		this.channelByPerson = channelByPerson;
	}
	public String getSmsTemplateId() {
		return smsTemplateId;
	}
	public void setSmsTemplateId(String smsTemplateId) {
		this.smsTemplateId = smsTemplateId;
	}
	public String getSalesPlanType() {
		return salesPlanType;
	}
	public void setSalesPlanType(String salesPlanType) {
		this.salesPlanType = salesPlanType;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public String getDiscountName() {
		return discountName;
	}
	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
    public TermsAndConditions getTermsAndConditions() {
        return termsAndConditions;
    }
    public void setTermsAndConditions(TermsAndConditions termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }
}