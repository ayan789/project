package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.AgentQueryReqVO;
import com.ccic.salessapp.common.core.web.HttpResult;

public interface AgentQueryService {

	HttpResult agentQuery(AgentQueryReqVO info) throws Exception;

}
