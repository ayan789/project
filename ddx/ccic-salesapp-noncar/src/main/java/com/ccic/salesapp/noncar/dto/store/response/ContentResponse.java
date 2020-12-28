package com.ccic.salesapp.noncar.dto.store.response;

import java.util.List;

import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.ccic.salesapp.noncar.repository.basedb.po.Content;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("门店资讯查询响应")
public class ContentResponse {
	
	@ApiModelProperty("资讯列表")
	List<Content> contentList;
	@ApiModelProperty("分页信息")	
	PageInfo pageInfo;
}
