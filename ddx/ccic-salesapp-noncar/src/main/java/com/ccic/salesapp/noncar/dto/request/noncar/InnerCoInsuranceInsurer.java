package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;

/**
 * �������뷽��Ϣ
 * @author Administrator
 *
 */
public class InnerCoInsuranceInsurer {
	private Long agentId;//����-������ID�����ڷ�ֱ��ҵ����ʹ��
	private String agreementName;//����-������Э������
	private String agreementNo;//����-������Э���
	private String businessSourceCode;//����-ҵ����Դ1/BusinessNature����Ӧ���������˵�ҵ����Դ�����ĩ����ҵ��ԭ��
	private String businessSource2Code;//����-ҵ����Դ2/BusinessNature����Ӧ����������2��ҵ����Դ�����ĩ����ҵ��ԭ��
	private String handlerId;//����-�����ˣ�����ֱ��ҵ����ʹ��
	private String innerCoHandlerId;//����-����������ID����Ӧ���չ�˾�ڲ�Ա��
	private String innerCoHandler2Id;//����-ҵ����շ���/����������2
	private String isLeader;//�Ƿ���������
	private String issueOrgId;//����-��������ID
	private String issueUserId;//����-����Ա
	private String issueOrgCode; //�������Ĺ�����������
	private String shareRate; //��������ռ�ݶ�
	private String handlerCode; //�����������˴���
	private String innerCoOrgCode;//����-����Ĺ�����������
	private String innerCoOrgId;//����-��������ID
	private String innerCoHandlerCode;//����-����Ĺ��������˴��룬��Ӧ���չ�˾�ڲ�Ա��
	private String innerCoHandler2Code;//����-����Ĺ���������2����
	private Double leaderTotalPremium;//������������
	private Double leaderTotalPremiumLocal;//����
	private Double ourShare;//ռȫ������
	private List<CoInsuranceInsurer> coInsuranceInsurerList;//�������뷽��Ϣ
	private Map<String,String> tempData;//��ʱ����
	private Long sequenceNumber;// ���
	
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	public String getAgreementName() {
		return agreementName;
	}
	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getBusinessSourceCode() {
		return businessSourceCode;
	}
	public void setBusinessSourceCode(String businessSourceCode) {
		this.businessSourceCode = businessSourceCode;
	}
	public String getBusinessSource2Code() {
		return businessSource2Code;
	}
	public void setBusinessSource2Code(String businessSource2Code) {
		this.businessSource2Code = businessSource2Code;
	}
	public String getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}
	public String getInnerCoOrgCode() {
		return innerCoOrgCode;
	}
	public void setInnerCoOrgCode(String innerCoOrgCode) {
		this.innerCoOrgCode = innerCoOrgCode;
	}
	public String getInnerCoHandlerCode() {
		return innerCoHandlerCode;
	}
	public void setInnerCoHandlerCode(String innerCoHandlerCode) {
		this.innerCoHandlerCode = innerCoHandlerCode;
	}
	public String getInnerCoHandler2Code() {
		return innerCoHandler2Code;
	}
	public void setInnerCoHandler2Code(String innerCoHandler2Code) {
		this.innerCoHandler2Code = innerCoHandler2Code;
	}
	public Double getLeaderTotalPremium() {
		return leaderTotalPremium;
	}
	public void setLeaderTotalPremium(Double leaderTotalPremium) {
		this.leaderTotalPremium = leaderTotalPremium;
	}
	public Double getLeaderTotalPremiumLocal() {
		return leaderTotalPremiumLocal;
	}
	public void setLeaderTotalPremiumLocal(Double leaderTotalPremiumLocal) {
		this.leaderTotalPremiumLocal = leaderTotalPremiumLocal;
	}
	public Double getOurShare() {
		return ourShare;
	}
	public void setOurShare(Double ourShare) {
		this.ourShare = ourShare;
	}
	public List<CoInsuranceInsurer> getCoInsuranceInsurerList() {
		return coInsuranceInsurerList;
	}
	public void setCoInsuranceInsurerList(
			List<CoInsuranceInsurer> coInsuranceInsurerList) {
		this.coInsuranceInsurerList = coInsuranceInsurerList;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public String getIssueOrgCode() {
		return issueOrgCode;
	}
	public void setIssueOrgCode(String issueOrgCode) {
		this.issueOrgCode = issueOrgCode;
	}
	public String getShareRate() {
		return shareRate;
	}
	public void setShareRate(String shareRate) {
		this.shareRate = shareRate;
	}
	public String getHandlerCode() {
		return handlerCode;
	}
	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode;
	}
	public String getHandlerId() {
		return handlerId;
	}
	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}
	public String getInnerCoHandlerId() {
		return innerCoHandlerId;
	}
	public void setInnerCoHandlerId(String innerCoHandlerId) {
		this.innerCoHandlerId = innerCoHandlerId;
	}
	public String getInnerCoHandler2Id() {
		return innerCoHandler2Id;
	}
	public void setInnerCoHandler2Id(String innerCoHandler2Id) {
		this.innerCoHandler2Id = innerCoHandler2Id;
	}
	public String getIssueOrgId() {
		return issueOrgId;
	}
	public void setIssueOrgId(String issueOrgId) {
		this.issueOrgId = issueOrgId;
	}
	public String getIssueUserId() {
		return issueUserId;
	}
	public void setIssueUserId(String issueUserId) {
		this.issueUserId = issueUserId;
	}
	public String getInnerCoOrgId() {
		return innerCoOrgId;
	}
	public void setInnerCoOrgId(String innerCoOrgId) {
		this.innerCoOrgId = innerCoOrgId;
	}
	

}
