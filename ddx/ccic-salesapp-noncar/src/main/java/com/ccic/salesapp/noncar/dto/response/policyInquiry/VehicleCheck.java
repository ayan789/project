package com.ccic.salesapp.noncar.dto.response.policyInquiry;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class VehicleCheck {
	
	private String inspectionChecker;//验车人
	private String inspectionNo;//验车码
	private String inspectionConditionCode;//验车情况
	private String inspectionDescription;//验车描述
	private String inspectionMethodCode;//验车方式
	private String inspectionResultCode;//验车结果
	private String   inspectionTime;//验车时间
	private String vehicleDamageLocationCode;//车损位置
	private String isPhotoUploadLater;//是否验车照后补
	private Integer maxPhotoDelayDays;//后补时间
	Map<String,String> extensionMap;//


}
