package com.ccic.salesapp.noncar.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("核保状态通知")
public class UnderwriteStatusRequest {
	@ApiModelProperty("投保单号")
    private String applicationNo;
	@ApiModelProperty("核保员代码")
	private String underwriterCode;
	@ApiModelProperty("核保员名称")
	private String underwriterName;
	@ApiModelProperty("核保状态(1:核保成功 0:核保失败)")
	private String underwriteStatus;
	@ApiModelProperty("核保时间(yyyy-MM-dd HH:mm:ss)")
	private String underwriteTime;
	@ApiModelProperty("核保意见")
	private String underwriteText;

}
