package com.ccic.salesapp.noncar.service;

import com.ccic.salessapp.common.core.web.HttpResult;

public interface UserInquiryService {

	HttpResult userInquiry(String userID) throws Exception;

}
