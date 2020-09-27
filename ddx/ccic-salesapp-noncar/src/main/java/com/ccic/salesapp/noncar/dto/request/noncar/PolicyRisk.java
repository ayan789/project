package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;

/**
 * �����Ϣ
 * @author Administrator
 *
 */	
public class PolicyRisk {
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
	private Double approvalQuality;// �˶�������\��ǣ������(��)
	private String cargoName;// ��������
	private String cargoType;// �������
	private String engineNo;// ��������
	private String licenseNo;// �����ƺ�
	private String packagingCount;// ��װ����
	private String packagingType;// ��װ��ʽ/����
	private String vehicleModelCode;// ����
	private String brandModelName;
	private String vehicleAge;

	public String getVehicleAge() {
		return vehicleAge;
	}
	public void setVehicleAge(String vehicleAge) {
		this.vehicleAge = vehicleAge;
	}

	private String vin;// ���ܺ�
	private String adminDivision;// ��������У԰�������գ�
	private String brand;// Ʒ��
	private String count;// ������ѧ��������}
	private Double deadPremiumRate;// ������������
	private Double estimateSalesAmount;// Ԥ�����۶�
	private String insuredCount;// �����
	private String insuredSubCategory;// ��ķ��࣬��ͬ��Ʒ��Ҫ�Ӹ��Ե�����������
	private String listString;// ����ʱ��
	private String listPlace;// ���еص�
	private Double medicalPremiumRate;// ҽ�Ʒѷ���
	private Double minSponsorFee;// ��ͳ�����������
	private String modelCode;// �ͺŹ�񣬲�ͬ��Ʒ��Ҫ�Ӹ��Ե�����������
	private String otherInsuredCategoryDesc;// ������ķ�������
	private Double price;// ����
	private String productName;// ��Ʒ����\��������
	private String productionCount;// ��������-��������
	private String productionUnit;// ��������-��λ
	private String projectName;// ��������/������/��Ŀ����
	private Double projectTotalCost;// ���������
	private Double raiseAmount;// ļ���ʽ��
	private String refGroupDesc;// �������
	private String refGroupSeq;// ����������
	private String schoolName;// ѧУ���ƣ�У԰�������գ�
	private String schoolNatureCode;// ѧУ����
	private String schoolType;// ѧУ���
	private String sellAreaCode;// ��������
	private Double sponsorFee;// ������������
	private String stockCode;// ��Ʊ����
	private String thirdPartyLiability;// ����������
	private Double unitPremium;// ��λ����
	private String warrantyYears;// �����ʱ�����
	private Double yearSalaryAmount;// �깤�ʶ�
	private String insuredGroupNo;// ���η������
	private String insuredUid;// ���Ψһ��
	private Map<String, String> tempData;// ��ʱ����
	private Long sequenceNumber;//���
	private List<PolicyCoverage> policyCoverageList;// ��������Ϣ
	private List<Vehicle> vehicleList;//������Ϣ
	
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
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
	public Double getApprovalQuality() {
		return approvalQuality;
	}
	public void setApprovalQuality(Double approvalQuality) {
		this.approvalQuality = approvalQuality;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getCargoType() {
		return cargoType;
	}
	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getPackagingCount() {
		return packagingCount;
	}
	public void setPackagingCount(String packagingCount) {
		this.packagingCount = packagingCount;
	}
	public String getPackagingType() {
		return packagingType;
	}
	public void setPackagingType(String packagingType) {
		this.packagingType = packagingType;
	}
	public String getVehicleModelCode() {
		return vehicleModelCode;
	}
	public void setVehicleModelCode(String vehicleModelCode) {
		this.vehicleModelCode = vehicleModelCode;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getAdminDivision() {
		return adminDivision;
	}
	public void setAdminDivision(String adminDivision) {
		this.adminDivision = adminDivision;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public Double getDeadPremiumRate() {
		return deadPremiumRate;
	}
	public void setDeadPremiumRate(Double deadPremiumRate) {
		this.deadPremiumRate = deadPremiumRate;
	}
	public Double getEstimateSalesAmount() {
		return estimateSalesAmount;
	}
	public void setEstimateSalesAmount(Double estimateSalesAmount) {
		this.estimateSalesAmount = estimateSalesAmount;
	}
	public String getInsuredCount() {
		return insuredCount;
	}
	public void setInsuredCount(String insuredCount) {
		this.insuredCount = insuredCount;
	}
	public String getInsuredSubCategory() {
		return insuredSubCategory;
	}
	public void setInsuredSubCategory(String insuredSubCategory) {
		this.insuredSubCategory = insuredSubCategory;
	}
	public String getListString() {
		return listString;
	}
	public void setListString(String listString) {
		this.listString = listString;
	}
	public String getListPlace() {
		return listPlace;
	}
	public void setListPlace(String listPlace) {
		this.listPlace = listPlace;
	}
	public Double getMedicalPremiumRate() {
		return medicalPremiumRate;
	}
	public void setMedicalPremiumRate(Double medicalPremiumRate) {
		this.medicalPremiumRate = medicalPremiumRate;
	}
	public Double getMinSponsorFee() {
		return minSponsorFee;
	}
	public void setMinSponsorFee(Double minSponsorFee) {
		this.minSponsorFee = minSponsorFee;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getOtherInsuredCategoryDesc() {
		return otherInsuredCategoryDesc;
	}
	public void setOtherInsuredCategoryDesc(String otherInsuredCategoryDesc) {
		this.otherInsuredCategoryDesc = otherInsuredCategoryDesc;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductionCount() {
		return productionCount;
	}
	public void setProductionCount(String productionCount) {
		this.productionCount = productionCount;
	}
	public String getProductionUnit() {
		return productionUnit;
	}
	public void setProductionUnit(String productionUnit) {
		this.productionUnit = productionUnit;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Double getProjectTotalCost() {
		return projectTotalCost;
	}
	public void setProjectTotalCost(Double projectTotalCost) {
		this.projectTotalCost = projectTotalCost;
	}
	public Double getRaiseAmount() {
		return raiseAmount;
	}
	public void setRaiseAmount(Double raiseAmount) {
		this.raiseAmount = raiseAmount;
	}
	public String getRefGroupDesc() {
		return refGroupDesc;
	}
	public void setRefGroupDesc(String refGroupDesc) {
		this.refGroupDesc = refGroupDesc;
	}
	public String getRefGroupSeq() {
		return refGroupSeq;
	}
	public void setRefGroupSeq(String refGroupSeq) {
		this.refGroupSeq = refGroupSeq;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolNatureCode() {
		return schoolNatureCode;
	}
	public void setSchoolNatureCode(String schoolNatureCode) {
		this.schoolNatureCode = schoolNatureCode;
	}
	public String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	public String getSellAreaCode() {
		return sellAreaCode;
	}
	public void setSellAreaCode(String sellAreaCode) {
		this.sellAreaCode = sellAreaCode;
	}
	public Double getSponsorFee() {
		return sponsorFee;
	}
	public void setSponsorFee(Double sponsorFee) {
		this.sponsorFee = sponsorFee;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getThirdPartyLiability() {
		return thirdPartyLiability;
	}
	public void setThirdPartyLiability(String thirdPartyLiability) {
		this.thirdPartyLiability = thirdPartyLiability;
	}
	public Double getUnitPremium() {
		return unitPremium;
	}
	public void setUnitPremium(Double unitPremium) {
		this.unitPremium = unitPremium;
	}
	public String getWarrantyYears() {
		return warrantyYears;
	}
	public void setWarrantyYears(String warrantyYears) {
		this.warrantyYears = warrantyYears;
	}
	public Double getYearSalaryAmount() {
		return yearSalaryAmount;
	}
	public void setYearSalaryAmount(Double yearSalaryAmount) {
		this.yearSalaryAmount = yearSalaryAmount;
	}
	public String getInsuredGroupNo() {
		return insuredGroupNo;
	}
	public void setInsuredGroupNo(String insuredGroupNo) {
		this.insuredGroupNo = insuredGroupNo;
	}
	public String getInsuredUid() {
		return insuredUid;
	}
	public void setInsuredUid(String insuredUid) {
		this.insuredUid = insuredUid;
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
	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}
	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}
	public void setBrandModelName(String brandModelName) {
		// TODO Auto-generated method stub
		this.brandModelName = brandModelName;
	}
	
	public String getBrandModelName() {
		return brandModelName;
	}
}
