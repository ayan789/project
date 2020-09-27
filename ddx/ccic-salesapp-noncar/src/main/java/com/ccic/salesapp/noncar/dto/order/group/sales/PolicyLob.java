package com.ccic.salesapp.noncar.dto.order.group.sales;

import java.math.BigDecimal;
import java.util.List;

import com.ccic.salesapp.noncar.dto.order.group.sales.policy.risk.PolicyRisk;

import lombok.Data;

@Data
public class PolicyLob {
	
	private String policyNo;// 响应字段 保单号
	
	private String proposalNo;// 响应字段 投保单号
	
	private String submissionNo;//联合销售单号
	
	private String isMainProduct;// 请求字段 是否主产品
	
	private BigDecimal premiumBeforeDiscount;//折扣前责任保费
	
	private BigDecimal duePremium;//折扣责任保费
	
	private String childPlanCode;//子产品方案代码
	
	private Integer numberOfCopies;//份数
	
	private String productLine;//一级产品线分类
	
	private String productCode;//产品代码

	private List<PolicyRisk> policyRiskList;
}
