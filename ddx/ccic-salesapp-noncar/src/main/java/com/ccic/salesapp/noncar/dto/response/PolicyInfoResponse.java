package com.ccic.salesapp.noncar.dto.response;

import lombok.Data;

import java.io.Serializable;

import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;

@Data
public class PolicyInfoResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.ccic.salesapp.noncar.dto.response.ResponseBody responseBody;
	
	private ResponseHead responseHead;
}
