package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.request.ElectronicPolicyRequest;
import com.ccic.salesapp.noncar.dto.request.SendElectronicPolicyRequest;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.LoginUser;

public interface ElectronicPolicyService {

	HttpResult electronicPolicy(ElectronicPolicyRequest reqVO, String netType) throws Exception;

	HttpResult sendElectronicPolicy(SendElectronicPolicyRequest reqVO, LoginUser user) throws Exception;

}
