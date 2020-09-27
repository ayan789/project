package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.List;

import lombok.Data;
@Data
public class InnerCoInsuranceInfo {
	private String isLeaderCollectAllPrem;//是否主联方收取所有保费
	private String leaderPolicyNo;//主联方保单号
	private String leaderTotalPremium;//主联方总保费
	private String leaderTotalSi;//主联方总保额
	private List<InnerCoInsuranceInsurer> innerCoInsuranceInsurerList;//联保参与分支机构信息
}
