package com.ccic.salesapp.noncar.dto.renewal.response;

import java.util.List;

import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;

import lombok.Data;

@Data
public class ClaimQueryResponse extends ResponseBody {
	 private String damageTimes;
	 private List<AccidentDTO> accident;
}
