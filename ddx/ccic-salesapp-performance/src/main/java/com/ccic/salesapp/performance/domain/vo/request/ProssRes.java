package com.ccic.salesapp.performance.domain.vo.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProssRes {
	 private double monthActualPremium;
	 private double monthTotalPremium;
	 private double rate;
}
