package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PlatformInfo {
	
	private BigDecimal cbPaidAmt;//平台上年度赔款金额
	private Integer cbPaidT;//平台上年度赔款次数
	private Integer violationPoints;//去年违章次数
	


}
