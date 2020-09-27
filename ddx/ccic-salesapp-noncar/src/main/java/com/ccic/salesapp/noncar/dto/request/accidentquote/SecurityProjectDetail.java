package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data
public class SecurityProjectDetail {
	private Date listDate ;//市时间
	private String listPlace ;//市地点
	private Double minSponsorFee ;//低承销及保荐费
	private String projectName ;//程名称/发行人/项目名称
	private Double raiseAmount ;//集资金额
	private Double sponsorFee ;//销及保荐费
	private String stockCode ;//票代码
	private Long sequenceNumber ;//序号
	private Map<String, String> tempData;// 临时数据
}
