package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;

import lombok.Data;
@Data
public class Policy {
	private String printNo; //单证流水号      Y
	private String businessNo; //投保单号/保险单号
	private String originTradeSerialNo; //原渠道交易流水号
	private String certiType; //业务类型
	private Long currUwCode; //当前核保人员代码
	private Date underwritingDate; //核批通过时间,Max（核批通过&见费出单收费时间），等价于承保时间
	
}
