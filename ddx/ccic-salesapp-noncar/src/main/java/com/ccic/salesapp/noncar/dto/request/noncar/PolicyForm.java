package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;

/**
 * �ر�Լ��/������Ϣ
 * 
 * @author Administrator
 *
 */
public class PolicyForm {
	private String customFormContent;// �Զ�����������
	private String customFormDescription;// �Զ�����������
	private String customFormLevelCode;// �Զ��������
	private String customFormNo;// �Զ��������
	private String customFormTitle;// �Զ����������
	private String customFormType;// �Զ�����������
	private String effectiveDate;// ������/��ʼ��Ч����,֧��ʱ����
	private String expiryDate;// �ձ����ڣ�����ֹ��/ֹ������/ʧЧ���ڣ����������ʱ�������
	private String formCode;// �������
	private String formType;// ��������
	private String formVariables;// �������
	private String insuredCount;// Ͷ������
	private String isCustomForm;// �Ƿ��Զ�������
	private String numberOfCopies;// ����
	private String policyStatus;// ����״̬��1δ��Ч2����Ч3ʧЧ4������ʱɾ��״̬��
	private Double premium;// ����/���շ�
	private String productElementCode;// ��ƷԪ�ش���
	private String productElementId;// ��ƷԪ��ID
	private String totalNumberOfCopies;// �ܷ������ŵ�ʱʹ�ã�=����*����
	private Double vatRate;// ��ֵ˰��
	private Long sequenceNumber;//���
	private Map<String, String> tempData;// ��ʱ����
	
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getCustomFormContent() {
		return customFormContent;
	}
	public void setCustomFormContent(String customFormContent) {
		this.customFormContent = customFormContent;
	}
	public String getCustomFormDescription() {
		return customFormDescription;
	}
	public void setCustomFormDescription(String customFormDescription) {
		this.customFormDescription = customFormDescription;
	}
	public String getCustomFormLevelCode() {
		return customFormLevelCode;
	}
	public void setCustomFormLevelCode(String customFormLevelCode) {
		this.customFormLevelCode = customFormLevelCode;
	}
	public String getCustomFormNo() {
		return customFormNo;
	}
	public void setCustomFormNo(String customFormNo) {
		this.customFormNo = customFormNo;
	}
	public String getCustomFormTitle() {
		return customFormTitle;
	}
	public void setCustomFormTitle(String customFormTitle) {
		this.customFormTitle = customFormTitle;
	}
	public String getCustomFormType() {
		return customFormType;
	}
	public void setCustomFormType(String customFormType) {
		this.customFormType = customFormType;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getFormCode() {
		return formCode;
	}
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public String getFormVariables() {
		return formVariables;
	}
	public void setFormVariables(String formVariables) {
		this.formVariables = formVariables;
	}
	public String getInsuredCount() {
		return insuredCount;
	}
	public void setInsuredCount(String insuredCount) {
		this.insuredCount = insuredCount;
	}
	public String getIsCustomForm() {
		return isCustomForm;
	}
	public void setIsCustomForm(String isCustomForm) {
		this.isCustomForm = isCustomForm;
	}
	public String getNumberOfCopies() {
		return numberOfCopies;
	}
	public void setNumberOfCopies(String numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public Double getPremium() {
		return premium;
	}
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	public String getProductElementCode() {
		return productElementCode;
	}
	public void setProductElementCode(String productElementCode) {
		this.productElementCode = productElementCode;
	}
	public String getProductElementId() {
		return productElementId;
	}
	public void setProductElementId(String productElementId) {
		this.productElementId = productElementId;
	}
	public String getTotalNumberOfCopies() {
		return totalNumberOfCopies;
	}
	public void setTotalNumberOfCopies(String totalNumberOfCopies) {
		this.totalNumberOfCopies = totalNumberOfCopies;
	}
	public Double getVatRate() {
		return vatRate;
	}
	public void setVatRate(Double vatRate) {
		this.vatRate = vatRate;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

}
