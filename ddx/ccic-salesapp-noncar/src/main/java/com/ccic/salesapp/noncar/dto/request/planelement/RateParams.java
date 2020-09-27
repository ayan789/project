package com.ccic.salesapp.noncar.dto.request.planelement;

import lombok.Data;

@Data
public class RateParams {
	private String rateAgeMin;
	private String rateAgeMax;
	private String isHaveSheBao;
	double premium;

}
