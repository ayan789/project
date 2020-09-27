package com.ccic.salesapp.performance.domain.vo.request;

import lombok.Data;

@Data
public class SendSmsRequest {
	private String userId;
	private String mobile;
	private String comcode;
}
