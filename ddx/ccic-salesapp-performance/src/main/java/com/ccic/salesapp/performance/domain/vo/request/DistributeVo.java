package com.ccic.salesapp.performance.domain.vo.request;

import java.math.BigDecimal;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel("总保费自动分配")
public class DistributeVo {
	@ApiModelProperty("业务员工号")
	private String usercode;
	@ApiModelProperty("年总保费")
	private int insurePremium ;
	@ApiModelProperty("年车险总保费")
	private int carInsurePremium;
	@ApiModelProperty("年意外险总保费")
	private int accInsurePremium ;
	@ApiModelProperty("年健康险总保费")
	private int heaInsurePremium ;
	@ApiModelProperty("年财产险总保费")
	private int propInsurePremium ;
}
