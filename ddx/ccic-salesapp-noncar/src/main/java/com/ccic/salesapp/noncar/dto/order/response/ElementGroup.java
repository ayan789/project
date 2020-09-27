package com.ccic.salesapp.noncar.dto.order.response;

import java.util.List;

import com.ccic.salesapp.noncar.repository.basedb.po.OrderDetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("元素分组")
public class ElementGroup {

	@ApiModelProperty("分组名称")
	String groupName;
	
	@ApiModelProperty("元素列表")
	List<OrderDetail> elementList;
	
}
