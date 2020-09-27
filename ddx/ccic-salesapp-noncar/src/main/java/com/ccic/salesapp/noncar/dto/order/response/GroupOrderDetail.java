package com.ccic.salesapp.noncar.dto.order.response;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class GroupOrderDetail extends GroupOrderResponse {
	
	List<Map<String,String>> insureNos;
	
}
