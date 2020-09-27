package com.ccic.salesapp.noncar.dto.order.response;

import java.util.List;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.order.request.Element;
import com.ccic.salesapp.noncar.dto.order.request.Product;
import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;
import com.ccic.salesapp.noncar.repository.basedb.po.Order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderResponse {

	@ApiModelProperty("元素分组列表")
	List<ElementGroup> elementGroupList;
	
	@ApiModelProperty("开始时间")
	String startDate;
	
	@ApiModelProperty("结束时间")
	String endDate;
	
	@ApiModelProperty("保障年限")
	String timeLimit;
	
	@ApiModelProperty("开票信息")
	InvoiceInfo invoiceInfo;
	
	@ApiModelProperty("方案险类")
	String planCategory;
	
	@ApiModelProperty("门店代码")
	String storeCode;
	
	@ApiModelProperty("用户代码")
	String userCode;
	
	@ApiModelProperty("代理信息")
	AgentInfoVO agentInfo;
	
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
	
	@ApiModelProperty("方案代码")
	String formulaCode;
	
	@ApiModelProperty("产品代码")
	String riskCode;
	
	@ApiModelProperty("订单编号")
	String orderNo;
	
	@ApiModelProperty("订单id")
	Long orderId;
	
	@ApiModelProperty("产品id")
	Long strategyId;
	
	@ApiModelProperty("方案编号")
	Long planId;
	
	@ApiModelProperty("方案份数")
	int formulaSize;// 方案份数
	
	@ApiModelProperty("是否暂存  1:暂存；0：提交核心")
	String isTemp;
	
	@ApiModelProperty("已选产品")
	List<Product> productList;
	
	
	@ApiModelProperty("订单信息")
	Order order;
	
	@ApiModelProperty("影像系统影像类别")
	String imgType;
	
	@ApiModelProperty("必须上传而没上传的影像列表")
	List<String> notUploadImgList;
	
}
