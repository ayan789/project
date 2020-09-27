package com.ccic.salesapp.noncar.dto.salesplan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("组合方案详情参数")
public class GroupPlanVo {
	@ApiModelProperty("业务员")
	String userCode;//用户code
	@ApiModelProperty(value="组合方案code",name="groupPlanCode",required=true)
	String groupPlanCode;//组合方案代码
	@ApiModelProperty("订单id")
	String orderId;//订单ID
	@ApiModelProperty("渠道")
	String channelCode;
	@ApiModelProperty("机构")
	String comCode;
	
}
