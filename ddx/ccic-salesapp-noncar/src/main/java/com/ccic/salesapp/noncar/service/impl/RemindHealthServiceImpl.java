package com.ccic.salesapp.noncar.service.impl;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.request.SendSmsRequestVo;
import com.ccic.salesapp.noncar.outService.esb.sms.bean.SendSmsRequest;
import com.ccic.salesapp.noncar.outService.esb.sms.bean.SendSmsRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.sms.bean.SendSmsResponse;
import com.ccic.salesapp.noncar.outService.esb.sms.intf.SendSms;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.request.PolicyMessageVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.response.ResultVO;

import com.ccic.salesapp.noncar.repository.basedb.mapper.PolicyMessageMapper;
import com.ccic.salesapp.noncar.service.AbstractFacade;
import com.ccic.salesapp.noncar.service.RemindHealthService;
import com.ccic.salessapp.common.utils.StringUtils;


@Slf4j
@Service
public class RemindHealthServiceImpl extends AbstractFacade implements RemindHealthService {
	@Autowired
	PolicyMessageMapper policyMessageMapper;
	/**
	 * 短信续保通知
	 */
	public JsonVO sendSMSImpl(SendSmsRequestVo smsRequest, String templat, String policyNo) {
		SendSmsRequestDTO requestBody=new SendSmsRequestDTO();
		requestBody.setComcode(smsRequest.getComcode());
		requestBody.setDestMobile(smsRequest.getMobile());
		String checkCode = getRandomNum();
		String content="";
		if(StringUtils.isNotBlank(templat)){
			content = templat.replace("checkCode", checkCode);
		}else{
			content = "大地保险修改手机号验证码为"+checkCode;
		}
		requestBody.setMessageContent(content);
		String str="1";
		requestBody.setReportType(new BigInteger(str.getBytes()));
		
//		XStream xs=new XStream();
		
		SendSmsRequest request=new SendSmsRequest();
		request.setRequestHead(this.getConsumerID());
		request.setRequestBody(requestBody);
		
		SendSms service=(SendSms) this.getBean("sendSms");
		SendSmsResponse response=service.sendSms(request);
		
		
		int status=response.getResponseHead().getStatus();
		String appMessage=response.getResponseHead().getAppMessage();
		String esbMessage=response.getResponseHead().getEsbMessage();
		
		JsonVO jsonVO=new JsonVO();
		ResultVO result=new ResultVO();
		int count = 0;
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("userCode", smsRequest.getUserId());
		param.put("policyNo", policyNo);
		//验证码发送成功
		if(status==0){
			try {
				count = policyMessageMapper.selectCodeByPolicyNoAndUser(param) ;
				if (count == 0) {
					PolicyMessageVO mess = new PolicyMessageVO();
					mess.setPolicyNo(policyNo);
					mess.setUserCode(smsRequest.getUserId());
					mess.setContent(templat);
					policyMessageMapper.insert(mess);
				}
			} catch (Exception e) {
				result.setCode("0");
				result.setMessage("信息入库更新失败");
				jsonVO.setResult(result);
				e.printStackTrace();
			}
			result.setCode("1");
			result.setMessage("验证码短信 发送成功");
			jsonVO.setResult(result);			
		}else{
			result.setCode("0");
			result.setMessage("验证码短信 发送失败："+appMessage);
			jsonVO.setResult(result);
		}
		return jsonVO;	
	}
	
	
	/**
	 * 生产六位验证码
	 * @return 
	 * @throws CustomException 
	 */
	private String getRandomNum(){
		String s = "";
		while(s.length()<6){
			s+=(int)(Math.random()*10);
		}
		return s;
	}
	
	
}
