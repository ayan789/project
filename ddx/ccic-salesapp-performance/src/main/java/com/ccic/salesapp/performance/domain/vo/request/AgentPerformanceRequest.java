package com.ccic.salesapp.performance.domain.vo.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("代理人业绩查询")
public class AgentPerformanceRequest {
	
	@NotBlank(message="代理人代码不能为空")
	@ApiModelProperty("代理人代码")
	String agentCode;
	
	
}
