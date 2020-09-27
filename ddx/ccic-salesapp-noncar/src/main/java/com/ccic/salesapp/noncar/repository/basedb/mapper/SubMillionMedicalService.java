package com.ccic.salesapp.noncar.repository.basedb.mapper;

import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import com.ccic.salessapp.common.core.web.HttpResult;

public interface SubMillionMedicalService {

	HttpResult subMillionMedical(StoreOracleRequestVO requestVo) throws Exception;

}
