package com.ccic.salesapp.performance.domain.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("代理人业绩")
public class AgentPerformanceResponse {
	
	@ApiModelProperty("昨日实收总保费")
	String total_d = "0";
	@ApiModelProperty("昨日实收车险保费")
	String car_d  = "0";
	@ApiModelProperty("昨日实收财产险保费")
	String nocar_d = "0";
	@ApiModelProperty("昨日实收意外险保费")
	String acc_d = "0";
	@ApiModelProperty("昨日实收健康险保费")
	String hea_d = "0";
	@ApiModelProperty("昨日实收总件数")
	String num_total_d = "0";
	@ApiModelProperty("昨日实收车险件数")
	String num_car_d = "0";
	@ApiModelProperty("昨日实收财产险件数")
	String num_nocar_d = "0";
	@ApiModelProperty("昨日实收意外险件数")
	String num_acc_d = "0";
	@ApiModelProperty("昨日实收健康险件数")
	String num_hea_d = "0";
	
	@ApiModelProperty("本月实收总保费")
	String total_m = "0";
	@ApiModelProperty("本月实收车险保费")
	String car_m = "0";
	@ApiModelProperty("本月实收财产险保费")
	String nocar_m = "0";
	@ApiModelProperty("本月实收意外险保费")
	String acc_m = "0";
	@ApiModelProperty("本月实收健康险保费")
	String hea_m = "0";
	@ApiModelProperty("本月实收总件数")
	String num_total_m = "0";
	@ApiModelProperty("本月实收车险件数")
	String num_car_m = "0";
	@ApiModelProperty("本月实收财产险件数")
	String num_nocar_m = "0";
	@ApiModelProperty("本月实收意外险件数")
	String num_acc_m = "0";
	@ApiModelProperty("本月实收健康险件数")
	String num_hea_m = "0";
	
	@ApiModelProperty("本季实收总保费")
	String total_q = "0";
	@ApiModelProperty("本季实收车险保费")
	String car_q = "0";
	@ApiModelProperty("本季实收财产险保费")
	String nocar_q = "0";
	@ApiModelProperty("本季实收意外险保费")
	String acc_q = "0";
	@ApiModelProperty("本季实收健康险保费")
	String hea_q = "0";
	@ApiModelProperty("本季实收总件数")
	String num_total_q = "0";
	@ApiModelProperty("本季实收车险件数")
	String num_car_q = "0";
	@ApiModelProperty("本季实收财产险件数")
	String num_nocar_q = "0";
	@ApiModelProperty("本季实收意外险件数")
	String num_acc_q = "0";
	@ApiModelProperty("本季实收健康险件数")
	String num_hea_q = "0";
	
	@ApiModelProperty("本年实收总保费")
	String total_y = "0";
	@ApiModelProperty("本年实收车险保费")
	String car_y = "0";
	@ApiModelProperty("本年实收财产险保费")
	String nocar_y = "0";
	@ApiModelProperty("本年实收意外险保费")
	String acc_y = "0";
	@ApiModelProperty("本年实收健康险保费")
	String hea_y = "0";
	@ApiModelProperty("本年实收总件数")
	String num_total_y = "0";
	@ApiModelProperty("本年实收车险件数")
	String num_car_y = "0";
	@ApiModelProperty("本年实收财产险件数")
	String num_nocar_y = "0";
	@ApiModelProperty("本年实收意外险件数")
	String num_acc_y = "0";
	@ApiModelProperty("本年实收健康险件数")
	String num_hea_y = "0";
	
}
