package com.ccic.salesapp.performance.domain.vo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 用户表
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:42:45
 */
public class SUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private BigDecimal id;
	//业务员代码
	private String userCode;
	//业务员名称
	private String userName;
	//英文名称
	private String userEname;
	//业务员所属机构
	private String deptCode;
	//业务员所属机构名称
	private String deptName;
	//业务员出单机构编码
	private String deptCodeMake;
	//业务员出单机构名称
	private String deptNameMake;
	//密码
	private String passwd;
	//手机号
	private String phone;
	//固定电话
	private String tel;
	//通信地址
	private String address;
	//邮编
	private String postcode;
	//邮箱
	private String email;
	//有效性 失效标志
	//Null激活
	//0=非激活
	//1=激活(默认)
	//2=失效-删除
	//3=失效-欠费暂停

	private String mark;
	//消息推送标志位
	private String deviceToken;
	//创建时间
	private Date createDate;
	//更新时间
	private Date updateDate;
	//token
	private String token;
	//创建时间
	private Date tokenExpireTime;
	//c3配置手机号
	private String cMobile;
	//门店代码
	private String storeCode;
	//连续登陆失败次数
	private BigDecimal defeatLogTimes;
	//账户锁定结束时间
	private Date lockExpireTime;
	//薪酬查询密码
	private String salaryPassword;
	//外部渠道代码
	private String channelCode;
	//是否接收报案信息 0不接收 1接收
	private String receiveReportMsg;
	//用户手机号
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	private String userTel;
	
	/**
	 * 设置：主键
	 */
	public void setId(BigDecimal id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public BigDecimal getId() {
		return id;
	}
	/**
	 * 设置：业务员代码
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * 获取：业务员代码
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * 设置：业务员名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：业务员名称
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：英文名称
	 */
	public void setUserEname(String userEname) {
		this.userEname = userEname;
	}
	/**
	 * 获取：英文名称
	 */
	public String getUserEname() {
		return userEname;
	}
	/**
	 * 设置：业务员所属机构
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	/**
	 * 获取：业务员所属机构
	 */
	public String getDeptCode() {
		return deptCode;
	}
	/**
	 * 设置：业务员所属机构名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取：业务员所属机构名称
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置：业务员出单机构编码
	 */
	public void setDeptCodeMake(String deptCodeMake) {
		this.deptCodeMake = deptCodeMake;
	}
	/**
	 * 获取：业务员出单机构编码
	 */
	public String getDeptCodeMake() {
		return deptCodeMake;
	}
	/**
	 * 设置：业务员出单机构名称
	 */
	public void setDeptNameMake(String deptNameMake) {
		this.deptNameMake = deptNameMake;
	}
	/**
	 * 获取：业务员出单机构名称
	 */
	public String getDeptNameMake() {
		return deptNameMake;
	}
	/**
	 * 设置：密码
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	/**
	 * 获取：密码
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：固定电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：固定电话
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：通信地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：通信地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：邮编
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * 获取：邮编
	 */
	public String getPostcode() {
		return postcode;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：有效性 失效标志
Null激活
0=非激活
1=激活(默认)
2=失效-删除
3=失效-欠费暂停

	 */
	public void setMark(String mark) {
		this.mark = mark;
	}
	/**
	 * 获取：有效性 失效标志
Null激活
0=非激活
1=激活(默认)
2=失效-删除
3=失效-欠费暂停

	 */
	public String getMark() {
		return mark;
	}
	/**
	 * 设置：消息推送标志位
	 */
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	/**
	 * 获取：消息推送标志位
	 */
	public String getDeviceToken() {
		return deviceToken;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 获取：token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 设置：创建时间
	 */
	public void setTokenExpireTime(Date tokenExpireTime) {
		this.tokenExpireTime = tokenExpireTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getTokenExpireTime() {
		return tokenExpireTime;
	}
	/**
	 * 设置：c3配置手机号
	 */
	public void setCMobile(String cMobile) {
		this.cMobile = cMobile;
	}
	/**
	 * 获取：c3配置手机号
	 */
	public String getCMobile() {
		return cMobile;
	}
	/**
	 * 设置：门店代码
	 */
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	/**
	 * 获取：门店代码
	 */
	public String getStoreCode() {
		return storeCode;
	}
	/**
	 * 设置：连续登陆失败次数
	 */
	public void setDefeatLogTimes(BigDecimal defeatLogTimes) {
		this.defeatLogTimes = defeatLogTimes;
	}
	/**
	 * 获取：连续登陆失败次数
	 */
	public BigDecimal getDefeatLogTimes() {
		return defeatLogTimes;
	}
	/**
	 * 设置：账户锁定结束时间
	 */
	public void setLockExpireTime(Date lockExpireTime) {
		this.lockExpireTime = lockExpireTime;
	}
	/**
	 * 获取：账户锁定结束时间
	 */
	public Date getLockExpireTime() {
		return lockExpireTime;
	}
	/**
	 * 设置：薪酬查询密码
	 */
	public void setSalaryPassword(String salaryPassword) {
		this.salaryPassword = salaryPassword;
	}
	/**
	 * 获取：薪酬查询密码
	 */
	public String getSalaryPassword() {
		return salaryPassword;
	}
	/**
	 * 设置：外部渠道代码
	 */
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	/**
	 * 获取：外部渠道代码
	 */
	public String getChannelCode() {
		return channelCode;
	}
	/**
	 * 设置：是否接收报案信息 0不接收 1接收
	 */
	public void setReceiveReportMsg(String receiveReportMsg) {
		this.receiveReportMsg = receiveReportMsg;
	}
	/**
	 * 获取：是否接收报案信息 0不接收 1接收
	 */
	public String getReceiveReportMsg() {
		return receiveReportMsg;
	}
}
