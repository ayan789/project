package com.ccic.salesapp.noncar.dto.store.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("门店资讯查询")
public class ContentRequest {
	
	@ApiModelProperty("门店代码")
	private String storeCode;
	
	@ApiModelProperty("当前页")
	private Integer page = 1;
	
	@ApiModelProperty("每页数量")
	private Integer size = 10;
	
	@ApiModelProperty("")
	private String keyWord;
	
	@ApiModelProperty("1:在门店中,null:全部产品")
	private String isInStore;
	
	@ApiModelProperty("1:分页; 其他:不分页；该参数目前仅对门店查询有效")
	private String isQueryByPage;
}
