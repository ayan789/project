package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;

public class PolicyPaymentInfo {
	private String accountHolderIdNo;// �˻�������֤����
	private String accountHolderIdType;// �˻�������֤������
	private String accountHolderName;// �˻�����������
	private String accountNo;// �������˺�
	private String accountType;// �˻�����
	private String bankCode;// ������/���д���
	private String chequeNo;// ֧Ʊ��
	private String comments;// ��ע��Ϣ/���
	private String depositeBankCode;// ���������ƴ���
	private int installmentPeriodCount;// ������
	private String isCustomerInMasterPolicy;// �Ƿ�ͻ���Ϣ��������������
	private String isInstallment;// �Ƿ���ڱ��
	private String isReceiveSms;// �Ƿ���ն���(Y/N)
	private int payModeCode;// ���ʽ
	private Double payRate;// ������������ڶ��˹�ͬ�����
	private Long payerPayeeCustId;// �ո����˵Ŀͻ�ID
	private Long payerPayeeCustomerSeq;// �ո����˵Ŀͻ����
	private String paymentCurrencyCode;// ֧������
	private String regionCode;// �������루��������ʡ������
	private String swiftNo;// SWIFT��
	private String lastUwId;// ���˱���ԱId
	private String isPrintClause;// �Ƿ��������
	private String isHaveOverseaDesc;// �Ƿ��к���˵����
	private String isNeedSpecialApprove;// �Ƿ���Ҫ����(Y/N)
	private String isMergePrint;// �Ƿ�ϲ���ӡ
	private List<Installment> installmentList;
	private Map<String, String> tempData; // ��ʱ����
	
	public String getAccountHolderIdNo() {
		return accountHolderIdNo;
	}
	public void setAccountHolderIdNo(String accountHolderIdNo) {
		this.accountHolderIdNo = accountHolderIdNo;
	}
	public String getAccountHolderIdType() {
		return accountHolderIdType;
	}
	public void setAccountHolderIdType(String accountHolderIdType) {
		this.accountHolderIdType = accountHolderIdType;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDepositeBankCode() {
		return depositeBankCode;
	}
	public void setDepositeBankCode(String depositeBankCode) {
		this.depositeBankCode = depositeBankCode;
	}
	public int getInstallmentPeriodCount() {
		return installmentPeriodCount;
	}
	public void setInstallmentPeriodCount(int installmentPeriodCount) {
		this.installmentPeriodCount = installmentPeriodCount;
	}
	public String getIsCustomerInMasterPolicy() {
		return isCustomerInMasterPolicy;
	}
	public void setIsCustomerInMasterPolicy(String isCustomerInMasterPolicy) {
		this.isCustomerInMasterPolicy = isCustomerInMasterPolicy;
	}
	public String getIsInstallment() {
		return isInstallment;
	}
	public void setIsInstallment(String isInstallment) {
		this.isInstallment = isInstallment;
	}
	public String getIsReceiveSms() {
		return isReceiveSms;
	}
	public void setIsReceiveSms(String isReceiveSms) {
		this.isReceiveSms = isReceiveSms;
	}
	public int getPayModeCode() {
		return payModeCode;
	}
	public void setPayModeCode(int payModeCode) {
		this.payModeCode = payModeCode;
	}
	public Double getPayRate() {
		return payRate;
	}
	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}
	public Long getPayerPayeeCustId() {
		return payerPayeeCustId;
	}
	public void setPayerPayeeCustId(Long payerPayeeCustId) {
		this.payerPayeeCustId = payerPayeeCustId;
	}
	public Long getPayerPayeeCustomerSeq() {
		return payerPayeeCustomerSeq;
	}
	public void setPayerPayeeCustomerSeq(Long payerPayeeCustomerSeq) {
		this.payerPayeeCustomerSeq = payerPayeeCustomerSeq;
	}
	public String getPaymentCurrencyCode() {
		return paymentCurrencyCode;
	}
	public void setPaymentCurrencyCode(String paymentCurrencyCode) {
		this.paymentCurrencyCode = paymentCurrencyCode;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getSwiftNo() {
		return swiftNo;
	}
	public void setSwiftNo(String swiftNo) {
		this.swiftNo = swiftNo;
	}
	public String getLastUwId() {
		return lastUwId;
	}
	public void setLastUwId(String lastUwId) {
		this.lastUwId = lastUwId;
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
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public List<Installment> getInstallmentList() {
		return installmentList;
	}
	public void setInstallmentList(List<Installment> installmentList) {
		this.installmentList = installmentList;
	}

	

}
