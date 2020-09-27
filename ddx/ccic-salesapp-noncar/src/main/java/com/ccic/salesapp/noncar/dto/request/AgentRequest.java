package com.ccic.salesapp.noncar.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("代理人查询")
public class AgentRequest {

	@ApiModelProperty("代理人代码")
	String agentCode;
}
