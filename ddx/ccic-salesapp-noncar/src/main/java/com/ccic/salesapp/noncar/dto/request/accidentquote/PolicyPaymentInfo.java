package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.List;

import lombok.Data;
@Data
public class PolicyPaymentInfo {
	private String accountHolderName;//银行账户持有人姓名
	private String accountNo;//银行账号
	private String payModeCode;//支付方式
	private String accountType;//账户性质
	private String regionCode;//银行所属省市区
	private String bankCode;//银行名称
	private String openBankCode;//开户行名称
	private String isReceiveSms;//是否接受我公司短信
	private String tel;//手机号
	private String installmentPeriodCount;//	分期数
	private List<Installment> installmentList;
}
