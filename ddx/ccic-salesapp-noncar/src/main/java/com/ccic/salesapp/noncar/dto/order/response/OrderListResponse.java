package com.ccic.salesapp.noncar.dto.order.response;

import java.util.List;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.order.request.Element;
import com.ccic.salesapp.noncar.dto.order.request.Product;
import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;
import com.ccic.salesapp.noncar.repository.basedb.po.OrderCt;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderListResponse {

	@ApiModelProperty("订单列表")
	List<StoreInsureInfo> orderList;
	
	int page;
	
	int size;
	
	@ApiModelProperty("分页信息")
	PageInfo pageInfo;
}
