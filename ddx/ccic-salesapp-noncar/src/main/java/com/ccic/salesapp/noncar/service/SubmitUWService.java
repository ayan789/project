package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.request.SubmitUnderWriteRequest2;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;

public interface SubmitUWService {

	Response submitUW(SubmitUnderWriteRequest2 requestBody) throws Exception;

}
