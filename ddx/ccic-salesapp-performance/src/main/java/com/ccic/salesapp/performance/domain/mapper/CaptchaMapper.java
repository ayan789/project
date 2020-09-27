package com.ccic.salesapp.performance.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.performance.domain.vo.dto.Captcha;

@Mapper
public interface CaptchaMapper {
	
	Captcha selectByRandom(String randomNum);
	
	void insert(Captcha captchaVO);
	
	void update(Captcha captchaVO);
}
