package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Map;


/**
 * 渠道信息
 * @author Administrator
 *
 */
public class ChannelOpInfo {
	private String agentQualificationNo   ;//代理人资格证号
	private Long approveUserId          ;//业务审批人员
	private String channelCode            ;//上海地区的销售渠道代码
	private String channelComCode         ;//渠道机构代码
	private String channelComName         ;//渠道机构名称
	private String channelName            ;//渠道名称
//	private Long channelOpId            ;//渠道操作人员Id
	private String channelOpName          ;//渠道操作人员姓名
	private String channelPlanContent     ;//渠道套餐内容
	private String channelPlanName        ;//渠道套餐名称
	private String channelProductCode     ;//渠道产品代码，外部渠道产品，不需要码表
	private String channelSeqNo           ;//渠道交易流水号
	private String contactTel             ;//联系电话
	private Long contractId             ;//合同ID(B2B)
	private String activityCode           ;//活动标识代码
	private String exhibitionCertificateNo;//展业证号
	private String channelProjectType     ;//项目类别
	private String intermediaryAddress    ;//中介机构地址
	private String intermediaryContactMode;//中介机构联系方式
	private String makeChannel            ;//出单渠道
	private String occupationLicNo        ;//职业证号
	private String operatorCode           ;//渠道操作员代码，非码表
	private String regionCode             ;//地区代码
	private String salesComAddress        ;//销售机构地址
	private String salesOccupationLicNo   ;//销售人员职业证号
	private String salesmanName           ;//销售人员姓名
	private String thirdPartyHandlerName  ;//第三方业务员姓名
	private String thirdPartyHandlerNo    ;//第三方业务员工号
	private String trxCode                ;//渠道交易代码
	private String trxDate                ;//渠道交易日期
	private Long sequenceNumber;//序号
	private Map<String,String> tempData; //临时数据
	
	public String getAgentQualificationNo() {
		return agentQualificationNo;
	}
	public void setAgentQualificationNo(String agentQualificationNo) {
		this.agentQualificationNo = agentQualificationNo;
	}
	public Long getApproveUserId() {
		return approveUserId;
	}
	public void setApproveUserId(Long approveUserId) {
		this.approveUserId = approveUserId;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getChannelComCode() {
		return channelComCode;
	}
	public void setChannelComCode(String channelComCode) {
		this.channelComCode = channelComCode;
	}
	public String getChannelComName() {
		return channelComName;
	}
	public void setChannelComName(String channelComName) {
		this.channelComName = channelComName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
//	public Long getChannelOpId() {
//		return channelOpId;
//	}
//	public void setChannelOpId(Long channelOpId) {
//		this.channelOpId = channelOpId;
//	}
	public String getChannelOpName() {
		return channelOpName;
	}
	public void setChannelOpName(String channelOpName) {
		this.channelOpName = channelOpName;
	}
	public String getChannelPlanContent() {
		return channelPlanContent;
	}
	public void setChannelPlanContent(String channelPlanContent) {
		this.channelPlanContent = channelPlanContent;
	}
	public String getChannelPlanName() {
		return channelPlanName;
	}
	public void setChannelPlanName(String channelPlanName) {
		this.channelPlanName = channelPlanName;
	}
	public String getChannelProductCode() {
		return channelProductCode;
	}
	public void setChannelProductCode(String channelProductCode) {
		this.channelProductCode = channelProductCode;
	}
	public String getChannelSeqNo() {
		return channelSeqNo;
	}
	public void setChannelSeqNo(String channelSeqNo) {
		this.channelSeqNo = channelSeqNo;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public String getExhibitionCertificateNo() {
		return exhibitionCertificateNo;
	}
	public void setExhibitionCertificateNo(String exhibitionCertificateNo) {
		this.exhibitionCertificateNo = exhibitionCertificateNo;
	}
	public String getChannelProjectType() {
		return channelProjectType;
	}
	public void setChannelProjectType(String channelProjectType) {
		this.channelProjectType = channelProjectType;
	}
	public String getIntermediaryAddress() {
		return intermediaryAddress;
	}
	public void setIntermediaryAddress(String intermediaryAddress) {
		this.intermediaryAddress = intermediaryAddress;
	}
	public String getIntermediaryContactMode() {
		return intermediaryContactMode;
	}
	public void setIntermediaryContactMode(String intermediaryContactMode) {
		this.intermediaryContactMode = intermediaryContactMode;
	}
	public String getMakeChannel() {
		return makeChannel;
	}
	public void setMakeChannel(String makeChannel) {
		this.makeChannel = makeChannel;
	}
	public String getOccupationLicNo() {
		return occupationLicNo;
	}
	public void setOccupationLicNo(String occupationLicNo) {
		this.occupationLicNo = occupationLicNo;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getSalesComAddress() {
		return salesComAddress;
	}
	public void setSalesComAddress(String salesComAddress) {
		this.salesComAddress = salesComAddress;
	}
	public String getSalesOccupationLicNo() {
		return salesOccupationLicNo;
	}
	public void setSalesOccupationLicNo(String salesOccupationLicNo) {
		this.salesOccupationLicNo = salesOccupationLicNo;
	}
	public String getSalesmanName() {
		return salesmanName;
	}
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	public String getThirdPartyHandlerName() {
		return thirdPartyHandlerName;
	}
	public void setThirdPartyHandlerName(String thirdPartyHandlerName) {
		this.thirdPartyHandlerName = thirdPartyHandlerName;
	}
	public String getThirdPartyHandlerNo() {
		return thirdPartyHandlerNo;
	}
	public void setThirdPartyHandlerNo(String thirdPartyHandlerNo) {
		this.thirdPartyHandlerNo = thirdPartyHandlerNo;
	}
	public String getTrxCode() {
		return trxCode;
	}
	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	public String getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public ChannelOpInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
