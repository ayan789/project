package com.ccic.salesapp.noncar.dto.request;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;




@Data
@ApiModel("删除订单request")
public class StoreOrderDeleteRequestVO {
	
	@ApiModelProperty("id")
	private List<String> id;

	@ApiModelProperty("用户ID")
	String userCode;
	
	@ApiModelProperty("新华用户标识 ")
	String channelCode;

	@ApiModelProperty("是否动态表单订单")
	String isMetaData;
}
