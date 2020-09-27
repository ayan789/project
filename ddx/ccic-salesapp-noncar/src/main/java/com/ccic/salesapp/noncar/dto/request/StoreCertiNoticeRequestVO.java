package com.ccic.salesapp.noncar.dto.request;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Data
@ApiModel("门店保单回写request")
public class StoreCertiNoticeRequestVO extends DataVO {
	
	@ApiModelProperty("投保单号")
	private String insureNo;
	
	@ApiModelProperty("保单号")
	private String policyNo;
	
	

	
	
	
}
