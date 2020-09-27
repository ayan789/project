package com.ccic.salesapp.performance.domain.vo.dto;

import java.util.Date;

public class OrderListEntity {
	private String id;
	private String operatorEmpno;//����Ա����
    private String startDate;//��������
    private String endDate;//����ֹ��
    private String quotationMode;//����ģʽ 
    private String status;//����״̬
    private String licenseNo;//���ƺ�
    private String applicantName;//Ͷ��������
    
    private String minStartDate;//����������
    private String maxStartDate;//������ֹ��
    
	private String orderNo;//������
	private String proposalNo;//Ͷ������
	private String policyNo;//������
	private String operateDate;//��������
	private String payApplyNo;//֧����
	private String identityCode;//֧����֤��
	private Double sumPremium;//���ձ���
	private Double sumTax;//����˰
	private String riskCode;//����
	private String jointSales;//�������۱�ʶ
	private String EYZapplicationNo;//������Ͷ������
	private String EYZpolicyNo;//�����ձ�����
	private Double EYZpremium;//�����ձ���
	private String YEJapplicationNo;//�泵��Ͷ������
	private String YEJpolicyNo;//�泵�ձ�����
	private Double YEJpremium;//�泵�ձ���
	private String WAEapplicationNo;//������Ͷ������
	private String WAEpolicyNo ;//�����ձ�����
	private Double WAEpremium;//�����ձ���
	private String insuredName;//������������
	private String businessNature;//ҵ����Դ
	private String agentCode;//�����˴���
	private String isSuccess;//�Ƿ񱨼۳ɹ�

	private String currentDate;//��ѯ�·�
	private String platformFlag;//ƽ̨��ʶ  1-�»�����
	private String nclUserCode;//�»��û���ʶ
	private Double commisionamntVci;//��ҵ��Ӷ��  �»�
	private Double commisionamntTci;//��ǿ��Ӷ��  �»�
	private Double managementfeeVci;//��ҵ�������� �»�
	private Double managementfeeTci;//��ǿ�������� �»�
	private Double premiumTci;
	private Double premiumVci;
	private String comeCode; //��������
	private String appliyInsuredNature; //Ͷ���˵�����
	private String appliyIdType; //Ͷ����֤������
	private String insuredNature;//�� ���˵�����
	private String insuredIdType; //������֤������
	private String machineCode;//��������
	private String querySequenceNo;//��ǿ�ղ�ѯ��
	private String querySequenceBzNo;//��ҵ�ղ�ѯ��
    private String electronicInsurance ;//�Ƿ��ǵ���Ͷ��     
    private String machinecodeStatus;//��֤��
    
    private String carOwnerNature;//��������
    private String carUseNatureCode;//����ʹ������
    private String carKindCode;//��������
    private String seatCount;//��λ��
    private Date applyMessageDate;//������֤������ʱ��
    private String quotationType;//�������� 1-����2-����3���̻챣
    private String projectCode;//��Ŀ����
    private String huataiPaymentStatus;//��̩��������״̬
    private String huataiPaymentStatusName;//��̩��������״̬

	private String newcoreFlag;//�º��ĳ�����־ 1�����º���
	
	private Date effectDate;//������
	private String carOwner;
	   
	public String getCarOwner() {
		return carOwner;
	}
	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMachinecodeStatus() {
		return machinecodeStatus;
	}
	public void setMachinecodeStatus(String machinecodeStatus) {
		this.machinecodeStatus = machinecodeStatus;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getQuerySequenceNo() {
		return querySequenceNo;
	}
	public void setQuerySequenceNo(String querySequenceNo) {
		this.querySequenceNo = querySequenceNo;
	}
	public String getQuerySequenceBzNo() {
		return querySequenceBzNo;
	}
	public void setQuerySequenceBzNo(String querySequenceBzNo) {
		this.querySequenceBzNo = querySequenceBzNo;
	}
	public String getElectronicInsurance() {
		return electronicInsurance;
	}
	public void setElectronicInsurance(String electronicInsurance) {
		this.electronicInsurance = electronicInsurance;
	}
	public String getAppliyInsuredNature() {
		return appliyInsuredNature;
	}
	public void setAppliyInsuredNature(String appliyInsuredNature) {
		this.appliyInsuredNature = appliyInsuredNature;
	}
	public String getInsuredNature() {
		return insuredNature;
	}
	public void setInsuredNature(String insuredNature) {
		this.insuredNature = insuredNature;
	}
	public String getOperatorEmpno() {
		return operatorEmpno;
	}
	public void setOperatorEmpno(String operatorEmpno) {
		this.operatorEmpno = operatorEmpno;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getQuotationMode() {
		return quotationMode;
	}
	public void setQuotationMode(String quotationMode) {
		this.quotationMode = quotationMode;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getPayApplyNo() {
		return payApplyNo;
	}
	public void setPayApplyNo(String payApplyNo) {
		this.payApplyNo = payApplyNo;
	}
	public String getIdentityCode() {
		return identityCode;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public Double getSumPremium() {
		return sumPremium;
	}
	public void setSumPremium(Double sumPremium) {
		this.sumPremium = sumPremium;
	}
	public Double getSumTax() {
		return sumTax;
	}
	public void setSumTax(Double sumTax) {
		this.sumTax = sumTax;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getJointSales() {
		return jointSales;
	}
	public void setJointSales(String jointSales) {
		this.jointSales = jointSales;
	}
	public String getEYZapplicationNo() {
		return EYZapplicationNo;
	}
	public void setEYZapplicationNo(String eYZapplicationNo) {
		EYZapplicationNo = eYZapplicationNo;
	}
	public String getEYZpolicyNo() {
		return EYZpolicyNo;
	}
	public void setEYZpolicyNo(String eYZpolicyNo) {
		EYZpolicyNo = eYZpolicyNo;
	}
	public Double getEYZpremium() {
		return EYZpremium;
	}
	public void setEYZpremium(Double eYZpremium) {
		EYZpremium = eYZpremium;
	}
	public String getYEJapplicationNo() {
		return YEJapplicationNo;
	}
	public void setYEJapplicationNo(String yEJapplicationNo) {
		YEJapplicationNo = yEJapplicationNo;
	}
	public String getYEJpolicyNo() {
		return YEJpolicyNo;
	}
	public void setYEJpolicyNo(String yEJpolicyNo) {
		YEJpolicyNo = yEJpolicyNo;
	}
	public Double getYEJpremium() {
		return YEJpremium;
	}
	public void setYEJpremium(Double yEJpremium) {
		YEJpremium = yEJpremium;
	}
	public String getWAEapplicationNo() {
		return WAEapplicationNo;
	}
	public void setWAEapplicationNo(String wAEapplicationNo) {
		WAEapplicationNo = wAEapplicationNo;
	}
	public String getWAEpolicyNo() {
		return WAEpolicyNo;
	}
	public void setWAEpolicyNo(String wAEpolicyNo) {
		WAEpolicyNo = wAEpolicyNo;
	}
	public Double getWAEpremium() {
		return WAEpremium;
	}
	public void setWAEpremium(Double wAEpremium) {
		WAEpremium = wAEpremium;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getBusinessNature() {
		return businessNature;
	}
	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getComeCode() {
		return comeCode;
	}
	public void setComeCode(String comeCode) {
		this.comeCode = comeCode;
	}	

	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getPlatformFlag() {
		return platformFlag;
	}
	public void setPlatformFlag(String platformFlag) {
		this.platformFlag = platformFlag;
	}
	public String getNclUserCode() {
		return nclUserCode;
	}
	public void setNclUserCode(String nclUserCode) {
		this.nclUserCode = nclUserCode;
	}
	public Double getCommisionamntVci() {
		return commisionamntVci;
	}
	public Double getCommisionamntTci() {
		return commisionamntTci;
	}
	public void setCommisionamntTci(Double commisionamntTci) {
		this.commisionamntTci = commisionamntTci;
	}
	public Double getManagementfeeVci() {
		return managementfeeVci;
	}
	public void setManagementfeeVci(Double managementfeeVci) {
		this.managementfeeVci = managementfeeVci;
	}
	public Double getManagementfeeTci() {
		return managementfeeTci;
	}
	public void setManagementfeeTci(Double managementfeeTci) {
		this.managementfeeTci = managementfeeTci;
	}
	public void setCommisionamntVci(Double commisionamntVci) {
		this.commisionamntVci = commisionamntVci;
	}
	public Double getPremiumTci() {
		return premiumTci;
	}
	public void setPremiumTci(Double premiumTci) {
		this.premiumTci = premiumTci;
	}
	public Double getPremiumVci() {
		return premiumVci;
	}
	public void setPremiumVci(Double premiumVci) {
		this.premiumVci = premiumVci;
	}
	public String getCarOwnerNature() {
		return carOwnerNature;
	}
	public void setCarOwnerNature(String carOwnerNature) {
		this.carOwnerNature = carOwnerNature;
	}
	public String getCarUseNatureCode() {
		return carUseNatureCode;
	}
	public void setCarUseNatureCode(String carUseNatureCode) {
		this.carUseNatureCode = carUseNatureCode;
	}
	public Date getApplyMessageDate() {
		return applyMessageDate;
	}
	public void setApplyMessageDate(Date applyMessageDate) {
		this.applyMessageDate = applyMessageDate;
	}
	public String getAppliyIdType() {
		return appliyIdType;
	}
	public void setAppliyIdType(String appliyIdType) {
		this.appliyIdType = appliyIdType;
	}
	public String getInsuredIdType() {
		return insuredIdType;
	}
	public void setInsuredIdType(String insuredIdType) {
		this.insuredIdType = insuredIdType;
	}
	public String getQuotationType() {
		return quotationType;
	}
	public void setQuotationType(String quotationType) {
		this.quotationType = quotationType;
	}
	public String getCarKindCode() {
		return carKindCode;
	}
	public void setCarKindCode(String carKindCode) {
		this.carKindCode = carKindCode;
	}
	public String getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getHuataiPaymentStatus() {
		return huataiPaymentStatus;
	}
	public void setHuataiPaymentStatus(String huataiPaymentStatus) {
		this.huataiPaymentStatus = huataiPaymentStatus;
	}
	public String getMinStartDate() {
		return minStartDate;
	}
	public void setMinStartDate(String minStartDate) {
		this.minStartDate = minStartDate;
	}
	public String getMaxStartDate() {
		return maxStartDate;
	}
	public void setMaxStartDate(String maxStartDate) {
		this.maxStartDate = maxStartDate;
	}
	public String getHuataiPaymentStatusName() {
		return huataiPaymentStatusName;
	}
	public void setHuataiPaymentStatusName(String huataiPaymentStatusName) {
		this.huataiPaymentStatusName = huataiPaymentStatusName;
	}
	
	public String getNewcoreFlag() {
		return newcoreFlag;
	}
	public void setNewcoreFlag(String newcoreFlag) {
		this.newcoreFlag = newcoreFlag;
	}
	public Date getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	
}