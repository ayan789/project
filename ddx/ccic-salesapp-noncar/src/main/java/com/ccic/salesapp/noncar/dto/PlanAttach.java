package com.ccic.salesapp.noncar.dto;

import lombok.Data;

@Data
public class PlanAttach {

	private String bizType;
	private Integer bizId;
	private String attachType;
	private String attachUrl;
	private String attachInUrl;
	private String attachCode;
	private String attachName;
	private Integer recordStatus;
	private String planCoverageName;

}
