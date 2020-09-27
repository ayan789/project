package com.ccic.salesapp.performance.domain.vo.dto;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Data
@ApiModel("查询用户提现设置response")
public class EnchashmentConfig {

	
	@ApiModelProperty("id")
	private String id;
	
	@ApiModelProperty("代理人代码")
	private String agentCode;
	
	@ApiModelProperty("是否自动提现：1，是  2，否")
	private String isAutomatic;
	
	@ApiModelProperty("自动提现的频率")
	private String frequency; 
	
}
