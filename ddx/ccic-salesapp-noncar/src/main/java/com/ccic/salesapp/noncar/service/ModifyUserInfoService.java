package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.AppCheckCode;
import com.ccic.salesapp.noncar.dto.request.SendSmsRequestVo;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salessapp.common.core.web.HttpResult;


public interface ModifyUserInfoService {

	public AppCheckCode getCheckCode(String userCode);
	public HttpResult sendsimplemessageservicerest(SendSmsRequestVo smsRequest, String templat);

}
