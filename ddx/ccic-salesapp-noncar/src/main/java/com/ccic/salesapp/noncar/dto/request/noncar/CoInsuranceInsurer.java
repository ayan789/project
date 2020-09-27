package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * �������뷽��Ϣ
 * @author Administrator
 *
 */
public class CoInsuranceInsurer {
	//�����˵�����ID(ͨ�����ֶ��ж���������˾�Ƿ��Ǵ��)
	private int insurerPartyId;
	//�Ƿ���������
	private String isLeader;
	//������������
	private String refPolicyNo;
	//������˾��֧��������
	private String coInsurerBranchName;
	//����
	private double shareRate;
	//��ʱ����
	private Map<String,String> tempData; 
	private Long sequenceNumber;//���
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public int getInsurerPartyId() {
		return insurerPartyId;
	}
	public void setInsurerPartyId(int insurerPartyId) {
		this.insurerPartyId = insurerPartyId;
	}
	public String getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}
	public String getRefPolicyNo() {
		return refPolicyNo;
	}
	public void setRefPolicyNo(String refPolicyNo) {
		this.refPolicyNo = refPolicyNo;
	}
	public String getCoInsurerBranchName() {
		return coInsurerBranchName;
	}
	public void setCoInsurerBranchName(String coInsurerBranchName) {
		this.coInsurerBranchName = coInsurerBranchName;
	}
	public double getShareRate() {
		return shareRate;
	}
	public void setShareRate(double shareRate) {
		this.shareRate = shareRate;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}


}
