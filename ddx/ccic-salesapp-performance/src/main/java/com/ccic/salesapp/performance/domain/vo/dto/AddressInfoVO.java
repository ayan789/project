package com.ccic.salesapp.performance.domain.vo.dto;
import java.io.Serializable;

/**
 * 地址信息
 * @author Administrator
 *
 */
public class AddressInfoVO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	
	private String addressCode;//邮政编码
	private String addressName;//地址
	private String provinceCode;//省份代码
	private String prefectureCode;//辖区代码
	private String countyCode; //县级代码
	private String comcode;//机构代码
	private String detailInfo;//详细地址（新核心）
	
	
	public String getComcode() {
		return comcode;
	}
	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getPrefectureCode() {
		return prefectureCode;
	}
	public void setPrefectureCode(String prefectureCode) {
		this.prefectureCode = prefectureCode;
	}
	public String getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	public String getDetailInfo() {
		return detailInfo;
	}
	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}	
	
	
}

