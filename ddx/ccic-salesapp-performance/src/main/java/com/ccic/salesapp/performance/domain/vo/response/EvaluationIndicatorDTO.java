package com.ccic.salesapp.performance.domain.vo.response;

import java.math.BigDecimal;

public class EvaluationIndicatorDTO {

	private String indicatorName;
	private BigDecimal indicatorWeight;
	private BigDecimal indicatorScore;

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	public BigDecimal getIndicatorWeight() {
		return indicatorWeight;
	}

	public void setIndicatorWeight(BigDecimal indicatorWeight) {
		this.indicatorWeight = indicatorWeight;
	}

	public BigDecimal getIndicatorScore() {
		return indicatorScore;
	}

	public void setIndicatorScore(BigDecimal indicatorScore) {
		this.indicatorScore = indicatorScore;
	}

}
