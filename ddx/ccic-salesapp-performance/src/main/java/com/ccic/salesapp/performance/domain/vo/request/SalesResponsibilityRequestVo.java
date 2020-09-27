package com.ccic.salesapp.performance.domain.vo.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ccic.salesapp.performance.domain.vo.ResponsibilityEditValid;
import com.ccic.salesapp.performance.domain.vo.dto.PerfResponsibilityDetail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesResponsibilityRequestVo {
	 /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Long id;

    /**
     * 责任书类型：个人:P 团队:T
     */
    @NotNull(groups = {ResponsibilityEditValid.class},message = "责任书类型不能为空")
    @ApiModelProperty("责任书类型：个人:P 团队:T")
    private String responsibilityType;

    /**
     * 业务员工号:签署人
     */
    @NotNull(groups = {ResponsibilityEditValid.class},message = "业务员工号不能为空")
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
    @NotNull(groups = {ResponsibilityEditValid.class},message="年总保费不能为空")
    @ApiModelProperty("年总保费")
    private Long insurePremium;

    /**
     * 车险保费    （年目标）
     */
    @NotNull(groups = {ResponsibilityEditValid.class},message="年车险保费不能为空")
    @ApiModelProperty("车险保费    （年目标）")
    private Long carInsurePremium;

    /**
     * 意外险保费（年目标）
     */
    @NotNull(groups = {ResponsibilityEditValid.class},message="年意外险保费不能为空")
    @ApiModelProperty("意外险保费（年目标）")
    private Long accInsurePremium;

    /**
     * 健康险保费（年目标）
     */
    @NotNull(groups = {ResponsibilityEditValid.class},message="年健康险保费不能为空")
    @ApiModelProperty("健康险保费（年目标）")
    private Long heaInsurePremium;

    /**
     * 财产险保费（年目标）
     */
    @NotNull(groups = {ResponsibilityEditValid.class},message="年财产险保费不能为空")
    @ApiModelProperty("财产险保费（年目标）")
    private Long propInsurePremium;
    
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
    @NotNull(groups = {ResponsibilityEditValid.class},message = "当前年份不能为空")
    @ApiModelProperty("当前年份")
    private String isYear;
    
    private String automaticFlag;
    
    
    
    //是否团队长 0:不是 1:是
    private String isTeamLeader;
    
    
    List<PerfResponsibilityDetail> perfResponsibilityDetailList = new ArrayList<PerfResponsibilityDetail>();
    
    

}
