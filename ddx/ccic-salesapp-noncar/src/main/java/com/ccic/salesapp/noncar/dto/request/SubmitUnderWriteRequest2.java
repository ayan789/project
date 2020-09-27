package com.ccic.salesapp.noncar.dto.request;

import java.math.BigDecimal;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;

import lombok.Data;

@Data
public class SubmitUnderWriteRequest2 extends RequestBody {
	private String businessType;//业务单类型	
	private String businessNo;//业务单号	
	private String productCode;//产品代码
	private BigDecimal sumPremium;//总保费
	private String insuredID;//被保险人证件号码
	private String operator;//String
	private String businessAttribute;//业务属性
}
