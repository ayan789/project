package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data

public class PolicyCustomer {
	private String customerRoleCode;//客户角色类型
	private String customerName;//客户姓名/单位名称
	private String idType;//证件类别
	private String indiMobile;//个人-手机号
	private String address;//个人/机构-地址
	private String email;//个人/机构-邮件地址
	private String idNo;//个人/机构-证件号
	private String partyCategory;//个人/机构-当事人类型/客户性质
	private String tel;//个人/机构-联系电话，固定电话
	private String indiGenderCode;//性别
	private String indiAge;//年龄
	private String customerRiskType;//客户分类类型
	private String customerLevelCode;//客户级别
	private String customerNo;//客户代码
	private String branchNatureCode;//个人-单位性质
	private String orgNatureOfUnitCode;//企业-单位性质
	private String postCode;//个人/机构-邮编
	private String idValidThru;//证件有效期起
	private String idValidStart;//
	@ApiModelProperty("扩展字段")
	private Map<String, String> extensionMap;


}
