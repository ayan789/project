package com.ccic.salesapp.performance.domain.vo.response;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel("月季度度达成进度")
public class ProgressMonthQuarterResponseVo {
	@ApiModelProperty("月总比率")
	private double MzRat = 0;
	@ApiModelProperty("月车险总比率")
	private double McRat = 0;
    @ApiModelProperty(" 月车意外险总比率")
	private double MaRat = 0;
    @ApiModelProperty(" 月健康险总比率")
	private double MhRat = 0;
    @ApiModelProperty("月财产总比率")
	private double MpRat = 0;
    @ApiModelProperty("月实际总保费")
	private double mzs;
    @ApiModelProperty("月目标总保费")
	private double mz;
    @ApiModelProperty("月实际车险总保费")
	private double mcs;
    @ApiModelProperty("月目标车险总保费")
	private double mc;
    @ApiModelProperty("  月实际意外总保费")
	private double mas;
    @ApiModelProperty(" 月目标意外总保费")
	private double ma;
    @ApiModelProperty("月实际健康险总保费")
	private double mhs;
    @ApiModelProperty(" 月目标健康险总保费")
	private double mh;
    @ApiModelProperty("月实际财产险总保费")
	private double mps;
    @ApiModelProperty("月目标财产险总保费")
	private double mp;
    @ApiModelProperty("季度总比率")
	private double QzRat = 0;
    @ApiModelProperty("季度车险总比率")
	private double QcRat = 0;
    @ApiModelProperty("季度意外险总比率")
	private double QaRat = 0;
    @ApiModelProperty("季度健康险总比率")
	private double QhRat = 0;
    @ApiModelProperty("季度财产总比率")
	private double QpRat = 0;
    @ApiModelProperty("季度实际总保费")
	private double qzs;
    @ApiModelProperty(" 季度目标总保费")
	private double qz;
    @ApiModelProperty("季度实际车险总保费")
	private double qcs;
    @ApiModelProperty(" 季度目标车险总保费")
	private double qc;
    @ApiModelProperty(" 季度实际意外总保费")
	private double qas;
    @ApiModelProperty("季度目标意外总保费")
	private double qa;
    @ApiModelProperty(" 季度实际健康险总保费")
	private double qhs;
    @ApiModelProperty(" 季度目标健康险总保费")
	private double qh;
    @ApiModelProperty("季度实际财产险总保费")
	private double qps;
    @ApiModelProperty("季度目标财产险总保费")
	private double qp;
}
