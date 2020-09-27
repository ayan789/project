package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class VehicleAccessory {
	private String    accessoryName;//设备名称
	private Integer   accessoryCount;//设备数量
	private String      purchaseDate;//设备购置日期
	private BigDecimal purchasePrice;//设备购置价
	private BigDecimal realValue;//设备实际价值



}
