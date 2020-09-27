package com.ccic.salesapp.noncar.dto.request;



/****
 * 反洗钱信息
 *
 */
public class AntiInfoVO{
	/**
	 * 关系人标志
	 * 投保人：1，被保人：2
	 * 
	 * **/
	private String insuredFlag;
	
	/**
	 * 被保人同投保人
	 * 是：1，否：2
	 * 
	 * **/
	private String equalAppliFlag;
	
	/**
	*性别
	* 1:男,2:女
	**/
	private String sex;
	
	/**年龄***/
	private String age;
    
	/**身份证件种类
	*01:外国护照,02:台胞证,03:港澳通行证,04:士兵证,05:军官证,06:出生证（未成年人）,07:驾驶证,08:居民身份证,09:户口本
	**/
	private String identityType;
	
	/**证件有效止期*
	**/
	private String identityEndDate;
	
	/**证件有效止期长期*
	*1:是,0:否
	**/
	private String identityIdEndDate;
	
	/**税务登记证号码***/
	private String taxRegistryNo;
	
	/**营业执照号码***/
	private String businessLicenseNo;
	
	/**营业执照有效起期***/
	private String licensenoStartdate;
	
	/**营业执照有效止期***/
	private String licensenoEndDate;
	
	/**营业执照有效长期*
	*1:是,0:否
	**/
	private String licensenoIdEndDate;
	
	/**经营范围***/
	private String businessRange;
	
	/**股东类型*
	*01:自然人控股,02:无法了解股权控制情况,03:非上市企业,04:股份合资企业,05:外商（含港澳台）独资,06:依法登记的各类组织,07:国家机关,08:事业单位,09:军队,10:上市公司,11:国有企业
	**/
	private String shareholderType;
	
	/**国籍
	*AFG:阿富汗,AGO:安哥拉,BOL:玻利维亚,CHN:中国,CUB:古巴,CYP:塞浦路斯,ECU:厄瓜多尔,ETH:埃塞俄比亚,GHA:加纳,IDN:印度尼西亚,IRN:伊朗,IRQ:伊拉克,KEN:肯尼亚,LKA:斯里兰卡,MMR:缅甸,NGA:尼日利亚,PAK:巴基斯坦,PRK:朝鲜,STP:圣多美和普林西比,SYR:叙利亚,THA:泰国,TKM:土库曼斯坦,TUR:土耳其,TZA:坦桑尼亚,UZB:乌兹别克斯坦,VNM:越南,YEM:也门
	**/
	private String nationality;
	
	/**单位性质*
	*自然人:01:无固定单位,02:个人自由职业,03:无职业,04:非上市企业,05:股份合资企业,06:外商（含港澳台）独资,07:依法登记的其他组织,08:国家机关,09:事业单位,10:军队,11:上市公司,12:国有企业
	
	*法人:01:未正式登记的组织,02:个体工商户,03:股份合资企业,04:非上市企业,05:外商（含港澳台）独资,06:依法登记的其他组织,07:国家机关,08:事业单位,09:军队,10:上市公司,11:国有企业
	**/
	private String unitNature;
	
	/**职业/行业*
	*
	*01:艺术品收藏,02:博彩,03:拍卖,04:废品收购,05:影视,06:娱乐,07:旅游,08:餐饮,09:零售,10:其他
	**/
	private String profession;
	
	/**职务*
	*01:外国政要关系人,02:政府/事业单位官员,03:国企高管,04:其他职务人员
	**/
	private String position;
	
	/**法人代表/负责人 姓名  ***/
	private String legalReprese;
	
	/** 法人代表/负责人 证件类型*
	*01:外国护照,02:台胞证,03:港澳通行证,04:士兵证,05:军官证,06:出生证（未成年人）,07:驾驶证,08:居民身份证,09:户口本
	*
	**/
	private String legalRepreseType;
	
	/**法人代表/负责人 证件号码***/
	private String legalRepreseNo;
	
	/**法人代表/负责人 证件有效止期***/
	private String legalRepreseED;
	
	/**法人代表/负责人 长期*
	*1:是,0:否
	**/
	private String legalRepreseIDED;
	
	/***控股股东/实际控制人 姓名**/
	private String controlHolder;
	
	/**控股股东/实际控制人 证件类型*
	*01:外国护照,02:台胞证,03:港澳通行证,04:士兵证,05:军官证,06:出生证（未成年人）,07:驾驶证,08:居民身份证,09:户口本
	**/
	private String controlHolderType;
	
	/**控股股东/实际控制人 证件号码***/
	private String controlHolderNo;
	
	/**控股股东/实际控制人 证件有效止期***/
	private String controlHolderED;
	
	/**控股股东/实际控制人 长期*
	*1:是,0:否
	**/
	private String controlHolderIDEd;
	
	/**授权经办人 姓名***/
	private String authorization;
	
	/**授权经办人 证件类型*
	*01:外国护照,02:台胞证,03:港澳通行证,04:士兵证,05:军官证,06:出生证（未成年人）,07:驾驶证,08:居民身份证,09:户口本
	**/
	private String authorizationType;
	
	/**授权经办人 证件号码***/
	private String authorizationNo;
	
	/**授权经办人 证件有效止期***/
	private String authorizationED;
	
	/**授权经办人 长期*
	*1:是,0:否
	**/
	private String authorizationIDED;
	
	/**代理人 信息 代理人身份*
	*01:本人,02:授权业务代表,03:亲属,04:其他
	**/
	private String agentType;
	
	/**代理人 信息 代理业务手续是否齐全*
	*01:齐全,02:待收集,03:材料缺失
	**/
	private String isComplete;
	
	/**代理人 信息 代理人姓名***/
	private String agentName;
	
	/**代理人 信息 联系方式***/
	private String agentPhone;
	
	/**代理人 信息 身份证明文件种类*
	*01:外国护照,02:台胞证,03:港澳通行证,04:士兵证,05:军官证,06:出生证（未成年人）,07:驾驶证,08:居民身份证,09:户口本
	**/
	private String agentIdentityType;
	
	/**代理人 信息 身份证明文件号码***/
	private String agentIdentityNo;
	
	/**代理人 信息 身份证明文件有效止期***/
	private String agentidentityEd;
	
	/**代理人 信息 长期*
	*1:是,0:否
	**/
	private String agentIdentityIdEd;
	
	/**代理人 信息 证件核查情况*
	*01:属实,02:涉嫌身份造假客户,03:实地核实客户,04:了解法定继承人以外的指定受益人情况的客户,05:匿名、假名,06:证件造假,07:证件失效,08:利用他人证件,09:犯罪嫌疑人,10:留存证件失效
	**/
	private String isIdentityCheck;
	

	public String getInsuredFlag() {
		return insuredFlag;
	}

	public void setInsuredFlag(String insuredFlag) {
		this.insuredFlag = insuredFlag;
	}

	public String getEqualAppliFlag() {
		return equalAppliFlag;
	}

	public void setEqualAppliFlag(String equalAppliFlag) {
		this.equalAppliFlag = equalAppliFlag;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityEndDate() {
		return identityEndDate;
	}

	public void setIdentityEndDate(String identityEndDate) {
		this.identityEndDate = identityEndDate;
	}

	public String getTaxRegistryNo() {
		return taxRegistryNo;
	}

	public void setTaxRegistryNo(String taxRegistryNo) {
		this.taxRegistryNo = taxRegistryNo;
	}

	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}

	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}

	public String getLicensenoStartdate() {
		return licensenoStartdate;
	}

	public void setLicensenoStartdate(String licensenoStartdate) {
		this.licensenoStartdate = licensenoStartdate;
	}

	public String getLicensenoEndDate() {
		return licensenoEndDate;
	}

	public void setLicensenoEndDate(String licensenoEndDate) {
		this.licensenoEndDate = licensenoEndDate;
	}

	public String getBusinessRange() {
		return businessRange;
	}

	public void setBusinessRange(String businessRange) {
		this.businessRange = businessRange;
	}

	public String getShareholderType() {
		return shareholderType;
	}

	public void setShareholderType(String shareholderType) {
		this.shareholderType = shareholderType;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getUnitNature() {
		return unitNature;
	}

	public void setUnitNature(String unitNature) {
		this.unitNature = unitNature;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLegalReprese() {
		return legalReprese;
	}

	public void setLegalReprese(String legalReprese) {
		this.legalReprese = legalReprese;
	}

	public String getLegalRepreseType() {
		return legalRepreseType;
	}

	public void setLegalRepreseType(String legalRepreseType) {
		this.legalRepreseType = legalRepreseType;
	}

	public String getLegalRepreseNo() {
		return legalRepreseNo;
	}

	public void setLegalRepreseNo(String legalRepreseNo) {
		this.legalRepreseNo = legalRepreseNo;
	}

	public String getLegalRepreseED() {
		return legalRepreseED;
	}

	public void setLegalRepreseED(String legalRepreseED) {
		this.legalRepreseED = legalRepreseED;
	}

	public String getLegalRepreseIDED() {
		return legalRepreseIDED;
	}

	public void setLegalRepreseIDED(String legalRepreseIDED) {
		this.legalRepreseIDED = legalRepreseIDED;
	}

	public String getControlHolder() {
		return controlHolder;
	}

	public void setControlHolder(String controlHolder) {
		this.controlHolder = controlHolder;
	}

	public String getControlHolderType() {
		return controlHolderType;
	}

	public void setControlHolderType(String controlHolderType) {
		this.controlHolderType = controlHolderType;
	}

	public String getControlHolderNo() {
		return controlHolderNo;
	}

	public void setControlHolderNo(String controlHolderNo) {
		this.controlHolderNo = controlHolderNo;
	}

	public String getControlHolderED() {
		return controlHolderED;
	}

	public void setControlHolderED(String controlHolderED) {
		this.controlHolderED = controlHolderED;
	}

	public String getControlHolderIDEd() {
		return controlHolderIDEd;
	}

	public void setControlHolderIDEd(String controlHolderIDEd) {
		this.controlHolderIDEd = controlHolderIDEd;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getAuthorizationType() {
		return authorizationType;
	}

	public void setAuthorizationType(String authorizationType) {
		this.authorizationType = authorizationType;
	}

	public String getAuthorizationNo() {
		return authorizationNo;
	}

	public void setAuthorizationNo(String authorizationNo) {
		this.authorizationNo = authorizationNo;
	}

	public String getAuthorizationED() {
		return authorizationED;
	}

	public void setAuthorizationED(String authorizationED) {
		this.authorizationED = authorizationED;
	}

	public String getAuthorizationIDED() {
		return authorizationIDED;
	}

	public void setAuthorizationIDED(String authorizationIDED) {
		this.authorizationIDED = authorizationIDED;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentPhone() {
		return agentPhone;
	}

	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}

	public String getAgentIdentityType() {
		return agentIdentityType;
	}

	public void setAgentIdentityType(String agentIdentityType) {
		this.agentIdentityType = agentIdentityType;
	}

	public String getAgentIdentityNo() {
		return agentIdentityNo;
	}

	public void setAgentIdentityNo(String agentIdentityNo) {
		this.agentIdentityNo = agentIdentityNo;
	}

	public String getAgentidentityEd() {
		return agentidentityEd;
	}

	public void setAgentidentityEd(String agentidentityEd) {
		this.agentidentityEd = agentidentityEd;
	}

	public String getAgentIdentityIdEd() {
		return agentIdentityIdEd;
	}

	public void setAgentIdentityIdEd(String agentIdentityIdEd) {
		this.agentIdentityIdEd = agentIdentityIdEd;
	}

	public String getIsIdentityCheck() {
		return isIdentityCheck;
	}

	public void setIsIdentityCheck(String isIdentityCheck) {
		this.isIdentityCheck = isIdentityCheck;
	}

	public String getIdentityIdEndDate() {
		return identityIdEndDate;
	}

	public void setIdentityIdEndDate(String identityIdEndDate) {
		this.identityIdEndDate = identityIdEndDate;
	}

	public String getLicensenoIdEndDate() {
		return licensenoIdEndDate;
	}

	public void setLicensenoIdEndDate(String licensenoIdEndDate) {
		this.licensenoIdEndDate = licensenoIdEndDate;
	}
    
}
