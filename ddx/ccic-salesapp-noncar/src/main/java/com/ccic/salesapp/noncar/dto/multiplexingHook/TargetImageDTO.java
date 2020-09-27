package com.ccic.salesapp.noncar.dto.multiplexingHook;

import lombok.Data;

@Data
public class TargetImageDTO {

	private String appCode;//业务类型
	private String classCode;//险类(有险类为必填)
	private String businessNo;//业务编号
	private String businessDate;//格式YYYYMMDD
}
