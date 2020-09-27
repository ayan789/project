package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;
import java.util.Map;


/**
 * ��Ʒ��Ϣ
 * 
 * @author Administrator
 *
 */
public class PolicyLob {
	private String businessLicenseNo;// ��Ӫ���֤/Ӫҵִ�պ���
	private String businessPlace;// Ӫҵ����(��ҵ��Ϣ)
	private String enterpriseNature;// ��ҵ����
	private String enterpriseNatureCode;// ��ҵ����(��ҵ��Ϣ)
	private String industryRisk;// ��������ʧ��ҵ
	private String industryType;// ��ҵ���(�칫��,����ҵ��,��������ҵ)
	private String liftCount;// ��������
	private String riskType;// ��������
	private String homePurposeCode;// ������;����ס�����ޣ�
	private String homeStructure;// ���ݽṹ���ֻ�ṹ��ש��ṹ��שľ�ṹ���ֽṹ�֡��ֽ�������ṹ��ש���ṹ��
	private String homeType;// �������ͣ���ס����ס���á����⡢������
	private String otherDescription;// ����˵��
	private String platformEndoNo;// ƽ̨������
	private String platformPolicyNo;// ƽ̨������
	private String safeProductionLicenseNo;// �������֤�ţ��󳤰�ȫ��������ʸ�֤�ţ�
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
	private Double annualRevenue;// ҵ��������(Ԫ)
	private Double auditOperatingRevenue;// �������ҵ��Ӫ����
	private String autoLiabilityScope;// ���ձ������η�Χ
	private Double avgPremiumRate;// ���ʣ��ϼƣ�
	private String businessNatureCode;// ��������Ӫҵ���ʣ�Ӫҵ���ʣ�
	private String businessNatureDesc;// Ӫҵ��������
	private String businessPlaceCount;// Ӫҵ��������
	private String calcBasis;// ���Ѽ������(DC:ĳ������������ѡ��������ѡ�񣬹��������գ����簴�����)
	private String cargoTypeDesc;// ��������ı���
	private String companySize;// ��˾��ģ����ҵ��ģ/Ͷ���˹�˾��ģ��
	private String compenProject;// �⳥��Ŀ
	private String compensationStandard;// �⳥��׼
	private String continIssueDay;// �����б���
	private String directorCount;// ���������
	private Double estiSumInsured;// ��װ���豸��Ԥ�Ʊ��ս��
	private Double expectedAnnualSalesAmount;// Ԥ�������۶�
	private String extendedReportYears;// ��չ������__��
	private String guaranteeYear;// ��������
	private String industryDetailDesc;// ��ҵ��ϸ����
	private String industryTypeDetail;// ��ҵ����ϸ��
	private String industryTypeFour;// ��ҵ�����ļ�
	private String industryTypeLarge;// ��ҵ���ʹ�
	private String industryTypeMiddle;// ��ҵ������
	private String insuranceArea;// Ͷ���ľ�Ӫ��Χ
	private String insuranceAreaDesc;// ��Ӫ��Χ˵��
	private String insuredAreaCode;// Ͷ������/�б�����Χ
	private String insuredBasisCode;// �б�����
	private String isProposalOverseas;// �Ƿ�Ͷ������ҵ��
	private String issueCompanyCount;// �б���ҵ����
	private String lastYearOperatingRevenue;// ���꾭Ӫ���루��Ԫ��
	private String licenseNo;// ִҵ���֤��
	private String listPlace;// ���еص�
	private Double minPremium;// ��ͱ��շ�
	private String openingDate;// ��ҵ���\����ʱ��
	private Double operatingRevenue;// ��Ӫ����/Ӫҵ��/��������/ҵ��������
	private String organizationalForm;// ��֯��ʽ
	private String otherTypeDesc;// �����������
	private String ownershipSystemCode;// ������
	private String partnerCount;// �ϻ�����
	private String possessNatureCode;// ռ������
	private Double premiumRate;// ���ʣ�������������ڴ�ӡ
	private String purchaseCarType;// �������ͣ��³����й泵�������³����³����й泵���ɳ������ֳ���
	private String qualiAccountantsCount;// ���о���ִҵ�ʸ�Ļ��ʦ����
	private String quotationNoFromPlatform;// ���۵�����Դ�ڹ㶫�ǳ����ƽ̨
	private Double registeredCapital;// ע���ʽ�
	private String retrospectivePeriodStart;// ׷�����ڣ���*��*��*��*ʱ��
	private String retrospectiveYears;// ׷���ڣ�_�ߣ���
	private String riskCount;// ��������
	private String riskCountType;// ������������
	private String riskLevelCode;// ���յȼ�
	private String schoolType;// ѧУ���
	private String sellAreaCode;// ��������
	private String shareholderCount;// �ɶ�����   
	private String square;//�����Ӫҵ�����ƽ���ף�
	private String staffSumCount;// Ա��������
	private String stockNo;// ��Ʊ����
	private Double sumSalesAmount;// �ۼ����۶�
	private Double totalAsset;// ���ʲ�
	private String transportArea;// �������
	private String transportType;// ���䷽ʽ
	private String unitCode;// ��λ���֡������ס��˹���ֹ��
	private String waitYear;// �ȴ��ڣ�����
	private Double yearSalaryAmount;// ���ܹ��ʶ�/�깤�ʶ�
	private String yearYield;// �����
	private Double duePremium;// Ӧ�ձ���/��ʵ����/RealPremium,=��������
	private int eachPersonCopies;// ÿ��Ͷ������
	private String effectiveDate;// ������/��ʼ��Ч����,֧��ʱ����
	private String expiryDate;// �ձ����ڣ�����ֹ��/ֹ������/ʧЧ���ڣ����������ʱ�������
	private String insuredCount;// �������/��������
	private String insuredNatureCode;// �����������ʣ�����ֵ���ӱ�������,������,������,������,������,�����̣��ȣ���ͬ��Ʒʹ����ͬ���������Ʒ������˼�¼��
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
	private String roadInsuredBasisCode; //�б�����(��·Σ�ջ��������������)ҵ��������岻ͬ��Ҫ���֣���jiafaȷ��
	private String empInsuredBasisCode; //�б�����(���ڹ���������)ҵ��������岻ͬ��Ҫ���֣���jiafaȷ��
	private String exhibitionName; //չ������
	private Map<String, String> tempData;// ��ʱ����
	private Long sequenceNumber;//���
	private List<AddressInfo> addressInfoList;// ��ַ��Ϣ
	private List<HomeDetail> homeDetailList;// �����嵥��Ϣ
	private List<NonMotorDetail> nonMotorDetailList;// �ǻ���������Ϣ
	private List<LiftDetail> liftDetailList;// �����嵥��Ϣ
	private List<PolicyRisk> policyRiskList;// �����Ϣ
	private List<CargoAgreement> cargoAgreementList;// ������ԤԼЭ��-������Ϣ
	private List<CargoTransport> cargoTransportList;// ������Ϣ
	private List<Voyage> voyageList;// ������Ϣ
	private List<VoyageTransit> voyageTransitList;// ת����Ϣ
	private List<SharedCoverageGroup> sharedCoverageGroupList;// ����������Ϣ
	private List<PolicyForm> policyFormList;// �ر�Լ����Ϣ
	private List<PolicyDeductible> policyDeductibleList;// ����ģ����Ϣ
	private List<LimitDeductible> limitDeductibleList;// ���������޶�������Ϣ
	private List<Person> personList;// ҽ����Ա��Ϣ/��Ա/���ʦ/���ʦ/��Ա��Ͷ���˵�Ͷ�в������ˡ����������ˡ���ֹ��쵼�Լ��������˵ļ�Ҫ��Ϣ
	private List<MedicalInstitution> medicalInstitutionList;// ҽ�ƻ�����Ϣ
	private List<Ward> wardList;// ���໤���嵥
	private List<MachineDetail> machineDetailList;// �����豸�嵥
	private List<PolicySchoolDetail> policySchoolDetailList;// ѧУ�嵥
	private List<StudentInfo> studentInfoList;// ѧ���嵥
	private List<Patent> patentList;// ר���嵥
	private List<Household> householdList;// �ֻ��嵥
	private List<ProductDetail> productDetailList;// ��Ʒ��Ϣ
	private List<Vehicle> vehicleList;// ����������Ϣ�嵥
	private List<AddressDetail> addressDetailList;// ��ַ��Ϣ
	private List<GradeInfo> gradeInfoList;// �꼶��Ϣ
	private List<SecurityProjectDetail> securityProjectDetailList;// ֤ȯ��������Ŀ��Ϣ
	private List<LawCaseDetail> lawCaseDetailList;// ���ϰ�����Ϣ
	private List<SuperviseeOwner> superviseeOwnerList;// �������������Ϣ
	private List<PersonDetail>personDetailList; //��������Ϣ
	private List<Engineering>engineeringList; //������Ϣ
	private List<PetsModelDtl>petsModelDtlList; //������Ϣ
	
	
	public String getRoadInsuredBasisCode() {
		return roadInsuredBasisCode;
	}
	public void setRoadInsuredBasisCode(String roadInsuredBasisCode) {
		this.roadInsuredBasisCode = roadInsuredBasisCode;
	}
	public String getEmpInsuredBasisCode() {
		return empInsuredBasisCode;
	}
	public void setEmpInsuredBasisCode(String empInsuredBasisCode) {
		this.empInsuredBasisCode = empInsuredBasisCode;
	}
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}
	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}
	public String getBusinessPlace() {
		return businessPlace;
	}
	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}
	public String getEnterpriseNature() {
		return enterpriseNature;
	}
	public void setEnterpriseNature(String enterpriseNature) {
		this.enterpriseNature = enterpriseNature;
	}
	public String getEnterpriseNatureCode() {
		return enterpriseNatureCode;
	}
	public void setEnterpriseNatureCode(String enterpriseNatureCode) {
		this.enterpriseNatureCode = enterpriseNatureCode;
	}
	public String getIndustryRisk() {
		return industryRisk;
	}
	public void setIndustryRisk(String industryRisk) {
		this.industryRisk = industryRisk;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String getLiftCount() {
		return liftCount;
	}
	public void setLiftCount(String liftCount) {
		this.liftCount = liftCount;
	}
	public String getRiskType() {
		return riskType;
	}
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	public String getHomePurposeCode() {
		return homePurposeCode;
	}
	public void setHomePurposeCode(String homePurposeCode) {
		this.homePurposeCode = homePurposeCode;
	}
	public String getHomeStructure() {
		return homeStructure;
	}
	public void setHomeStructure(String homeStructure) {
		this.homeStructure = homeStructure;
	}
	public String getHomeType() {
		return homeType;
	}
	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}
	public String getOtherDescription() {
		return otherDescription;
	}
	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}
	public String getPlatformEndoNo() {
		return platformEndoNo;
	}
	public void setPlatformEndoNo(String platformEndoNo) {
		this.platformEndoNo = platformEndoNo;
	}
	public String getPlatformPolicyNo() {
		return platformPolicyNo;
	}
	public void setPlatformPolicyNo(String platformPolicyNo) {
		this.platformPolicyNo = platformPolicyNo;
	}
	public String getSafeProductionLicenseNo() {
		return safeProductionLicenseNo;
	}
	public void setSafeProductionLicenseNo(String safeProductionLicenseNo) {
		this.safeProductionLicenseNo = safeProductionLicenseNo;
	}
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
	public Double getAnnualRevenue() {
		return annualRevenue;
	}
	public void setAnnualRevenue(Double annualRevenue) {
		this.annualRevenue = annualRevenue;
	}
	public Double getAuditOperatingRevenue() {
		return auditOperatingRevenue;
	}
	public void setAuditOperatingRevenue(Double auditOperatingRevenue) {
		this.auditOperatingRevenue = auditOperatingRevenue;
	}
	public String getAutoLiabilityScope() {
		return autoLiabilityScope;
	}
	public void setAutoLiabilityScope(String autoLiabilityScope) {
		this.autoLiabilityScope = autoLiabilityScope;
	}
	public Double getAvgPremiumRate() {
		return avgPremiumRate;
	}
	public void setAvgPremiumRate(Double avgPremiumRate) {
		this.avgPremiumRate = avgPremiumRate;
	}
	public String getBusinessNatureCode() {
		return businessNatureCode;
	}
	public void setBusinessNatureCode(String businessNatureCode) {
		this.businessNatureCode = businessNatureCode;
	}
	public String getBusinessNatureDesc() {
		return businessNatureDesc;
	}
	public void setBusinessNatureDesc(String businessNatureDesc) {
		this.businessNatureDesc = businessNatureDesc;
	}
	public String getBusinessPlaceCount() {
		return businessPlaceCount;
	}
	public void setBusinessPlaceCount(String businessPlaceCount) {
		this.businessPlaceCount = businessPlaceCount;
	}
	public String getCalcBasis() {
		return calcBasis;
	}
	public void setCalcBasis(String calcBasis) {
		this.calcBasis = calcBasis;
	}
	public String getCargoTypeDesc() {
		return cargoTypeDesc;
	}
	public void setCargoTypeDesc(String cargoTypeDesc) {
		this.cargoTypeDesc = cargoTypeDesc;
	}
	public String getCompanySize() {
		return companySize;
	}
	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}
	public String getCompenProject() {
		return compenProject;
	}
	public void setCompenProject(String compenProject) {
		this.compenProject = compenProject;
	}
	public String getCompensationStandard() {
		return compensationStandard;
	}
	public void setCompensationStandard(String compensationStandard) {
		this.compensationStandard = compensationStandard;
	}
	public String getContinIssueDay() {
		return continIssueDay;
	}
	public void setContinIssueDay(String continIssueDay) {
		this.continIssueDay = continIssueDay;
	}
	public String getDirectorCount() {
		return directorCount;
	}
	public void setDirectorCount(String directorCount) {
		this.directorCount = directorCount;
	}
	public Double getEstiSumInsured() {
		return estiSumInsured;
	}
	public void setEstiSumInsured(Double estiSumInsured) {
		this.estiSumInsured = estiSumInsured;
	}
	public Double getExpectedAnnualSalesAmount() {
		return expectedAnnualSalesAmount;
	}
	public void setExpectedAnnualSalesAmount(Double expectedAnnualSalesAmount) {
		this.expectedAnnualSalesAmount = expectedAnnualSalesAmount;
	}
	public String getExtendedReportYears() {
		return extendedReportYears;
	}
	public void setExtendedReportYears(String extendedReportYears) {
		this.extendedReportYears = extendedReportYears;
	}
	public String getGuaranteeYear() {
		return guaranteeYear;
	}
	public void setGuaranteeYear(String guaranteeYear) {
		this.guaranteeYear = guaranteeYear;
	}
	public String getIndustryDetailDesc() {
		return industryDetailDesc;
	}
	public void setIndustryDetailDesc(String industryDetailDesc) {
		this.industryDetailDesc = industryDetailDesc;
	}
	public String getIndustryTypeDetail() {
		return industryTypeDetail;
	}
	public void setIndustryTypeDetail(String industryTypeDetail) {
		this.industryTypeDetail = industryTypeDetail;
	}
	public String getIndustryTypeFour() {
		return industryTypeFour;
	}
	public void setIndustryTypeFour(String industryTypeFour) {
		this.industryTypeFour = industryTypeFour;
	}
	public String getIndustryTypeLarge() {
		return industryTypeLarge;
	}
	public void setIndustryTypeLarge(String industryTypeLarge) {
		this.industryTypeLarge = industryTypeLarge;
	}
	public String getIndustryTypeMiddle() {
		return industryTypeMiddle;
	}
	public void setIndustryTypeMiddle(String industryTypeMiddle) {
		this.industryTypeMiddle = industryTypeMiddle;
	}
	public String getInsuranceArea() {
		return insuranceArea;
	}
	public void setInsuranceArea(String insuranceArea) {
		this.insuranceArea = insuranceArea;
	}
	public String getInsuranceAreaDesc() {
		return insuranceAreaDesc;
	}
	public void setInsuranceAreaDesc(String insuranceAreaDesc) {
		this.insuranceAreaDesc = insuranceAreaDesc;
	}
	public String getInsuredAreaCode() {
		return insuredAreaCode;
	}
	public void setInsuredAreaCode(String insuredAreaCode) {
		this.insuredAreaCode = insuredAreaCode;
	}
	public String getInsuredBasisCode() {
		return insuredBasisCode;
	}
	public void setInsuredBasisCode(String insuredBasisCode) {
		this.insuredBasisCode = insuredBasisCode;
	}
	public String getIsProposalOverseas() {
		return isProposalOverseas;
	}
	public void setIsProposalOverseas(String isProposalOverseas) {
		this.isProposalOverseas = isProposalOverseas;
	}
	public String getIssueCompanyCount() {
		return issueCompanyCount;
	}
	public void setIssueCompanyCount(String issueCompanyCount) {
		this.issueCompanyCount = issueCompanyCount;
	}
	public String getLastYearOperatingRevenue() {
		return lastYearOperatingRevenue;
	}
	public void setLastYearOperatingRevenue(String lastYearOperatingRevenue) {
		this.lastYearOperatingRevenue = lastYearOperatingRevenue;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getListPlace() {
		return listPlace;
	}
	public void setListPlace(String listPlace) {
		this.listPlace = listPlace;
	}
	public Double getMinPremium() {
		return minPremium;
	}
	public void setMinPremium(Double minPremium) {
		this.minPremium = minPremium;
	}
	public String getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}
	public Double getOperatingRevenue() {
		return operatingRevenue;
	}
	public void setOperatingRevenue(Double operatingRevenue) {
		this.operatingRevenue = operatingRevenue;
	}
	public String getOrganizationalForm() {
		return organizationalForm;
	}
	public void setOrganizationalForm(String organizationalForm) {
		this.organizationalForm = organizationalForm;
	}
	public String getOtherTypeDesc() {
		return otherTypeDesc;
	}
	public void setOtherTypeDesc(String otherTypeDesc) {
		this.otherTypeDesc = otherTypeDesc;
	}
	public String getOwnershipSystemCode() {
		return ownershipSystemCode;
	}
	public void setOwnershipSystemCode(String ownershipSystemCode) {
		this.ownershipSystemCode = ownershipSystemCode;
	}
	public String getPartnerCount() {
		return partnerCount;
	}
	public void setPartnerCount(String partnerCount) {
		this.partnerCount = partnerCount;
	}
	public String getPossessNatureCode() {
		return possessNatureCode;
	}
	public void setPossessNatureCode(String possessNatureCode) {
		this.possessNatureCode = possessNatureCode;
	}
	public Double getPremiumRate() {
		return premiumRate;
	}
	public void setPremiumRate(Double premiumRate) {
		this.premiumRate = premiumRate;
	}
	public String getPurchaseCarType() {
		return purchaseCarType;
	}
	public void setPurchaseCarType(String purchaseCarType) {
		this.purchaseCarType = purchaseCarType;
	}
	public String getQualiAccountantsCount() {
		return qualiAccountantsCount;
	}
	public void setQualiAccountantsCount(String qualiAccountantsCount) {
		this.qualiAccountantsCount = qualiAccountantsCount;
	}
	public String getQuotationNoFromPlatform() {
		return quotationNoFromPlatform;
	}
	public void setQuotationNoFromPlatform(String quotationNoFromPlatform) {
		this.quotationNoFromPlatform = quotationNoFromPlatform;
	}
	public Double getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(Double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getRetrospectivePeriodStart() {
		return retrospectivePeriodStart;
	}
	public void setRetrospectivePeriodStart(String retrospectivePeriodStart) {
		this.retrospectivePeriodStart = retrospectivePeriodStart;
	}
	public String getRetrospectiveYears() {
		return retrospectiveYears;
	}
	public void setRetrospectiveYears(String retrospectiveYears) {
		this.retrospectiveYears = retrospectiveYears;
	}
	public String getRiskCount() {
		return riskCount;
	}
	public void setRiskCount(String riskCount) {
		this.riskCount = riskCount;
	}
	public String getRiskCountType() {
		return riskCountType;
	}
	public void setRiskCountType(String riskCountType) {
		this.riskCountType = riskCountType;
	}
	public String getRiskLevelCode() {
		return riskLevelCode;
	}
	public void setRiskLevelCode(String riskLevelCode) {
		this.riskLevelCode = riskLevelCode;
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
	public String getShareholderCount() {
		return shareholderCount;
	}
	public void setShareholderCount(String shareholderCount) {
		this.shareholderCount = shareholderCount;
	}
	public String getSquare() {
		return square;
	}
	public void setSquare(String square) {
		this.square = square;
	}
	public String getStaffSumCount() {
		return staffSumCount;
	}
	public void setStaffSumCount(String staffSumCount) {
		this.staffSumCount = staffSumCount;
	}
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public Double getSumSalesAmount() {
		return sumSalesAmount;
	}
	public void setSumSalesAmount(Double sumSalesAmount) {
		this.sumSalesAmount = sumSalesAmount;
	}
	public Double getTotalAsset() {
		return totalAsset;
	}
	public void setTotalAsset(Double totalAsset) {
		this.totalAsset = totalAsset;
	}
	public String getTransportArea() {
		return transportArea;
	}
	public void setTransportArea(String transportArea) {
		this.transportArea = transportArea;
	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getWaitYear() {
		return waitYear;
	}
	public void setWaitYear(String waitYear) {
		this.waitYear = waitYear;
	}
	public Double getYearSalaryAmount() {
		return yearSalaryAmount;
	}
	public void setYearSalaryAmount(Double yearSalaryAmount) {
		this.yearSalaryAmount = yearSalaryAmount;
	}
	public String getYearYield() {
		return yearYield;
	}
	public void setYearYield(String yearYield) {
		this.yearYield = yearYield;
	}
	public Double getDuePremium() {
		return duePremium;
	}
	public void setDuePremium(Double duePremium) {
		this.duePremium = duePremium;
	}
	public int getEachPersonCopies() {
		return eachPersonCopies;
	}
	public void setEachPersonCopies(int eachPersonCopies) {
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
	public String getInsuredCount() {
		return insuredCount;
	}
	public void setInsuredCount(String insuredCount) {
		this.insuredCount = insuredCount;
	}
	public String getInsuredNatureCode() {
		return insuredNatureCode;
	}
	public void setInsuredNatureCode(String insuredNatureCode) {
		this.insuredNatureCode = insuredNatureCode;
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
	public List<AddressInfo> getAddressInfoList() {
		return addressInfoList;
	}
	public void setAddressInfoList(List<AddressInfo> addressInfoList) {
		this.addressInfoList = addressInfoList;
	}
	public List<HomeDetail> getHomeDetailList() {
		return homeDetailList;
	}
	public void setHomeDetailList(List<HomeDetail> homeDetailList) {
		this.homeDetailList = homeDetailList;
	}
	
	public List<NonMotorDetail> getNonMotorDetailList() {
		return nonMotorDetailList;
	}
	public void setNonMotorDetailList(List<NonMotorDetail> nonMotorDetailList) {
		this.nonMotorDetailList = nonMotorDetailList;
	}
	public List<LiftDetail> getLiftDetailList() {
		return liftDetailList;
	}
	public void setLiftDetailList(List<LiftDetail> liftDetailList) {
		this.liftDetailList = liftDetailList;
	}
	public List<PolicyRisk> getPolicyRiskList() {
		return policyRiskList;
	}
	public void setPolicyRiskList(List<PolicyRisk> policyRiskList) {
		this.policyRiskList = policyRiskList;
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
	public List<Person> getPersonList() {
		return personList;
	}
	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	public List<MedicalInstitution> getMedicalInstitutionList() {
		return medicalInstitutionList;
	}
	public void setMedicalInstitutionList(
			List<MedicalInstitution> medicalInstitutionList) {
		this.medicalInstitutionList = medicalInstitutionList;
	}
	public List<Ward> getWardList() {
		return wardList;
	}
	public void setWardList(List<Ward> wardList) {
		this.wardList = wardList;
	}
	public List<MachineDetail> getMachineDetailList() {
		return machineDetailList;
	}
	public void setMachineDetailList(List<MachineDetail> machineDetailList) {
		this.machineDetailList = machineDetailList;
	}
	public List<PolicySchoolDetail> getPolicySchoolDetailList() {
		return policySchoolDetailList;
	}
	public void setPolicySchoolDetailList(
			List<PolicySchoolDetail> policySchoolDetailList) {
		this.policySchoolDetailList = policySchoolDetailList;
	}
	public List<StudentInfo> getStudentInfoList() {
		return studentInfoList;
	}
	public void setStudentInfoList(List<StudentInfo> studentInfoList) {
		this.studentInfoList = studentInfoList;
	}
	public List<Patent> getPatentList() {
		return patentList;
	}
	public void setPatentList(List<Patent> patentList) {
		this.patentList = patentList;
	}
	public List<Household> getHouseholdList() {
		return householdList;
	}
	public void setHouseholdList(List<Household> householdList) {
		this.householdList = householdList;
	}
	public List<ProductDetail> getProductDetailList() {
		return productDetailList;
	}
	public void setProductDetailList(List<ProductDetail> productDetailList) {
		this.productDetailList = productDetailList;
	}
	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}
	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}
	public List<AddressDetail> getAddressDetailList() {
		return addressDetailList;
	}
	public void setAddressDetailList(List<AddressDetail> addressDetailList) {
		this.addressDetailList = addressDetailList;
	}
	public List<GradeInfo> getGradeInfoList() {
		return gradeInfoList;
	}
	public void setGradeInfoList(List<GradeInfo> gradeInfoList) {
		this.gradeInfoList = gradeInfoList;
	}
	public List<SecurityProjectDetail> getSecurityProjectDetailList() {
		return securityProjectDetailList;
	}
	public void setSecurityProjectDetailList(
			List<SecurityProjectDetail> securityProjectDetailList) {
		this.securityProjectDetailList = securityProjectDetailList;
	}
	public List<LawCaseDetail> getLawCaseDetailList() {
		return lawCaseDetailList;
	}
	public void setLawCaseDetailList(List<LawCaseDetail> lawCaseDetailList) {
		this.lawCaseDetailList = lawCaseDetailList;
	}
	public List<SuperviseeOwner> getSuperviseeOwnerList() {
		return superviseeOwnerList;
	}
	public void setSuperviseeOwnerList(List<SuperviseeOwner> superviseeOwnerList) {
		this.superviseeOwnerList = superviseeOwnerList;
	}
	public List<PersonDetail> getPersonDetailList() {
		return personDetailList;
	}
	public void setPersonDetailList(List<PersonDetail> personDetailList) {
		this.personDetailList = personDetailList;
	}
	public List<Engineering> getEngineeringList() {
		return engineeringList;
	}
	public void setEngineeringList(List<Engineering> engineeringList) {
		this.engineeringList = engineeringList;
	}
	public String getExhibitionName() {
		return exhibitionName;
	}
	public void setExhibitionName(String exhibitionName) {
		this.exhibitionName = exhibitionName;
	}
	public List<PetsModelDtl> getPetsModelDtlList() {
		return petsModelDtlList;
	}
	public void setPetsModelDtlList(List<PetsModelDtl> petsModelDtlList) {
		this.petsModelDtlList = petsModelDtlList;
	}
	
	
}