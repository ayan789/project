package com.ccic.salesapp.noncar.service;
import com.ccic.salesapp.noncar.dto.request.SendSmsRequestVo;
import com.ccic.salesapp.noncar.dto.response.JsonVO;

/**
 * 意健险续保提醒查询
 * @author sunyz
 *
 */
public interface RemindHealthService {
	
	/**
	 * 发送续保短信提醒
	 * @param smsRequest
	 * @param templat
	 * @return
	 */
	public JsonVO sendSMSImpl(SendSmsRequestVo smsRequest, String templat, String policyNo);
}

