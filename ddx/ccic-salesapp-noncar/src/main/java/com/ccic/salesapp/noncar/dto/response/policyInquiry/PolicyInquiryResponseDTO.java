package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.util.List;

@Data
public class PolicyInquiryResponseDTO {
	private Policy policy;//保单信息
	private List<Endorsement> endorsementList;//批单信息列表

	

}