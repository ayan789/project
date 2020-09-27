package com.ccic.salesapp.noncar.dto;

import java.util.Map;

import lombok.Data;

@Data
public class PolicyCommissionRate {
	private Double defaultRate; //   默认佣金率
	private Double maxRate; //       最大佣金率
	private Double minRate; //       最小佣金率
	private Double ruleEngineRate; //规则引擎返回佣金比例
	private Map<String,String> tempData; //临时数据
	private Long sequenceNumber ; //序号
}
