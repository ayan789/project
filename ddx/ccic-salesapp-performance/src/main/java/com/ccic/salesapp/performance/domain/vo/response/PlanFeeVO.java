package com.ccic.salesapp.performance.domain.vo.response;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Data
@ApiModel("手续费信息")
public class PlanFeeVO {	
	
	@ApiModelProperty("加密后的代理人代码")
	private String desagentCode;
	
	@ApiModelProperty("代理人代码")
	private String agentCode;
	
	@ApiModelProperty("代理人名称")
	private String agentName;
	
	@ApiModelProperty("手续费")
	private String planFee;
	
	@ApiModelProperty("年提现总额")
	private String sumFeePayYear;		
	
	@ApiModelProperty("年到账总额")
	private String sumFeeAccountYear;	
	
	@ApiModelProperty("月提现总额")
	private String sumFeePayMonth;		
	
	@ApiModelProperty("月到账总额")
	private String sumFeeAccountMonth;	
	
	@ApiModelProperty("年扣税总额")
	private String sumTaxDeductionYear;	
	
	@ApiModelProperty("月扣税总额")
	private String sumTaxDeductionMonth;
	

	
	
}
