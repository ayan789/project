package com.example.tlyanprd;

import java.util.List;

import lombok.Data;

@Data
public class PlanRate {
	private Long strategyId;
	private Long planId;
	private String node;
	private String userCode;
	private Long[] planCoverageIds;
	private List<RateVo> rate;

}
