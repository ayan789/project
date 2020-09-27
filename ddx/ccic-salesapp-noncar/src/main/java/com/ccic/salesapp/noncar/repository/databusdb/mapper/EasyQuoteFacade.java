package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import com.ccic.salesapp.noncar.dto.request.EasyQuoteReqVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;


public interface EasyQuoteFacade {

	public abstract JsonVO easyQuote(EasyQuoteReqVO easyQuoteReqVO);

}