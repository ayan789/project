package com.ccic.salesapp.noncar.repository.basedb.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("代理人信息")
public class Agent {

	@ApiModelProperty("代理人id")
	String id;
	
	@ApiModelProperty("业务来源于")
	String businessSource;
	
	@ApiModelProperty("代理人代码")
	String agentCode;
	
	@ApiModelProperty("代理人姓名")
	String agentName;
	
	@ApiModelProperty("用户id")
	String staffCode;
	
}
