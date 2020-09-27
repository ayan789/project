package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;


/**
 * ������Ϣ
 * @author Administrator
 *
 */
public class InnerCoInsuranceInfo {
	private String  isLeader             ;//�Ƿ���������
	private String  isLeaderCollectAllPre;//�Ƿ�����������ȡ���б���
	private String  leaderPolicyNo       ;//�������˱�����
	private Double  leaderTotalPremium   ;//���������ܱ���
	private Double  leaderTotalSi        ;//���������ܱ���
	private Double  ourShare             ;//�ҷ�����
	private Double  ourTotalShare        ;//�ҷ�ռ��������
	private List<InnerCoInsuranceInsurer> innerCoInsuranceInsurerList;//���������֧����
	private Map<String,String> tempData;//��ʱ����
	private Long sequenceNumber;// ���
	
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}
	public String getIsLeaderCollectAllPre() {
		return isLeaderCollectAllPre;
	}
	public void setIsLeaderCollectAllPre(String isLeaderCollectAllPre) {
		this.isLeaderCollectAllPre = isLeaderCollectAllPre;
	}
	public String getLeaderPolicyNo() {
		return leaderPolicyNo;
	}
	public void setLeaderPolicyNo(String leaderPolicyNo) {
		this.leaderPolicyNo = leaderPolicyNo;
	}
	public List<InnerCoInsuranceInsurer> getInnerCoInsuranceInsurerList() {
		return innerCoInsuranceInsurerList;
	}
	public void setInnerCoInsuranceInsurerList(
			List<InnerCoInsuranceInsurer> innerCoInsuranceInsurerList) {
		this.innerCoInsuranceInsurerList = innerCoInsuranceInsurerList;
	}
	public Double getLeaderTotalPremium() {
		return leaderTotalPremium;
	}
	public void setLeaderTotalPremium(Double leaderTotalPremium) {
		this.leaderTotalPremium = leaderTotalPremium;
	}
	public Double getLeaderTotalSi() {
		return leaderTotalSi;
	}
	public void setLeaderTotalSi(Double leaderTotalSi) {
		this.leaderTotalSi = leaderTotalSi;
	}
	public Double getOurShare() {
		return ourShare;
	}
	public void setOurShare(Double ourShare) {
		this.ourShare = ourShare;
	}
	public Double getOurTotalShare() {
		return ourTotalShare;
	}
	public void setOurTotalShare(Double ourTotalShare) {
		this.ourTotalShare = ourTotalShare;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	



}
