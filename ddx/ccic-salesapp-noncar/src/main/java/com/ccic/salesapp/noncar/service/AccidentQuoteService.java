package com.ccic.salesapp.noncar.service;

import java.util.List;

import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteRequest;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;

public interface AccidentQuoteService {

	Response accidentQuote(RequestBody requestBody,String bussNo, HttpResult result, UserVO userVO) throws Exception;
	/**
	 * 调用ESB接口查询保单信息
	 * @param PolicyNo
	 * @return
	 * @throws Exception
	 */
	AccidentQuoteRequest getHistoryPolicyInfo(String PolicyNo) throws Exception;
	/**
	 * 判定给定被保人是否在给定的被保人列表中
	 * @param personInsured
	 * @param personInsuredList
	 * @return
	 */
	boolean isNewInsured(PersonInsured personInsured, List<PersonInsured> personInsuredList);

}
