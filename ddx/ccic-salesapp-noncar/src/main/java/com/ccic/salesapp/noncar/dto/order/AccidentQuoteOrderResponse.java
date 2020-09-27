package com.ccic.salesapp.noncar.dto.order;

import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteResponse;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;

import lombok.Data;

@Data
public class AccidentQuoteOrderResponse {

	ResponseHead responseHead;
	
	AccidentQuoteResponse responseBody;
	
}
