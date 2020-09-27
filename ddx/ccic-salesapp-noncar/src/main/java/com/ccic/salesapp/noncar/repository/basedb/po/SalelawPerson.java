package com.ccic.salesapp.noncar.repository.basedb.po;

import java.util.Date;

public class SalelawPerson {
    private String usercode;

    private Long id;

    private String username;

    private String comcode;

    private String comcanme;

    private String channelcode;

    private String channelname;

    private String teamcode;

    private String teamcname;

    private String persontypeid;

    private String persontypename;

    private String dutyid;

    private String dutyname;

    private String rankcode;

    private String rankname;

    private String abtype;

    private Date entrydate;

    private Date salesdate;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String validstatus;

    private String certNo;

    private String employType;

    private String salesType;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getComcode() {
        return comcode;
    }

    public void setComcode(String comcode) {
        this.comcode = comcode == null ? null : comcode.trim();
    }

    public String getComcanme() {
        return comcanme;
    }

    public void setComcanme(String comcanme) {
        this.comcanme = comcanme == null ? null : comcanme.trim();
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode == null ? null : channelcode.trim();
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname == null ? null : channelname.trim();
    }

    public String getTeamcode() {
        return teamcode;
    }

    public void setTeamcode(String teamcode) {
        this.teamcode = teamcode == null ? null : teamcode.trim();
    }

    public String getTeamcname() {
        return teamcname;
    }

    public void setTeamcname(String teamcname) {
        this.teamcname = teamcname == null ? null : teamcname.trim();
    }

    public String getPersontypeid() {
        return persontypeid;
    }

    public void setPersontypeid(String persontypeid) {
        this.persontypeid = persontypeid == null ? null : persontypeid.trim();
    }

    public String getPersontypename() {
        return persontypename;
    }

    public void setPersontypename(String persontypename) {
        this.persontypename = persontypename == null ? null : persontypename.trim();
    }

    public String getDutyid() {
        return dutyid;
    }

    public void setDutyid(String dutyid) {
        this.dutyid = dutyid == null ? null : dutyid.trim();
    }

    public String getDutyname() {
        return dutyname;
    }

    public void setDutyname(String dutyname) {
        this.dutyname = dutyname == null ? null : dutyname.trim();
    }

    public String getRankcode() {
        return rankcode;
    }

    public void setRankcode(String rankcode) {
        this.rankcode = rankcode == null ? null : rankcode.trim();
    }

    public String getRankname() {
        return rankname;
    }

    public void setRankname(String rankname) {
        this.rankname = rankname == null ? null : rankname.trim();
    }

    public String getAbtype() {
        return abtype;
    }

    public void setAbtype(String abtype) {
        this.abtype = abtype == null ? null : abtype.trim();
    }

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public Date getSalesdate() {
        return salesdate;
    }

    public void setSalesdate(Date salesdate) {
        this.salesdate = salesdate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getValidstatus() {
        return validstatus;
    }

    public void setValidstatus(String validstatus) {
        this.validstatus = validstatus == null ? null : validstatus.trim();
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public String getEmployType() {
        return employType;
    }

    public void setEmployType(String employType) {
        this.employType = employType == null ? null : employType.trim();
    }

    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType == null ? null : salesType.trim();
    }
}