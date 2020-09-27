package com.ccic.salessapp.common.request;

import lombok.Data;

/**
 * 登陆用户
 *
 * */
@Data
public class LoginUser {

	private String userCode;//用户代码
	private String userName;//用户名
	private String comCode;//归属机构代码
	private String comName;//归属机构名称
	private String channelCode;//外部渠道代码
}
