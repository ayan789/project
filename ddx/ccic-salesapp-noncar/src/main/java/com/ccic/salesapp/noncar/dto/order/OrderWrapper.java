package com.ccic.salesapp.noncar.dto.order;

import java.util.List;

import com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer;
import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.InvoiceInfo;
import com.ccic.salesapp.noncar.repository.basedb.po.Order;
import com.ccic.salesapp.noncar.repository.basedb.po.OrderCt;

import lombok.Data;

@Data
public class OrderWrapper {

	Order order;
	
	List<OrderCt> orderCtList;
	
	List<PolicyCustomer> customerList;
	
	AgentInfoVO agentInfo;
	
	InvoiceInfo invoiceInfo;
}
