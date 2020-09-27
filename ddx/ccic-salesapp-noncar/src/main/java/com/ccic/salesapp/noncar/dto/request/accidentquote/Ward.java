package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;
@Data
public class Ward {
	private String age;//被监护人年龄
	private String classes;//被监护人班级
	private String dateOfBirth;//出生日期
	private String effectiveDate;//起保日期
	private String expiryDate;//终保日期
	private String genderCode;//被监护人性别
	private String idNo;//被监护人证件号码
	private String idType;//被监护人证件类型
	private String name;//被监护人姓名
	private String schoolRegionCode;//学校/地区
	private Long sequenceNumber ;//序号
	private Map<String, String> tempData;// 临时数据
}
