package com.ccic.salesapp.noncar.dto.order.group.sales.policy.risk;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ccic.salesapp.noncar.dto.order.group.sales.PolicyCoverage;
import com.ccic.salesapp.noncar.dto.request.accidentquote.Beneficiary;
import com.ccic.salesapp.noncar.dto.request.accidentquote.CustomerDeclaration;

import lombok.Data;

@Data
public class PolicyRisk {

	
	Integer	numberOfCopies;//	N	Integer	份数
	String planCode;//	N	String	方案名称
	String productLine;//	N	String	AH/PC/AUTO
	String riskType;//	N	String	HEALTHY/PROPERTY/LIABILITY
	Integer insuranceType;//	N	Integer	主险/附加/赠送
	String itemCategory; //标的分类

	private BigDecimal duePremium;//责任保费
	private BigDecimal discountPremium;//折扣责任保费
	
	List<PolicyCoverage> policyCoverageList;
	
	
	/**
	 * 每个标的信息都要有被保人信息 
	 */
	/**************************************/
	//被保人序号
	private Long sequenceNumber;
	//客户角色类型(被保人，附属被保人)
	private String customerRoleCode;
	//个人-性别
	private String indiGenderCode;
	//年龄
	private Long age;
	//出生日期
	private String dateOfBirth;
	//健康状况
	private String healthStatus;
	//教育程度
	private String educationCode;
	//在职标志
	private String isOnJob;
	//生育状况
	private String bearStatus;
	//单位性质
	private String branchNatureCode;
	//客户姓名
	private String customerName;
	//关联保障计划组别
	private String keyCoveragePlanGroup;
	//婚姻状况
	private String maritalStatus;
	//有无社保
	private String socialSecurityFlag;
	//医保标识
	private String medicalInsuranceCode;
	//月工资收入
//		private BigDecimal monthWageIncome;
	//职业代码
	private String occupationCode;
	//职业类别
	private String occupationType;
	//兼职职业
	private String parttimeOccupationCode;
	//角色
	//private String customerRoleCode;
	//学生类型
//		private String studentType;
	//关联主被保险人序号
	private Long refInsuredSeq;
	//个人/机构-地址
	private String address;
	//个人/机构-邮件地址
	private String email;
	//个人/机构-证件号
	private String idNo;
	//个人/机构-证件有效期(止期)
	private String idValidThru;
	//个人-身份证有效期止期(止期)
	private Date indiIdEndDate;
	//个人-身份证有效期起期(止期)
	private Date indiIdStartDate;
	//个人-证件类别
	private String idType;
	//个人-手机号
	private String indiMobile;
	//个人/机构-国籍
	private String nationalityCode;
	//个人/机构-当事人类型/客户性质，个人/法人
	private String partyCategory;
	//个人/机构-邮编
	private String postCode;
	//个人/机构-联系电话，固定电话
	private String tel;
	//客户别名
	private String indiAliasName;
	//客户名称（拼音）
	private String namePinyin;
	//行业一级/二级分类代码,存放最底层代码
	private String industryCategory;
	//社保卡号
	private String socialSecurityNo;
	//所在国家
	private String locationCountryCode;
	//投保人与被保人关系
	private String polHolderInsuredRelaCode;
	//受益方式
	private String benefitModeCode;
	//是否使用公共保障计划
	private String isUsePublicLiability;
	//标的名称，内部冗余，根据一定规则从不同的标的子类中取值，用于报表/查询等
	private String insuredName;
	//机构-单位性质
	private String natureOfUnitCode;
	//附属被保险人与被保险人关系
	private String addInsuredInsuredRelaCode;
	//单位性质（反洗钱专用）
	private String antiMoneyOfUnitNatureCode;
	//个人/机构-代理人/代办人证件类型
	private String authAgentIdNo;
	//个人/机构-代理人/代办人证件号码
	private String authAgentIdType;
	//个人/机构-代理人/代办人有效止期
	private Date authAgentValidThru;
	//个人-身份证有效期起期(止期)
	private String idValidStart;
	//学校
	private String schoolName;
	//班级
	private String classes;
	//学号
	private String studentNo;
	//关联保障类型序号
	private Long refGuaranteeSeq;
	//手术类型
	private String operationType;
	//车/船次号
	private String vehicleNo;
	//座位号
	private String seatNo;
	//起始站
	private String startStation;
	//到达站 
	private String destination;
	//客票号
	private String ticketCode;
	//留学国家或地区
	private String studyAbroadCountryCode;
	//留学国家或地区教育机构名称
	private String studyAbroadOrgName;
	//航班号
	private String flightNo;
	//乘机日
	private String checkInDate;
	//麻醉类型
	private String anesthesiaType;
	//被保险人单证流水号
	private String insuredPrintNo;
	//单证流水号 
	private String visaSerialNumber;
	//受益人信息
	private List<Beneficiary> beneficiaryList;
	private String hasAffilInsured;//是否有附属被保人
	private Long affilInsuredNumber;//附属被保人人数
	private String authAgentType;//代理人身份
    private Date effectiveDate;//生效日期
    private Date expiryDate;//终止日期
    //告知信息列表
    private List<CustomerDeclaration> customerDeclarationList;
    //保障类型 区分被保人保障类型（1-普通分组，2-家庭分组，3-兼有）。例如被保人只享有家庭分组的保障，则传2；如果家庭分组和普通分组都享有，则传3
    private String guaranteeType;
}
