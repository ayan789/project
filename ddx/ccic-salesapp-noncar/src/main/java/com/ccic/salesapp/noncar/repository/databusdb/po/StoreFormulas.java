package com.ccic.salesapp.noncar.repository.databusdb.po;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("产品方案")
@Data
public class StoreFormulas {

	@ApiModelProperty("方案代码")
	private String formulaCode;// 方案代码
	@ApiModelProperty("方案名称")
	private String formulaName;// 方案名称
	@ApiModelProperty("方案保额")
	private String amount;// 方案保额
	@ApiModelProperty("方案保费")
	private String premium;// 方案保费
	@ApiModelProperty("上限数量")
	private String limQuantity;// 上限数量
	@ApiModelProperty("险种代码")
	private String riskCode;// 险种代码
	@ApiModelProperty("机构代码")
	private String comCode;// 机构代码
	@ApiModelProperty("产品名称")
	private String productName;// 产品名称
	@ApiModelProperty("产品代码")
	private String productCode;// 产品代码
	@ApiModelProperty("保险期限")
	private String timeLimit;// 保险期限
	@ApiModelProperty("储存多个保额JSON数据")
	private String amountsJson;// 储存多个保额JSON数据
	@ApiModelProperty("储存多个保费JSON数据")
	private String premiumsJson;// 储存多个保费JSON数据
	@ApiModelProperty("JSON扩展")
	private String extensionJson;// JSON扩展
	@ApiModelProperty("每个方案的保障内容")
	private String amountInfoJson;// 每个方案的保障内容
	@ApiModelProperty("新核心标识 1新核心 0老核心")
	private String newCore; // 新核心标识 1新核心 0老核心
	@ApiModelProperty("是否有社保")
	private String isHaveSheBao;// 是否有社保-针对2020款百万医疗个人版方案
	@ApiModelProperty("保障内容")
	private String addRiskAmountJson;
	@ApiModelProperty("")
	private String addRiskPremiumsJson;
	
	@ApiModelProperty("见费出单标志 ：1:见费出单；0：非见费出单")
	private String isIssueAfterPay;

}
