package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;
import lombok.Data;

@Data
public class PolicyInquiryResponse extends ResponseBody {
	private PolicyInquiryResponseBody policyElementString;
}
