package com.ccic.salesapp.noncar.dto.response.policyInquiry;


import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;
import lombok.Data;

import java.util.Date;

@Data
public class PolicyInquiryRequest extends RequestBody {

	private String policyNo;//	保单号
	private String businessAttribute;//	业务属性
	private String channelSource;//	渠道来源
	private String rollBackDate;//	回滚时间
	private String queryType;//查询类型
	
}
