package com.ccic.salesapp.noncar.dto;

import lombok.Data;
import java.util.List;

import com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer;

@Data
public class PaymentInformationVo {
	
	private String proposalNo;//投保单号
	private String productName;//产品信息
	private String applicantName;//投保人
	private String paymentNo;//支付号
	private String checkNo; //校验码
	private String sumPremium; //保费
	private List<PolicyCustomer> insureList; //被保人
	private String mobile; //电话
}
