package com.ccic.salesapp.performance.domain.vo.request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改提现设置request")
public class UpdateConfigRequest {
	
	@ApiModelProperty("是否提现")
	private String isAutomatic;
	
	@ApiModelProperty("自动提现的频率")
	private String frequency;
	
	@ApiModelProperty("用户工号")
	String userCode;
	
	@ApiModelProperty("代理人工号")
	String agentCode;
	
	

}
