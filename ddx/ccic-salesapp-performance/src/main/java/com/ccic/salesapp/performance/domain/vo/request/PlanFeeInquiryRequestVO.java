package com.ccic.salesapp.performance.domain.vo.request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("代理人手续费查询request")
public class PlanFeeInquiryRequestVO{
	
	
	@ApiModelProperty("01-查询个人代理所有未结算的手续费金额,02-根据归属经办人查询下属个人代理所有未结算的手续费金额")
	private String queryType;
	
	@ApiModelProperty("加密后的代理人code")
	private String desagentCode;	
	
	@ApiModelProperty("人员工号")
	private String userCode;
	
	@ApiModelProperty("代理人工号")
	private String agentCode;
	
	@ApiModelProperty("经办人工号")
	private String handlerCode;
	
	@ApiModelProperty("页数")
	PageVO page;
}
