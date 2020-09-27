package com.ccic.salesapp.noncar.dto.order.group.sales.policy.risk;

import lombok.Data;

@Data
public class LiabilityPolicyRisk extends PolicyRisk {

	private String insuredName;//	N	String	标的名称
	private String productName;//	N	String	卡、账户类型
	private String planCode;//	N	String	方案名称

	
}
