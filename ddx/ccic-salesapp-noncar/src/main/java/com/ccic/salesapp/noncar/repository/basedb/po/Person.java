package com.ccic.salesapp.noncar.repository.basedb.po;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class Person {
		/**
		 * age	年龄
		comments	备注信息/简介
		doctorCode	医务人员类别
		effectiveDate	起保日期（保障起期）
		elementType	保单元素类型
		expiryDate	终保日期（保障止期）
		genderCode	性别（男,女）
		hospitalComName	所在医疗机构名称
		incumbentJobTitleCode	现任岗位/科室
		insuredCode	标的类别
		issuerData	签发日期
		jobSeniority	从业年限
		licenseNo	执照号码
		licenseQualificationNo	执业资格证编号
		licenseTypeName	执照种类(非码表）
		managerType	船舶管理人类型
		occupationQualification	职业资格
		parentElementType	上级保单元素类型
		parentPolicyElementId	上级保单元素ID
		personEducationCode	学历(专科,本科,硕士,博士,其他)
		personIdNo	证件号码*
		personIdType	证件类型
		personName	姓名(船舶管理人名称)
		personWork	工种
		personWorkType	工种类型
		policyStatus	保单状态（1未生效2已生效3失效4批改临时删除状态）
		representativeJobTitle	职位
		sequenceNumber	序号
		yearSalaryAmount	年工资额
		yearUndertookAmount	年经手金额
		sequenceNumber	序号
		tempData	临时数据
		
		 */
	private long personListId;
	private long orderId;
	private int age ;
	private String comments ;
	private String doctorCode ;
	private Date effectiveDate ;
	private String elementType ;
	private Date expiryDate ;
	private String genderCode ;
	private String hospitalComName ;
	private String incumbentJobTitleCode ;
	private String insuredCode ;
	private Date issuerData ;
	private int jobSeniority ;
	private String licenseNo ;
	private String licenseQualificationNo ;
	private String licenseTypeName ;
	private String managerType ;
	private String occupationQualification ;
	private String parentElementType ;
	private Long parentPolicyElementId ;
	private String personEducationCode ;
	private String personIdNo ;
	private String personIdType ;
	private String personName ;
	private String personWork ;
	private String personWorkType ;
	private int policyStatus ;
	private String representativeJobTitle ;
	private Long sequenceNumber ;
	private Double yearSalaryAmount ;
	private Double yearUndertookAmount ;
    private Map<String, String> tempData;
    
    private Date createTime;
    
    private String createUser;
    
}
