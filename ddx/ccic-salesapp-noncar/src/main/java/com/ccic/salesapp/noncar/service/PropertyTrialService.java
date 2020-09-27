package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.PropertyTrialRequest;
import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;

public interface PropertyTrialService {

	Response propertyTrial(PropertyTrialRequest requestBody, String orderNo, HttpResult result, UserVO userVO,
			AgentInfoVO agentInfoVO) throws Exception;

}
