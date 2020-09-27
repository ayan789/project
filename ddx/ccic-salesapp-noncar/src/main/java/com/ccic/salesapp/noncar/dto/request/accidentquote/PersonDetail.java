package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;
@Data
public class PersonDetail {
	private String industryCode ;//行业代码
	private String insuredCode ;//标的类别
	private String personName ;//姓名(船舶管理人名称)
	private String personIdType ;//证件类型
	private String personIdNo ;//证件号码*
	private String genderCode ;//性别（男,女）
	private int age ;//年龄
	private String personEducationCode ;//学历(专科,本科,硕士,博士,其他)
	private String licenseQualificationNo ;//执业资格证编号
	private String licenseTypeName ;//执照种类(非码表）
	private String licenseNo ;//执照号码
	private String issuerData ;//签发日期
	private String representativeJobTitle ;//职位
	private String occupationQualification ;//职业资格
	private int jobSeniority ;//从业年限
	private String personWorkType ;//工种类型
	private String personWork ;//工种
	private Double yearSalaryAmount ;//年工资额
	private Double yearUndertookAmount ;//年经手金额
	private String comments ;//备注信息/简介
	private String incumbentJobTitleCode ;//现任岗位/科室
	private String hospitalComName ;//所在医疗机构名称
	private String doctorCode ;//医务人员类别
	private String jobType ;//岗位类别（是文本，jiafa）
	private Double eachPersonPremium ;//每人基本保费
	private int personCount ;//人数
	private Double doctorPremium ;//医务人员保费
	private String managerType ;//船舶管理人类型
	private String personGroupNo ;//人员分组序号
	private String effectiveDate ;//起保日期（保障起期）
	private String expiryDate ;//终保日期（保障止期）
	private Long sequenceNumber ;//序号
	private String BenefitPolHolderRelaCode ;//与投保人关系
	private Map<String,String> tempData ;//临时数据
}
