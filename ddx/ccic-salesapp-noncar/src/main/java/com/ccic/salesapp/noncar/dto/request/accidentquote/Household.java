package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data
public class Household {
	private int buildingArea;//建筑面积
	private Date effectiveDate;//起保日期
	private Date expiryDate;//终保日期
	private Date handoverDate;//交房日期
	private String houseNo;//户号
	private int policyStatus;//保单状态（1未生效2已生效3失效4批改临时删除状态）
	private Long sequenceNumber ;//序号
	private Map<String, String> tempData;// 临时数据
}
