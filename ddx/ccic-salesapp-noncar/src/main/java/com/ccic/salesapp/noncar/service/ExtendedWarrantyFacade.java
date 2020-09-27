package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.request.PrePayMentRequestVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;

public interface ExtendedWarrantyFacade {
	
	public JsonVO prePayMent(PrePayMentRequestVO prePayMent) throws Exception;

	public JsonVO prePayMentTwo(PrePayMentRequestVO prePayMent) throws Exception;

}
