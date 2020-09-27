package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

@Data
public class Endorsement {
	
	private String endoNo;//批改号
	private String applyName;//批改申请人
	private String applyDate;//批改申请日期
	private String endoEffectiveDate;//批改生效日期
	private String endoTypeName;//批单类型名称
	private String endoType;//批单类型
	private String endoStatusDesc;//批单状态描述
	private String endoStatus;//批单状态
	private String stopTravelStartDate;//停驶起期
	private String stopTravelEndDate;//ͣʻ停驶止期


}
