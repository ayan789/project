package com.ccic.salesapp.noncar.dto.order.request;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderRequest {

	@ApiModelProperty("投保要素数据")
	public List<Element> metaDataList;
	
	@NotBlank(message="开始时间不能为空")
	@ApiModelProperty("开始时间")
	String startDate;
	
	@NotBlank(message="结束时间不能为空")
	@ApiModelProperty("结束时间")
	String endDate;
	
	@ApiModelProperty("保障年限")
	String timeLimit;
	
    @ApiModelProperty("0:无发票信息,1:个人，2：企业")
    String invoiceFlag;
	
	@ApiModelProperty("开票信息")
	InvoiceInfo invoiceInfo;
	
	@ApiModelProperty("方案险类")
	String planCategory;
	
	@ApiModelProperty("门店代码")
	String storeCode;
	
	@NotBlank(message="用户代码不能为空")
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
	
	@ApiModelProperty("份数")
	Integer formulaSize;
	
	@ApiModelProperty("产品代码")
	String riskCode;
	
	@ApiModelProperty("订单编号")
	String orderNo;
	
	@ApiModelProperty("订单编号")
	Long orderId;
	
	@NotNull(message="产品id不能为空")
	@ApiModelProperty("产品id")
	Long strategyId;
	
	@NotNull(message="方案id不能为空")
	@ApiModelProperty("方案编号")
	Long planId;
	
	@ApiModelProperty("是否暂存  1:暂存；0：提交核心")
	String isTemp;
	
	@ApiModelProperty("订单来源： 0： app内出单 ；1：  分享内容出单； 2： 分享计划书出单")
	String orderSource;
	
	@ApiModelProperty("上个保期保单号")
	String relationPolicyNo;// 上个保期保单号
	
	@ApiModelProperty("是否续保 Y:是 N：否")
	private String isRenewal;
	
	@ApiModelProperty("已选产品")
	List<Product> productList;
	
	@ApiModelProperty("非接口参数,上下文参数")
	Map<String,Object> map;
	
}
