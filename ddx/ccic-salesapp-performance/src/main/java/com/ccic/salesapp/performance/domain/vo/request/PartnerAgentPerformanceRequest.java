package com.ccic.salesapp.performance.domain.vo.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("伙伴代理人业绩查询")
public class PartnerAgentPerformanceRequest {
	
	@NotBlank(message="伙伴代理人代码不能为空")
	@ApiModelProperty("伙伴代理人代码")
	String agentCode;
	
	
}
