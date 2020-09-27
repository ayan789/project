package com.ccic.salesapp.performance.domain.vo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:29:20
 */
public class STeamRealfeeV implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//年月
	private String yearMonth;
	//团队长工号
	private String teamladerCd;
	//团队长姓名
	private String teamladerName;
	//团队代码
	private String teamCd;
	//团队名称
	private String teamName;
	//团队归属机构代码
	private String vestOrgcd;
	//实收保费
	private BigDecimal realfee;
	//净实收保费
	private BigDecimal netRealfee;
	//最后更新时间
	private Date lastUpdate;
	//
	private BigDecimal cfee;

	/**
	 * 设置：年月
	 */
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	/**
	 * 获取：年月
	 */
	public String getYearMonth() {
		return yearMonth;
	}
	/**
	 * 设置：团队长工号
	 */
	public void setTeamladerCd(String teamladerCd) {
		this.teamladerCd = teamladerCd;
	}
	/**
	 * 获取：团队长工号
	 */
	public String getTeamladerCd() {
		return teamladerCd;
	}
	/**
	 * 设置：团队长姓名
	 */
	public void setTeamladerName(String teamladerName) {
		this.teamladerName = teamladerName;
	}
	/**
	 * 获取：团队长姓名
	 */
	public String getTeamladerName() {
		return teamladerName;
	}
	/**
	 * 设置：团队代码
	 */
	public void setTeamCd(String teamCd) {
		this.teamCd = teamCd;
	}
	/**
	 * 获取：团队代码
	 */
	public String getTeamCd() {
		return teamCd;
	}
	/**
	 * 设置：团队名称
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	/**
	 * 获取：团队名称
	 */
	public String getTeamName() {
		return teamName;
	}
	/**
	 * 设置：团队归属机构代码
	 */
	public void setVestOrgcd(String vestOrgcd) {
		this.vestOrgcd = vestOrgcd;
	}
	/**
	 * 获取：团队归属机构代码
	 */
	public String getVestOrgcd() {
		return vestOrgcd;
	}
	/**
	 * 设置：实收保费
	 */
	public void setRealfee(BigDecimal realfee) {
		this.realfee = realfee;
	}
	/**
	 * 获取：实收保费
	 */
	public BigDecimal getRealfee() {
		return realfee;
	}
	/**
	 * 设置：净实收保费
	 */
	public void setNetRealfee(BigDecimal netRealfee) {
		this.netRealfee = netRealfee;
	}
	/**
	 * 获取：净实收保费
	 */
	public BigDecimal getNetRealfee() {
		return netRealfee;
	}
	/**
	 * 设置：最后更新时间
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	/**
	 * 获取：最后更新时间
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}
	/**
	 * 设置：
	 */
	public void setCfee(BigDecimal cfee) {
		this.cfee = cfee;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getCfee() {
		return cfee;
	}
}
