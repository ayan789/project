package com.ccic.salesapp.noncar.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.request.SendMessageRequest;
import com.ccic.salesapp.noncar.outService.esb.sms.bean.SendSmsRequest;
import com.ccic.salesapp.noncar.outService.esb.sms.bean.SendSmsRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.sms.bean.SendSmsResponse;
import com.ccic.salesapp.noncar.outService.esb.sms.intf.SendSms;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SMSMessageMapper;
import com.ccic.salesapp.noncar.service.SmsMessageService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.request.LoginUser;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsMessageServiceImpl implements SmsMessageService {

	@Autowired
	SendSms service;
	
	@Autowired
	SMSMessageMapper sMSMessageMapper;

	@Override
	public HttpResult sendMessage(SendMessageRequest reqVO,LoginUser loginUser) {
		SendSmsRequestDTO requestBody=new SendSmsRequestDTO();
		requestBody.setDestMobile(reqVO.getMobile());
		requestBody.setMessageContent(reqVO.getMessage());
		requestBody.setComcode(reqVO.getComCode());
		String str="1";
		requestBody.setReportType(new BigInteger(str.getBytes()));
		
		SendSmsRequest request=new SendSmsRequest();
		request.setRequestHead(this.getConsumerID());
		request.setRequestBody(requestBody);
		try {
			//SendSms service=(SendSms) this.getBean("sendSms");
			SendSmsResponse response=service.sendSms(request);
			
			int status=response.getResponseHead().getStatus();
			String appMessage=response.getResponseHead().getAppMessage();
			String esbMessage=response.getResponseHead().getEsbMessage();
			log.info("--status:"+status+"--appMessage:"+appMessage+"--esbMessage"+esbMessage);
			if(status==0){
				Map<String, String> param = new HashMap<String, String>();
				param.put("mobile", reqVO.getMobile());
				param.put("userCode", loginUser!=null?loginUser.getUserCode():"");
				param.put("message", reqVO.getMessage());
				/*
				 * DefaultDAO dao = new DefaultDAO(); dao.doInsert("smsMessage.insert", param);
				 */
				sMSMessageMapper.insert(param);
				/*
				 * result.setCode("1"); result.setMessage("调用短信接口成功"); json.setResult(result);
				 */
				return HttpResult.success();
			}else {
				/*
				 * result.setCode("0"); result.setMessage("调用短信接口失败："+appMessage);
				 * json.setResult(result);
				 */
				return HttpResult.error(0, "调用短信接口失败："+appMessage);
			}
		}catch (Exception e) {
			/*
			 * result.setCode("0"); result.setMessage("调用短信接口异常："+e.getMessage());
			 * json.setResult(result);
			 */
			e.printStackTrace();
			return HttpResult.error(0, "调用短信接口异常："+e.getMessage());
		}
	}
	
	public RequestHeadDTO getConsumerID(){
		RequestHeadDTO requestHead = new RequestHeadDTO();
		requestHead.setConsumerID("supsale");
		return requestHead;
	}
	
	public  boolean isRepeatSendByMobile(String mobile){
		Integer c=0;
		try {
			c = sMSMessageMapper.countByMobile( mobile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c>2?true:false;
	}
	
}
