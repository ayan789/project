package com.ccic.salesapp.noncar.dto.request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;




@Data
@ApiModel("订单详情查询request")
public class StoreQueryDetailReqVO{
		

	@ApiModelProperty("投保单号")
	private String insureNo;                	


	
}
