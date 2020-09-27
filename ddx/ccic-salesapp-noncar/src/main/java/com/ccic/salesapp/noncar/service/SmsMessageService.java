package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.request.SendMessageRequest;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.LoginUser;

public interface SmsMessageService {

	HttpResult sendMessage(SendMessageRequest request, LoginUser user);

}
