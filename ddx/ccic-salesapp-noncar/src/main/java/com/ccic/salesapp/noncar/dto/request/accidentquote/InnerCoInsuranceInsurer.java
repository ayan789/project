package com.ccic.salesapp.noncar.dto.request.accidentquote;

import lombok.Data;

@Data
public class InnerCoInsuranceInsurer {
	private String isLeader;//是否主联方
	private String refPolicyNo;//关联保单号码
	private String shareRate;//比例
	private String totalShareRate;//占全单比例
	private String agentCode;//联保-代理人代码
	private String agreementNo;//联保-代理人协议号
	private String agreementName;//联保-代理人协议名称
	private String handlerCode;//联保-经办人代码
	private String issueUserCode;//联保-出单员
	private String issueOrgCode;//联保-出单机构ID
	private String innerCoHandlerCode;//联保-归属经办人代码
	private String innerCoOrgCode;//联保-归属机构代码
	private String innerCoHandler2Code;//联保-业务风险分类
	private String associatedProposalNo ;//关联投保单号
	private String businessSourceCode ;//联保-业务来源1(三级编码)
	private String businessSource2Code ;//联保-业务来源2(三级编码)
	private String innerCoOrgName ;//联保-归属机构名称
	private String businessSourceParentCode ;//联保-业务来源1(二级编码)
}
