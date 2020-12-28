package com.ccic.salesapp.noncar.dto.product.response;

import java.util.List;

import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品列表")
public class ProductListResponse {

	@ApiModelProperty("产品列表")
	List<StoreProduct> productList;
	@ApiModelProperty("总页数")	
	PageInfo pageInfo;
	
	@ApiModelProperty("今日访问量(门店首页用)")	
	Integer todayAccess;
	
	@ApiModelProperty("总计访问量(门店首页用)")	
	Integer totalAccess;
	
	@ApiModelProperty("微信号(门店首页用)")	
	String wechat;
}
