package com.ccic.salesapp.noncar.dto.store.response;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewsResponse {
	@ApiModelProperty("资讯列表")
	List<HashMap<String, Object>> storeNewsList;
	@ApiModelProperty("分页信息")	
	PageInfo pageInfo;
}
