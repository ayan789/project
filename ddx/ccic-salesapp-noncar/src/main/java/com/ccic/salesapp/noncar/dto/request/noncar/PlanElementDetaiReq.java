package com.ccic.salesapp.noncar.dto.request.noncar;

import lombok.Data;

@Data
public class PlanElementDetaiReq {
	private String userCode;
	private Integer strategyId;
	private String productCode;
	private String code;// compute prospectus
	private Long orderId;//
	
}
