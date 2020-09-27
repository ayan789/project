package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data
public class Person {
	private int age ;//年龄
	private String comments ;//备注信息/简介
	private String doctorCode ;//医务人员类别
	private Date effectiveDate ;//起保日期（保障起期）
	private String elementType ;//保单元素类型
	private Date expiryDate ;//终保日期（保障止期）
	private String genderCode ;//性别（男,女）
	private String hospitalComName ;//所在医疗机构名称
	private String incumbentJobTitleCode ;//现任岗位/科室
	private String insuredCode ;//标的类别
	private Date issuerData ;//签发日期
	private int jobSeniority ;//从业年限
	private String licenseNo ;//执照号码
	private String licenseQualificationNo ;//执业资格证编号
	private String licenseTypeName ;//执照种类(非码表）
	private String managerType ;//船舶管理人类型
	private String occupationQualification ;//职业资格
	private String parentElementType ;//上级保单元素类型
	private Long parentPolicyElementId ;//上级保单元素ID
	private String personEducationCode ;//学历(专科,本科,硕士,博士,其他)
	private String personIdNo ;//证件号码*
	private String personIdType ;//证件类型
	private String personName ;//姓名(船舶管理人名称)
	private String personWork ;//工种
	private String personWorkType ;//工种类型
	private int policyStatus ;//保单状态（1未生效2已生效3失效4批改临时删除状态）
	private String representativeJobTitle ;//职位
	private Long sequenceNumber ;//序号
	private Double yearSalaryAmount ;//年工资额
	private Double yearUndertookAmount ;//年经手金额
    private Map<String, String> tempData;// 临时数据
}
