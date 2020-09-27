package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;



/**
 * Ͷ����������Ϣ
 * @author Administrator
 *
 */
public class PolicyCustomer {
	private String accountName                ;//����/����-�˻�����
	private String accountNo                  ;//����/����-�����˺�
	private String addInsuredInsuredRelaCode  ;//�������������뱻�����˹�ϵ
	private String address                    ;//����/����-��ַ
	private String antiMoneyOfUnitNatureCode  ;//��λ���ʣ���ϴǮר�ã�
	private String antiMoneyResultCode        ;//��ϴǮ���������н�������ڲ��ķ�ϴǮϵͳ���أ�0:δ���У�1�����У����ʱ����̺��������̶���д�룩
	private String authAgentContactMode       ;//����/����-������/��������ϵ��ʽ
	private String authAgentIdNo              ;//����/����-������/������֤������
	private String authAgentIdType            ;//����/����-������/������֤������
	private String authAgentIdentity          ;//����-���������
	private String authAgentName              ;//����/����-������/����������
	private String authAgentType              ;//����/����-������/����������
	private String authAgentValidThru         ;//����/����-������/��������Чֹ��
	private String bankCode                   ;//����/����-�����д���
	private String bearStatus                 ;//����״��
	private String branchNatureCode           ;//��λ����
	private String businessLicenseNo          ;//��Ӫ���֤/Ӫҵִ�պ���
	private String businessLicenseValidStart  ;//Ӫҵִ����Ч������
	private String businessLicenseValidThru   ;//Ӫҵִ����Ч��(ֹ��)
	private String businessScope              ;//��Ӫ��Χ
	private String customerName               ;//�ͻ�����/��λ����
	private String customerRiskType           ;//�ͻ��������ͣ�181000��VIP����181001����ϴǮ������181002�����պ�����181003�����ú�����181004����������
	private String customerRoleCode           ;//�ͻ���ɫ����(Ͷ����/�����ˡ�����/�ؼ���ϵ��)
	private String customerNo                 ;//�ͻ���/�ͻ�����
	private String educationCode              ;//�����̶�
	private String email                      ;//����/����-�ʼ���ַ
	private String idCheckResultCode          ;//֤�������/֤���˲����
	private String idNo                       ;//����/����-֤����
	private String idType                     ;//֤�����(���ˡ�����)
	private String idValidStart               ;//�������֤��Ч������(ֹ��)
	private String idValidThru                ;//����/����-֤����Ч��(ֹ��)
	private String indiAge                    	  ;//����
	private String indiDateOfBirth            ;//����-��������
	private String indiGenderCode             ;//����-�Ա�
	private String indiIdAddress              ;//����-���֤����סַ
	private String indiIdGatherCaptcha        ;//�����������֤�ɼ���֤��
	private String indiIdIssuingAuthority     ;//����-���֤ǩ������
	private String indiJobStatus              ;//����-��ҵ���
	private String indiJobTitle               ;//����-ְ��
	private String indiMobile                 ;//����-�ֻ���
	private String indiNation                 ;//����-����
	private String indiOccupationCode         ;//����-ְҵ����
	private String indiOccupationType         ;//����-ְҵ���
	private String industryCategory           ;//��ҵһ��/�����������,�����ײ����
	private String insuredVehicleRelaCode     ;//�������복����ϵ
	private String invoiceType                ;//��Ʊ����
	private String isAuthAgentProcedure       ;//����/����-������/������ҵ�������Ƿ���ȫ,Y/N
	private String isCollectIdInfo            ;//�Ƿ���ݲɼ�
	private String isContactPolHolder         ;//�Ƿ�ؼ���������Ͷ����Ϊͬһ��
	private String isIdValidLongTerm          ;//֤���Ƿ�����Ч
	private String isOnJob                    ;//��ְ��־
	private String isReceiveSms               ;//�Ƿ���ն���(Y/N)
	private String locationCountryCode        ;//���ڹ���
	private String maritalStatus              ;//����״��
	private String medicalInsuranceCode       ;//ҽ����ʶ
	private Double monthSalaryIncome          ;//�¹�������
	private String namePinyin                 ;//�ͻ����ƣ�ƴ����
	private String nationalEconomyCate        ;//���񾭼���ҵ���࣬����������/����/����/С�࣬�洢��С���࣬ʹ�ù������
	private String nationalityCode            ;//����/����-����
	private String orgAuthAgentIdNo           ;//����-��Ȩ������֤������
	private String orgAuthAgentIdType         ;//����-��Ȩ������֤������
	private String orgAuthAgentIdValidThru    ;//����-��Ȩ������֤��������Ч��(ֹ��)
	private String orgAuthAgentName           ;//����-��Ȩ������
	private String orgContactAddress          ;//����-��ϵ�˵�ַ
	private String orgContactEmail            ;//����-��ϵ������
	private String orgContactFax              ;//����-��ϵ�˴���
	private String orgContactPersonName       ;//����-��ϵ������
	private String orgContactPersonTel        ;//����-��ϵ�˵绰
	private String orgLegalReprIdNo           ;//����-���˴���/������֤������
	private String orgLegalReprIdType         ;//����-���˴���/������֤������
	private String orgLegalReprIdValidStart   ;//���˴���/������֤����Ч������
	private String orgLegalReprIdValidThru    ;//����-���˴���/������֤����Ч��(ֹ��)
	private String orgLegalReprJob            ;//����-���˴���/������ְ��
	private String orgLegalReprName           ;//����-���˴���/������
	private String orgMobile                  ;//����-�ƶ��绰
	private String orgNatureOfUnitCode        ;//����-��λ����
	private String orgRegistrationAddress     ;//����-��λע���ַ
	private String orgRegistrationName        ;//����-��λע������
	private String orgRegistrationTel         ;//����-��λע��绰
	private String orgShareholderIdNo         ;//����-�عɹɶ�/ʵ�ʿ�����֤������
	private String orgShareholderIdType       ;//����-�عɹɶ�/ʵ�ʿ�����֤������
	private String orgShareholderIdValidStart ;//�عɹɶ�/ʵ�ʿ�����֤����Ч������
	private String orgShareholderIdValidThru  ;//����-�عɹɶ�/ʵ�ʿ�����֤����Ч��(ֹ��)
	private String orgShareholderName         ;//����-�عɹɶ�/ʵ�ʿ�����
	private String orgShareholderStructureCode;//����-��Ȩ����
	private String orgSocialSecurityRegNo     ;//����-�籣�ǼǺ�
	private String orgTaxNo                   ;//����-��˰��ʶ���
	private String orgTaxOrgName              ;//����-��˰�˵�λ����
	private String parttimeOccupationCode     ;//��ְְҵ
	private String partyCategory              ;//����/����-����������/�ͻ����ʣ�����/����
	private String permanentAddress           ;//��פ��ַ
	private String polHolderInsuredRelaCode   ;//Ͷ�����뱻���˹�ϵ
	private String postCode                   ;//����/����-�ʱ�
	private String organizationCode           ;//��֯�������루�ⲿ�������룬����Ҫ���
	private String residenceNationalityCode   ;//ס������
	private String shareholderType            ;//�ɶ�����
	private String socialSecurityNo           ;//�籣����
	private String studentType                ;//ѧ������/����ְ��
	private String studyAbroadCountryCode     ;//��ѧ���һ����
	private String studyAbroadOrgName         ;//��ѧ���һ����������������
	private String taxRegistAddress           ;//˰��Ǽǵ�ַ
	private String taxRegistryPhone           ;//˰��Ǽǵ绰����
	private String tel                        ;//����/����-��ϵ�绰���̶��绰
	private Long sequenceNumber;//���
	private Map<String,String> tempData; //��ʱ����
	private String contactPersonName;		//��ϵ������
	private String contactPersonTel;		//��ϵ�˵绰
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAddInsuredInsuredRelaCode() {
		return addInsuredInsuredRelaCode;
	}
	public void setAddInsuredInsuredRelaCode(String addInsuredInsuredRelaCode) {
		this.addInsuredInsuredRelaCode = addInsuredInsuredRelaCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAntiMoneyOfUnitNatureCode() {
		return antiMoneyOfUnitNatureCode;
	}
	public void setAntiMoneyOfUnitNatureCode(String antiMoneyOfUnitNatureCode) {
		this.antiMoneyOfUnitNatureCode = antiMoneyOfUnitNatureCode;
	}
	public String getAntiMoneyResultCode() {
		return antiMoneyResultCode;
	}
	public void setAntiMoneyResultCode(String antiMoneyResultCode) {
		this.antiMoneyResultCode = antiMoneyResultCode;
	}
	public String getAuthAgentContactMode() {
		return authAgentContactMode;
	}
	public void setAuthAgentContactMode(String authAgentContactMode) {
		this.authAgentContactMode = authAgentContactMode;
	}
	public String getAuthAgentIdNo() {
		return authAgentIdNo;
	}
	public void setAuthAgentIdNo(String authAgentIdNo) {
		this.authAgentIdNo = authAgentIdNo;
	}
	public String getAuthAgentIdType() {
		return authAgentIdType;
	}
	public void setAuthAgentIdType(String authAgentIdType) {
		this.authAgentIdType = authAgentIdType;
	}
	public String getAuthAgentIdentity() {
		return authAgentIdentity;
	}
	public void setAuthAgentIdentity(String authAgentIdentity) {
		this.authAgentIdentity = authAgentIdentity;
	}
	public String getAuthAgentName() {
		return authAgentName;
	}
	public void setAuthAgentName(String authAgentName) {
		this.authAgentName = authAgentName;
	}
	public String getAuthAgentType() {
		return authAgentType;
	}
	public void setAuthAgentType(String authAgentType) {
		this.authAgentType = authAgentType;
	}
	public String getAuthAgentValidThru() {
		return authAgentValidThru;
	}
	public void setAuthAgentValidThru(String authAgentValidThru) {
		this.authAgentValidThru = authAgentValidThru;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBearStatus() {
		return bearStatus;
	}
	public void setBearStatus(String bearStatus) {
		this.bearStatus = bearStatus;
	}
	public String getBranchNatureCode() {
		return branchNatureCode;
	}
	public void setBranchNatureCode(String branchNatureCode) {
		this.branchNatureCode = branchNatureCode;
	}
	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}
	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}
	public String getBusinessLicenseValidStart() {
		return businessLicenseValidStart;
	}
	public void setBusinessLicenseValidStart(String businessLicenseValidStart) {
		this.businessLicenseValidStart = businessLicenseValidStart;
	}
	public String getBusinessLicenseValidThru() {
		return businessLicenseValidThru;
	}
	public void setBusinessLicenseValidThru(String businessLicenseValidThru) {
		this.businessLicenseValidThru = businessLicenseValidThru;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerRiskType() {
		return customerRiskType;
	}
	public void setCustomerRiskType(String customerRiskType) {
		this.customerRiskType = customerRiskType;
	}
	public String getCustomerRoleCode() {
		return customerRoleCode;
	}
	public void setCustomerRoleCode(String customerRoleCode) {
		this.customerRoleCode = customerRoleCode;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getEducationCode() {
		return educationCode;
	}
	public void setEducationCode(String educationCode) {
		this.educationCode = educationCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdCheckResultCode() {
		return idCheckResultCode;
	}
	public void setIdCheckResultCode(String idCheckResultCode) {
		this.idCheckResultCode = idCheckResultCode;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdValidStart() {
		return idValidStart;
	}
	public void setIdValidStart(String idValidStart) {
		this.idValidStart = idValidStart;
	}
	public String getIdValidThru() {
		return idValidThru;
	}
	public void setIdValidThru(String idValidThru) {
		this.idValidThru = idValidThru;
	}
	public String getIndiAge() {
		return indiAge;
	}
	public void setIndiAge(String indiAge) {
		this.indiAge = indiAge;
	}
	public String getIndiDateOfBirth() {
		return indiDateOfBirth;
	}
	public void setIndiDateOfBirth(String indiDateOfBirth) {
		this.indiDateOfBirth = indiDateOfBirth;
	}
	public String getIndiGenderCode() {
		return indiGenderCode;
	}
	public void setIndiGenderCode(String indiGenderCode) {
		this.indiGenderCode = indiGenderCode;
	}
	public String getIndiIdAddress() {
		return indiIdAddress;
	}
	public void setIndiIdAddress(String indiIdAddress) {
		this.indiIdAddress = indiIdAddress;
	}
	public String getIndiIdGatherCaptcha() {
		return indiIdGatherCaptcha;
	}
	public void setIndiIdGatherCaptcha(String indiIdGatherCaptcha) {
		this.indiIdGatherCaptcha = indiIdGatherCaptcha;
	}
	public String getIndiIdIssuingAuthority() {
		return indiIdIssuingAuthority;
	}
	public void setIndiIdIssuingAuthority(String indiIdIssuingAuthority) {
		this.indiIdIssuingAuthority = indiIdIssuingAuthority;
	}
	public String getIndiJobStatus() {
		return indiJobStatus;
	}
	public void setIndiJobStatus(String indiJobStatus) {
		this.indiJobStatus = indiJobStatus;
	}
	public String getIndiJobTitle() {
		return indiJobTitle;
	}
	public void setIndiJobTitle(String indiJobTitle) {
		this.indiJobTitle = indiJobTitle;
	}
	public String getIndiMobile() {
		return indiMobile;
	}
	public void setIndiMobile(String indiMobile) {
		this.indiMobile = indiMobile;
	}
	public String getIndiNation() {
		return indiNation;
	}
	public void setIndiNation(String indiNation) {
		this.indiNation = indiNation;
	}
	public String getIndiOccupationCode() {
		return indiOccupationCode;
	}
	public void setIndiOccupationCode(String indiOccupationCode) {
		this.indiOccupationCode = indiOccupationCode;
	}
	public String getIndiOccupationType() {
		return indiOccupationType;
	}
	public void setIndiOccupationType(String indiOccupationType) {
		this.indiOccupationType = indiOccupationType;
	}
	public String getIndustryCategory() {
		return industryCategory;
	}
	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}
	public String getInsuredVehicleRelaCode() {
		return insuredVehicleRelaCode;
	}
	public void setInsuredVehicleRelaCode(String insuredVehicleRelaCode) {
		this.insuredVehicleRelaCode = insuredVehicleRelaCode;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getIsAuthAgentProcedure() {
		return isAuthAgentProcedure;
	}
	public void setIsAuthAgentProcedure(String isAuthAgentProcedure) {
		this.isAuthAgentProcedure = isAuthAgentProcedure;
	}
	public String getIsCollectIdInfo() {
		return isCollectIdInfo;
	}
	public void setIsCollectIdInfo(String isCollectIdInfo) {
		this.isCollectIdInfo = isCollectIdInfo;
	}
	public String getIsContactPolHolder() {
		return isContactPolHolder;
	}
	public void setIsContactPolHolder(String isContactPolHolder) {
		this.isContactPolHolder = isContactPolHolder;
	}
	public String getIsIdValidLongTerm() {
		return isIdValidLongTerm;
	}
	public void setIsIdValidLongTerm(String isIdValidLongTerm) {
		this.isIdValidLongTerm = isIdValidLongTerm;
	}
	public String getIsOnJob() {
		return isOnJob;
	}
	public void setIsOnJob(String isOnJob) {
		this.isOnJob = isOnJob;
	}
	public String getIsReceiveSms() {
		return isReceiveSms;
	}
	public void setIsReceiveSms(String isReceiveSms) {
		this.isReceiveSms = isReceiveSms;
	}
	public String getLocationCountryCode() {
		return locationCountryCode;
	}
	public void setLocationCountryCode(String locationCountryCode) {
		this.locationCountryCode = locationCountryCode;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getMedicalInsuranceCode() {
		return medicalInsuranceCode;
	}
	public void setMedicalInsuranceCode(String medicalInsuranceCode) {
		this.medicalInsuranceCode = medicalInsuranceCode;
	}
	public Double getMonthSalaryIncome() {
		return monthSalaryIncome;
	}
	public void setMonthSalaryIncome(Double monthSalaryIncome) {
		this.monthSalaryIncome = monthSalaryIncome;
	}
	public String getNamePinyin() {
		return namePinyin;
	}
	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin;
	}
	public String getNationalEconomyCate() {
		return nationalEconomyCate;
	}
	public void setNationalEconomyCate(String nationalEconomyCate) {
		this.nationalEconomyCate = nationalEconomyCate;
	}
	public String getNationalityCode() {
		return nationalityCode;
	}
	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}
	public String getOrgAuthAgentIdNo() {
		return orgAuthAgentIdNo;
	}
	public void setOrgAuthAgentIdNo(String orgAuthAgentIdNo) {
		this.orgAuthAgentIdNo = orgAuthAgentIdNo;
	}
	public String getOrgAuthAgentIdType() {
		return orgAuthAgentIdType;
	}
	public void setOrgAuthAgentIdType(String orgAuthAgentIdType) {
		this.orgAuthAgentIdType = orgAuthAgentIdType;
	}
	public String getOrgAuthAgentIdValidThru() {
		return orgAuthAgentIdValidThru;
	}
	public void setOrgAuthAgentIdValidThru(String orgAuthAgentIdValidThru) {
		this.orgAuthAgentIdValidThru = orgAuthAgentIdValidThru;
	}
	public String getOrgAuthAgentName() {
		return orgAuthAgentName;
	}
	public void setOrgAuthAgentName(String orgAuthAgentName) {
		this.orgAuthAgentName = orgAuthAgentName;
	}
	public String getOrgContactAddress() {
		return orgContactAddress;
	}
	public void setOrgContactAddress(String orgContactAddress) {
		this.orgContactAddress = orgContactAddress;
	}
	public String getOrgContactEmail() {
		return orgContactEmail;
	}
	public void setOrgContactEmail(String orgContactEmail) {
		this.orgContactEmail = orgContactEmail;
	}
	public String getOrgContactFax() {
		return orgContactFax;
	}
	public void setOrgContactFax(String orgContactFax) {
		this.orgContactFax = orgContactFax;
	}
	public String getOrgContactPersonName() {
		return orgContactPersonName;
	}
	public void setOrgContactPersonName(String orgContactPersonName) {
		this.orgContactPersonName = orgContactPersonName;
	}
	public String getOrgContactPersonTel() {
		return orgContactPersonTel;
	}
	public void setOrgContactPersonTel(String orgContactPersonTel) {
		this.orgContactPersonTel = orgContactPersonTel;
	}
	public String getOrgLegalReprIdNo() {
		return orgLegalReprIdNo;
	}
	public void setOrgLegalReprIdNo(String orgLegalReprIdNo) {
		this.orgLegalReprIdNo = orgLegalReprIdNo;
	}
	public String getOrgLegalReprIdType() {
		return orgLegalReprIdType;
	}
	public void setOrgLegalReprIdType(String orgLegalReprIdType) {
		this.orgLegalReprIdType = orgLegalReprIdType;
	}
	public String getOrgLegalReprIdValidStart() {
		return orgLegalReprIdValidStart;
	}
	public void setOrgLegalReprIdValidStart(String orgLegalReprIdValidStart) {
		this.orgLegalReprIdValidStart = orgLegalReprIdValidStart;
	}
	public String getOrgLegalReprIdValidThru() {
		return orgLegalReprIdValidThru;
	}
	public void setOrgLegalReprIdValidThru(String orgLegalReprIdValidThru) {
		this.orgLegalReprIdValidThru = orgLegalReprIdValidThru;
	}
	public String getOrgLegalReprJob() {
		return orgLegalReprJob;
	}
	public void setOrgLegalReprJob(String orgLegalReprJob) {
		this.orgLegalReprJob = orgLegalReprJob;
	}
	public String getOrgLegalReprName() {
		return orgLegalReprName;
	}
	public void setOrgLegalReprName(String orgLegalReprName) {
		this.orgLegalReprName = orgLegalReprName;
	}
	public String getOrgMobile() {
		return orgMobile;
	}
	public void setOrgMobile(String orgMobile) {
		this.orgMobile = orgMobile;
	}
	public String getOrgNatureOfUnitCode() {
		return orgNatureOfUnitCode;
	}
	public void setOrgNatureOfUnitCode(String orgNatureOfUnitCode) {
		this.orgNatureOfUnitCode = orgNatureOfUnitCode;
	}
	public String getOrgRegistrationAddress() {
		return orgRegistrationAddress;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getContactPersonTel() {
		return contactPersonTel;
	}
	public void setContactPersonTel(String contactPersonTel) {
		this.contactPersonTel = contactPersonTel;
	}
	public void setOrgRegistrationAddress(String orgRegistrationAddress) {
		this.orgRegistrationAddress = orgRegistrationAddress;
	}
	public String getOrgRegistrationName() {
		return orgRegistrationName;
	}
	public void setOrgRegistrationName(String orgRegistrationName) {
		this.orgRegistrationName = orgRegistrationName;
	}
	public String getOrgRegistrationTel() {
		return orgRegistrationTel;
	}
	public void setOrgRegistrationTel(String orgRegistrationTel) {
		this.orgRegistrationTel = orgRegistrationTel;
	}
	public String getOrgShareholderIdNo() {
		return orgShareholderIdNo;
	}
	public void setOrgShareholderIdNo(String orgShareholderIdNo) {
		this.orgShareholderIdNo = orgShareholderIdNo;
	}
	public String getOrgShareholderIdType() {
		return orgShareholderIdType;
	}
	public void setOrgShareholderIdType(String orgShareholderIdType) {
		this.orgShareholderIdType = orgShareholderIdType;
	}
	public String getOrgShareholderIdValidStart() {
		return orgShareholderIdValidStart;
	}
	public void setOrgShareholderIdValidStart(String orgShareholderIdValidStart) {
		this.orgShareholderIdValidStart = orgShareholderIdValidStart;
	}
	public String getOrgShareholderIdValidThru() {
		return orgShareholderIdValidThru;
	}
	public void setOrgShareholderIdValidThru(String orgShareholderIdValidThru) {
		this.orgShareholderIdValidThru = orgShareholderIdValidThru;
	}
	public String getOrgShareholderName() {
		return orgShareholderName;
	}
	public void setOrgShareholderName(String orgShareholderName) {
		this.orgShareholderName = orgShareholderName;
	}
	public String getOrgShareholderStructureCode() {
		return orgShareholderStructureCode;
	}
	public void setOrgShareholderStructureCode(String orgShareholderStructureCode) {
		this.orgShareholderStructureCode = orgShareholderStructureCode;
	}
	public String getOrgSocialSecurityRegNo() {
		return orgSocialSecurityRegNo;
	}
	public void setOrgSocialSecurityRegNo(String orgSocialSecurityRegNo) {
		this.orgSocialSecurityRegNo = orgSocialSecurityRegNo;
	}
	public String getOrgTaxNo() {
		return orgTaxNo;
	}
	public void setOrgTaxNo(String orgTaxNo) {
		this.orgTaxNo = orgTaxNo;
	}
	public String getOrgTaxOrgName() {
		return orgTaxOrgName;
	}
	public void setOrgTaxOrgName(String orgTaxOrgName) {
		this.orgTaxOrgName = orgTaxOrgName;
	}
	public String getParttimeOccupationCode() {
		return parttimeOccupationCode;
	}
	public void setParttimeOccupationCode(String parttimeOccupationCode) {
		this.parttimeOccupationCode = parttimeOccupationCode;
	}
	public String getPartyCategory() {
		return partyCategory;
	}
	public void setPartyCategory(String partyCategory) {
		this.partyCategory = partyCategory;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getPolHolderInsuredRelaCode() {
		return polHolderInsuredRelaCode;
	}
	public void setPolHolderInsuredRelaCode(String polHolderInsuredRelaCode) {
		this.polHolderInsuredRelaCode = polHolderInsuredRelaCode;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	public String getResidenceNationalityCode() {
		return residenceNationalityCode;
	}
	public void setResidenceNationalityCode(String residenceNationalityCode) {
		this.residenceNationalityCode = residenceNationalityCode;
	}
	public String getShareholderType() {
		return shareholderType;
	}
	public void setShareholderType(String shareholderType) {
		this.shareholderType = shareholderType;
	}
	public String getSocialSecurityNo() {
		return socialSecurityNo;
	}
	public void setSocialSecurityNo(String socialSecurityNo) {
		this.socialSecurityNo = socialSecurityNo;
	}
	public String getStudentType() {
		return studentType;
	}
	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}
	public String getStudyAbroadCountryCode() {
		return studyAbroadCountryCode;
	}
	public void setStudyAbroadCountryCode(String studyAbroadCountryCode) {
		this.studyAbroadCountryCode = studyAbroadCountryCode;
	}
	public String getStudyAbroadOrgName() {
		return studyAbroadOrgName;
	}
	public void setStudyAbroadOrgName(String studyAbroadOrgName) {
		this.studyAbroadOrgName = studyAbroadOrgName;
	}
	public String getTaxRegistAddress() {
		return taxRegistAddress;
	}
	public void setTaxRegistAddress(String taxRegistAddress) {
		this.taxRegistAddress = taxRegistAddress;
	}
	public String getTaxRegistryPhone() {
		return taxRegistryPhone;
	}
	public void setTaxRegistryPhone(String taxRegistryPhone) {
		this.taxRegistryPhone = taxRegistryPhone;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}

	
}
