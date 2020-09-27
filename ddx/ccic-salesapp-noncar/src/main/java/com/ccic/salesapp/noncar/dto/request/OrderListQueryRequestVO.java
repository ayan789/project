package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;

/**
 * 订单列表请求
 * @author ping
 *
 */
public class OrderListQueryRequestVO extends DataVO{
	private String staffCode;//工号
	private String orderNo;//订单号
	private String proposalNo;//投保单号 
	private String policyNo;//保单号
	private String startDate ;//报价起期 
	private String endDate;//报价止期 
	private String quotationMode;//报价模式 
	private String underWriteStatus;//核保状态 
	private String licenseNo ;//车牌号
	private String applicantName;//投保人姓名
	private String insuredName;//被保险人姓名
	private String currentDate;
	private String platformFlag;//平台标识  1-新华出单
	private String nclUserCode;//新华用户标识
	
    private String minStartDate;//起保日期起期
    private String maxStartDate;//起保日期止期
   
	
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
//	public String getOrderNo() {
//		return orderNo;
//	}
//	public void setOrderNo(String orderNo) {
//		this.orderNo = orderNo;
//	}
//	public String getProposalNo() {
//		return proposalNo;
//	}
//	public void setProposalNo(String proposalNo) {
//		this.proposalNo = proposalNo;
//	}
//	public String getPolicyNo() {
//		return policyNo;
//	}
//	public void setPolicyNo(String policyNo) {
//		this.policyNo = policyNo;
//	}
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
	public String getUnderWriteStatus() {
		return underWriteStatus;
	}
	public void setUnderWriteStatus(String underWriteStatus) {
		this.underWriteStatus = underWriteStatus;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
//	public String getInsuredName() {
//		return insuredName;
//	}
//	public void setInsuredName(String insuredName) {
//		this.insuredName = insuredName;
//	}
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
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	
	
}
