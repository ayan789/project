package com.ccic.salesapp.noncar.dto;

import lombok.Data;

@Data
public class NewbizInfo {
	private String curUwLevelCode;// 当前核保级别
	private String initUwLevelCode;//初始核保级别（仅针对本轮核保）
	private String completeUwDate;// 完成核保日期
	private String uwNote;//         核保注释信息
	private String agencyQueryNo;//  中介信息查询码
	private String quotationDate;//  询价日期
	private String uwOpinion;//      核保意见
	private String keyProjectCode;// 关键项目
	private String autoUwResult;//   自动核保结果(1退回修改/2核保不通过/3核保通过)
	private String currUwId;//       当前核保人员ID
	private String uwDecisionCode;// 核保结论类型
	private String proposalCancelDate;//      投保单作废时间
	private String proposalRejectReasonCode;//投保单拒绝原因
	private String proposalCancelOperId;//    投保单作废人员
	private String proposalDescription;//     投保描述
	private String proposalRejectDesc;//      投保单拒绝原因描述
	private String proposalComments;//        投保备注
	private String proposalCreateDate;//      投保单创建时间
	private String sendUwDate;//              提交核保日期
	private String firstDataEntryDate;//      首次录单时间
	private String indiIdGatherCaptcha;//     北京地区身份证采集验证码
	private String specialApproveApplyNotes;//特批申请备注
	private String quoteRatingExpiryDate;//   询价单报价过期时间
	private String proposalStatus;//          投保单状态
	private String proposalSentDate;//        投保单发送给客户的时间
	private String quotationExpiryDate;//     询价单过期时间
	private String indiIdCardReaderNo;//      身份证读卡器编号
	private String logoForPrintCode;//        打印的品牌LOGO
	private String isSeparationPlatform;//    是否脱离平台
	private String isSaveRatingDetail;//      是否保存保费计算详细步骤信息
	private String nextUwLevelCode;//         下一个核保级别（仅针对本轮核保）
	private String lastUwId;//                最后核保人员Id
	private String lastUwOrganId;//           最后核保机构Id
	private String lastUwLevelCode;//         最终/最高核保级别
	private String isAntiMoneyPass;//         是否反洗钱客户识别，是否通过(Y/N)
	private String lastDataEntryDate;//       录单时间（记录最后一次用户点击Save/提交）
	private String isPrintClause;//           是否加载条款
	private String isHaveOverseaDesc;//       是否有海外说明书
	private String isNeedSpecialApprove;//    是否需要特批(Y/N)
	private String isMergePrint;//            是否合并打印
	private String sequenceNumber;// 序号
}
