package com.ccic.salesapp.noncar.dto.request.planelement;

import lombok.Data;


@Data
public class RdsRate {
	int planId;
	Long rateAgeMin;
	Long rateAgeMax;
	Long periodMin;
	Long periodMax;
}
