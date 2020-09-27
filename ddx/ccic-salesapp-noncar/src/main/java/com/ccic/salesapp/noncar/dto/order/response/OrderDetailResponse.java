package com.ccic.salesapp.noncar.dto.order.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.InvoiceInfo;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditions;
import com.ccic.salesapp.noncar.repository.basedb.po.Order;
import com.ccic.salesapp.noncar.repository.basedb.po.OrderCt;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderDetailResponse {

	@ApiModelProperty("元素分组列表")
	List<ElementGroup> elementList;
	
	@ApiModelProperty("产品名称")
	String productName;
	
	@ApiModelProperty("开始时间")
	String startDate;
	
	@ApiModelProperty("结束时间")
	String endDate;
	
	@ApiModelProperty("保障年限")
	String timeLimit;
	
    @ApiModelProperty("0:无发票信息,1:个人，2：企业")
    String invoiceFlag;
	
	@ApiModelProperty("开票信息")
	InvoiceInfo invoiceInfo = new InvoiceInfo();
	
	@ApiModelProperty("方案险类")
	String planCategory;
	
	@ApiModelProperty("门店代码")
	String storeCode;
	
	@ApiModelProperty("用户代码")
	String userCode;
	
	@ApiModelProperty("代理信息")
	AgentInfoVO agentInfo = new AgentInfoVO();
	
	@ApiModelProperty("渠道代码")
	String businessNatureCode;
	@ApiModelProperty("渠道名称")
	String  businessNatureName;
	
	@ApiModelProperty("投保单号")
	String insureNo;
	
	@ApiModelProperty("是否代理人")
	String isAgent;
	
	@ApiModelProperty("保额")
	BigDecimal sumAmount;
	
	@ApiModelProperty("应收保费")
	BigDecimal sumPremium;
	
	@ApiModelProperty("方案代码")
	String formulaCode;
	
	@ApiModelProperty("产品代码")
	String riskCode;
	
	@ApiModelProperty("订单编号")
	String orderNo;
	
	@ApiModelProperty("产品id")
	Long strategyId;
	
	@ApiModelProperty("方案编号")
	Long planId;
	
	@ApiModelProperty("方案份数")
	int formulaSize;// 方案份数
	
	@ApiModelProperty("是否暂存  1:暂存；0：提交核心")
	String isTemp;
	
	@ApiModelProperty("已选产品")
	List<OrderCt> productList;
	
	@ApiModelProperty("订单")
	Order order;
	
	@ApiModelProperty("未上传的影像列表")
	List<String> notUploadImgList;
	
	@ApiModelProperty("责任条款信息")
	TermsAndConditions termsAndConditions;
	
	@ApiModelProperty("订单过期剩余毫秒数")
	Long intervalDate;
	
	@ApiModelProperty("上架方案数量")
	private Integer onlinePlanNum;
	
	@ApiModelProperty("非接口参数,上下文参数")
	Map<String,Object> map;
	
	
	
}
