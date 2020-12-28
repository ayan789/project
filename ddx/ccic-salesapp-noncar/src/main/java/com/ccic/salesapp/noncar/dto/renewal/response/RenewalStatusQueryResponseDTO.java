package com.ccic.salesapp.noncar.dto.renewal.response;

import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;

import lombok.Data;

@Data
public class RenewalStatusQueryResponseDTO extends ResponseBody {
	//是否可续保 1是0否
	String renewalStatus;
	//是否已续保 1是0否
	String isRenewal;
	
}
