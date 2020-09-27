package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;
@Data
public class ProductDetail {
	private String brand;// 品牌
	private String idNo;// 身份证号
	private Double insuranceAmount;// 保险金额
	private String insuredName;// 被保险人姓名
	private String isSingle;// 单台/多台
	private String model;// 机型
	private String modelNo;// 机型号
	private Long sequenceNumber;// 序号
	private Map<String, String> tempData;// 临时数据
}
