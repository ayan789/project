package com.ccic.salesapp.noncar.dto.order.group.sales.policy.risk;

import lombok.Data;

@Data
public class PropertyPolicyRisk extends PolicyRisk {

	private String itemCategory;//		项目类别
	private String insuredItemName;//	N	String	标的项目名称
	private String insuredName;//	N	String	标的名称
	private String insuredCategory;//	N	String	标的分类
	private String regionCode;//	N	String	区编码 例如"110101"
	private String postCode;//	N	String	邮编
	private String address;//	N	String	详细地址
	private String fullAddress;//	N	String	完整地址
}
