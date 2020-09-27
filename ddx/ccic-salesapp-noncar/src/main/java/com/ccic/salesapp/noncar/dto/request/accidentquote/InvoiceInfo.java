package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;

@Data
public class InvoiceInfo {
		//发票类型
		private String invoiceType;
		//开票公司信息(同投保人/同被保人/第三方)
		private String copyDataFromType;
		//开户行
		private String bankCode;
		//开户行账号
		private String accountNo;
		//公司名称
		private String companyName;
		//纳税人类型
		private String taxPayerType;
		//纳税人识别号
		private String taxPayerNo;
		//税务登记地址
		private String taxRegisterAddress;
		//客户类型
		private String customerType;
		private String taxRegisterTel;//税务登记电话
		private String email;//邮箱地址
		private String mobile;//手机号码
		
		private String depositeBankName;// 开户行名称代码
		private Map<String, String> tempData;// 临时数据
		private Long sequenceNumber;//序号
}
