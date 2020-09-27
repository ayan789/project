package com.ccic.salesapp.noncar.dto.request;
import java.util.ArrayList;
import java.util.List;
import com.ccic.salesapp.noncar.dto.AgentVO;
import com.ccic.salesapp.noncar.dto.CarTaxVO;
import com.ccic.salesapp.noncar.dto.DataVO;
import com.ccic.salesapp.noncar.dto.DeviceVO;
import com.ccic.salesapp.noncar.dto.OtherVO;
import com.ccic.salesapp.noncar.dto.VatInfoVO;
import com.ccic.salesapp.noncar.dto.VehicleJingyouVO;


/**
 * 精确报价请求VO
 * @author Administrator
 *
 */
public class IPreciseQuoteRequestVO extends DataVO{

	private String staffCode;//员工代码 Y 
	private String orderNo;//订单号 第二次必传
	private String orderSource;//订单来源 M 后台赋值
	private String riskCode ;//险种代码 Y
	private String makeCo;//出单机构 Y -----------警惕出错
	private String comCode;//归属机构 Y
	private String handlerCode;//经办员 Y
	private String businessNature;//业务来源 后台赋值---0101-传统直销；1402-网销合作
	private String businessAttribute;//业务来源属性---- 后台赋值---销售支持业务来源属性为“0801”
	private String operateDate;//交易日期 Y
	private String startDate;//起保日期 Y
	private String endDate;//终保日期 Y
	private String shortRateFlag;//商业险短期费率计算方式 
	private String quotationType;//报价类型  详见代码2.1.31-----------123，指单独交强险 或者 商业险 或者合并，与DDH DDK无关
	private String operatorCode ;//操作员代码 Y
	private String isSpecial;//人工核保业务标志 Y 详见代码2.1.32
	private String specialType;//人工核保业务类型 
	private String lastYearEndDate;//上年商业险保险止期
	private String isRenewed;//是否续保 Y 详见代码2.1.32
	private String renewPolicyNo;//续保保单号 续保时非空
	private String contractNo;//合同号
	private String expectDiscount;//期望折扣率
	private String limitType;//底限折扣类型 详见代码2.1.33，默认为：3
	private String isAboutAgri;//涉农标志 false/true
	private String handler2Code;//经办员2 Y
	private String businessNature2;// Y 业务来源2
	private String quotationMode;//报价模式  1-传统报价，2-网销报价，3-同时报价 2013-12-17比价接口增加 ,4-代理报价
	
	private String isAgentMode;//代理报价模式    1-代理报价  
	
	private String proposalNo;//投保单号
	private String policyNo;//保单号
	private String payApplyNo;//支付号
	private String identityCode;//支付验证码
	private String ciDiscount;//交强险折扣
	private String biDiscount;//商业险折扣
	private String sumCartax;//车船税
	private String ciSumPremium;//交强险保费
	private String biSumPremium;//商业险保费
	private String totalFee;//车险合计
	private String handlerName;//经办员姓名
	private String lossNumber;//出险次数------2014-03-14订单详情 增加
	
	private String queryAreaCode;//指定查询地区代码-----2015-4-4 增加 （商车改革）
	
	private String addressName;//出单点地址
	
	private String handler2Name;//经办员2姓名
	
	private String jointSaleFlag;//联合销售标识（1-是；0-否）
	
	private List<JointSaleVO> jointSales;//联合销售信息
	private VatInfoVO vatInfo;//增值税信息
	
	private SalesVO sales;//销售人员信息  订单详情使用
	
	private AutoRenewalVO autoRenewal;//自动续保信息
	
	private String status;//订单状态
	private String sumPremium;//合计保费
	private String desProposalNo;//加密后的投保单号
	private String quoteCarType ;//1.家用车 2.非家用车,3-o2o
	private String saleChannel ;//直销/直团渠道
	private String isStoreSale ;//是否门店直销 1-是 0-否
	private String tempSaveFlag;//暂存标记1-是
	private String operatorPhone;//归属经办人电话
	private String machineCode;//机器编码
	private String machinecodeStatus;//状态
	private String flag;//标志
	private String isElectrInsurance;//是否是电子投保

		//新华新增字段
	private String orderType; //订单类型 0 移动销售出单  1新华出单
	private String xinHuaUserCode;//新华用户code
	
	//新核心新增字段
	private String newcoreFlag;//新核心出单标志	
	//新核心拆单字段，如果单交单商该字段为空，交商混保时商业险为主，交强险为关联投保单
	private String conProposalNo;//交强险投保单号
	private String conPolicyNo;//交强险保单号
	
	private String payFlag;//能否支付：1是0否
	//新核心交强险起保时间
	private String trafficStartDate;
    //新核心交强险终保时间
	private String trafficEndDate;
	//配送信息
	private PostInfoVO postInfo;
	//投保地区代码
	private String insAreaCode;
	
	public String getConProposalNo() {
		return conProposalNo;
	}
	public void setConProposalNo(String conProposalNo) {
		this.conProposalNo = conProposalNo;
	}
	public String getConPolicyNo() {
		return conPolicyNo;
	}
	public void setConPolicyNo(String conPolicyNo) {
		this.conPolicyNo = conPolicyNo;
	}
	public String getNewcoreFlag() {
		return newcoreFlag;
	}
	public void setNewcoreFlag(String newcoreFlag) {
		this.newcoreFlag = newcoreFlag;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getXinHuaUserCode() {
		return xinHuaUserCode;
	}
	public void setXinHuaUserCode(String xinHuaUserCode) {
		this.xinHuaUserCode = xinHuaUserCode;
	}
	
	public String getIsElectrInsurance() {
		return isElectrInsurance;
	}
	public void setIsElectrInsurance(String isElectrInsurance) {
		this.isElectrInsurance = isElectrInsurance;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMachinecodeStatus() {
		return machinecodeStatus;
	}
	public void setMachinecodeStatus(String machinecodeStatus) {
		this.machinecodeStatus = machinecodeStatus;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getQuoteCarType() {
		return quoteCarType;
	}
	public void setQuoteCarType(String quoteCarType) {
		this.quoteCarType = quoteCarType;
	}
	public String getQueryAreaCode() {
		return queryAreaCode;
	}
	public void setQueryAreaCode(String queryAreaCode) {
		this.queryAreaCode = queryAreaCode;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getIsAgentMode() {
		return isAgentMode;
	}
	public void setIsAgentMode(String isAgentMode) {
		this.isAgentMode = isAgentMode;
	}
	public String getCiSumPremium() {
		return ciSumPremium;
	}
	public void setCiSumPremium(String ciSumPremium) {
		this.ciSumPremium = ciSumPremium;
	}
	public String getBiSumPremium() {
		return biSumPremium;
	}
	public void setBiSumPremium(String biSumPremium) {
		this.biSumPremium = biSumPremium;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPayApplyNo() {
		return payApplyNo;
	}
	public void setPayApplyNo(String payApplyNo) {
		this.payApplyNo = payApplyNo;
	}
	public String getIdentityCode() {
		return identityCode;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public String getCiDiscount() {
		return ciDiscount;
	}
	public void setCiDiscount(String ciDiscount) {
		this.ciDiscount = ciDiscount;
	}
	public String getBiDiscount() {
		return biDiscount;
	}
	public void setBiDiscount(String biDiscount) {
		this.biDiscount = biDiscount;
	}
	public String getSumCartax() {
		return sumCartax;
	}
	public void setSumCartax(String sumCartax) {
		this.sumCartax = sumCartax;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	public String getLossNumber() {
		return lossNumber;
	}
	public void setLossNumber(String lossNumber) {
		this.lossNumber = lossNumber;
	}
	public List<EngageVO> getEngage() {
		return engage;
	}
	public void setEngage(List<EngageVO> engage) {
		this.engage = engage;
	}
	public ProjectChannelVO getProjectChannel() {
		return projectChannel;
	}
	public void setProjectChannel(ProjectChannelVO projectChannel) {
		this.projectChannel = projectChannel;
	}
	private CarVO car;//车辆信息 Y
	private List<DeviceVO> devices;//新增设备
	private CarTaxVO carTax;//车船税
	private List<PrivyVO> privy;//联系人列表 Y
	private List<KindVO> kinds;//险别信息 Y
	private List<CheckVO> checks;//查询校验
	private VehicleJingyouVO vehicleJingyou;//精友整车库 Domain Y
	private OtherVO other;//其他信息
	private List<ExtendInfoVO> extendInfo;//扩展信息 
	private AgentVO agent;//代理人信息
	private List<CommissionVO> commissions;//手续费信息
	
	List<EngageVO> engage = new ArrayList<EngageVO>();//特别约定--------订单含
	ProjectChannelVO projectChannel = new ProjectChannelVO();//渠道信息----订单或许有
	
	public AgentVO getAgent() {
		return agent;
	}
	public void setAgent(AgentVO agent) {
		this.agent = agent;
	}
	
	public List<CommissionVO> getCommissions() {
		return commissions;
	}
	public void setCommissions(List<CommissionVO> commissions) {
		this.commissions = commissions;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getMakeCo() {
		return makeCo;
	}
	public void setMakeCo(String makeCo) {
		this.makeCo = makeCo;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getHandlerCode() {
		return handlerCode;
	}
	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode;
	}
	public String getBusinessNature() {
		return businessNature;
	}
	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}
	public String getBusinessAttribute() {
		return businessAttribute;
	}
	public void setBusinessAttribute(String businessAttribute) {
		this.businessAttribute = businessAttribute;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getShortRateFlag() {
		return shortRateFlag;
	}
	public void setShortRateFlag(String shortRateFlag) {
		this.shortRateFlag = shortRateFlag;
	}
	public String getQuotationType() {
		return quotationType;
	}
	public void setQuotationType(String quotationType) {
		this.quotationType = quotationType;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getIsSpecial() {
		return isSpecial;
	}
	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}
	public String getSpecialType() {
		return specialType;
	}
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	public String getLastYearEndDate() {
		return lastYearEndDate;
	}
	public void setLastYearEndDate(String lastYearEndDate) {
		this.lastYearEndDate = lastYearEndDate;
	}
	public String getIsRenewed() {
		return isRenewed;
	}
	public void setIsRenewed(String isRenewed) {
		this.isRenewed = isRenewed;
	}
	public String getRenewPolicyNo() {
		return renewPolicyNo;
	}
	public void setRenewPolicyNo(String renewPolicyNo) {
		this.renewPolicyNo = renewPolicyNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getExpectDiscount() {
		return expectDiscount;
	}
	public void setExpectDiscount(String expectDiscount) {
		this.expectDiscount = expectDiscount;
	}
	public String getLimitType() {
		return limitType;
	}
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}
	public String getIsAboutAgri() {
		return isAboutAgri;
	}
	public void setIsAboutAgri(String isAboutAgri) {
		this.isAboutAgri = isAboutAgri;
	}
	public CarVO getCar() {
		return car;
	}
	public void setCar(CarVO car) {
		this.car = car;
	}
	public CarTaxVO getCarTax() {
		return carTax;
	}
	
	public void setCarTax(CarTaxVO carTax) {
		this.carTax = carTax;
	}
	public List<PrivyVO> getPrivy() {
		return privy;
	}
	public void setPrivy(List<PrivyVO> privy) {
		this.privy = privy;
	}
	
	public VehicleJingyouVO getVehicleJingyou() {
		return vehicleJingyou;
	}
	public void setVehicleJingyou(VehicleJingyouVO vehicleJingyou) {
		this.vehicleJingyou = vehicleJingyou;
	}
	public OtherVO getOther() {
		return other;
	}
	public void setOther(OtherVO other) {
		this.other = other;
	}
	public List<ExtendInfoVO> getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(
			List<ExtendInfoVO> extendInfo) {
		this.extendInfo = extendInfo;
	}
	public List<DeviceVO> getDevices() {
		return devices;
	}
	public void setDevices(List<DeviceVO> devices) {
		this.devices = devices;
	}
	public List<CheckVO> getChecks() {
		return checks;
	}
	public void setChecks(List<CheckVO> checks) {
		this.checks = checks;
	}
	public List<KindVO> getKinds() {
		return kinds;
	}
	public void setKinds(List<KindVO> kinds) {
		this.kinds = kinds;
	}
	public String getHandler2Code() {
		return handler2Code;
	}
	public void setHandler2Code(String handler2Code) {
		this.handler2Code = handler2Code;
	}
	public String getBusinessNature2() {
		return businessNature2;
	}
	public void setBusinessNature2(String businessNature2) {
		this.businessNature2 = businessNature2;
	}
	public String getQuotationMode() {
		return quotationMode;
	}
	public void setQuotationMode(String quotationMode) {
		this.quotationMode = quotationMode;
	}
	public String getHandler2Name() {
		return handler2Name;
	}
	public void setHandler2Name(String handler2Name) {
		this.handler2Name = handler2Name;
	}
	public String getJointSaleFlag() {
		return jointSaleFlag;
	}
	public void setJointSaleFlag(String jointSaleFlag) {
		this.jointSaleFlag = jointSaleFlag;
	}
	public List<JointSaleVO> getJointSales() {
		return jointSales;
	}
	public void setJointSales(List<JointSaleVO> jointSales) {
		this.jointSales = jointSales;
	}
	public VatInfoVO getVatInfo() {
		return vatInfo;
	}
	public void setVatInfo(VatInfoVO vatInfo) {
		this.vatInfo = vatInfo;
	}
	public SalesVO getSales() {
		return sales;
	}
	public void setSales(SalesVO sales) {
		this.sales = sales;
	}
	public AutoRenewalVO getAutoRenewal() {
		return autoRenewal;
	}
	public void setAutoRenewal(AutoRenewalVO autoRenewal) {
		this.autoRenewal = autoRenewal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSumPremium() {
		return sumPremium;
	}
	public void setSumPremium(String sumPremium) {
		this.sumPremium = sumPremium;
	}
	public String getDesProposalNo() {
		return desProposalNo;
	}
	public void setDesProposalNo(String desProposalNo) {
		this.desProposalNo = desProposalNo;
	}
	public String getIsStoreSale() {
		return isStoreSale;
	}
	public void setIsStoreSale(String isStoreSale) {
		this.isStoreSale = isStoreSale;
	}
	public String getTempSaveFlag() {
		return tempSaveFlag;
	}
	public void setTempSaveFlag(String tempSaveFlag) {
		this.tempSaveFlag = tempSaveFlag;
	}
	public String getOperatorPhone() {
		return operatorPhone;
	}
	public void setOperatorPhone(String operatorPhone) {
		this.operatorPhone = operatorPhone;
	}
	public String getSaleChannel() {
		return saleChannel;
	}
	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}
	public String getPayFlag() {
		return payFlag;
	}
	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}
	public String getTrafficStartDate() {
		return trafficStartDate;
	}
	public void setTrafficStartDate(String trafficStartDate) {
		this.trafficStartDate = trafficStartDate;
	}
	public String getTrafficEndDate() {
		return trafficEndDate;
	}
	public void setTrafficEndDate(String trafficEndDate) {
		this.trafficEndDate = trafficEndDate;
	}
	public PostInfoVO getPostInfo() {
		return postInfo;
	}
	public void setPostInfo(PostInfoVO postInfo) {
		this.postInfo = postInfo;
	}
	public String getInsAreaCode() {
		return insAreaCode;
	}
	public void setInsAreaCode(String insAreaCode) {
		this.insAreaCode = insAreaCode;
	}
	
}
