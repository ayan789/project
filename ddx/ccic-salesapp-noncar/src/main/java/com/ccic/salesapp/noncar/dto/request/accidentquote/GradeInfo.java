package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;
@Data
public class GradeInfo {
	private int additionalInsuredCount ;//附加被保险人数
	private int enrolledStudentCount ;//在册学生数
	private String gradeName ;//年级
	private int insuredStudentCount ;//投保学生数
	private String isCommonInsured ;//是否共同被保险人
	private String proposalType ;//投保方式
	private int schoolId ;//学校ID(显示为编码)
	private String schoolSystemCode ;//学制
	private int teacherCount ;//派遣教师人数
	private Long sequenceNumber ;//序号
    private Map<String, String> tempData;// 临时数据
}
