package com.ccic.salesapp.performance.domain.vo.response;

import java.util.List;

import com.ccic.salesapp.performance.domain.vo.dto.KPIItem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("KPI")
public class KPIResponse {
	@ApiModelProperty("总得分")
	String score;
	@ApiModelProperty("KPI考核项目列表")
	List<KPIItem>  kpiItems;
	
}
