package com.ccic.salesapp.performance.domain.vo.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Captcha {
	private BigDecimal id;
	private String randomNum;	//随机数
	private String captcha;		//验证码
	private int status;			//状态
}
