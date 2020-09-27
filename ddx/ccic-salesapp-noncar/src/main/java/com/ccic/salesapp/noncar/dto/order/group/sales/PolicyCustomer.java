package com.ccic.salesapp.noncar.dto.order.group.sales;

import java.util.Map;

import lombok.Data;

@Data
public class PolicyCustomer {
	
	/*************意健险被保人特有字段***************/
	private Long age;
	
	private String socialSecurityFlag;
	
	
	
	/***********************************/
	private String accountName                ;//个人/机构-账户名称
	private String accountNo                  ;//个人/机构-开户账号
	private String addInsuredInsuredRelaCode  ;//附属被保险人与被保险人关系
	private String address                    ;//个人/机构-地址
	private String antiMoneyOfUnitNatureCode  ;//单位性质（反洗钱专用）
	private String antiMoneyResultCode        ;//反洗钱黑名单命中结果，由内部的反洗钱系统返回（0:未命中，1：命中）（呈报流程和批改流程都会写入）
	private String authAgentContactMode       ;//个人/机构-代理人/代办人联系方式
	private String authAgentIdNo              ;//个人/机构-代理人/代办人证件号码
	private String authAgentIdType            ;//个人/机构-代理人/代办人证件类型
	private String authAgentIdentity          ;//机构-代理人身份
	private String authAgentName              ;//个人/机构-代理人/代办人姓名
	private String authAgentType              ;//个人/机构-代理人/代办人类型
	private String authAgentValidThru         ;//个人/机构-代理人/代办人有效止期
	private String bankCode                   ;//个人/机构-开户行代码
	private String bearStatus                 ;//生育状况
	private String branchNatureCode           ;//单位性质
	private String businessLicenseNo          ;//经营许可证/营业执照号码
	private String businessLicenseValidStart  ;//营业执照有效期起期
	private String businessLicenseValidThru   ;//营业执照有效期(止期)
	private String businessScope              ;//经营范围
	private String customerName               ;//客户姓名/单位名称
	private String customerRiskType           ;//客户分类类型（181000：VIP级别181001：反洗钱黑名单181002：风险黑名单181003：信用黑名单181004：灰名单）
	private String customerRoleCode           ;//客户角色类型(投保人/被保人、车主/关键联系人)
	private String customerNo                 ;//客户号/客户编码
	private String educationCode              ;//教育程度
	private String email                      ;//个人/机构-邮件地址
	private String idCheckResultCode          ;//证件检查结果/证件核查情况
	private String idNo                       ;//个人/机构-证件号
	private String idType                     ;//证件类别(个人、机构)
	private String idValidStart               ;//个人身份证有效期起期(止期)
	private String idValidThru                ;//个人/机构-证件有效期(止期)
	private String indiAge                    	  ;//年龄
	private String indiDateOfBirth            ;//个人-出生日期
	private String indiGenderCode             ;//个人-性别
	private String indiIdAddress              ;//个人-身份证户籍住址
	private String indiIdGatherCaptcha        ;//北京地区身份证采集验证码
	private String indiIdIssuingAuthority     ;//个人-身份证签发机构
	private String indiJobStatus              ;//个人-从业情况
	private String indiJobTitle               ;//个人-职务
	private String indiMobile                 ;//个人-手机号
	private String indiNation                 ;//个人-民族
	private String indiOccupationCode         ;//个人-职业代码
	private String indiOccupationType         ;//个人-职业类别
	private String industryCategory           ;//行业一级/二级分类代码,存放最底层代码
	private String insuredVehicleRelaCode     ;//被保人与车辆关系
	private String invoiceType                ;//发票类型
	private String isAuthAgentProcedure       ;//个人/机构-代理人/代办人业务手续是否齐全,Y/N
	private String isCollectIdInfo            ;//是否身份采集
	private String isContactPolHolder         ;//是否关键联络人与投保人为同一人
	private String isIdValidLongTerm          ;//证件是否长期有效
	private String isOnJob                    ;//在职标志
	private String isReceiveSms               ;//是否接收短信(Y/N)
	private String locationCountryCode        ;//所在国家
	private String maritalStatus              ;//婚姻状况
	private String medicalInsuranceCode       ;//医保标识
	private Double monthSalaryIncome          ;//月工资收入
	private String namePinyin                 ;//客户名称（拼音）
	private String nationalEconomyCate        ;//国民经济行业分类，可以是门类/大类/种类/小类，存储最小分类，使用国标代码
	private String nationalityCode            ;//个人/机构-国籍
	private String orgAuthAgentIdNo           ;//机构-授权经办人证件号码
	private String orgAuthAgentIdType         ;//机构-授权经办人证件类型
	private String orgAuthAgentIdValidThru    ;//机构-授权经办人证件号码有效期(止期)
	private String orgAuthAgentName           ;//机构-授权经办人
	private String orgContactAddress          ;//机构-联系人地址
	private String orgContactEmail            ;//机构-联系人邮箱
	private String orgContactFax              ;//机构-联系人传真
	private String orgContactPersonName       ;//机构-联系人姓名
	private String orgContactPersonTel        ;//机构-联系人电话
	private String orgLegalReprIdNo           ;//机构-法人代表/负责人证件号码
	private String orgLegalReprIdType         ;//机构-法人代表/负责人证件类型
	private String orgLegalReprIdValidStart   ;//法人代表/负责人证件有效期起期
	private String orgLegalReprIdValidThru    ;//机构-法人代表/负责人证件有效期(止期)
	private String orgLegalReprJob            ;//机构-法人代表/负责人职务
	private String orgLegalReprName           ;//机构-法人代表/负责人
	private String orgMobile                  ;//机构-移动电话
	private String orgNatureOfUnitCode        ;//机构-单位性质
	private String orgRegistrationAddress     ;//机构-单位注册地址
	private String orgRegistrationName        ;//机构-单位注册名称
	private String orgRegistrationTel         ;//机构-单位注册电话
	private String orgShareholderIdNo         ;//机构-控股股东/实际控制人证件号码
	private String orgShareholderIdType       ;//机构-控股股东/实际控制人证件类型
	private String orgShareholderIdValidStart ;//控股股东/实际控制人证件有效期起期
	private String orgShareholderIdValidThru  ;//机构-控股股东/实际控制人证件有效期(止期)
	private String orgShareholderName         ;//机构-控股股东/实际控制人
	private String orgShareholderStructureCode;//机构-股权构成
	private String orgSocialSecurityRegNo     ;//机构-社保登记号
	private String orgTaxNo                   ;//机构-纳税人识别号
	private String orgTaxOrgName              ;//机构-纳税人单位名称
	private String parttimeOccupationCode     ;//兼职职业
	private String partyCategory              ;//个人/机构-当事人类型/客户性质，个人/法人
	private String permanentAddress           ;//常驻地址
	private String polHolderInsuredRelaCode   ;//投保人与被保人关系
	private String postCode                   ;//个人/机构-邮编
	private String organizationCode           ;//组织机构代码（外部机构代码，不需要码表）
	private String residenceNationalityCode   ;//住所国籍
	private String shareholderType            ;//股东类型
	private String socialSecurityNo           ;//社保卡号
	private String studentType                ;//学生类型/工作职务
	private String studyAbroadCountryCode     ;//留学国家或地区
	private String studyAbroadOrgName         ;//留学国家或地区教育机构名称
	private String taxRegistAddress           ;//税务登记地址
	private String taxRegistryPhone           ;//税务登记电话号码
	private String tel                        ;//个人/机构-联系电话，固定电话
	private Long sequenceNumber;//序号
	private Map<String,String> tempData; //临时数据
	private String contactPersonName;		//联系人姓名
	private String contactPersonTel;		//联系人电话
	
	//财产险字段
	private String fullAddress;//	N	String	完整地址
}
