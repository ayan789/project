package com.example.tlyanprd;

import lombok.Data;

@Data
public class RateVo {
//	private Long planId;
//	private String planCoverageId;
	private Long rateAgeMin;
	private Long rateAgeMax;
	private Long periodMin;
	private Long periodMax;
	private Integer rateSeatMin;
	private Integer rateSeatMax;
	

}
