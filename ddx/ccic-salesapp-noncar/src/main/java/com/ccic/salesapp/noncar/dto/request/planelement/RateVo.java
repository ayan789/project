package com.ccic.salesapp.noncar.dto.request.planelement;

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
	private String rateSex;
	private String rateSocialSecurityFlag;
	private String rateOccupationType;
	private Double premium;

	

}
