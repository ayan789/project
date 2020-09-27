package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;
@Data
public class PolicyDeductible {
	private String fullContent;//	完整免赔内容，根据模板内容以及变量值拼接
	private Double deductibleAmount;//免赔额
    private Double deductibleRate;//	免赔率
    private Long sequenceNumber;// 序号
    private Map<String, String> tempData;// 临时数据
}
