package com.ccic.salesapp.performance.domain.vo.response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Data
@ApiModel("销售行动力评估")
public class AssessResponse {
	

	@ApiModelProperty("人均产能")
	public String capaCity="0.00";
	
	@ApiModelProperty("客户数")
	public String customerTotal="0.00";
	
	@ApiModelProperty("月0保费天数")
	public String totalZeroPremiumDay="0.00";
	
	@ApiModelProperty("赔付率")
	public String lossRatio="0.00";
	
	@ApiModelProperty("续保率")
	public String renewalRatio="0.00";
	
	@ApiModelProperty("非车业务占比")
	public String noncarPremiumRatio="0.00";
	
	@ApiModelProperty("人均产能-对标数据")
	public String capacityStandard="0.00";
	
	@ApiModelProperty("客户数-对标数据")
	public String customerTotalStandard="0.00";
	
	@ApiModelProperty("月0保费天数-对标数据")
	public String totalZeroPremiumDayStandard="0.00";
	
	@ApiModelProperty("赔付率-对标数据")
	public String lossRatioStandard="0.00";
	
	@ApiModelProperty("续保率-对标数据")
	public String renewalRatioStandard="0.00";
	
	@ApiModelProperty("非车业务占比-对标数据")
	public String nocarPremiumRatioStandard="0.00";
	
}
