package com.ccic.salesapp.performance.domain.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ccic.salesapp.performance.domain.vo.ResponsibilityEditValid;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 责任书明细
 *
 * @date 2020-03-18
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel("责任书明细")
public class PerfResponsibilityDetail {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 责任书id
     */
    @ApiModelProperty("责任书id")
    private Long responsibilityId;

    /**
     * 月总保费
     */
    @NotNull(groups = {ResponsibilityEditValid.class},message="月总保费不能为空")
    @ApiModelProperty("月总保费")
    private Long insurePremium;

    /**
     * 车险保费（月目标）
     */
    @NotNull(groups = {ResponsibilityEditValid.class},message="月车险保费不能为空")
    @ApiModelProperty("车险保费（月目标）")
    private Long carInsurePremium;

    /**
     * 意外险保费（月目标）
     */
    @NotNull(groups = {ResponsibilityEditValid.class},message="月意外险保费不能为空")
    @ApiModelProperty("意外险保费（月目标）")
    private Long accInsurePremium;

    /**
     * 健康险保费（月目标）
     */
    @NotNull(groups = {ResponsibilityEditValid.class},message="月健康险保费不能为空")
    @ApiModelProperty("健康险保费（月目标）")
    private Long heaInsurePremium;

    /**
     * 财产险保费（月目标）
     */
    @NotNull(groups = {ResponsibilityEditValid.class},message="月财产险保费不能为空")
    @ApiModelProperty("财产险保费（月目标）")
    private Long propInsurePremium;

    /**
     * 当前月份 1-12
     */
    @ApiModelProperty("当前月份 1-12")
    private String monthStep;

    /**
     * 当前季度
     */
    @ApiModelProperty("当前季度")
    private String quarterStep;

    /**
     * 创建人工号
     */
    @ApiModelProperty("创建人工号")
    private String createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 最后修改人工号
     */
    @ApiModelProperty("最后修改人工号")
    private String updateUser;

    /**
     * 最后修改时间
     */
    @ApiModelProperty("最后修改时间")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResponsibilityId() {
        return responsibilityId;
    }

    public void setResponsibilityId(Long responsibilityId) {
        this.responsibilityId = responsibilityId;
    }

    public Long getInsurePremium() {
        return insurePremium;
    }

    public void setInsurePremium(Long insurePremium) {
        this.insurePremium = insurePremium;
    }

    public Long getCarInsurePremium() {
        return carInsurePremium;
    }

    public void setCarInsurePremium(Long carInsurePremium) {
        this.carInsurePremium = carInsurePremium;
    }

    public Long getAccInsurePremium() {
        return accInsurePremium;
    }

    public void setAccInsurePremium(Long accInsurePremium) {
        this.accInsurePremium = accInsurePremium;
    }

    public Long getHeaInsurePremium() {
        return heaInsurePremium;
    }

    public void setHeaInsurePremium(Long heaInsurePremium) {
        this.heaInsurePremium = heaInsurePremium;
    }

    public Long getPropInsurePremium() {
        return propInsurePremium;
    }

    public void setPropInsurePremium(Long propInsurePremium) {
        this.propInsurePremium = propInsurePremium;
    }

    public String getMonthStep() {
        return monthStep;
    }

    public void setMonthStep(String monthStep) {
        this.monthStep = monthStep;
    }

    public String getQuarterStep() {
        return quarterStep;
    }

    public void setQuarterStep(String quarterStep) {
        this.quarterStep = quarterStep;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}