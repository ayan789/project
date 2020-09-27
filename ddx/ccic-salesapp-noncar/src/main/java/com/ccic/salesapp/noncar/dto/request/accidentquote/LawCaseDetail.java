package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;
@Data
public class LawCaseDetail {
	private String behaviorPreservation ;//含行为保全
	private String caseNo ;//立案号
	private String caseStatus ;//案件状态
	private String caseType ;//案件类型
	private String courtName ;//法院名称
	private String courtType ;//法院类型
	private String evidencePreservation ;//含证据保全
	private String insuranceCoverage ;//保险责任
	private Long lawCaseId ;//诉讼案件清单ID（主键）
	private String lawFirmName ;//申请人委托律所名称
	private Double lawInsuredAmount ;//诉讼标的额
	private String preservationInsuredDesc ;//保全标的描述
	private String preservationInsuredType ;//保全标的类型和保全模式
	private String repondentByName ;//保全被申请人名称
	private String repondentName ;//保全申请人名称
	private Long sequenceNumber ;//序号
	private Map<String, String> tempData;// 临时数据
}
