package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;



/**
 * 险别信息VO
 * @author lining
 *
 */
public class KindVO extends DataVO{

	private String kindCode; //险别代码
	private int serialno; //序号
	private int quantity; //投保人数
	private double amount; //保额
	private double deductibleValue; //自负额
	private double deductibleRate; //免赔率
	private double unitAmount; //每人保额
	private String modeCode; //附加类型
	private String kindFlag; //不计免赔标志
	private double adjustRate; //单个险别优惠
	private double benchmarkPremium; //标准保费/基础保费
	private double premium; //实缴保费
	public String getKindCode() {
		return kindCode;
	}
	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}
	public int getSerialno() {
		return serialno;
	}
	public void setSerialno(int serialno) {
		this.serialno = serialno;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getDeductibleValue() {
		return deductibleValue;
	}
	public void setDeductibleValue(double deductibleValue) {
		this.deductibleValue = deductibleValue;
	}
	public double getDeductibleRate() {
		return deductibleRate;
	}
	public void setDeductibleRate(double deductibleRate) {
		this.deductibleRate = deductibleRate;
	}
	public double getUnitAmount() {
		return unitAmount;
	}
	public void setUnitAmount(double unitAmount) {
		this.unitAmount = unitAmount;
	}
	public String getModeCode() {
		return modeCode;
	}
	public void setModeCode(String modeCode) {
		this.modeCode = modeCode;
	}
	public String getKindFlag() {
		return kindFlag;
	}
	public void setKindFlag(String kindFlag) {
		this.kindFlag = kindFlag;
	}
	public double getAdjustRate() {
		return adjustRate;
	}
	public void setAdjustRate(double adjustRate) {
		this.adjustRate = adjustRate;
	}
	public double getBenchmarkPremium() {
		return benchmarkPremium;
	}
	public void setBenchmarkPremium(double benchmarkPremium) {
		this.benchmarkPremium = benchmarkPremium;
	}
	public double getPremium() {
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}
}
