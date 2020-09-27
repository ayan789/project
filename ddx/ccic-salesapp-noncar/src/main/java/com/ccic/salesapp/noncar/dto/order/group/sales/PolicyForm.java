package com.ccic.salesapp.noncar.dto.order.group.sales;

import java.util.Map;

import lombok.Data;

@Data
public class PolicyForm {
	private String customFormContent;// 自定义条款内容
	private String customFormDescription;// 自定义条款描述
	private String customFormLevelCode;// 自定义条款级别
	private String customFormNo;// 自定义条款号
	private String customFormTitle;// 自定义条款标题
	private String customFormType;// 自定义条款类型
	private String effectiveDate;// 起保日期/起始生效日期,支持时分秒
	private String expiryDate;// 终保日期，保单止期/止保日期/失效日期，不包含这个时间点在内
	private String formCode;// 条款代码
	private String formType;// 条款类型
	private String formVariables;// 条款变量
	private String insuredCount;// 投保户数
	private String isCustomForm;// 是否自定义条款
	private String numberOfCopies;// 份数
	private String policyStatus;// 保单状态（1未生效2已生效3失效4批改临时删除状态）
	private Double premium;// 保费/保险费
	private String productElementCode;// 产品元素代码
	private String productElementId;// 产品元素ID
	private String totalNumberOfCopies;// 总份数，团单时使用，=份数*人数
	private Double vatRate;// 增值税率
	private Long sequenceNumber;//序号
	private Map<String, String> tempData;// 临时数据
}
