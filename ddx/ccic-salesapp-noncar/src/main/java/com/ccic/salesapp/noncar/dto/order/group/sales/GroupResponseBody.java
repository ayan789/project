package com.ccic.salesapp.noncar.dto.order.group.sales;

import java.math.BigDecimal;
import java.util.List;

import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;

import lombok.Data;

@Data
public class GroupResponseBody extends ResponseBody{
	/**归属经办人2代码*/
	private String  belongToHandler2Code;
	/**归属经办人代码*/
	private String  belongToHandlerCode;

	/**出单渠道*/
	private String  businessAttribute;
	
	/**归属经办人的业务来源*/
	private String  businessSourceCode;
	
	/**归属经办人2的业务来源*/
	private String  businessSource2Code;
	
	/**业务机构*/
	private String  orgCode;

	/**起保日期*/
	private String  effectiveDate;
	/**终保日期*/
	private String  expiryDate;
	/**是否见费出单*/
	private String isIssueAfterPay;
	/**投保日期*/
	private String proposalDate;
	/**保费币种代码*/
	private String premiumCurrencyCode;
	/**组合方案代码*/
	private String groupPlanCode;
	/**销售方案代码*/
	private String salesPlanCode;
	/**从人渠道代码*/
	private String channelByPerson;
	
	private BigDecimal premiumBeforeDiscount;//折扣前责任保费
	
	private BigDecimal duePremium;//折扣责任保费
	
	private String agentCode;
	
	private String agreementNo;
	
	private String belongToHandler2Name;
	
	private String belongToHandlerName;
	
	private String handlerCode;
	
	private String handlerName;
	
	private String issueOrgCode;
	
	private String issueUserId;
	
	private List<InvoiceInfo> invoiceInfoList;
	
	/****/
	
	/**渠道信息*/
	private List<ChannelOpInfo>  channelOpInfoList ;
	/**投被保人信息*/
	private List<PolicyCustomer>  policyCustomerList ;
	/**特别约定信息*/
	private List<PolicyForm>  policyFormList;
	
	private List<PolicyLob> policyLobList;
}
