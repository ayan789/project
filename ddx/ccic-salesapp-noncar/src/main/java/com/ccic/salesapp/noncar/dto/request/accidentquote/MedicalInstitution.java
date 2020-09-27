package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data
public class MedicalInstitution {
	private int emergeTreatPersonTime ;//门急诊人次
	private int inHospitalPersonTime ;//住院人次
	private String licenseQualificationNo ;//执业许可证/执业资格证编号
	private String medicalInstituLevelCode ;//医疗机构等级
	private String medicalInstituNatureCode ;//医疗机构性质
	private String medicalInstituType ;//医疗机构类别
	private int medicalStaffCount ;//医务人员数量
	private Date openingDate ;//开业年份\成立时间
	private int sickbedCount ;//病床数
	private int surgeryPersonTime ;//手术人次
	private Long sequenceNumber ;//序号
    private Map<String, String> tempData;// 临时数据
}
