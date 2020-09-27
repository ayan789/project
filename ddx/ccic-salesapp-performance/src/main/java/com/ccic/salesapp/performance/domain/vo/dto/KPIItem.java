package com.ccic.salesapp.performance.domain.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("KPI考核项")
public class KPIItem {
	@ApiModelProperty("项目名称")
	public String kpiItem;
	@ApiModelProperty("项目比重")
	public String kpiItemWeight;
	@ApiModelProperty("项目得分")
	public String score;
}
