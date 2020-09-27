package com.ccic.salesapp.noncar.dto.response.policyInquiry;


import lombok.Data;

import java.util.List;
@Data
public class PolicyInquiryResponseBody {
	private List<PolicyInquiryResponseDTO> policyInfoList;//保单信息列表
	private List<String> nonAutoPolicyList;//车+X的X保单列表


}
