package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class VehicleTax {

	private String    taxPayerNo;//纳税人识别号
	private String    taxPayerName;//纳税人名称
	private String    vehicleAddress;//车辆落户地址
	private String    computerCode;//微机编码
	private String    postCode;//邮政编码
	private BigDecimal curYearTax;//当年缴纳税款
	private BigDecimal deductibleRate;//减免比例
	private String    deductibleReasonCode;//减免税原因
	private String    deductibleSchemeCode;//减免税方案
	private Long      lastTaxYear;//上次缴税年度
	private BigDecimal lateFee;//滞纳金
	private Date      lateFeeStartDate;//滞纳金开始计算日期不含当日
	private String    mailingAddress;//通讯地址
	private String    tel;//联系电话
	private BigDecimal previousYearsTax;//往年补缴税款
	private String    rejectPayReasonCode;//拒缴原因
	private String    residenceAddress;//居住地址/注册地址
	private BigDecimal standardTax;//标准税款
	private String    streetTownCode;//街道乡镇代码
	private String    taxCertificateNo;//完税/减免税凭证号
	private String    taxNo;//税票号
	private String    taxNoType;//税票号码类型
	private String    taxEndDate;//税款所属期
	private String    taxStartDate;//计税起始日期
	private String    taxType;//纳税类型代码
	private String    taxTypeName;//纳税类型名称
	private String    taxVoucherIssuerName;//开具完税/减免凭证的税务机关名称
	private BigDecimal deductibleAmount;//减免金额
	private BigDecimal totalTax;//车船税总金额
	private String rejectPayReasonTxt;//拒缴原因

	
	


}
