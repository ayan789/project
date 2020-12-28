package com.ccic.salesapp.noncar.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.noncar.dto.AppCheckCode;
import com.ccic.salesapp.noncar.dto.request.SendSmsRequestVo;
import com.ccic.salesapp.noncar.dto.request.Sendsimplemessageservicerest;
import com.ccic.salesapp.noncar.dto.response.SendSimpleMessageResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.sms.intf.SendSms;
import com.ccic.salesapp.noncar.repository.basedb.mapper.AppCheckCodeMapper;
import com.ccic.salesapp.noncar.service.AbstractFacade;
import com.ccic.salesapp.noncar.service.ModifyUserInfoService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.utils.PropertiesUtil;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salessapp.common.utils.UnirestUtil;

import kong.unirest.JsonNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ModifyUserInfoServiceImpl extends AbstractFacade implements ModifyUserInfoService{

	@Autowired
	AppCheckCodeMapper appCheckCodeMapper;
	@Autowired
	SendSms service;
	
	@Value("${spring.profiles.active}")
	String active;
	
	@Value("${esb.consumerID.sendsimplemessageservicerest}")
	String consumerID;
	
	@Override
	/*
	 * 查询验证码
	 */
	public AppCheckCode getCheckCode(String userCode) {
		Map<String, String> param=new HashMap<String, String>();
		param.put("userCode",userCode);
		AppCheckCode appCheckCode=new AppCheckCode();
		try {
			appCheckCode = appCheckCodeMapper.selectCodeByUser(param);
		} catch (Exception e) {
			log.info("查询验证码失败！");
			e.printStackTrace();
		}
		return appCheckCode;
	}

	/**
	 * 生产六位验证码
	 * @return
	 * @throws
	 */
	private String getRandomNum(){
		String s = "";
		while(s.length()<6){
			s+=(int)(Math.random()*10);
		}
		return s;
	}


	@Override
	public HttpResult sendsimplemessageservicerest(SendSmsRequestVo smsRequest, String templat) {
		HttpResult result = HttpResult.success();
		Request request = new Request();
		RequestHead requestHead =new RequestHead();
		requestHead.setConsumerID(consumerID);
		Sendsimplemessageservicerest requestBody = new Sendsimplemessageservicerest();
		requestBody.setChannel(getSubChannelAndMessageType().getChannel());
		requestBody.setSubChannel(getSubChannelAndMessageType().getSubChannel());
		requestBody.setMessageType(getSubChannelAndMessageType().getMessageType());
		requestBody.setComcode(smsRequest.getComcode());
		requestBody.setTargetType(getSubChannelAndMessageType().getTargetType());
		requestBody.setTarget(smsRequest.getMobile());
		String checkCode = getRandomNum();
		String content="";
		if(StringUtils.isNotBlank(templat)){
			content = templat.replace("checkCode", checkCode);
		}else{
			content = "大地保险修改手机号验证码为"+checkCode;
		}
		requestBody.setContent(content);
		requestBody.setPriority(getSubChannelAndMessageType().getPriority());
		request.setRequestHead(requestHead);
		request.setRequestBody(requestBody);
		log.info("#######普通短信请求参数："+JSON.toJSONString(request));
		JsonNode jsonNode = UnirestUtil.postForJson(PropertiesUtil.getProperty("esb.address.sendsimplemessageservicerest", null), request);
		log.info("#######普通短信响应参数："+jsonNode);
		SendSimpleMessageResponseDTO response = JSON.parseObject(jsonNode.toString(), SendSimpleMessageResponseDTO.class);
		int status = response.getResponseHead().getStatus();
		String appMessage=response.getResponseHead().getAppMessage();
		String esbMessage=response.getResponseHead().getEsbMessage();
		log.info("--status:"+status+"--appMessage:"+appMessage+"--esbMessage"+esbMessage);
		int count = 0;
		String longTime = String.valueOf(System.currentTimeMillis());
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("userCode", smsRequest.getUserId());
		param.put("checkCode", checkCode);
		param.put("longTime", longTime);
		param.put("mobile", smsRequest.getMobile());
		//验证码发送成功
		if(status==0){
			try {
				//执行更新
				count = appCheckCodeMapper.upCheckCode(param);
				if(count==0){
					//执行插入
					AppCheckCode codeReq = new AppCheckCode();
					codeReq.setCheckCode(checkCode);
					codeReq.setLongTime(longTime);
					codeReq.setMobile(smsRequest.getMobile());
					codeReq.setUserCode(smsRequest.getUserId());
					count = appCheckCodeMapper.insert(codeReq);
				}
			} catch (Exception e) {
				result.setCode("0");
				result.setMsg("验证码入库更新失败");
				e.printStackTrace();
			}
			if(!"dev".equals(active.toLowerCase()) && !"test".equals(active.toLowerCase()) && !"uat".equals(active.toLowerCase()) && !"sit".equals(active.toLowerCase())) {
				checkCode = "";
			}
			result.setCode("1");
			result.setMsg("验证码发送成功"+checkCode);//应孙操要求返回验证码到前端
		}else{
			result.setCode("0");
			result.setMsg("验证码短信发送失败："+appMessage);
		}
		return result;	
	}
	
	
	public static Sendsimplemessageservicerest getSubChannelAndMessageType() {
		Sendsimplemessageservicerest requestBody = new Sendsimplemessageservicerest();
		requestBody.setSubChannel("106");
		requestBody.setMessageType("10001");
		requestBody.setChannel("sms");
		requestBody.setTargetType("1");
		String str="3";
		requestBody.setPriority(new BigInteger(str.getBytes()));
		return requestBody;
	}
}
