package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

@Data
public class InvoiceInfo {
	private String invoiceType;//发票类型
	private String bankCode;//开户行
	private String accountNo;//开户行账号
	private String companyName;//公司名称
	private String mobile;//手机号码
	private String taxPayerType;//纳税人类型
	private String taxPayerNo;//纳税人识别号
	private String taxRegisterTel;//税务登记电话
	private String taxRegisterAddress;//税务登记地址
	private String customerType;//客户类型
	private String email;//邮件地址
}
