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
}
