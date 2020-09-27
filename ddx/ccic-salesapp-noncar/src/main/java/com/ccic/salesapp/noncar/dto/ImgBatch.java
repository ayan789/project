package com.ccic.salesapp.noncar.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
@Data
@ApiModel(value = "ImgBatch", description = "文件相关接口请求bean")
public class ImgBatch {

	private String comCode;
	private String operator;
	private String operatorName;
	private String operatorRole;
	private String insurNo;
	private String appCode;
	private String classCode;
	private String businessNo;
	private String pageIds;
	private String createUser;
	private String createTime;
	private String updateTime;
}

