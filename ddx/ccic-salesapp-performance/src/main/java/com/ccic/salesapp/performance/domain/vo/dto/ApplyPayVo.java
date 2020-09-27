package com.ccic.salesapp.performance.domain.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("支付号申请")
public class ApplyPayVo {
	@ApiModelProperty("投保单号")
	String insureNo;
}
