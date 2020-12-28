package com.ccic.salesapp.noncar.dto.order.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.InvoiceInfo;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditions;
import com.ccic.salesapp.noncar.repository.basedb.po.Order;
import com.ccic.salesapp.noncar.repository.basedb.po.OrderCt;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderResponseUrl {

	@ApiModelProperty("责任条款信息")
	TermsAndConditions termsAndConditions;
	
	private String mobile;
	
	
	private String isIssueAfterPay;
	
	private String scene;
	
	
	private String title;
	
	
	private Map<String,Object> healthNotification;

}
