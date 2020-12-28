package com.ccic.salesapp.noncar.dto.product.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品列表查询")
public class ProductListRequest {

	@ApiModelProperty("门店代码")
	private String storeCode;	
	
	@ApiModelProperty("用户代码")
	private String userCode;
	
	@ApiModelProperty("机构代码")
	private String comCode;
	
	@ApiModelProperty("渠道代码")
	private String channelCode;
	
	@ApiModelProperty("产品类型")
	private String  productType;

	@ApiModelProperty("当前页")
	private Integer page = 1;
	
	@ApiModelProperty("每页数量")
	private Integer size = 10;
	
	@ApiModelProperty("")
	private String needPayComCode;
	
	@ApiModelProperty("")
	private String productRisk;
	
	@ApiModelProperty("")
	private String keyWord;
	
	@ApiModelProperty("1:在门店中,null:全部产品")
	private String isInStore;
	
	@ApiModelProperty("1:分页; 其他:不分页；该参数目前仅对门店查询有效")
	private String isQueryByPage;
	
	@ApiModelProperty("适用人群")
	private List<String> peopleList;
	
	@ApiModelProperty("排序字段")
	private String orderBy;
	
	@ApiModelProperty("排序方式：asc desc")
	private String asc;
	
	@ApiModelProperty("用户类型标识：1 :新华用户")
	private String platformFlag;

	@ApiModelProperty("微信openId")
	private String openId;
}
