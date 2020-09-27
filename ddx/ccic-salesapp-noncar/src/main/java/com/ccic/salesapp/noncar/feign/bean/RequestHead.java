package com.ccic.salesapp.noncar.feign.bean;

import lombok.Data;

@Data
public class RequestHead {
	String classCode;
	String consumerID;
	String consumerSeqNo;
	String providerID;
	String regionCode;
	String riskCode;
	String seqNo;
	String version;
}
