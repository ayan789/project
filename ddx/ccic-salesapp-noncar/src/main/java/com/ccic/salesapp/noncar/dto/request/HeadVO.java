package com.ccic.salesapp.noncar.dto.request;


import com.ccic.salessapp.common.request.LoginUser;
import lombok.Data;

@Data
public class HeadVO  {
	private String function="";
	private String method="";
	private String deviceType="";
	
	private String userCode;//工号
	private String timestamp;//时间戳
	private String sign;//签名 sign=hex(sha-256(function+method+userCode+timestamp+token))
	private String tokenIdx;//token序号
	
	private String requestIP;//ip地址
	private LoginUser loginUser;//登陆用户信息

}
