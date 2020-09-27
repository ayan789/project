package com.ccic.salesapp.performance.domain.vo.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel("查询责任书列表,领导签名图片查询,获取责任书目标值")
public class SalesResponsibilityVo {
	@ApiModelProperty("主键")
	private String id;
	
	@ApiModelProperty("业务员工号:签署人")
	private String userCode;
	
	@ApiModelProperty("责任书名称")
	private String responsibilityName;
	
	@ApiModelProperty("责任书类型")
	private String responsibilityType;
	
	@ApiModelProperty("责任书内容")
	private String responsibilityNote;
	
	@ApiModelProperty("车险保费    （年目标）")
	private BigDecimal carInsurePremium;
	
	@ApiModelProperty("意外险保费（年目标）")
	private BigDecimal accInsurePremium;
	
	@ApiModelProperty("健康险保费（年目标）")
	private BigDecimal heaInsurePremium;
	
	@ApiModelProperty("财产险保费（年目标）")
	private BigDecimal propInsurePremium;
	
	@ApiModelProperty("一月总保费（月目标）")
	private String janTotalPremium;
	
	@ApiModelProperty("二月总保费（月目标）")
	private String febTotalPremium;

	@ApiModelProperty("三月总保费（月目标）")
	private String marTotalPremium;
	
	@ApiModelProperty("四月总保费（月目标）")
	private String aprTotalPremium;
	
	@ApiModelProperty("五月总保费（月目标）")
	private String mayTotalPremium;
	
	@NotBlank(message = "六月总保费不能为空")
	@ApiModelProperty("六月总保费（月目标）")
	private String junTotalPremium;
	
	@ApiModelProperty("七月总保费（月目标）")
	private String julTotalPremium;
	
	@ApiModelProperty("八月总保费（月目标）")
	private String augTotalPremium;
	
	@ApiModelProperty("九月总保费（月目标）")
	private String sepTotalPremium;
	
	@ApiModelProperty("十月总保费（月目标）")
	private String octTotalPremium;
	
	@ApiModelProperty("十一月总保费（月目标）")
	private String novTotalPremium;
	
	@ApiModelProperty("十二月总保费（月目标）")
	private String decTotalPremium;
	
	@ApiModelProperty("年度")
	private String isYear;
	
	@ApiModelProperty("分页")
	PageVO page;
}
