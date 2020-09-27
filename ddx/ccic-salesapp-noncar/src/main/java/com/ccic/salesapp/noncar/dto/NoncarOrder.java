package com.ccic.salesapp.noncar.dto;

import java.util.Date;
import java.util.List;

import com.ccic.salesapp.noncar.dto.request.DataVO;

import lombok.Data;

@Data
public class NoncarOrder extends DataVO {
	private String id;
	private String orderNo;
	private String orderStatus;// 订单状态
	private String isDelete;// 删除标识
	private String productCode;// 产品代码
	private String insureNo;// 投保单号
	private String policyNo;// 保单号
	private String productName;// 核心产品名称
	private String userCode;// 用户代码
	private Date startDate;// 保障开始日期
	private Date endDate;// 保障截止日期
	private String identityCode;// 验证码
	private String payApplyNo;// 支付号
	private Integer planId;// 方案主键
	private String InsuranceName;// 被保人姓名
	private String applicantName;// 投保人姓名
	private String idCardType;// 证件类型
	private String comCode;// 出单机构代码
	private String customerId;// 角色ID
	private String orderId;// 订单ID
	private String customerRoleCode;// 客户角色类型
	private String isContactPolHolder;// 是否与投保人为同一人
	private String polHolderInsuredRelaCode;// 与被保险人关系
	private String permanentAddress;// 常驻地址
	private String partyCategory;// 客户类型
	private String customerName;// 客户姓名/名称
	private String indiAge;// 年龄
	private String indiGenderCode;// 性别
	private Date indiDateOfBirth;// 出生日期
	private String idType;// 证件类型
	private String idNo;// 证件号码
	private String indiMobile;// 个人-手机号
	private String email;// 邮箱地址
	private String signStatus;// 签名状态，1 已签名 ，0 未签名
	private String sumPremium;// 总保费
	private String clauseCode;// 条款代码
	private String kindCode;// 责任代码
	private String duePremium; // 保费
	private String insured; // 保额
	private Integer needSign;
	private String handlerCode;// 经办人代码
	private String imgId; // 影像ID
	private String imgType; // 影像类型
	private Integer strategyId; // 投保人表关联Id
	private Integer strategyType;// 销售方案类型(1:非车 2:联合销售)
	private Integer validStatus; // 是否有效(1:是 0:否)
	private String insureType;// 承保险种代码
	private Integer uploadPhoto;// 是否上传影像(1:是0:否)
	private Integer policyStartType;// 保险起期时间类型(1:t+1 2:t+2)
	private Integer policyStartRange;// 保险起期时间调整范围
	private Date policyCreateDate;//保单生成日期
	private Date payDate;//支付時間
	private Date createTime;
	private String operatorCode;//操作员代码
	private String operatorName;//操作员名称
	private String agentCode;//代理人代码
	private String agreementNo;//代理人协议号
	private String agreementName;//代理人协议名称
	private String handlerCode2;//经办人代码2
	private String mobile;
	private String isIssueAfterPay;//是否非见费 1：见费 0 非见费'
	private String isNeedSign;//是否需要签名 1需要 0 不需要
	private String isNeedUploadImg;//是否需要 1需要 0 不需要
	private String insuranceCategory;
	private List<String> notUploadImgList;
	private Long relationOrderId;
	private String strategyCode;
}
