package com.ccic.salesapp.performance.domain.vo.request;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel("消息通知")
public class MessageVo {
	@ApiModelProperty("业务员工号")
	private String userCode;
	@ApiModelProperty("通知类型")
	private String messageType;
}
