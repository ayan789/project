package com.ccic.salesapp.noncar.dto.request.noncar;


/**
 * ����Լ������Ϣ
 * @author Administrator
 *
 */
public class NewbizInfo {
	private String curUwLevelCode;// ��ǰ�˱�����
	private String initUwLevelCode;//��ʼ�˱����𣨽���Ա��ֺ˱���
	private String completeUwDate;// ��ɺ˱�����
	private String uwNote;//         �˱�ע����Ϣ
	private String agencyQueryNo;//  �н���Ϣ��ѯ��
	private String quotationDate;//  ѯ������
	private String uwOpinion;//      �˱����
	private String keyProjectCode;// �ؼ���Ŀ
	private String autoUwResult;//   �Զ��˱����(1�˻��޸�/2�˱���ͨ��/3�˱�ͨ��)
	private String currUwId;//       ��ǰ�˱���ԱID
	private String uwDecisionCode;// �˱���������
	private String proposalCancelDate;//      Ͷ��������ʱ��
	private String proposalRejectReasonCode;//Ͷ�����ܾ�ԭ��
	private String proposalCancelOperId;//    Ͷ����������Ա
	private String proposalDescription;//     Ͷ������
	private String proposalRejectDesc;//      Ͷ�����ܾ�ԭ������
	private String proposalComments;//        Ͷ����ע
	private String proposalCreateDate;//      Ͷ��������ʱ��
	private String sendUwDate;//              �ύ�˱�����
	private String firstDataEntryDate;//      �״�¼��ʱ��
	private String indiIdGatherCaptcha;//     �����������֤�ɼ���֤��
	private String specialApproveApplyNotes;//�������뱸ע
	private String quoteRatingExpiryDate;//   ѯ�۵����۹���ʱ��
	private String proposalStatus;//          Ͷ����״̬
	private String proposalSentDate;//        Ͷ�������͸��ͻ���ʱ��
	private String quotationExpiryDate;//     ѯ�۵�����ʱ��
	private String indiIdCardReaderNo;//      ���֤���������
	private String logoForPrintCode;//        ��ӡ��Ʒ��LOGO
	private String isSeparationPlatform;//    �Ƿ�����ƽ̨
	private String isSaveRatingDetail;//      �Ƿ񱣴汣�Ѽ�����ϸ������Ϣ
	private String nextUwLevelCode;//         ��һ���˱����𣨽���Ա��ֺ˱���
	private String lastUwId;//                ���˱���ԱId
	private String lastUwOrganId;//           ���˱�����Id
	private String lastUwLevelCode;//         ����/��ߺ˱�����
	private String isAntiMoneyPass;//         �Ƿ�ϴǮ�ͻ�ʶ���Ƿ�ͨ��(Y/N)
	private String lastDataEntryDate;//       ¼��ʱ�䣨��¼���һ���û����Save/�ύ��
	private String isPrintClause;//           �Ƿ��������
	private String isHaveOverseaDesc;//       �Ƿ��к���˵����
	private String isNeedSpecialApprove;//    �Ƿ���Ҫ����(Y/N)
	private String isMergePrint;//            �Ƿ�ϲ���ӡ
	private String sequenceNumber;// ���
	
	
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getCurUwLevelCode() {
		return curUwLevelCode;
	}
	public void setCurUwLevelCode(String curUwLevelCode) {
		this.curUwLevelCode = curUwLevelCode;
	}
	public String getInitUwLevelCode() {
		return initUwLevelCode;
	}
	public void setInitUwLevelCode(String initUwLevelCode) {
		this.initUwLevelCode = initUwLevelCode;
	}
	public String getCompleteUwDate() {
		return completeUwDate;
	}
	public void setCompleteUwDate(String completeUwDate) {
		this.completeUwDate = completeUwDate;
	}
	public String getUwNote() {
		return uwNote;
	}
	public void setUwNote(String uwNote) {
		this.uwNote = uwNote;
	}
	public String getAgencyQueryNo() {
		return agencyQueryNo;
	}
	public void setAgencyQueryNo(String agencyQueryNo) {
		this.agencyQueryNo = agencyQueryNo;
	}
	public String getQuotationDate() {
		return quotationDate;
	}
	public void setQuotationDate(String quotationDate) {
		this.quotationDate = quotationDate;
	}
	public String getUwOpinion() {
		return uwOpinion;
	}
	public void setUwOpinion(String uwOpinion) {
		this.uwOpinion = uwOpinion;
	}
	public String getKeyProjectCode() {
		return keyProjectCode;
	}
	public void setKeyProjectCode(String keyProjectCode) {
		this.keyProjectCode = keyProjectCode;
	}
	public String getAutoUwResult() {
		return autoUwResult;
	}
	public void setAutoUwResult(String autoUwResult) {
		this.autoUwResult = autoUwResult;
	}
	public String getCurrUwId() {
		return currUwId;
	}
	public void setCurrUwId(String currUwId) {
		this.currUwId = currUwId;
	}
	public String getUwDecisionCode() {
		return uwDecisionCode;
	}
	public void setUwDecisionCode(String uwDecisionCode) {
		this.uwDecisionCode = uwDecisionCode;
	}
	public String getProposalCancelDate() {
		return proposalCancelDate;
	}
	public void setProposalCancelDate(String proposalCancelDate) {
		this.proposalCancelDate = proposalCancelDate;
	}
	public String getProposalRejectReasonCode() {
		return proposalRejectReasonCode;
	}
	public void setProposalRejectReasonCode(String proposalRejectReasonCode) {
		this.proposalRejectReasonCode = proposalRejectReasonCode;
	}
	public String getProposalCancelOperId() {
		return proposalCancelOperId;
	}
	public void setProposalCancelOperId(String proposalCancelOperId) {
		this.proposalCancelOperId = proposalCancelOperId;
	}
	public String getProposalDescription() {
		return proposalDescription;
	}
	public void setProposalDescription(String proposalDescription) {
		this.proposalDescription = proposalDescription;
	}
	public String getProposalRejectDesc() {
		return proposalRejectDesc;
	}
	public void setProposalRejectDesc(String proposalRejectDesc) {
		this.proposalRejectDesc = proposalRejectDesc;
	}
	public String getProposalComments() {
		return proposalComments;
	}
	public void setProposalComments(String proposalComments) {
		this.proposalComments = proposalComments;
	}
	public String getProposalCreateDate() {
		return proposalCreateDate;
	}
	public void setProposalCreateDate(String proposalCreateDate) {
		this.proposalCreateDate = proposalCreateDate;
	}
	public String getSendUwDate() {
		return sendUwDate;
	}
	public void setSendUwDate(String sendUwDate) {
		this.sendUwDate = sendUwDate;
	}
	public String getFirstDataEntryDate() {
		return firstDataEntryDate;
	}
	public void setFirstDataEntryDate(String firstDataEntryDate) {
		this.firstDataEntryDate = firstDataEntryDate;
	}
	public String getIndiIdGatherCaptcha() {
		return indiIdGatherCaptcha;
	}
	public void setIndiIdGatherCaptcha(String indiIdGatherCaptcha) {
		this.indiIdGatherCaptcha = indiIdGatherCaptcha;
	}
	public String getSpecialApproveApplyNotes() {
		return specialApproveApplyNotes;
	}
	public void setSpecialApproveApplyNotes(String specialApproveApplyNotes) {
		this.specialApproveApplyNotes = specialApproveApplyNotes;
	}
	public String getQuoteRatingExpiryDate() {
		return quoteRatingExpiryDate;
	}
	public void setQuoteRatingExpiryDate(String quoteRatingExpiryDate) {
		this.quoteRatingExpiryDate = quoteRatingExpiryDate;
	}
	public String getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(String proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	public String getProposalSentDate() {
		return proposalSentDate;
	}
	public void setProposalSentDate(String proposalSentDate) {
		this.proposalSentDate = proposalSentDate;
	}
	public String getQuotationExpiryDate() {
		return quotationExpiryDate;
	}
	public void setQuotationExpiryDate(String quotationExpiryDate) {
		this.quotationExpiryDate = quotationExpiryDate;
	}
	public String getIndiIdCardReaderNo() {
		return indiIdCardReaderNo;
	}
	public void setIndiIdCardReaderNo(String indiIdCardReaderNo) {
		this.indiIdCardReaderNo = indiIdCardReaderNo;
	}
	public String getLogoForPrintCode() {
		return logoForPrintCode;
	}
	public void setLogoForPrintCode(String logoForPrintCode) {
		this.logoForPrintCode = logoForPrintCode;
	}
	public String getIsSeparationPlatform() {
		return isSeparationPlatform;
	}
	public void setIsSeparationPlatform(String isSeparationPlatform) {
		this.isSeparationPlatform = isSeparationPlatform;
	}
	public String getIsSaveRatingDetail() {
		return isSaveRatingDetail;
	}
	public void setIsSaveRatingDetail(String isSaveRatingDetail) {
		this.isSaveRatingDetail = isSaveRatingDetail;
	}
	public String getNextUwLevelCode() {
		return nextUwLevelCode;
	}
	public void setNextUwLevelCode(String nextUwLevelCode) {
		this.nextUwLevelCode = nextUwLevelCode;
	}
	public String getLastUwId() {
		return lastUwId;
	}
	public void setLastUwId(String lastUwId) {
		this.lastUwId = lastUwId;
	}
	public String getLastUwOrganId() {
		return lastUwOrganId;
	}
	public void setLastUwOrganId(String lastUwOrganId) {
		this.lastUwOrganId = lastUwOrganId;
	}
	public String getLastUwLevelCode() {
		return lastUwLevelCode;
	}
	public void setLastUwLevelCode(String lastUwLevelCode) {
		this.lastUwLevelCode = lastUwLevelCode;
	}
	public String getIsAntiMoneyPass() {
		return isAntiMoneyPass;
	}
	public void setIsAntiMoneyPass(String isAntiMoneyPass) {
		this.isAntiMoneyPass = isAntiMoneyPass;
	}
	public String getLastDataEntryDate() {
		return lastDataEntryDate;
	}
	public void setLastDataEntryDate(String lastDataEntryDate) {
		this.lastDataEntryDate = lastDataEntryDate;
	}
	public String getIsPrintClause() {
		return isPrintClause;
	}
	public void setIsPrintClause(String isPrintClause) {
		this.isPrintClause = isPrintClause;
	}
	public String getIsHaveOverseaDesc() {
		return isHaveOverseaDesc;
	}
	public void setIsHaveOverseaDesc(String isHaveOverseaDesc) {
		this.isHaveOverseaDesc = isHaveOverseaDesc;
	}
	public String getIsNeedSpecialApprove() {
		return isNeedSpecialApprove;
	}
	public void setIsNeedSpecialApprove(String isNeedSpecialApprove) {
		this.isNeedSpecialApprove = isNeedSpecialApprove;
	}
	public String getIsMergePrint() {
		return isMergePrint;
	}
	public void setIsMergePrint(String isMergePrint) {
		this.isMergePrint = isMergePrint;
	}


}
