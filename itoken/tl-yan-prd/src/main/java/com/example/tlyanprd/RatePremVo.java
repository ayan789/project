package com.example.tlyanprd;

import lombok.Data;

@Data
public class RatePremVo {
	
	private Integer planId;
	private Integer rateAgeMinType;
	private Long rateAgeMin;
	private Integer rateAgeMaxType;
	private Long rateAgeMax;
	private Long periodMin;
	private Long periodMax;
	private Integer ratePeriod;
	private Double premium;
	
}
