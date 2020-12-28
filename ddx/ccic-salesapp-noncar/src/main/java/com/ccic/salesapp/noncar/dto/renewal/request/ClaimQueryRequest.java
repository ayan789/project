package com.ccic.salesapp.noncar.dto.renewal.request;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;

import lombok.Data;

@Data
public class ClaimQueryRequest extends RequestBody {
	private String policyNo;
}
