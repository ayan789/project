package com.ccic.salesapp.performance.domain.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("年度达成进度")
public class ProgressYearResponseVo {
	@ApiModelProperty("实际总保费")
	double monthActualPremium;
	@ApiModelProperty("目标总保费")
	double monthTotalPremium;
	@ApiModelProperty("比率")
	double rate;
	
	 
}
