package com.ccic.salesapp.noncar.repository.databusdb.po;

import lombok.Data;

import java.util.Date;

@Data
public class StoreInsureInfo {
	private Long  id;                          //物理主键
	private String insureNo;                	//投保单号
	private String policyNo;                	//保单号
	private String insuranceCode;        		//险种代码
	private String insuranceName;       		//险种名称
	private Date   createDate;             	//记录创建日期
	private Date   startDate;                	//保单起期
	private Date   endDate;                 	//保单止期
	private String startTime;                	//保单起时
	private String endTime;                 	//保单起时
	private String insurancePeriod;      		//保险期限
	private Date   underwriteDate;       		//核保日期
	private String sumPremium;           		//总保费
	private String sumAmount;            		//总保额
	private String businessType;          		//业务归属
	private String status;                     //投保单状态
	private String userId;                     //所属用户
	private int insuranceCount;        		//投保份数
	private String licensePlateNo;         	//车牌号
	private String comCode;                	//业务归属机构
	private String handlerCode;           		//经办人代码
	private String operatorCode;         		//操作员代码
	private String operatorName;        		//操作员名称
	private String payApplyNo;            		//支付号
	private String identityCode;            	//支付验证码
	private String applicantName;         		//投保人姓名
	private String appliIdentify;            	//投保人身份证号码
	private String appliIdentifyType;			//投保人证件类型
	private String insuredName;           		//被保人姓名
	private String insuredIdentify;         	//被保人身份证号码
	private String insuredIdentifyType;			//被保险人证件类型
	private Date   birthDate;                	//出生日期
	private String mobileNo;               	//手机号
	private String address;                	//地址
	private String email;                      //邮箱
	private String formulaCode;				//方案代码
	private String formulaName;				//方案名称
	private String relateInsured; 				//与被保险人的关系
	private String imei;						//设备号
	private String agentCode;//代理人代码
	private String agreementNo;//代理人协议号
	private String agreementName;//代理人协议名称
	private String insuranceType;//保险类别
	private String signStatus;//签名状态
	private String imgId;//影像ID
	private String additionalCode;//附加险是否选中代码以_分割

	private String handlerCode2;//经办人2
	private String desProposalNo;//加密后的投保单号
	private String isRunApp;//是否app出单
	private String platformFlag;				//平台标识 1-新华出单
	private String nclUserCode;					//新华用户标识

	//监护人责任险
	private String beGuardianSex;				//被监护人性别
	private Date beGuardianBirthDate;			//被监护人生日
	private String productCode;			//产品代码
	//露天展台 0露天 1非露天
	private String isOpenair;
	//展位编号
	private String showNo;
	//展会名称
	private String showName;

	private String payFlag;//能否支付：1是0否
	private String provinceCode;//省份代码
	private String prefectureCode;//辖区代码
	private String countyCode; //县级代码

	private String productName;//产品名称

	//展会面积
	private String exhibitionArea;
	//被保人手机号码
	private String insuredMobile;

	private Long associatedInsureID;//关联投保单ID

}
