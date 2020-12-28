package com.ccic.salesapp.noncar.dto.order;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("子产品")
public class ChildProduct {
	
	@ApiModelProperty("子方案代码")
	Integer childPlanId;
	
	@ApiModelProperty("方案代码")
	String planCode;
	
	@ApiModelProperty("产品代码")
	String productCode;
	
	@ApiModelProperty("产品名称")
	String productName;
	
	@ApiModelProperty("子方案名称")
	String childPlanName;
	
	@ApiModelProperty("产品线")
	String productLine;
	
	@ApiModelProperty("条款列表")
	List<Clause> clauseList;

}
