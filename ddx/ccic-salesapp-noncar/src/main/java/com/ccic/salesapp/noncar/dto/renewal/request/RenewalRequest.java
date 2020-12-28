package com.ccic.salesapp.noncar.dto.renewal.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RenewalRequest {

	@ApiModelProperty("保单号")
	private String policyNo;
	
}
