package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;

/**
 * ������Ϣ
 * @author Administrator
 *
 */
public class CoInsuranceInfo {
	//�Ƿ���������
	private String isLeader;
	//�Ƿ�����������ȡ���б���
	private String isLeaderCollectAllPrem;
	//�������˱�����
	private String leaderPolicyNo;
	//���������ܱ���
	private double leaderTotalPremium;
	//���������ܱ���
	private double leaderTotalSi;
	//�������뷽��Ϣ
	private List<CoInsuranceInsurer> coInsuranceInsurerList;
	public String getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}
	public String getIsLeaderCollectAllPrem() {
		return isLeaderCollectAllPrem;
	}
	public void setIsLeaderCollectAllPrem(String isLeaderCollectAllPrem) {
		this.isLeaderCollectAllPrem = isLeaderCollectAllPrem;
	}
	public String getLeaderPolicyNo() {
		return leaderPolicyNo;
	}
	public void setLeaderPolicyNo(String leaderPolicyNo) {
		this.leaderPolicyNo = leaderPolicyNo;
	}
	public double getLeaderTotalPremium() {
		return leaderTotalPremium;
	}
	public void setLeaderTotalPremium(double leaderTotalPremium) {
		this.leaderTotalPremium = leaderTotalPremium;
	}
	public double getLeaderTotalSi() {
		return leaderTotalSi;
	}
	public void setLeaderTotalSi(double leaderTotalSi) {
		this.leaderTotalSi = leaderTotalSi;
	}
	public List<CoInsuranceInsurer> getCoInsuranceInsurerList() {
		return coInsuranceInsurerList;
	}
	public void setCoInsuranceInsurerList(
			List<CoInsuranceInsurer> coInsuranceInsurerList) {
		this.coInsuranceInsurerList = coInsuranceInsurerList;
	}


}
