package com.ccic.salesapp.noncar.dto.order.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品信息")
public class Product {

	@ApiModelProperty("条款代码")
	String clauseCode;
	
	@ApiModelProperty("责任代码")
	String kindCode;
	
	@ApiModelProperty("应收保费/费率")
	Double duePremium;
	
	@ApiModelProperty("保费/费率类型")
	String premiumOrrate;
	
	@ApiModelProperty("保额")
	Double insured;
	
	@ApiModelProperty("责任名称")
	String productName;
	//责任分组类别1-普通分组，2-家庭分组
	private String insuredGroupType;
	
	private List<String> kindCodes; 
}
