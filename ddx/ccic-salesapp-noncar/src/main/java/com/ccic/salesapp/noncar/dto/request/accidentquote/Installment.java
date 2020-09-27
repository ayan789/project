package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class Installment {
	//分期ID
		private Long installmentId;
		//数据字典对象ID
		private Long businessObjectId;
		//保单ID
		private Long policyId;
		//JSON格式动态字段
		private String dynamicFields;
		//分期序号
		private Long installmentPeriodSeq;
		//分期金额
		private BigDecimal installmentAmount;
		//应收日期
		private Date dueDate;
		//不含税费用金额
		private BigDecimal noTaxFeeAmount;
		//分期日期
		private Date installmentDate;
		//VAT金额
		private BigDecimal vat;
		//增值税，原币种
		private BigDecimal vatOrg;
		//增值税，本币
		private BigDecimal vatLocal;
}
