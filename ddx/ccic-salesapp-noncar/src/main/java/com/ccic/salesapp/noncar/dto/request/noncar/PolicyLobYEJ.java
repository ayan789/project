package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;

/**
 * ��Ʒ��Ϣ---������
 * @author Administrator
 *
 */
public class PolicyLobYEJ{
	private Double additionRatio;// �ӳɱ����������ڻ����գ�
	private String airInsurerBranch;// ��������
	private String billNumber;// �ᵥ�ţ������ڻ����գ�
	private String cargoCount;// ��װ��������ҳ��¼������ı���
	private String cargoGoods;// ���ջ�����Ŀ�������ڻ����գ�
	private String cargoIssueDate;// ǩ������-�����ڻ�����
	private String cargoMarks;// ��ͷ���
	private String contractNo;// ��ͬ�ţ������ڻ����գ�
	private Double contractPrice;// ��ͬ�۸�
	private String creditNumber;// ����֤�ţ��������䱣�գ�
	private String effectiveDatePrint;// �������ڴ�ӡ
	private String invoiceAmount;// ��Ʊ����
	private String invoiceCurrencyCode;// ��Ʊ����
	private String invoiceNumber;// ��Ʊ�ţ������ڻ����գ�
	private String isModifiedClause;// �Ƿ��޸����DC:�Ƿ��޸���Э����Լ���ĳб�������
	private String isModifiedDeductInfo;// �Ƿ��޸�������Ϣ��DC:�Ƿ��޸���Э����Լ���ĳб�������
	private String isModifiedForm;// �Ƿ��޸��ر�Լ����DC:�Ƿ��޸���Э����Լ���ĳб�������
	private String originalCount;// ��������
	private String priceCondition;// �۸������������ڻ����գ�
	private Double duePremium;// Ӧ�ձ���/��ʵ����/RealPremium,=��������
	private String eachPersonCopies;// ÿ��Ͷ������
	private String effectiveDate;// ������/��ʼ��Ч����,֧��ʱ����
	private String expiryDate;// �ձ����ڣ�����ֹ��/ֹ������/ʧЧ���ڣ����������ʱ�������
	private String mainClauseId;// ��������ID�����ڷǳ����ƷͳһΪ������ѡ������
	private String mainCoverageId;// ��������ID�����ڷǳ����ƷͳһΪ������ѡ�����������µ�����
	private String otherInsuredCondition;// �����б�����
	private String planCode;// ��������
	private String policyStatus;// ����״̬��1δ��Ч2����Ч3ʧЧ4������ʱɾ��״̬��
	private String productCode;// ��Ʒ����
	private String proposalType;// Ͷ����ʽ
	private Double sumInsured;// ����
	private String sumInsuredModeCode;// ����ȷ����ʽ\���պ��ַ�ʽȷ�����ս��
	private String totalNumberOfCopies;// �ܷ���
	private String totalPeopleNumber;// ������
	private Double vat;// VAT���(��ֵ˰)
	private Double vatRate;// ��ֵ˰��
	private Map <String,String> tempData;// ��ʱ����
	private List<PolicyRisk> policyRiskList;// �����Ϣ
	private List<SharedCoverageGroup> sharedCoverageGroupList;// ����������Ϣ
	private List<PolicyForm> policyFormList;// �ر�Լ����Ϣ
	private List<PolicyDeductible> policyDeductibleList;// ����ģ����Ϣ
	private List<LimitDeductible> limitDeductibleList;// ���������޶�������Ϣ
	private List<CargoAgreement> cargoAgreementList;// ������ԤԼЭ��-������Ϣ
	private List<CargoTransport> cargoTransportList;// ������Ϣ
	private List<Voyage> voyageList;// ������Ϣ
	private List<VoyageTransit> voyageTransitList;// ת����Ϣ
	public Double getAdditionRatio() {
		return additionRatio;
	}
	public void setAdditionRatio(Double additionRatio) {
		this.additionRatio = additionRatio;
	}
	public String getAirInsurerBranch() {
		return airInsurerBranch;
	}
	public void setAirInsurerBranch(String airInsurerBranch) {
		this.airInsurerBranch = airInsurerBranch;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getCargoCount() {
		return cargoCount;
	}
	public void setCargoCount(String cargoCount) {
		this.cargoCount = cargoCount;
	}
	public String getCargoGoods() {
		return cargoGoods;
	}
	public void setCargoGoods(String cargoGoods) {
		this.cargoGoods = cargoGoods;
	}
	public String getCargoIssueDate() {
		return cargoIssueDate;
	}
	public void setCargoIssueDate(String cargoIssueDate) {
		this.cargoIssueDate = cargoIssueDate;
	}
	public String getCargoMarks() {
		return cargoMarks;
	}
	public void setCargoMarks(String cargoMarks) {
		this.cargoMarks = cargoMarks;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public Double getContractPrice() {
		return contractPrice;
	}
	public void setContractPrice(Double contractPrice) {
		this.contractPrice = contractPrice;
	}
	public String getCreditNumber() {
		return creditNumber;
	}
	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}
	public String getEffectiveDatePrint() {
		return effectiveDatePrint;
	}
	public void setEffectiveDatePrint(String effectiveDatePrint) {
		this.effectiveDatePrint = effectiveDatePrint;
	}
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getInvoiceCurrencyCode() {
		return invoiceCurrencyCode;
	}
	public void setInvoiceCurrencyCode(String invoiceCurrencyCode) {
		this.invoiceCurrencyCode = invoiceCurrencyCode;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getIsModifiedClause() {
		return isModifiedClause;
	}
	public void setIsModifiedClause(String isModifiedClause) {
		this.isModifiedClause = isModifiedClause;
	}
	public String getIsModifiedDeductInfo() {
		return isModifiedDeductInfo;
	}
	public void setIsModifiedDeductInfo(String isModifiedDeductInfo) {
		this.isModifiedDeductInfo = isModifiedDeductInfo;
	}
	public String getIsModifiedForm() {
		return isModifiedForm;
	}
	public void setIsModifiedForm(String isModifiedForm) {
		this.isModifiedForm = isModifiedForm;
	}
	public String getOriginalCount() {
		return originalCount;
	}
	public void setOriginalCount(String originalCount) {
		this.originalCount = originalCount;
	}
	public String getPriceCondition() {
		return priceCondition;
	}
	public void setPriceCondition(String priceCondition) {
		this.priceCondition = priceCondition;
	}
	public Double getDuePremium() {
		return duePremium;
	}
	public void setDuePremium(Double duePremium) {
		this.duePremium = duePremium;
	}
	public String getEachPersonCopies() {
		return eachPersonCopies;
	}
	public void setEachPersonCopies(String eachPersonCopies) {
		this.eachPersonCopies = eachPersonCopies;
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
	public String getMainClauseId() {
		return mainClauseId;
	}
	public void setMainClauseId(String mainClauseId) {
		this.mainClauseId = mainClauseId;
	}
	public String getMainCoverageId() {
		return mainCoverageId;
	}
	public void setMainCoverageId(String mainCoverageId) {
		this.mainCoverageId = mainCoverageId;
	}
	public String getOtherInsuredCondition() {
		return otherInsuredCondition;
	}
	public void setOtherInsuredCondition(String otherInsuredCondition) {
		this.otherInsuredCondition = otherInsuredCondition;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProposalType() {
		return proposalType;
	}
	public void setProposalType(String proposalType) {
		this.proposalType = proposalType;
	}
	public Double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getSumInsuredModeCode() {
		return sumInsuredModeCode;
	}
	public void setSumInsuredModeCode(String sumInsuredModeCode) {
		this.sumInsuredModeCode = sumInsuredModeCode;
	}
	public String getTotalNumberOfCopies() {
		return totalNumberOfCopies;
	}
	public void setTotalNumberOfCopies(String totalNumberOfCopies) {
		this.totalNumberOfCopies = totalNumberOfCopies;
	}
	public String getTotalPeopleNumber() {
		return totalPeopleNumber;
	}
	public void setTotalPeopleNumber(String totalPeopleNumber) {
		this.totalPeopleNumber = totalPeopleNumber;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
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
	public List<PolicyRisk> getPolicyRiskList() {
		return policyRiskList;
	}
	public void setPolicyRiskList(List<PolicyRisk> policyRiskList) {
		this.policyRiskList = policyRiskList;
	}
	public List<SharedCoverageGroup> getSharedCoverageGroupList() {
		return sharedCoverageGroupList;
	}
	public void setSharedCoverageGroupList(
			List<SharedCoverageGroup> sharedCoverageGroupList) {
		this.sharedCoverageGroupList = sharedCoverageGroupList;
	}
	public List<PolicyForm> getPolicyFormList() {
		return policyFormList;
	}
	public void setPolicyFormList(List<PolicyForm> policyFormList) {
		this.policyFormList = policyFormList;
	}
	public List<PolicyDeductible> getPolicyDeductibleList() {
		return policyDeductibleList;
	}
	public void setPolicyDeductibleList(List<PolicyDeductible> policyDeductibleList) {
		this.policyDeductibleList = policyDeductibleList;
	}
	public List<LimitDeductible> getLimitDeductibleList() {
		return limitDeductibleList;
	}
	public void setLimitDeductibleList(List<LimitDeductible> limitDeductibleList) {
		this.limitDeductibleList = limitDeductibleList;
	}
	public List<CargoAgreement> getCargoAgreementList() {
		return cargoAgreementList;
	}
	public void setCargoAgreementList(List<CargoAgreement> cargoAgreementList) {
		this.cargoAgreementList = cargoAgreementList;
	}
	public List<CargoTransport> getCargoTransportList() {
		return cargoTransportList;
	}
	public void setCargoTransportList(List<CargoTransport> cargoTransportList) {
		this.cargoTransportList = cargoTransportList;
	}
	public List<Voyage> getVoyageList() {
		return voyageList;
	}
	public void setVoyageList(List<Voyage> voyageList) {
		this.voyageList = voyageList;
	}
	public List<VoyageTransit> getVoyageTransitList() {
		return voyageTransitList;
	}
	public void setVoyageTransitList(List<VoyageTransit> voyageTransitList) {
		this.voyageTransitList = voyageTransitList;
	}

}
