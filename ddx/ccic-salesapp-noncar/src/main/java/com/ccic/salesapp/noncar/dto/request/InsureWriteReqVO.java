package com.ccic.salesapp.noncar.dto.request;
import lombok.Data;

@Data
public class InsureWriteReqVO  {

	/*投保人信息*/
	private String applicantName;//投保人姓名
	private String email;//邮箱
	private String mobileNo;//手机号
	private String birthDate ;//出生日期
	private String applicantidentifyType ;//证件类型
	private String applicantidentifyNumber ;//证件号码

	/*被保人信息*/
	private String benefitPolHolderRelaCode;//与投保人关系
	private String insuredName;//被保人姓名
	private String insuredidentifyType ;//证件类型
	private String insured_identify ;//证件号码
	private String insuredmobileNo;//手机号
	private String insuredbirthDate ;//出生日期
	private String socialType ;//社保类型
	

	private String securityInfo;//保障期限

	/*开票信息*/
	private String isInvoice;//是否开票

	private String other;//其他字段






}
