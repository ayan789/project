package com.ccic.salesapp.noncar.repository.basedb.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * 核心用户表
 *
 * @date 2020-10-22
 */
@ApiModel("核心用户表")
public class User {
    /**
     * 员工代码
     */
    @ApiModelProperty("员工代码")
    private String usercode;

    /**
     * 
     */
    @ApiModelProperty("")
    private Long id;

    /**
     * 员工名称
     */
    @ApiModelProperty("员工名称")
    private String username;

    /**
     * 归属机构代码
     */
    @ApiModelProperty("归属机构代码")
    private String comcode;

    /**
     * 出单机构代码
     */
    @ApiModelProperty("出单机构代码")
    private String makecom;

    /**
     * 
     */
    @ApiModelProperty("")
    private String accountcode;

    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String phone;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String mobile;

    /**
     * 通信地址
     */
    @ApiModelProperty("通信地址")
    private String address;

    /**
     * 邮政编码
     */
    @ApiModelProperty("邮政编码")
    private String postcode;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 员工标志 --** [1]: 1操作员 --** [2]: 1经办人 --** [3]：1业务员  
     */
    @ApiModelProperty("员工标志 --** [1]: 1操作员 --** [2]: 1经办人 --** [3]：1业务员  ")
    private String userflag;

    /**
     * 允许登录的应用系统 --** [1]: 1核心业务系统 --** [2]：1财务系统 --** [3]：1再保险系统 --** [4]：1 单证系统 --** [5]：1核保核赔系统 
     */
    @ApiModelProperty("允许登录的应用系统 --** [1]: 1核心业务系统 --** [2]：1财务系统 --** [3]：1再保险系统 --** [4]：1 单证系统 --** [5]：1核保核赔系统 ")
    private String loginsystem;

    /**
     * 最新员工代码  
     */
    @ApiModelProperty("最新员工代码  ")
    private String newusercode;

    /**
     * 效力状态(0失效/1有效) 
     */
    @ApiModelProperty("效力状态(0失效/1有效) ")
    private String validstatus;

    /**
     * 专项代码(对应会计科目)  
     */
    @ApiModelProperty("专项代码(对应会计科目)  ")
    private String articlecode;

    /**
     * 外网出单标志：1，是；0或空，否 
     */
    @ApiModelProperty("外网出单标志：1，是；0或空，否 ")
    private String extranetsingleflag;

    /**
     * 代理账户标志：1，是，0或空，否  
     */
    @ApiModelProperty("代理账户标志：1，是，0或空，否  ")
    private String agentaccountflag;

    /**
     * 财产保险销售职业证号
     */
    @ApiModelProperty("财产保险销售职业证号")
    private String vocationcode;

    /**
     * 系统人员创建时间 
     */
    @ApiModelProperty("系统人员创建时间 ")
    private Date createdate;

    /**
     * 进本公司工作时间  
     */
    @ApiModelProperty("进本公司工作时间  ")
    private Date partintime;

    /**
     * 外网出单标志：1，是；0或空，--**是否销售序列人员  
     */
    @ApiModelProperty("外网出单标志：1，是；0或空，--**是否销售序列人员  ")
    private String saleseqflag;

    /**
     * 手机验证码（客户经理登录）
     */
    @ApiModelProperty("手机验证码（客户经理登录）")
    private String authNum;

    /**
     * 
     */
    @ApiModelProperty("")
    private Date hireDate;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
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
        this.username = username;
    }

    public String getComcode() {
        return comcode;
    }

    public void setComcode(String comcode) {
        this.comcode = comcode;
    }

    public String getMakecom() {
        return makecom;
    }

    public void setMakecom(String makecom) {
        this.makecom = makecom;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserflag() {
        return userflag;
    }

    public void setUserflag(String userflag) {
        this.userflag = userflag;
    }

    public String getLoginsystem() {
        return loginsystem;
    }

    public void setLoginsystem(String loginsystem) {
        this.loginsystem = loginsystem;
    }

    public String getNewusercode() {
        return newusercode;
    }

    public void setNewusercode(String newusercode) {
        this.newusercode = newusercode;
    }

    public String getValidstatus() {
        return validstatus;
    }

    public void setValidstatus(String validstatus) {
        this.validstatus = validstatus;
    }

    public String getArticlecode() {
        return articlecode;
    }

    public void setArticlecode(String articlecode) {
        this.articlecode = articlecode;
    }

    public String getExtranetsingleflag() {
        return extranetsingleflag;
    }

    public void setExtranetsingleflag(String extranetsingleflag) {
        this.extranetsingleflag = extranetsingleflag;
    }

    public String getAgentaccountflag() {
        return agentaccountflag;
    }

    public void setAgentaccountflag(String agentaccountflag) {
        this.agentaccountflag = agentaccountflag;
    }

    public String getVocationcode() {
        return vocationcode;
    }

    public void setVocationcode(String vocationcode) {
        this.vocationcode = vocationcode;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getPartintime() {
        return partintime;
    }

    public void setPartintime(Date partintime) {
        this.partintime = partintime;
    }

    public String getSaleseqflag() {
        return saleseqflag;
    }

    public void setSaleseqflag(String saleseqflag) {
        this.saleseqflag = saleseqflag;
    }

    public String getAuthNum() {
        return authNum;
    }

    public void setAuthNum(String authNum) {
        this.authNum = authNum;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}