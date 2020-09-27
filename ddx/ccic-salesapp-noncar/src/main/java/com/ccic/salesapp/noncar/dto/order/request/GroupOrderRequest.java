package com.ccic.salesapp.noncar.dto.order.request;

import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.order.ChildProduct;
import com.ccic.salesapp.noncar.dto.order.Customer;
import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupOrderRequest  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("开始时间")
	String startDate;
	
	@ApiModelProperty("结束时间")
	String endDate;
	
	@ApiModelProperty("销售方案id")
	Integer salesPlanId;
	
	@ApiModelProperty("销售方案代码")
	String salesPlanCode;
	
	@ApiModelProperty("组合方案代码")
	String groupPlanCode;
	
	@ApiModelProperty("组合产品代码")
	String groupProductName;
	
	@ApiModelProperty("投被保人数据")
	List<Customer> customerList;
	
    @ApiModelProperty("0:无发票信息,1:个人，2：企业")
    String invoiceFlag;
	
	@ApiModelProperty("开票信息")
	InvoiceInfo invoiceInfo;
	
	@NotBlank(message="用户代码不能为空")
	@ApiModelProperty("用户代码")
	String userCode;
	
	@ApiModelProperty("代理信息")
	AgentInfoVO agentInfo;
	
	@ApiModelProperty("业务来源代码")
	String businessNatureCode;
	
	@ApiModelProperty("业务来源名称")
	String businessNatureName;
	
	@ApiModelProperty("投保单号")
	String insureNo;
	
	@ApiModelProperty("是否代理人")
	String isAgent;
	
	@ApiModelProperty("保额")
	Double sumAmount;
	
	@ApiModelProperty("应收保费")
	Double sumPremium;
	
	@ApiModelProperty("折前保费")
	Double premiumBeforeDiscount;
	
	@ApiModelProperty("份数")
	Integer numberOfCopys;
	
	@ApiModelProperty("订单编号")
	String orderNo;
	
	@ApiModelProperty("订单编号")
	Long orderId;
	
	@ApiModelProperty("试算：price;暂存 temp;提交到销售中台 order; 新建计划书:plan")
	String action;
	
	@ApiModelProperty("订单来源： 0： app内出单 ；1：  分享内容出单； 2： 分享计划书出单")
	String orderSource;
	
	@ApiModelProperty("上个保期保单号")
	String relationPolicyNo;// 上个保期保单号
	
	@ApiModelProperty("是否续保 Y:是 N：否")
	private String isRenewal;
	
	@ApiModelProperty("已选产品")
	List<ChildProduct> childProductList	;
	
	HashMap<String,Object> map = new HashMap<String,Object>();
	
}
