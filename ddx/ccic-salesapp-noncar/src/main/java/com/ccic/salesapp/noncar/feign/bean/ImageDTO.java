package com.ccic.salesapp.noncar.feign.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImageDTO implements Serializable {
	
	private String userCode;
	private String comCode; //机构代码
	private String classCode;//险种
	private String bussNo; //业务编号
	private String busiDate; //批次年份
	private String imgType; //业务类型：CJXS-客户管理，UW_D-车险，UW_W-健康，UW_Y-货物运输
	private String flag; //0 内网 1外网
	
}
