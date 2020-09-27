package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class DiscountDetail {
	
	private String discountCode;//优惠折扣代码
	private BigDecimal discountRate;//实际优惠折扣比率
	private String discountName;//是否保单级别优惠
	private String isPolicyLevel;//是否保单级别优惠
	private String productElementCode;//险种代码
	private String condition;//优惠条件





}
