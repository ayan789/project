package com.ccic.salesapp.noncar.service;

import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.LoginUser;

public interface NewcoreSubmitUWService {

	HttpResult submitUW(String orderNo, String proposalNo, String riskCode, LoginUser loginUser) throws Exception;

}
