package com.ccic.salesapp.noncar.dto.order.response;

import java.util.HashMap;
import java.util.List;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.order.ChildProduct;
import com.ccic.salesapp.noncar.dto.order.Customer;
import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;
import com.ccic.salesapp.noncar.repository.basedb.po.Order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupOrderResponse {

	
	@ApiModelProperty("开始时间")
	String startDate;
	
	@ApiModelProperty("结束时间")
	String endDate;
	
	@ApiModelProperty("保费")
	Double duePremium;
	
	@ApiModelProperty("折扣保费")
	Double discountPremium;
	
	HashMap<String,Double> premiumMap = new HashMap<String,Double>();
	
	@ApiModelProperty("投被保人数据")
	List<Customer> customerList;
	
	@ApiModelProperty("开票信息")
	InvoiceInfo invoiceInfo = new InvoiceInfo();
	
	@ApiModelProperty("用户代码")
	String userCode;
	
	@ApiModelProperty("代理信息")
	AgentInfoVO agentInfo = new AgentInfoVO();
	
	@ApiModelProperty("业务来源")
	String businessNatureCode;
	
	@ApiModelProperty("投保单号")
	String insureNo;
	
	@ApiModelProperty("是否代理人")
	String isAgent;
	
	@ApiModelProperty("保额")
	Double sumAmount;
	
	@ApiModelProperty("应收保费")
	Double sumPremium;
	
	@ApiModelProperty("订单编号")
	String orderNo;
	
	@ApiModelProperty("销售编号")
	String salesPlanCode;
	
	@ApiModelProperty("订单id")
	Long orderId;
	
	
	@ApiModelProperty("已选产品")
	List<ChildProduct> childProductList	;
	
	
	@ApiModelProperty("订单信息")
	Order order;
	
	@ApiModelProperty("失效时间")
	Long intervalDate;
	
	String proposalNo;
	
	
	
}
