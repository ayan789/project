package com.ccic.salesapp.noncar.dto.response;
/**
 *
 */
public class GetPayUrlResponseVO{

	private String url;
    private String qrCode;
    
    //江苏支付宝支付用到以下字段
    private String applicantName;	//投保人姓名
    private String applicantIdentifyType;	//投保人证件类型
    private String applicantIdentifyNo;	//投保人证件号码
    private String startDate;	//起保日期
    private String endDate;	//终保日期
    private String ciSumPremium;	//交强险保费合计
    private String biSumPremium;	//商业险保费合计
    private String sumCartax;	//车船税
    private String joinSaleE;	//联合销售-意外险
    private String joinSaleY;	//联合销售-随车行李险
    private String receivingSide;	//收款方
    
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantIdentifyType() {
		return applicantIdentifyType;
	}

	public void setApplicantIdentifyType(String applicantIdentifyType) {
		this.applicantIdentifyType = applicantIdentifyType;
	}

	public String getApplicantIdentifyNo() {
		return applicantIdentifyNo;
	}

	public void setApplicantIdentifyNo(String applicantIdentifyNo) {
		this.applicantIdentifyNo = applicantIdentifyNo;
	}

	public String getCiSumPremium() {
		return ciSumPremium;
	}

	public void setCiSumPremium(String ciSumPremium) {
		this.ciSumPremium = ciSumPremium;
	}

	public String getBiSumPremium() {
		return biSumPremium;
	}

	public void setBiSumPremium(String biSumPremium) {
		this.biSumPremium = biSumPremium;
	}

	public String getSumCartax() {
		return sumCartax;
	}

	public void setSumCartax(String sumCartax) {
		this.sumCartax = sumCartax;
	}

	public String getReceivingSide() {
		return receivingSide;
	}

	public void setReceivingSide(String receivingSide) {
		this.receivingSide = receivingSide;
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

	public String getJoinSaleE() {
		return joinSaleE;
	}

	public void setJoinSaleE(String joinSaleE) {
		this.joinSaleE = joinSaleE;
	}

	public String getJoinSaleY() {
		return joinSaleY;
	}

	public void setJoinSaleY(String joinSaleY) {
		this.joinSaleY = joinSaleY;
	}

}
