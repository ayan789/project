package com.ccic.salesapp.performance.domain.vo.request;

import java.math.BigInteger;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;

import lombok.Data;

@Data
public class Sendsimplemessageservicerest extends RequestBody {

	private String comcode;
	private String channel;
	private String channelId;
	private String srcNumber;
	private String subChannel;
	private String messageType;
	private BigInteger messageFormat;
	private String destMobile;
	private String messageContent;
	private String messageTemplate;
	private String target;
	private String targetType;
	private String content;
	private BigInteger priority;
	private BigInteger messagePriority;
	private XMLGregorianCalendar sendTime;
	private XMLGregorianCalendar exprireTime;
	private XMLGregorianCalendar expireTime;
	private BigInteger reportType;

	
}
