package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data
public class StudentInfo {
	private int age ;//年龄
	private String classes ;//班级
	private Date dateOfBirth ;//出生日期
	private Date effectiveDate ;//起保日期
	private String elementType ;//保单元素类型
	private Date expiryDate ;//终保日期
	private String genderCode ;//性别
	private String idNo ;//身份证号/学号
	private String parentElementType ;//上级保单元素类型
	private Long parentPolicyElementId ;//上级保单元素ID
	private int policyStatus ;//保单状态（1未生效2已生效3失效4批改临时删除状态）
	private Long schoolId ;//学校ID(显示为编码)
	private Long sequenceNumber ;//序号
	private String studentName ;//学生姓名
	private Map<String, String> tempData;// 临时数据
}
