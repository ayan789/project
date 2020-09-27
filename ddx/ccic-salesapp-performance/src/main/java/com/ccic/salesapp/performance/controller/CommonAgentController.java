package com.ccic.salesapp.performance.controller;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.performance.domain.vo.dto.QueryPolicyVo;
import com.ccic.salesapp.performance.domain.vo.request.BillPackToFKRequest;
import com.ccic.salesapp.performance.domain.vo.request.CommonAgentVO;
import com.ccic.salesapp.performance.domain.vo.request.FeePayDetailRequest;
import com.ccic.salesapp.performance.domain.vo.request.PlanFeeDetailInquiryRequestVO;
import com.ccic.salesapp.performance.domain.vo.request.PlanFeeInquiryRequestVO;
import com.ccic.salesapp.performance.domain.vo.request.QueryWithdrawalVo;
import com.ccic.salesapp.performance.domain.vo.request.UpdateConfigRequest;
import com.ccic.salesapp.performance.service.CommonAgentService;
import com.ccic.salessapp.common.core.web.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(tags = "佣金、手续费提现", description = "佣金、手续费提现", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "commonAgent")
public class CommonAgentController {

	@Autowired
	CommonAgentService commonAgentService;

	@Autowired
	MessageNoticeFeignClient messageNoticeFeignClient;


	@PostMapping(value = "/addCommonAgent")
	@ApiOperation(value = "添加常用代理人", notes = "添加常用代理人", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> addCommonAgent(@RequestBody CommonAgentVO commonAgentVO){
		log.info("addCommonAgent:"+JSONObject.toJSONString(commonAgentVO));
		HttpResult<?> httpResult = commonAgentService.addCommonAgent(commonAgentVO);
		return httpResult;		
	}

	@PostMapping(value = "/updateCommonAgent")
	@ApiOperation(value = "更新常用代理人信息", notes = "更新常用代理人信息", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> updateCommonAgent(@RequestBody CommonAgentVO commonAgentVO) {
		log.info("updateCommonAgent:"+JSONObject.toJSONString(commonAgentVO));
		HttpResult<?> httpResult = commonAgentService.updateCommonAgent(commonAgentVO);
		return httpResult;
	}

	@PostMapping(value = "/planFeeInquiry")
	@ApiOperation(value = "代理人手续费查询", notes = "代理人手续费查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> planFeeInquiry(@RequestBody PlanFeeInquiryRequestVO planFeeInquiryRequestVO) {
		log.info("planFeeInquiry:"+JSONObject.toJSONString(planFeeInquiryRequestVO));
		HttpResult<?> httpResult = commonAgentService.planFeeInquiry(planFeeInquiryRequestVO);
		return httpResult;
	}

	@PostMapping(value = "/planFeeDetailInquiry")
	@ApiOperation(value = "当日手续费明细查询", notes = "当日手续费明细查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> planFeeDetailInquiry(@RequestBody PlanFeeDetailInquiryRequestVO planFeeDetailInquiryRequestVO) {
		log.info("planFeeDetailInquiry:"+JSONObject.toJSONString(planFeeDetailInquiryRequestVO));
		HttpResult<?> httpResult = commonAgentService.planFeeDetailInquiry(planFeeDetailInquiryRequestVO);
		return httpResult;
	}

	@PostMapping(value = "/billPackToFK")
	@ApiOperation(value = "代理人手续费提現", notes = "代理人手续费提现", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> billPackToFK(@RequestBody BillPackToFKRequest billPackToFKRequestVO) {
		log.info("billPackToFK:"+JSONObject.toJSONString(billPackToFKRequestVO));
		HttpResult<?> httpResult = commonAgentService.billPackToFK(billPackToFKRequestVO);
		return httpResult;
	}

	@PostMapping(value = "/queryPolicyList")
	@ApiOperation(value = "提现页面初始化查询", notes = "提现页面初始化查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> queryPolicyList(@RequestBody QueryPolicyVo  queryPolicyVo) {
		log.info("queryPolicyList:"+JSONObject.toJSONString(queryPolicyVo));
		HttpResult<?> httpResult = commonAgentService.queryPolicyList(queryPolicyVo);
		return httpResult; 
	}

	@PostMapping(value = "/updateConfig")
	@ApiOperation(value = "修改提现设置", notes = "修改提现设置", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> updateConfig(@RequestBody UpdateConfigRequest updateConfigRequestVO) {
		log.info("updateConfig:"+JSONObject.toJSONString(updateConfigRequestVO));
		HttpResult<?> httpResult = commonAgentService.updateConfig(updateConfigRequestVO);
		return httpResult;
	}

	@PostMapping(value = "/queryWithdrawalConfig")
	@ApiOperation(value = "查询用户的提现配置", notes = "查询用户的提现配置", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> queryWithdrawalConfig(@RequestBody QueryWithdrawalVo queryWithdrawalVo) {
		log.info("queryWithdrawalConfig:"+JSONObject.toJSONString(queryWithdrawalVo));
		HttpResult<?> httpResult = commonAgentService.queryWithdrawalConfig(queryWithdrawalVo);
		return httpResult;
	}

	@PostMapping(value = "/feePayDetailQuery")
	@ApiOperation(value = "查询提现明细记录", notes = "查询提现明细记录", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> feePayDetailQuery(@RequestBody FeePayDetailRequest feePayDetailRequestVO) throws Exception {
		log.info("feePayDetailQuery:"+JSONObject.toJSONString(feePayDetailRequestVO));
		HttpResult<?> httpResult = commonAgentService.feePayDetailQuery(feePayDetailRequestVO);
		return httpResult;
	}

	@PostMapping(value = "/queryInviteUserDetail")
	@ApiOperation(value = "代理人注册信息查询", notes = "代理人注册信息查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> queryInviteUserDetail(@RequestBody PlanFeeInquiryRequestVO planFeeInquiryRequestVO) {
		log.info("queryInviteUserDetail:"+JSONObject.toJSONString(planFeeInquiryRequestVO));
		HttpResult<?> httpResult = commonAgentService.queryInviteUserDetail(planFeeInquiryRequestVO);
		return httpResult;
	}
	
	@PostMapping(value = "/queryCashWithdrawal")
	@ApiOperation(value = "代理人提现信息查询", notes = "代理人提现信息查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> queryCashWithdrawal(@RequestBody PlanFeeInquiryRequestVO planFeeInquiryRequestVO) {
		log.info("queryCashWithdrawal:"+JSONObject.toJSONString(planFeeInquiryRequestVO));
		HttpResult<?> httpResult = commonAgentService.queryCashWithdrawal(planFeeInquiryRequestVO);
		return httpResult;
	}
	
//	@PostMapping("/sendMsg")
//	private HttpResult sendMsg(@RequestBody MessageSendVO messageVo) throws Exception {
//		messageVo.setExtendMessage("");
//		messageVo.setMessageType("cust-photo-upload");
//		messageVo.setPlatform("");
//		messageVo.setProviderID("");
//		messageVo.setSubTitle("最新特别消息");
//		messageVo.setTitle("超级APP尊敬的客户");
//		messageVo.setBizNo("CUST_PHOTO_UPLOAD_"+System.currentTimeMillis());
//		System.out.println(JSONObject.toJSONString(messageVo));
//		List<MessageSendVO> messageVoList = new ArrayList<MessageSendVO>();
//		messageVoList.add(messageVo);
//		System.out.println(JSONArray.toJSONString(messageVoList));
//		String msgData = JSONObject.toJSONString(messageVoList);
//		System.out.println(msgData);
//		HttpResult res = messageNoticeFeignClient.sendMessage(messageVoList);
//		System.out.println(JSONObject.toJSONString(res));
//		return null;
//		
//	}


}
