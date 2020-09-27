package com.ccic.salesapp.noncar.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品责任条款信息")
public class Clause {

	@ApiModelProperty("条款代码")
	String clauseCode;
	
	@ApiModelProperty("责任代码")
	String kindCode;
	
	@ApiModelProperty("责任名称")
	String kindName;
	
	@ApiModelProperty("保额单位")
	String unit;
	
	@ApiModelProperty("责任类型")
	String kindType;
	
	@ApiModelProperty("责任id")
	Integer coverageId;
	

	@ApiModelProperty("标的类型")
	String insuredType;

	@ApiModelProperty("保额")
	String sumInsured;

}
