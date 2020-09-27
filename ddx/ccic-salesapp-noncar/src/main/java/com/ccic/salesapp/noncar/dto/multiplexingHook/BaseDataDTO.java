package com.ccic.salesapp.noncar.dto.multiplexingHook;

import lombok.Data;

@Data
public class BaseDataDTO {

	private String comCode;//机构号码
	private String operator;//操作员ID
	private String operatorRole;//操作角色
}
