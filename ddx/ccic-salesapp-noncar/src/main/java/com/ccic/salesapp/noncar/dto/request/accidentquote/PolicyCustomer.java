package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;

import lombok.Data;
@Data
public class PolicyCustomer {
	private String customerRoleCode;//客户角色类型(投保人、关键联系人)
	private String isContactPolHolder;//是否与投保人为同一人
	private String polHolderInsuredRelaCode;//与被保险人关系
	private String permanentAddress;//常驻地址
	private String partyCategory;//客户类型
	private String customerName;//客户姓名/名称
	private Long indiAge;//年龄
	private String indiGenderCode;//性别
	private String indiDateOfBirth;//出生日期
	private String idType;//证件类型
	private String idNo;//证件号码
	private Date idValidThru;//证件有效期
	private String indiMobile;//个人-手机号
	private String email;//邮箱地址
	private String indiOccupationCode;//个人-职业代码
	private String indiOccupationType;//个人-职业类别
	private String nationalityCode;//国籍
	private String locationCountryCode;//所在国家
	private String tel;//联系人电话
	private String address;//联系地址
	private String postCode;//邮编
	private String antiMoneyOfUnitNatureCode;//单位性质（反洗钱专用）
	private String branchNatureCode;//单位性质
	private String nationalEconomyCate;//行业分类(存放代码表中行业小类码值)
	private String industryCategory;//机构-行业二级分类(存放代码表中行业小类码值)
	private String orgSocialSecurityRegNo;//社保保险登记号
	private Long totalEmployeeCount;//单位总人数
	private Long avgChildCount;//平均附属子女数
	private String orgContactFax;//传真
	private String authAgentIdentity;//代理人身份
	private String indiAliasName;//客户别名
	private String namePinyin;//客户名称（拼音）
	private String indiJobTitle;//客户职务
	private Date businessLicenseValidThru;//营业执照有效期
	private String shareholderType;//股东类型
	private String orgShareholderName;//实际股东/实际控制人
	private String orgShareholderIdType;//控股股东/实际控制人证件类型
	private String orgShareholderIdNo;//控股股东/实际控制人证件号码
	private Date orgShareholderIdValidThru;//控股股东/实际控制人证件有效期
	private String orgLegalReprName;//法人代表/负责人
	private String orgLegalReprIdType;//法人代表/负责人证件类型
	private String orgLegalReprIdNo;//法人代表/负责人证件号码
	private Date orgLegalReprIdValidThru;//法人代表/负责人证件有效期
	private String orgLegalReprJob;//法人代表/负责人职务
	private String orgAuthAgentName;//授权经办人
	private String orgAuthAgentIdType;//授权经办人证件类型
	private String orgAuthAgentIdNo;//授权经办人证件号码
	private Date orgAuthAgentIdValidThru;//授权经办人证件有效期
	private String orgContactEmail;//机构-联系人邮箱
	private String orgContactAddress;//机构-联系人地址
}
