package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;



/**
 * 投保单信息
 * @author 孟秀萍
 *
 */
public class ICarApplicationVO extends DataVO{

	private String applicationNo;//投保单号          
	private String payNo;//支付号                    
	private String insuredName;//被保人姓名          
	private String insuredCertNo;//被保人证件号    
	private String applicantName;//投保人姓名   　　　　            
	private String respStartDate;//保险开始时间       
	private String respEndDate;//保险终止时间        
	private Double sumAmount;//总保额                
	private Double sumPremium;//总保费  
	private String status;//保单状态
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getInsuredCertNo() {
		return insuredCertNo;
	}
	public void setInsuredCertNo(String insuredCertNo) {
		this.insuredCertNo = insuredCertNo;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getRespStartDate() {
		return respStartDate;
	}
	public void setRespStartDate(String respStartDate) {
		this.respStartDate = respStartDate;
	}
	public String getRespEndDate() {
		return respEndDate;
	}
	public void setRespEndDate(String respEndDate) {
		this.respEndDate = respEndDate;
	}
	public Double getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(Double sumAmount) {
		this.sumAmount = sumAmount;
	}
	public Double getSumPremium() {
		return sumPremium;
	}
	public void setSumPremium(Double sumPremium) {
		this.sumPremium = sumPremium;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

 
}
