package com.ccic.salesapp.performance.domain.vo.response;

public class IPerformanceResponse {
	String userName;//用户名称
	String comName;//机构名称
	String channelName;//渠道名称
	String personTypeId;//岗位代码
	String rankName;//职级名称
	String abType;//A/B模式
	String renewalRate;//续保率（%）
	String monthPremiumPlan;//当月累计考核保费计划（万元）
	String yearPremiumPlan;//当年累计考核保费计划（万元）
	
	String realPremiumAll;//实收保费(总)
	String realPremiumCar;//实收保费(车险)
	String realPremiumNoCar;//实收保费(非车险)
	String realPremiumAcc;//实收保费(意外险)
	String realPremiumHea;//实收保费(健康险)
	
	String realPremiumAll2;//实收保费(总)-年业绩第二个时间点统计
	String realPremiumCar2;//实收保费(车险)-年业绩第二个时间点统计
	String realPremiumNoCar2;//实收保费(非车险)-年业绩第二个时间点统计
	String realPremiumAcc2;//实收保费(意外险)-年业绩第二个时间点统计
	String realPremiumHea2;//实收保费(健康险)-年业绩第二个时间点统计
	
	String standardPremiumAll;//考核保费(总)
	String standardPremiumRate;//考核保费达成率(总)%
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getPersonTypeId() {
		return personTypeId;
	}
	public void setPersonTypeId(String personTypeId) {
		this.personTypeId = personTypeId;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getAbType() {
		return abType;
	}
	public void setAbType(String abType) {
		this.abType = abType;
	}
	public String getRenewalRate() {
		return renewalRate;
	}
	public void setRenewalRate(String renewalRate) {
		this.renewalRate = renewalRate;
	}
	public String getMonthPremiumPlan() {
		return monthPremiumPlan;
	}
	public void setMonthPremiumPlan(String monthPremiumPlan) {
		this.monthPremiumPlan = monthPremiumPlan;
	}
	public String getYearPremiumPlan() {
		return yearPremiumPlan;
	}
	public void setYearPremiumPlan(String yearPremiumPlan) {
		this.yearPremiumPlan = yearPremiumPlan;
	}
	public String getRealPremiumAll() {
		return realPremiumAll;
	}
	public void setRealPremiumAll(String realPremiumAll) {
		this.realPremiumAll = realPremiumAll;
	}
	public String getRealPremiumCar() {
		return realPremiumCar;
	}
	public void setRealPremiumCar(String realPremiumCar) {
		this.realPremiumCar = realPremiumCar;
	}
	public String getRealPremiumNoCar() {
		return realPremiumNoCar;
	}
	public void setRealPremiumNoCar(String realPremiumNoCar) {
		this.realPremiumNoCar = realPremiumNoCar;
	}
	public String getRealPremiumAcc() {
		return realPremiumAcc;
	}
	public void setRealPremiumAcc(String realPremiumAcc) {
		this.realPremiumAcc = realPremiumAcc;
	}
	public String getRealPremiumHea() {
		return realPremiumHea;
	}
	public void setRealPremiumHea(String realPremiumHea) {
		this.realPremiumHea = realPremiumHea;
	}
	public String getStandardPremiumAll() {
		return standardPremiumAll;
	}
	public void setStandardPremiumAll(String standardPremiumAll) {
		this.standardPremiumAll = standardPremiumAll;
	}
	public String getStandardPremiumRate() {
		return standardPremiumRate;
	}
	public void setStandardPremiumRate(String standardPremiumRate) {
		this.standardPremiumRate = standardPremiumRate;
	}
	public String getRealPremiumAll2() {
		return realPremiumAll2;
	}
	public void setRealPremiumAll2(String realPremiumAll2) {
		this.realPremiumAll2 = realPremiumAll2;
	}
	public String getRealPremiumCar2() {
		return realPremiumCar2;
	}
	public void setRealPremiumCar2(String realPremiumCar2) {
		this.realPremiumCar2 = realPremiumCar2;
	}
	public String getRealPremiumNoCar2() {
		return realPremiumNoCar2;
	}
	public void setRealPremiumNoCar2(String realPremiumNoCar2) {
		this.realPremiumNoCar2 = realPremiumNoCar2;
	}
	public String getRealPremiumAcc2() {
		return realPremiumAcc2;
	}
	public void setRealPremiumAcc2(String realPremiumAcc2) {
		this.realPremiumAcc2 = realPremiumAcc2;
	}
	public String getRealPremiumHea2() {
		return realPremiumHea2;
	}
	public void setRealPremiumHea2(String realPremiumHea2) {
		this.realPremiumHea2 = realPremiumHea2;
	}

	
}
