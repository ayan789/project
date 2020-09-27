package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.List;
import java.util.Map;


import lombok.Data;
@Data
public class AddressInfo {
	//场所类型标的名称
		private String addressName;
		//邮编
		private String postCode;
		//国家代码
		private String countryCode;
		//地区代码
		private String regionCode;
		//地址
		private String address;
		//完整地址(内部冗余)
		private String fullAddress;
		//姓名
		private String name;
		//证件类型
		private String idType;
		//证件号码
		private String idNo;
		//应收保费/真实保费/RealPremium,=调整保费
		private double duePremium;
		//保额
		private double sumAmout;
		//标的信息
		private List<PolicyRisk> policyRiskList;
		private Map<String, String> tempData;// 临时数据
		private Long sequenceNumber;//序号
}
