package com.ccic.salesapp.noncar.dto;
/**
 * 用户信息VO
 * @author lining
 *
 */
public class UserVO {

	private String userCode; //员工代码
	private String userName; //员工名称	
	private String userEName; //员工英文名称
	private String company; //归属机构编码
	private String comCName; //归属机构名称
	private String makeCompany; //出单机构编码
	private String makeCompanyCName; //出单机构名称
	private String phone; //电话号码
	private String mobile;  //	手机号码
	private String address;  //通信地址
	private String postcode;  //邮编
	private String email;  //邮箱
	private String valid;  //有效性
	private String password;//密码
//	private String hdwNo;//手机串号
	private String deviceToken;//消息推送标志--20130516增
	private String system;//消息推送标志--20131216增
	private String version;//版本号   20131220增加 
	private String terminalType;//终端类型 android/Iphone 20140123 add
	private String comRole;//机构保费查看资格 1 有资格 0没有。20141104 增加
	
	private String loginFlag;// 1-为手机验证码登陆 0-为其他     
	private String token;//令牌
	private String tokenIdx;//令牌序号
	private String storeCode;//门店代码
	
	private String handlerCode;// 归属经办人代码   
	private String handlerName;//归属经办人名称   
	private String comCode;//归属机构代码
	private String comName;//归属机构名称
	private String agentCode;//代理人代码
	private String channelCode;//从人渠道
	private String isSaleMan;//1-销售人员 0-非销人员
	
	private String xinHuaComCode;//新华所属机构
	private String xinHuaUserCode;//新华用户工号
	private String xinHuaUserName;//新华用户姓名
	private String xinHuaBusinessNature;//新华-代理业务来源
	private String xinHuaAgentCode;//新华-代理人代码
	private String xinHuaAgreementNo;//新华-代理人协议
	
	private String isVipManager;//是否为vip管家
	
	private String salaryPassword;//薪酬查询密码

	private String isAuditMan;	//是否审核员
	
	public String getIsVipManager() {
		return isVipManager;
	}
	
	public void setIsVipManager(String isVipManager) {
		this.isVipManager = isVipManager;
	}
public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
public String getLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
public String getComRole() {
		return comRole;
	}
	public void setComRole(String comRole) {
		this.comRole = comRole;
	}
public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	//	public String getSimNo() {
//		return simNo;
//	}
//	public void setSimNo(String simNo) {
//		this.simNo = simNo;
//	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEName() {
		return userEName;
	}
	public void setUserEName(String userEName) {
		this.userEName = userEName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getComCName() {
		return comCName;
	}
	public void setComCName(String comCName) {
		this.comCName = comCName;
	}
	public String getMakeCompany() {
		return makeCompany;
	}
	public void setMakeCompany(String makeCompany) {
		this.makeCompany = makeCompany;
	}
	public String getMakeCompanyCName() {
		return makeCompanyCName;
	}
	public void setMakeCompanyCName(String makeCompanyCName) {
		this.makeCompanyCName = makeCompanyCName;
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
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenIdx() {
		return tokenIdx;
	}
	public void setTokenIdx(String tokenIdx) {
		this.tokenIdx = tokenIdx;
	}
	public String getHandlerCode() {
		return handlerCode;
	}
	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getXinHuaComCode() {
		return xinHuaComCode;
	}
	public void setXinHuaComCode(String xinHuaComCode) {
		this.xinHuaComCode = xinHuaComCode;
	}
	public String getXinHuaUserCode() {
		return xinHuaUserCode;
	}
	public void setXinHuaUserCode(String xinHuaUserCode) {
		this.xinHuaUserCode = xinHuaUserCode;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getIsSaleMan() {
		return isSaleMan;
	}
	public void setIsSaleMan(String isSaleMan) {
		this.isSaleMan = isSaleMan;
	}
	public String getSalaryPassword() {
		return salaryPassword;
	}
	public void setSalaryPassword(String salaryPassword) {
		this.salaryPassword = salaryPassword;
	}
	public String getXinHuaBusinessNature() {
		return xinHuaBusinessNature;
	}
	public void setXinHuaBusinessNature(String xinHuaBusinessNature) {
		this.xinHuaBusinessNature = xinHuaBusinessNature;
	}
	public String getXinHuaAgentCode() {
		return xinHuaAgentCode;
	}
	public void setXinHuaAgentCode(String xinHuaAgentCode) {
		this.xinHuaAgentCode = xinHuaAgentCode;
	}
	public String getXinHuaAgreementNo() {
		return xinHuaAgreementNo;
	}
	public void setXinHuaAgreementNo(String xinHuaAgreementNo) {
		this.xinHuaAgreementNo = xinHuaAgreementNo;
	}
	public String getXinHuaUserName() {
		return xinHuaUserName;
	}
	public void setXinHuaUserName(String xinHuaUserName) {
		this.xinHuaUserName = xinHuaUserName;
	}
	/**
	 * @return the isAuditMan
	 */
	public String getIsAuditMan() {
		return isAuditMan;
	}
	/**
	 * @param isAuditMan the isAuditMan to set
	 */
	public void setIsAuditMan(String isAuditMan) {
		this.isAuditMan = isAuditMan;
	}

	
}
