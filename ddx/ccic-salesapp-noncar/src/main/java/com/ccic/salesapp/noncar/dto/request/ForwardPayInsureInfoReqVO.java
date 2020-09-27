package com.ccic.salesapp.noncar.dto.request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.ccic.salesapp.noncar.dto.DataVO;


@Data
@ApiModel("订单转发支付查询信息request")
public class ForwardPayInsureInfoReqVO extends DataVO{
	
	@ApiModelProperty("投保单号")
	private String proposalNo;
	
	@ApiModelProperty("转发时间")
	private String forwardDate;	
	
	@ApiModelProperty("区分转发和复制投保单 0-转发 1 复制投保单")
	private String status;	
	
	
	
}
