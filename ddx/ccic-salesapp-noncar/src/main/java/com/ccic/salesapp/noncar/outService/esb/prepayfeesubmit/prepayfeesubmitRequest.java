package com.ccic.salesapp.noncar.outService.esb.prepayfeesubmit;

import java.math.BigDecimal;

public class prepayfeesubmitRequest {
	String policyNo; // Y 批增保费需要传 保单号  
	String mainCertiNo; // Y 交强商业绑定号 主业务号
	String paymentNo;// Y 联合销售绑定支付号 绑定单号
	String platformSeqNO; // Y 上海平台特有，对于投保单是投保预确认码，对于批单是批改查询码 上海平台代码
	String contractNo; // Y 暂时不用 合同号
	String prePayFlag; // Y 0-非预付费，1-预付费 标志位
	String appliCode; // Y 客户号 投保人代码
	String insuredCode; // Y 客户号 被保险人代码
	String agentCode; // Y 代理人代码
	BigDecimal custNo;// Y 先不用，新承保暂时赋值为空 客户人员关系编号
	String doubleInputFlag; // Y "是否需要双录-[1]0代表不需要，1代表需要[2]字母代表地区（地区代码详见数据字典）" 是否双录认证标志
	String realNameCertiFlag; // Y 是否实名认证标志：[1]0代表不需要，1代表需要[2]字母代表地区（地区代码详见数据字典） 是否实名认证标志
}
