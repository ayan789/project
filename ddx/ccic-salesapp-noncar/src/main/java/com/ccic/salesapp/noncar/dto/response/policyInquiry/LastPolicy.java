package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.util.List;

import com.ccic.salesapp.renewal.outService.policyInquiry.LastPolicyCoverage;
@Data
public class LastPolicy {
	
	private String lastPolicyNo;//上年保单号
	private List<LastPolicyCoverage> lastPolicyCoverages;//上年保单险别列表

	
	
	


}
