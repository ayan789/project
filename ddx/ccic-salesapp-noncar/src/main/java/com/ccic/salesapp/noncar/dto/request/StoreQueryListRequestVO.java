package com.ccic.salesapp.noncar.dto.request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;




@Data
@ApiModel("门店订单列表查询request")
public class StoreQueryListRequestVO{                  

	@ApiModelProperty("用户ID")
	private String userCode;
	
	@ApiModelProperty("保险类别")
	private String insuranceType;				

	@ApiModelProperty("订单状态")
	private String status;                     

	@ApiModelProperty("保单起期")
	private String startDate;                	

	@ApiModelProperty("保单止期")
	private String endDate;                 	

	@ApiModelProperty("创建日期")
	private String currentDate;
	
	@ApiModelProperty("险种代码")
	private String insuranceCode;        		

	@ApiModelProperty("投保单号")
	private String insureNo;                	

	@ApiModelProperty("投保人姓名")
	private String applicantName;         		

	@ApiModelProperty("日期筛选查询ID")
	private String dateRange;

	@ApiModelProperty("保费筛选查询ID")
	private String amountRange;

	@ApiModelProperty("日期筛选起期")
	private String orderStrtDate;

	@ApiModelProperty("日期筛选止期")
	private String orderEndDate;

	@ApiModelProperty("最低保费")
	private String minPremium;

	@ApiModelProperty("最高保费")
	private String maxPremium;

	@ApiModelProperty("出单来源")
	private String runappID;
	
	@ApiModelProperty("当前页")
	private Integer page=0;
	
	@ApiModelProperty("每页数量")
	private Integer size=0;
	
	@ApiModelProperty("模糊查询关键词")
	private String keyword;
	
}
