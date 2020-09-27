package com.ccic.salesapp.noncar.dto.request;

import java.util.List;

import com.ccic.salesapp.noncar.dto.request.accidentquote.GuaranteeGroup;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyCoverage;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyForm;

import lombok.Data;
@Data
public class InsuredGroup {
	//标的组序号
		private String insuredGroupNo;
		//分组类型 1-普通分组，2-家庭分组
		private String insuredGroupType;
		//户数 
		private Long familyCount;
		//方案代码
		private String planCode;
		//份数
		private String numberOfCopies;
		//分组人数/标的数
		private String insuredCount;
		//责任条款信息列表
		private List<PolicyCoverage> policyCoverageList;
		//保障分组列表
		private List<GuaranteeGroup> guaranteeGroupList;
		//特约条款信息列表如无特约条款，整个列表不传
		
		private List<PolicyForm> policyFormList;
}
