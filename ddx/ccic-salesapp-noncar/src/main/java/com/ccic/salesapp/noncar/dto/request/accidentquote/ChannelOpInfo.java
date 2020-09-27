package com.ccic.salesapp.noncar.dto.request.accidentquote;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * �ⲿ��������ʱ�ĸ�����Ϣ		
 * @author Administrator
 *
 */
@Data
public class ChannelOpInfo {
	//渠道代码
		private String channelCode;
		//渠道机构代码
		private String channelComCode;
		//渠道机构名称
		private String channelComName;
		//渠道名称
		private String channelName;
		//渠道产品代码
		private String channelProductCode;
		//渠道交易流水号
		private String channelSeqNo;
		//渠道交易日期
		private String trxDate;
		//代理人资格证号
		private String agentQualificationNo;
		//职业证号
		private String occupationLicNo;
		//渠道操作员代码
		private String operatorCode;
		//销售人员姓名
		private String salesmanName;
		//渠道交易代码
		private String trxCode;
		//销售人员职业证号
		private String salesOccupationLicNo;
		//销售机构地址
		private String salesComAddress;
		//联系电话
		private String contactTel;
		//展业证号
		private String exhibitionCertificateNo;
		//中介机构联系方式
		private String intermediaryContactMode;
		//中介机构地址
		private String intermediaryAddress;
		//地区代码
//		private String regionCode;
		
		@ApiModelProperty("第三方业务员工号")
		private String thirdPartyHandlerNo;// 第三方业务员工号
		@ApiModelProperty("第三方业务员姓名")
		private String thirdPartyHandlerName;// 第三方业务员姓名
		
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
	
	public String getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}
	public String getAgentQualificationNo() {
		return agentQualificationNo;
	}
	public void setAgentQualificationNo(String agentQualificationNo) {
		this.agentQualificationNo = agentQualificationNo;
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
	public String getSalesmanName() {
		return salesmanName;
	}
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	public String getTrxCode() {
		return trxCode;
	}
	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}
	public String getSalesOccupationLicNo() {
		return salesOccupationLicNo;
	}
	public void setSalesOccupationLicNo(String salesOccupationLicNo) {
		this.salesOccupationLicNo = salesOccupationLicNo;
	}
	public String getSalesComAddress() {
		return salesComAddress;
	}
	public void setSalesComAddress(String salesComAddress) {
		this.salesComAddress = salesComAddress;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getExhibitionCertificateNo() {
		return exhibitionCertificateNo;
	}
	public void setExhibitionCertificateNo(String exhibitionCertificateNo) {
		this.exhibitionCertificateNo = exhibitionCertificateNo;
	}
	public String getIntermediaryContactMode() {
		return intermediaryContactMode;
	}
	public void setIntermediaryContactMode(String intermediaryContactMode) {
		this.intermediaryContactMode = intermediaryContactMode;
	}
	public String getIntermediaryAddress() {
		return intermediaryAddress;
	}
	public void setIntermediaryAddress(String intermediaryAddress) {
		this.intermediaryAddress = intermediaryAddress;
	}


}
