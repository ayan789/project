package com.ccic.salesapp.performance.domain.vo.request;

import lombok.Data;

/**
 * 常用代理人
 */
@Data
public class CommonAgentVO{

	private String id;
	private String staffCode;// 员工编号
	private String bussinessSource;// 业务来源
	private String agentCode;// 代理人代码
	private String agentName;// 代理人姓名
	private String userCode;

}
