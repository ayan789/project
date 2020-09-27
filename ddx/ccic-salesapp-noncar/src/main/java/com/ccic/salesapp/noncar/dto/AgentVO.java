package com.ccic.salesapp.noncar.dto;
import java.util.List;

import com.ccic.salesapp.noncar.dto.request.ExtendInfoVO;



public class AgentVO extends DataVO {
	/***
	 * 
	 */
	private String certificateNo;				//许可证号
	private String agencyCode;					//代理人代码
	private String agencyName;					//代理人名字
	private List<ExtendInfoVO> extendInfo ;		//扩展信息
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public List<ExtendInfoVO> getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(List<ExtendInfoVO> extendInfo) {
		this.extendInfo = extendInfo;
	}
	
}
