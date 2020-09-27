package com.ccic.salesapp.performance.domain.vo.response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;


@Data
@ApiModel("代理人手续费查询response")
public class PlanFeeInquiryResponseVO {
	
	@ApiModelProperty("手续费信息")
	private List <PlanFeeVO> planFees;
	
	@ApiModelProperty("待生效手续费")
	private String pendingEffectFee;
	
	@ApiModelProperty("最小支付金额")
	private String minPayFee;

	@ApiModelProperty("提现开关")
	private String notAllowedPay;//提现开关 0 可提现 1 不可提现
		
}
