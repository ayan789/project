package com.ccic.salesapp.noncar.repository.basedb.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 方案责任详情表
 *
 * @date 2020-11-05
 */
@ApiModel("方案责任详情表")
public class PlanCtDetail {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 方案主键ID	
     */
    @ApiModelProperty("方案主键ID	")
    private Integer planId;

    /**
     * 方案条款责任id
     */
    @ApiModelProperty("方案条款责任id")
    private Long planCoverageId;

    /**
     * 条款代码及名称
     */
    @ApiModelProperty("条款代码及名称")
    private String clauseCode;

    /**
     * 条款属性(1: 主险 2: 附加险)
     */
    @ApiModelProperty("条款属性(1: 主险 2: 附加险)")
    private String clauseProperty;

    /**
     * 是否计入总保额
     */
    @ApiModelProperty("是否计入总保额")
    private String ifCaltotalSum;

    /**
     * 承保是否必录
     */
    @ApiModelProperty("承保是否必录")
    private String ctNecessary;

    /**
     * 是否修改责任描述
     */
    @ApiModelProperty("是否修改责任描述")
    private String ifChangeDesc;

    /**
     * 责任描述
     */
    @ApiModelProperty("责任描述")
    private String ctDesc;

    /**
     * 责任英文描述
     */
    @ApiModelProperty("责任英文描述")
    private String ctDescEn;

    /**
     * 评残标准
     */
    @ApiModelProperty("评残标准")
    private String disabilityStandard;

    /**
     * 保额最大值
     */
    @ApiModelProperty("保额最大值")
    private BigDecimal sumInsuredMax;

    /**
     * 保额最小值
     */
    @ApiModelProperty("保额最小值")
    private BigDecimal sumInsuredMin;

    /**
     * 保额默认值
     */
    @ApiModelProperty("保额默认值")
    private BigDecimal sumInsuredDefault;

    /**
     * 保费/费率类型
     */
    @ApiModelProperty("保费/费率类型")
    private String premiumOrrate;

    /**
     * 保费/费率最大值
     */
    @ApiModelProperty("保费/费率最大值")
    private BigDecimal premiumMax;

    /**
     * 保费/费率最小值
     */
    @ApiModelProperty("保费/费率最小值")
    private BigDecimal premiumMin;

    /**
     * 保费/费率默认值
     */
    @ApiModelProperty("保费/费率默认值")
    private BigDecimal premiumDefault;

    /**
     * 是否有附属被保险人
     */
    @ApiModelProperty("是否有附属被保险人")
    private String ifSuinsured;

    /**
     * 附属被保险人上限
     */
    @ApiModelProperty("附属被保险人上限")
    private Integer insuredLimit;

    /**
     * 每增加x天增加y元
     */
    @ApiModelProperty("每增加x天增加y元")
    private Integer increasexDay;

    /**
     * 
     */
    @ApiModelProperty("")
    private BigDecimal increaseyPrem;

    /**
     * 保额共用方式
     */
    @ApiModelProperty("保额共用方式")
    private String premiumUsetype;

    /**
     * 折扣比例
     */
    @ApiModelProperty("折扣比例")
    private String discountPercent;

    /**
     * 关联责任
     */
    @ApiModelProperty("关联责任")
    private Integer relatedCtId;

    /**
     * 保期选项
     */
    @ApiModelProperty("保期选项")
    private String rateTypes;

    /**
     * 因子选项
     */
    @ApiModelProperty("因子选项")
    private String periodTypes;

    /**
     * 与座位数联动(1:是 0:否)
     */
    @ApiModelProperty("与座位数联动(1:是 0:否)")
    private Integer linkSeats;

    /**
     * 保额单位(1:元 2:元/座 3:元/家庭 4:元/天)
     */
    @ApiModelProperty("保额单位(1:元 2:元/座 3:元/家庭 4:元/天)")
    private String insureUnit;

    /**
     * 保障说明
     */
    @ApiModelProperty("保障说明")
    private String insureRemark;

    /**
     * 附加说明
     */
    @ApiModelProperty("附加说明")
    private String otherRemark;

    /**
     * 是否显示(1:是 0:否)
     */
    @ApiModelProperty("是否显示(1:是 0:否)")
    private Integer visible;

    /**
     * 是否必选(1:是 0:否)
     */
    @ApiModelProperty("是否必选(1:是 0:否)")
    private Integer required;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Long getPlanCoverageId() {
        return planCoverageId;
    }

    public void setPlanCoverageId(Long planCoverageId) {
        this.planCoverageId = planCoverageId;
    }

    public String getClauseCode() {
        return clauseCode;
    }

    public void setClauseCode(String clauseCode) {
        this.clauseCode = clauseCode;
    }

    public String getClauseProperty() {
        return clauseProperty;
    }

    public void setClauseProperty(String clauseProperty) {
        this.clauseProperty = clauseProperty;
    }

    public String getIfCaltotalSum() {
        return ifCaltotalSum;
    }

    public void setIfCaltotalSum(String ifCaltotalSum) {
        this.ifCaltotalSum = ifCaltotalSum;
    }

    public String getCtNecessary() {
        return ctNecessary;
    }

    public void setCtNecessary(String ctNecessary) {
        this.ctNecessary = ctNecessary;
    }

    public String getIfChangeDesc() {
        return ifChangeDesc;
    }

    public void setIfChangeDesc(String ifChangeDesc) {
        this.ifChangeDesc = ifChangeDesc;
    }

    public String getCtDesc() {
        return ctDesc;
    }

    public void setCtDesc(String ctDesc) {
        this.ctDesc = ctDesc;
    }

    public String getCtDescEn() {
        return ctDescEn;
    }

    public void setCtDescEn(String ctDescEn) {
        this.ctDescEn = ctDescEn;
    }

    public String getDisabilityStandard() {
        return disabilityStandard;
    }

    public void setDisabilityStandard(String disabilityStandard) {
        this.disabilityStandard = disabilityStandard;
    }

    public BigDecimal getSumInsuredMax() {
        return sumInsuredMax;
    }

    public void setSumInsuredMax(BigDecimal sumInsuredMax) {
        this.sumInsuredMax = sumInsuredMax;
    }

    public BigDecimal getSumInsuredMin() {
        return sumInsuredMin;
    }

    public void setSumInsuredMin(BigDecimal sumInsuredMin) {
        this.sumInsuredMin = sumInsuredMin;
    }

    public BigDecimal getSumInsuredDefault() {
        return sumInsuredDefault;
    }

    public void setSumInsuredDefault(BigDecimal sumInsuredDefault) {
        this.sumInsuredDefault = sumInsuredDefault;
    }

    public String getPremiumOrrate() {
        return premiumOrrate;
    }

    public void setPremiumOrrate(String premiumOrrate) {
        this.premiumOrrate = premiumOrrate;
    }

    public BigDecimal getPremiumMax() {
        return premiumMax;
    }

    public void setPremiumMax(BigDecimal premiumMax) {
        this.premiumMax = premiumMax;
    }

    public BigDecimal getPremiumMin() {
        return premiumMin;
    }

    public void setPremiumMin(BigDecimal premiumMin) {
        this.premiumMin = premiumMin;
    }

    public BigDecimal getPremiumDefault() {
        return premiumDefault;
    }

    public void setPremiumDefault(BigDecimal premiumDefault) {
        this.premiumDefault = premiumDefault;
    }

    public String getIfSuinsured() {
        return ifSuinsured;
    }

    public void setIfSuinsured(String ifSuinsured) {
        this.ifSuinsured = ifSuinsured;
    }

    public Integer getInsuredLimit() {
        return insuredLimit;
    }

    public void setInsuredLimit(Integer insuredLimit) {
        this.insuredLimit = insuredLimit;
    }

    public Integer getIncreasexDay() {
        return increasexDay;
    }

    public void setIncreasexDay(Integer increasexDay) {
        this.increasexDay = increasexDay;
    }

    public BigDecimal getIncreaseyPrem() {
        return increaseyPrem;
    }

    public void setIncreaseyPrem(BigDecimal increaseyPrem) {
        this.increaseyPrem = increaseyPrem;
    }

    public String getPremiumUsetype() {
        return premiumUsetype;
    }

    public void setPremiumUsetype(String premiumUsetype) {
        this.premiumUsetype = premiumUsetype;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Integer getRelatedCtId() {
        return relatedCtId;
    }

    public void setRelatedCtId(Integer relatedCtId) {
        this.relatedCtId = relatedCtId;
    }

    public String getRateTypes() {
        return rateTypes;
    }

    public void setRateTypes(String rateTypes) {
        this.rateTypes = rateTypes;
    }

    public String getPeriodTypes() {
        return periodTypes;
    }

    public void setPeriodTypes(String periodTypes) {
        this.periodTypes = periodTypes;
    }

    public Integer getLinkSeats() {
        return linkSeats;
    }

    public void setLinkSeats(Integer linkSeats) {
        this.linkSeats = linkSeats;
    }

    public String getInsureUnit() {
        return insureUnit;
    }

    public void setInsureUnit(String insureUnit) {
        this.insureUnit = insureUnit;
    }

    public String getInsureRemark() {
        return insureRemark;
    }

    public void setInsureRemark(String insureRemark) {
        this.insureRemark = insureRemark;
    }

    public String getOtherRemark() {
        return otherRemark;
    }

    public void setOtherRemark(String otherRemark) {
        this.otherRemark = otherRemark;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}