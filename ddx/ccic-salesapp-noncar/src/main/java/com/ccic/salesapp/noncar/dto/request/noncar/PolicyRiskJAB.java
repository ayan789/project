package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;

/**
 * �����Ϣ    �Ҳ���
 * 
 * @author Administrator
 *
 */
public class PolicyRiskJAB{
	private Double duePremium;// Ӧ�ձ���/��ʵ����/RealPremium,=��������
	private String effectiveDate;// ������/��ʼ��Ч����,֧��ʱ����
	private String expiryDate;// �ձ����ڣ�����ֹ��/ֹ������/ʧЧ���ڣ����������ʱ�������
	private String insuredCategory;// ��ķ���
	private String insuredDescription;// �������
	private String insuredGroupSeqNo;// ���η������
	private String insuredName;// ������ƣ��ڲ����࣬����һ������Ӳ�ͬ�ı��������ȡֵ�����ڱ���/��ѯ��
	private String insuredNo;// ��ı��(ƽ̨����)
	private String numberOfCopies;// ����
	private String planCode;// ��������
	private String productElementCode;// ��ƷԪ�ش���
	private String policyStatus;// ����״̬��1δ��Ч2����Ч3ʧЧ4������ʱɾ��״̬��
	private Double premiumRate;// ����
	private Double sumInsured;// ����
	private String totalNumberOfCopies;// �ܷ������ŵ�ʱʹ�ã�=����*����
	private Double vat;// VAT���(��ֵ˰)
	private Double vatRate;// ��ֵ˰��
	private String addressSequenceNumber;// ��ַ���
	private String insuredItemName;// �����Ŀ����
	private String itemCategory;// ��Ŀ���
	private String itemValueBasisCode;// ���ռ�ֵȷ������(����ʱ�����ü�ֵ,����ʱ���������,����ʱ���г���ֵ,����ʱ������ԭֵ,������ֵ)
	private String otherValueBasisDesc;// ������ֵ˵��
	private String sumInsuredBasisCode;// ���ս��ȷ������(����ԭֵ,�������,�����ؽ���,����ԭֵ�ӳ�*%,�������ӳ�*%,�г���,������,��ֵ,��ֵ,����)
	private String wordUnitSecurityEquip;// ����ȷ������˵��
	private Map<String, String> tempData;// ��ʱ����
	private List<PolicyCoverage> policyCoverageList;// ��������Ϣ
	public Double getDuePremium() {
		return duePremium;
	}
	public void setDuePremium(Double duePremium) {
		this.duePremium = duePremium;
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
	public String getInsuredCategory() {
		return insuredCategory;
	}
	public void setInsuredCategory(String insuredCategory) {
		this.insuredCategory = insuredCategory;
	}
	public String getInsuredDescription() {
		return insuredDescription;
	}
	public void setInsuredDescription(String insuredDescription) {
		this.insuredDescription = insuredDescription;
	}
	public String getInsuredGroupSeqNo() {
		return insuredGroupSeqNo;
	}
	public void setInsuredGroupSeqNo(String insuredGroupSeqNo) {
		this.insuredGroupSeqNo = insuredGroupSeqNo;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getInsuredNo() {
		return insuredNo;
	}
	public void setInsuredNo(String insuredNo) {
		this.insuredNo = insuredNo;
	}
	public String getNumberOfCopies() {
		return numberOfCopies;
	}
	public void setNumberOfCopies(String numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public String getProductElementCode() {
		return productElementCode;
	}
	public void setProductElementCode(String productElementCode) {
		this.productElementCode = productElementCode;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public Double getPremiumRate() {
		return premiumRate;
	}
	public void setPremiumRate(Double premiumRate) {
		this.premiumRate = premiumRate;
	}
	public Double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getTotalNumberOfCopies() {
		return totalNumberOfCopies;
	}
	public void setTotalNumberOfCopies(String totalNumberOfCopies) {
		this.totalNumberOfCopies = totalNumberOfCopies;
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
	public String getAddressSequenceNumber() {
		return addressSequenceNumber;
	}
	public void setAddressSequenceNumber(String addressSequenceNumber) {
		this.addressSequenceNumber = addressSequenceNumber;
	}
	public String getInsuredItemName() {
		return insuredItemName;
	}
	public void setInsuredItemName(String insuredItemName) {
		this.insuredItemName = insuredItemName;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getItemValueBasisCode() {
		return itemValueBasisCode;
	}
	public void setItemValueBasisCode(String itemValueBasisCode) {
		this.itemValueBasisCode = itemValueBasisCode;
	}
	public String getOtherValueBasisDesc() {
		return otherValueBasisDesc;
	}
	public void setOtherValueBasisDesc(String otherValueBasisDesc) {
		this.otherValueBasisDesc = otherValueBasisDesc;
	}
	public String getSumInsuredBasisCode() {
		return sumInsuredBasisCode;
	}
	public void setSumInsuredBasisCode(String sumInsuredBasisCode) {
		this.sumInsuredBasisCode = sumInsuredBasisCode;
	}
	public String getWordUnitSecurityEquip() {
		return wordUnitSecurityEquip;
	}
	public void setWordUnitSecurityEquip(String wordUnitSecurityEquip) {
		this.wordUnitSecurityEquip = wordUnitSecurityEquip;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public List<PolicyCoverage> getPolicyCoverageList() {
		return policyCoverageList;
	}
	public void setPolicyCoverageList(List<PolicyCoverage> policyCoverageList) {
		this.policyCoverageList = policyCoverageList;
	}

}
