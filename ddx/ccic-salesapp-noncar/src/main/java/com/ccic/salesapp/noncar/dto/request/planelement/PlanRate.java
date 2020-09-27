package com.ccic.salesapp.noncar.dto.request.planelement;

import java.util.List;

import lombok.Data;

@Data
public class PlanRate {
	private Integer planId;
	private List<Long> planCoverageIds;
	private List<RateVo> rate;

}
