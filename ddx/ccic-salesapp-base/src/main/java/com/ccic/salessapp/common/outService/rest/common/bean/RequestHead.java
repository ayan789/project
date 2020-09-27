package com.ccic.salessapp.common.outService.rest.common.bean;

import com.ccic.salessapp.common.utils.PropertiesUtil;
import lombok.Data;

import java.util.UUID;

@Data
public class RequestHead {

	private String seqNo; //流水号
	private String consumerSeqNo;//消费方流水号
	private String consumerID;//服务消费方ID   Y
	private String providerID;//服务提供方ID
	private String classCode;//险类
	private String riskCode;//险种
	private String regionCode;//地区
	private String version;//服务版本

	public static RequestHead getDefault() {
		RequestHead requestHead = new RequestHead();
		String consumerId = PropertiesUtil.getProperty("sonic.platform.interface.consumer-id","supsale");
		requestHead.setConsumerID(consumerId);
		requestHead.setConsumerSeqNo(getUUID());
		return requestHead;
	}

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
}
