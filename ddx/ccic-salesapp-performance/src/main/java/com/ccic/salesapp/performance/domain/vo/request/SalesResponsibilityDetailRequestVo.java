package com.ccic.salesapp.performance.domain.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesResponsibilityDetailRequestVo {
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
    @ApiModelProperty("月总保费")
    private Long insurePremium;

    /**
     * 车险保费（月目标）
     */
    @ApiModelProperty("车险保费（月目标）")
    private Long carInsurePremium;

    /**
     * 意外险保费（月目标）
     */
    @ApiModelProperty("意外险保费（月目标）")
    private Long accInsurePremium;

    /**
     * 健康险保费（月目标）
     */
    @ApiModelProperty("健康险保费（月目标）")
    private Long heaInsurePremium;

    /**
     * 财产险保费（月目标）
     */
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

}
