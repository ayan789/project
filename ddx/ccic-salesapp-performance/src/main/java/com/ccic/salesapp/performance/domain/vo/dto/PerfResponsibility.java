package com.ccic.salesapp.performance.domain.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * 责任书
 *
 * @date 2020-03-18
 */
@ApiModel("责任书")
public class PerfResponsibility {
    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Long id;

    /**
     * 责任书类型：个人:P 团队:T
     */
    @ApiModelProperty("责任书类型：个人:P 团队:T")
    private String responsibilityType;

    /**
     * 业务员工号:签署人
     */
    @ApiModelProperty("业务员工号:签署人")
    private String userCode;

    /**
     * 团队编号
     */
    @ApiModelProperty("团队编号")
    private String teamCode;

    /**
     * 责任书名称
     */
    @ApiModelProperty("责任书名称")
    private String responsibilityName;

    /**
     * 责任书内容
     */
    @ApiModelProperty("责任书内容")
    private String responsibilityNote;

    /**
     * 年总保费
     */
    @ApiModelProperty("年总保费")
    private Long insurePremium;

    /**
     * 车险保费    （年目标）
     */
    @ApiModelProperty("车险保费    （年目标）")
    private Long carInsurePremium;

    /**
     * 意外险保费（年目标）
     */
    @ApiModelProperty("意外险保费（年目标）")
    private Long accInsurePremium;

    /**
     * 健康险保费（年目标）
     */
    @ApiModelProperty("健康险保费（年目标）")
    private Long heaInsurePremium;

    /**
     * 财产险保费（年目标）
     */
    @ApiModelProperty("财产险保费（年目标）")
    private Long propInsurePremium;

    /**
     * 更改次数
     */
    @ApiModelProperty("更改次数")
    private Integer multiple;

    /**
     * 签署状态：已签署：1 未签署：0
     */
    @ApiModelProperty("签署状态：已签署：1 未签署：0")
    private String signStatus;

    /**
     * 签署时间
     */
    @ApiModelProperty("签署时间")
    private Date signTime;

    /**
     * 团队长变更
     */
    @ApiModelProperty("团队长变更")
    private String changeStatus;

    /**
     * 当前年份
     */
    @ApiModelProperty("当前年份")
    private String isYear;

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

    /**
     * 领导签名
     */
    @ApiModelProperty("领导签名")
    private String leaderSign;

    /**
     * 业务员签名
     */
    @ApiModelProperty("业务员签名")
    private String userSign;
    
    /**
     * 分配标识
     */
    private String automaticFlag;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponsibilityType() {
        return responsibilityType;
    }

    public void setResponsibilityType(String responsibilityType) {
        this.responsibilityType = responsibilityType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getResponsibilityName() {
        return responsibilityName;
    }

    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }

    public String getResponsibilityNote() {
        return responsibilityNote;
    }

    public void setResponsibilityNote(String responsibilityNote) {
        this.responsibilityNote = responsibilityNote;
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

    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        this.multiple = multiple;
    }

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    public String getIsYear() {
        return isYear;
    }

    public void setIsYear(String isYear) {
        this.isYear = isYear;
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

    public String getLeaderSign() {
        return leaderSign;
    }

    public void setLeaderSign(String leaderSign) {
        this.leaderSign = leaderSign;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

	public String getAutomaticFlag() {
		return automaticFlag;
	}

	public void setAutomaticFlag(String automaticFlag) {
		this.automaticFlag = automaticFlag;
	}
    
    
}