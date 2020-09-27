package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CoInsuranceInfo {
	private String internalCoInsuranceType;//联保标识
	private BigDecimal coinInsRate;//共保比例
	private BigDecimal innerCoinRate;//联保比例
	private String isLeaderGenerated;//是否由主联生成从联数据

}
