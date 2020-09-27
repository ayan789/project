package com.ccic.salesapp.noncar.dto;

import java.util.List;

import lombok.Data;

@Data
public class CoInsuranceInfo {
	//是否主保险人
		private String isLeader;
		//是否主保险人收取所有保费
		private String isLeaderCollectAllPrem;
		//主保险人保单号
		private String leaderPolicyNo;
		//主保险人总保费
		private double leaderTotalPremium;
		//主保险人总保额
		private double leaderTotalSi;
		//共保参与方信息
		private List<CoInsuranceInsurer> coInsuranceInsurerList;
}
