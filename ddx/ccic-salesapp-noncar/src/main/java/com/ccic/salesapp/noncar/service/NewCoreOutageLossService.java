package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import com.ccic.salessapp.common.core.web.HttpResult;

public interface NewCoreOutageLossService {

	HttpResult placeOrder(StoreOracleRequestVO reqBody);

}
