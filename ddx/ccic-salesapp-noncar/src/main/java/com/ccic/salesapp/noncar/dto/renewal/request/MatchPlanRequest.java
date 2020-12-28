package com.ccic.salesapp.noncar.dto.renewal.request;

import java.util.List;

import com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured;

import lombok.Data;

@Data
public class MatchPlanRequest {

	//被保人信息
	List<PersonInsured> personInsured;
	//续保产品代码
	String productCode;
	
}
