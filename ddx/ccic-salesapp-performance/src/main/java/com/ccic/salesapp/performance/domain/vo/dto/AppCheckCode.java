package com.ccic.salesapp.performance.domain.vo.dto;

import lombok.Data;

@Data
public class AppCheckCode {
	private String id; //主键
	private String userCode;//业务员代码
	private String checkCode;//验证码
	private String mobile;//手机号
	private String create_date;//创建时间
	private String update_date;//更新时间
	private String longTime;//时间戳
}
