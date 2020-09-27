package com.ccic.salesapp.performance.domain.vo.dto;

import java.io.Serializable;
import java.util.Date;



/**
 * 人员信息表(从基本法同步)
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:16:31
 */
public class SSalelawPerson implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//人员工号
	private String usercode;
	//姓名
	private String username;
	//机构代码
	private String comcode;
	//机构名称
	private String comcanme;
	//渠道代码
	private String channelcode;
	//渠道名称
	private String channelname;
	//团队代码
	private String teamcode;
	//团队名称
	private String teamcname;
	//岗位代码
	private String persontypeid;
	//岗位名称
	private String persontypename;
	//职务代码
	private String dutyid;
	//职务名称
	private String dutyname;
	//职级代码
	private String rankcode;
	//职级名称
	private String rankname;
	//A/B模式
	private String abtype;
	//更新时间
	private Date updatedate;
	
	private Date salesDate;
	
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
	 * 设置：姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：姓名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：机构代码
	 */
	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
	/**
	 * 获取：机构代码
	 */
	public String getComcode() {
		return comcode;
	}
	/**
	 * 设置：机构名称
	 */
	public void setComcanme(String comcanme) {
		this.comcanme = comcanme;
	}
	/**
	 * 获取：机构名称
	 */
	public String getComcanme() {
		return comcanme;
	}
	/**
	 * 设置：渠道代码
	 */
	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}
	/**
	 * 获取：渠道代码
	 */
	public String getChannelcode() {
		return channelcode;
	}
	/**
	 * 设置：渠道名称
	 */
	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
	/**
	 * 获取：渠道名称
	 */
	public String getChannelname() {
		return channelname;
	}
	/**
	 * 设置：团队代码
	 */
	public void setTeamcode(String teamcode) {
		this.teamcode = teamcode;
	}
	/**
	 * 获取：团队代码
	 */
	public String getTeamcode() {
		return teamcode;
	}
	/**
	 * 设置：团队名称
	 */
	public void setTeamcname(String teamcname) {
		this.teamcname = teamcname;
	}
	/**
	 * 获取：团队名称
	 */
	public String getTeamcname() {
		return teamcname;
	}
	/**
	 * 设置：岗位代码
	 */
	public void setPersontypeid(String persontypeid) {
		this.persontypeid = persontypeid;
	}
	/**
	 * 获取：岗位代码
	 */
	public String getPersontypeid() {
		return persontypeid;
	}
	/**
	 * 设置：岗位名称
	 */
	public void setPersontypename(String persontypename) {
		this.persontypename = persontypename;
	}
	/**
	 * 获取：岗位名称
	 */
	public String getPersontypename() {
		return persontypename;
	}
	/**
	 * 设置：职务代码
	 */
	public void setDutyid(String dutyid) {
		this.dutyid = dutyid;
	}
	/**
	 * 获取：职务代码
	 */
	public String getDutyid() {
		return dutyid;
	}
	/**
	 * 设置：职务名称
	 */
	public void setDutyname(String dutyname) {
		this.dutyname = dutyname;
	}
	/**
	 * 获取：职务名称
	 */
	public String getDutyname() {
		return dutyname;
	}
	/**
	 * 设置：职级代码
	 */
	public void setRankcode(String rankcode) {
		this.rankcode = rankcode;
	}
	/**
	 * 获取：职级代码
	 */
	public String getRankcode() {
		return rankcode;
	}
	/**
	 * 设置：职级名称
	 */
	public void setRankname(String rankname) {
		this.rankname = rankname;
	}
	/**
	 * 获取：职级名称
	 */
	public String getRankname() {
		return rankname;
	}
	/**
	 * 设置：A/B模式
	 */
	public void setAbtype(String abtype) {
		this.abtype = abtype;
	}
	/**
	 * 获取：A/B模式
	 */
	public String getAbtype() {
		return abtype;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdatedate() {
		return updatedate;
	}
	public Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
	
	
}
