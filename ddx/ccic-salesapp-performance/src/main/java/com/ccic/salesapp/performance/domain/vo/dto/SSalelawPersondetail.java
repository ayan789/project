package com.ccic.salesapp.performance.domain.vo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 考核保费及达成表(从基本法同步)
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:20:59
 */
public class SSalelawPersondetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//人员工号
	private String usercode;
	//统计月份yyyymm
	private String calcmonth;
	//上月月度考核保费
	private BigDecimal monthstandardpremium;
	//上月月度计划
	private BigDecimal monthsumpremiumplan;
	//上月月度考核保费达成率
	private BigDecimal monthpremiumreachrate;
	//当月的月度计划,是统计月份+1的月度计划
	private BigDecimal currentmonthpremiumplan;
	//截止上月底累计考核保费
	private BigDecimal sumstandardpremium;
	//全年考核保费计划
	private BigDecimal yearsumpremiumplan;
	//全年考核保费达成率
	private BigDecimal yearpremiumreachrate;
	//更新时间，增量字段
	private Date updatedate;

	/**
	 * 设置：人员工号
	 */
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	/**
	 * 获取：人员工号
	 */
	public String getUsercode() {
		return usercode;
	}
	/**
	 * 设置：统计月份yyyymm
	 */
	public void setCalcmonth(String calcmonth) {
		this.calcmonth = calcmonth;
	}
	/**
	 * 获取：统计月份yyyymm
	 */
	public String getCalcmonth() {
		return calcmonth;
	}
	/**
	 * 设置：上月月度考核保费
	 */
	public void setMonthstandardpremium(BigDecimal monthstandardpremium) {
		this.monthstandardpremium = monthstandardpremium;
	}
	/**
	 * 获取：上月月度考核保费
	 */
	public BigDecimal getMonthstandardpremium() {
		return monthstandardpremium;
	}
	/**
	 * 设置：上月月度计划
	 */
	public void setMonthsumpremiumplan(BigDecimal monthsumpremiumplan) {
		this.monthsumpremiumplan = monthsumpremiumplan;
	}
	/**
	 * 获取：上月月度计划
	 */
	public BigDecimal getMonthsumpremiumplan() {
		return monthsumpremiumplan;
	}
	/**
	 * 设置：上月月度考核保费达成率
	 */
	public void setMonthpremiumreachrate(BigDecimal monthpremiumreachrate) {
		this.monthpremiumreachrate = monthpremiumreachrate;
	}
	/**
	 * 获取：上月月度考核保费达成率
	 */
	public BigDecimal getMonthpremiumreachrate() {
		return monthpremiumreachrate;
	}
	/**
	 * 设置：当月的月度计划,是统计月份+1的月度计划
	 */
	public void setCurrentmonthpremiumplan(BigDecimal currentmonthpremiumplan) {
		this.currentmonthpremiumplan = currentmonthpremiumplan;
	}
	/**
	 * 获取：当月的月度计划,是统计月份+1的月度计划
	 */
	public BigDecimal getCurrentmonthpremiumplan() {
		return currentmonthpremiumplan;
	}
	/**
	 * 设置：截止上月底累计考核保费
	 */
	public void setSumstandardpremium(BigDecimal sumstandardpremium) {
		this.sumstandardpremium = sumstandardpremium;
	}
	/**
	 * 获取：截止上月底累计考核保费
	 */
	public BigDecimal getSumstandardpremium() {
		return sumstandardpremium;
	}
	/**
	 * 设置：全年考核保费计划
	 */
	public void setYearsumpremiumplan(BigDecimal yearsumpremiumplan) {
		this.yearsumpremiumplan = yearsumpremiumplan;
	}
	/**
	 * 获取：全年考核保费计划
	 */
	public BigDecimal getYearsumpremiumplan() {
		return yearsumpremiumplan;
	}
	/**
	 * 设置：全年考核保费达成率
	 */
	public void setYearpremiumreachrate(BigDecimal yearpremiumreachrate) {
		this.yearpremiumreachrate = yearpremiumreachrate;
	}
	/**
	 * 获取：全年考核保费达成率
	 */
	public BigDecimal getYearpremiumreachrate() {
		return yearpremiumreachrate;
	}
	/**
	 * 设置：更新时间，增量字段
	 */
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	/**
	 * 获取：更新时间，增量字段
	 */
	public Date getUpdatedate() {
		return updatedate;
	}
}
