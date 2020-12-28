package com.ccic.salesapp.noncar.dto.renewal.request;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RenewalStatusQueryRequestDTO extends RequestBody {

	@ApiModelProperty("保单号")
	private String policyNo;
	
}
