package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;
@Data
public class PolicySchoolDetail {
	private String address ;//地址
	private String adminDivision ;//行政区域（校园方责任险）
	private String schoolName ;//学校名称（校园方责任险）
	private String schoolNatureCode ;//学校性质
	private String schoolType ;//学校类别
	private String thirdPartyLiability ;//第三者责任
	private Long sequenceNumber ;//序号
	private Map<String, String> tempData;// 临时数据
}
