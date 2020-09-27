package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;
@Data
public class SuperviseeOwner {
	private Double annualTurnover ;//年营业额
	private String creditRating ;//信用评级
	private Double registeredCapital ;//注册资金
	private String supervisionAddress ;//监管地点
	private String supervisionItemName ;//监管物品名称
	private String supervisionItemOwnerName ;//监管物品所有人
	private String supervisionItemType ;//监管物品类型
	private Double supervisionItemValue ;//监管物品价值
	private String supervisionTerm ;//监管期限
	private Double totalAsset ;//总资产
	private Long sequenceNumber ;//序号
	private Map<String, String> tempData;// 临时数据
}
