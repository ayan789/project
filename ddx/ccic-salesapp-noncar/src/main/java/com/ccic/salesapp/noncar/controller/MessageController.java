package com.ccic.salesapp.noncar.controller;
import com.ccic.salesapp.noncar.dto.request.SendSmsRequestVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ccic.salesapp.noncar.dto.request.ISendMessageRequestVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.service.RemindHealthService;
import com.ccic.salesapp.noncar.service.impl.RemindHealthServiceImpl;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.LoginUser;
import com.ccic.salessapp.common.utils.RSAUtils;
import com.ccic.salessapp.common.utils.StringUtils;

@RestController
public class MessageController {
	/**
	 * 发送短信续保通知
	 * @param jsonVO
	 * @return
	 */
	@PostMapping("/sendMessage")
	public HttpResult sendMessage(Object jsonVO){
		JsonVO json=(JsonVO)jsonVO;
		LoginUser loginUser = json.getHead().getLoginUser();
		ISendMessageRequestVO requestVO = (ISendMessageRequestVO) json.getData();

		String url = requestVO.getUrl();//续保链接地址
		String username = requestVO.getUsername();//投保人用户名
		String mobile = requestVO.getMobile();//投保人手机号
		String endDate = requestVO.getEndDate();//终止日期
		String policyNo = requestVO.getPolicyNo();//保单号

		if(StringUtils.isEmpty(url)){
			HttpResult result = HttpResult.error(0, "续保链接地址为空！");
			return result;
		}
		if(StringUtils.isEmpty(username)){
			HttpResult result = HttpResult.error(0, "投保人用户名为空！");
			return result;
		}
		if(StringUtils.isEmpty(mobile)){
			HttpResult result = HttpResult.error(0, "投保人手机号为空！");
			return result;
		}
		if(StringUtils.isEmpty(endDate)){
			HttpResult result = HttpResult.error(0, "终止日期为空！");
			return result;
		}
		if(StringUtils.isEmpty(policyNo)){
			HttpResult result = HttpResult.error(0, "保单号为空！");
			return result;
		}
		try {
			mobile = RSAUtils.decryptByPrivate(mobile);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		RemindHealthService impl = new RemindHealthServiceImpl();

		try {
			//请求获取验证码接口
			SendSmsRequestVo smsRequest = new SendSmsRequestVo();
			smsRequest.setComcode(loginUser.getComCode());
			smsRequest.setMobile(mobile);
			smsRequest.setUserId(loginUser.getUserCode());
			String templat = "【中国大地保险】尊敬的" + username + "女士/先生，您在大地保险投保的天地关爱百万医疗保险即将于" 
					+ endDate + "到期，请立即访问" + url
					+ "续保。若您未能及时续保，重新投保后将增设30天观察期。如有疑问请致电95590。若您已经续保，请忽略此短信内容。退订回TD。";
			JsonVO jsonVO1 = impl.sendSMSImpl(smsRequest, templat, policyNo);
			json.setResult(jsonVO1.getResult());
		} catch (Exception e) {
			HttpResult result = HttpResult.error(0, "发送验证码异常");
			return result;
			
		}
		return null;
	}


}
