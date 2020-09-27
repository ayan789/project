package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VehiclePolicyLob {
	
	private VehicleInsured policyRisk;//车辆信息
	private String proposalConfirmNo;//投保确认码
	private String proposalQueryNo;//投保查询码
	private BigDecimal additionalCostRate;//附加费用率
	private BigDecimal freeChannelRate;//自主渠道系数
	private BigDecimal freeUwRate;//自主核保系数
	private BigDecimal ncdRate;//无赔款优待系数
	private BigDecimal trafficViolationRate;//交通违法系数
	private VehicleTax vehicleTax;//车船税
	private VehicleCheck vehicleCheck;//验车信息
	private String isCompulsoryInsurance;//是否交强险保单
	private List<PolicyForm> policyForms;//特约信息

}
