package com.ccic.salesapp.noncar.dto.response;
import java.util.List;

import com.ccic.salesapp.noncar.dto.DataVO;
import com.ccic.salesapp.noncar.dto.OrderInfoVO;
/**
 * 订单 返回列表
 * @author ping
 *
 */
public class OrderListQueryResponseVO extends DataVO{

	List<OrderInfoVO> orderList;

	public List<OrderInfoVO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderInfoVO> orderList) {
		this.orderList = orderList;
	}
}
