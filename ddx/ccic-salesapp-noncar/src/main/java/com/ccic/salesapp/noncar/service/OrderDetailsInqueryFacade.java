package com.ccic.salesapp.noncar.service;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.LoginUser;



public interface OrderDetailsInqueryFacade {

	public JsonVO OrderDetailsInquery(String orderNo,String proposalNo,String policyNo,String quotationType,LoginUser loginUser)throws Exception; 
}
