package com.ccic.salesapp.noncar.dto;
import java.util.List;
import com.ccic.salesapp.noncar.dto.request.ExtendInfoVO;


/**
 * 车船税信息
 * @author Administrator
 *
 */
public class CarTaxVO extends DataVO {

	private String taxFlag ;//缴税标志 Y
	private String startTaxDate ;//当年计税起始日期格式：YYYY-MM-DD  
	
	private AnnualTaxVO currentTaxDue;//本年度纳税对象（单条） Y
	private List<ExtendInfoVO> extendInfo ;
	
	private String benchmarkTax ;//标准税款 
	private String tax ;//当年缴纳税款 
	private String taxOverdue;//往年补缴税款 
	private String surCharge ;//滞纳金
	private String sumPayment;//总支付税金
	
	private String adjustRate;//订单详情-----减税比例 20130724 增加
	private String lastTaxDate;//订单详情----上次缴税日期 20130724 
	private String refuseReason;///订单详情----拒缴原因 20130724 增加
	
	private String sumTax;//订单详情---合计税款
	private String adjustTax;//订单详情--减免金额
	private String lastBZendDate;//订单详情--滞纳金开始计算日期
	private String taxPayerNo;//订单详情--纳税人识别号
	private String taxCertificateno1;//订单详情----完税证明 --
	private String revenue ;//订单详情--税务机关  ------
	private String noTaxCarType ;//订单详情--免税车辆类型  ------
	
	
	
	public String getSumTax() {
		return sumTax;
	}
	public void setSumTax(String sumTax) {
		this.sumTax = sumTax;
	}
	public String getAdjustTax() {
		return adjustTax;
	}
	public void setAdjustTax(String adjustTax) {
		this.adjustTax = adjustTax;
	}
	public String getLastBZendDate() {
		return lastBZendDate;
	}
	public void setLastBZendDate(String lastBZendDate) {
		this.lastBZendDate = lastBZendDate;
	}
	public String getTaxPayerNo() {
		return taxPayerNo;
	}
	public void setTaxPayerNo(String taxPayerNo) {
		this.taxPayerNo = taxPayerNo;
	}
	public String getTaxCertificateno1() {
		return taxCertificateno1;
	}
	public void setTaxCertificateno1(String taxCertificateno1) {
		this.taxCertificateno1 = taxCertificateno1;
	}
	public String getNoTaxCarType() {
		return noTaxCarType;
	}
	public void setNoTaxCarType(String noTaxCarType) {
		this.noTaxCarType = noTaxCarType;
	}
	public String getAdjustRate() {
		return adjustRate;
	}
	public void setAdjustRate(String adjustRate) {
		this.adjustRate = adjustRate;
	}
	public String getLastTaxDate() {
		return lastTaxDate;
	}
	public void setLastTaxDate(String lastTaxDate) {
		this.lastTaxDate = lastTaxDate;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	public String getTaxFlag() {
		return taxFlag;
	}
	public void setTaxFlag(String taxFlag) {
		this.taxFlag = taxFlag;
	}
	public String getBenchmarkTax() {
		return benchmarkTax;
	}
	public void setBenchmarkTax(String benchmarkTax) {
		this.benchmarkTax = benchmarkTax;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getTaxOverdue() {
		return taxOverdue;
	}
	public void setTaxOverdue(String taxOverdue) {
		this.taxOverdue = taxOverdue;
	}
	public String getSurCharge() {
		return surCharge;
	}
	public void setSurCharge(String surCharge) {
		this.surCharge = surCharge;
	}
	public String getSumPayment() {
		return sumPayment;
	}
	public void setSumPayment(String sumPayment) {
		this.sumPayment = sumPayment;
	}
	
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
	public String getStartTaxDate() {
		return startTaxDate;
	}
	public void setStartTaxDate(String startTaxDate) {
		this.startTaxDate = startTaxDate;
	}
	public AnnualTaxVO getCurrentTaxDue() {
		return currentTaxDue;
	}
	public void setCurrentTaxDue(AnnualTaxVO currentTaxDue) {
		this.currentTaxDue = currentTaxDue;
	}
	public List<ExtendInfoVO> getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(
			List<ExtendInfoVO> extendInfo) {
		this.extendInfo = extendInfo;
	}
	
	
}