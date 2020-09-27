package com.ccic.salesapp.noncar.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("电子保单")
public class ElectronicPolicyRequest {
	private String policyNo; //保单号
}
