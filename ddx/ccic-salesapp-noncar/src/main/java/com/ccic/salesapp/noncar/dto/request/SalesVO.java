package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;




/**
 * 销售人员
 * @author Administrator
 *
 */
public class SalesVO extends DataVO {

	private String salesManName  ;//销售人员姓名          
	private String mobile;//联系方式                      
	private String occupationID  ;//展业证号              
	private String vocationCode  ;//经办人职业证号(执业证书编号)
	private String agentPermitNo ;//个人代理人资格证号    
	private String salePolicyAddr;//出单地址              
	private String intermediaryAddr;//中介机构地址        
	private String intermediaryContNumb;//中介机构联系方式
	private String validDate ;//生效日期
	private String salemanIdentifyNumber;//销售人员身份证号
	private String salesAgencyName; //销售机构名称	
	public String getSalesManName() {
		return salesManName;
	}
	public void setSalesManName(String salesManName) {
		this.salesManName = salesManName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOccupationID() {
		return occupationID;
	}
	public void setOccupationID(String occupationID) {
		this.occupationID = occupationID;
	}
	public String getVocationCode() {
		return vocationCode;
	}
	public void setVocationCode(String vocationCode) {
		this.vocationCode = vocationCode;
	}
	public String getAgentPermitNo() {
		return agentPermitNo;
	}
	public void setAgentPermitNo(String agentPermitNo) {
		this.agentPermitNo = agentPermitNo;
	}
	public String getSalePolicyAddr() {
		return salePolicyAddr;
	}
	public void setSalePolicyAddr(String salePolicyAddr) {
		this.salePolicyAddr = salePolicyAddr;
	}
	public String getIntermediaryAddr() {
		return intermediaryAddr;
	}
	public void setIntermediaryAddr(String intermediaryAddr) {
		this.intermediaryAddr = intermediaryAddr;
	}
	public String getIntermediaryContNumb() {
		return intermediaryContNumb;
	}
	public void setIntermediaryContNumb(String intermediaryContNumb) {
		this.intermediaryContNumb = intermediaryContNumb;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public String getSalemanIdentifyNumber() {
		return salemanIdentifyNumber;
	}
	public void setSalemanIdentifyNumber(String salemanIdentifyNumber) {
		this.salemanIdentifyNumber = salemanIdentifyNumber;
	}
	public String getSalesAgencyName() {
		return salesAgencyName;
	}
	public void setSalesAgencyName(String salesAgencyName) {
		this.salesAgencyName = salesAgencyName;
	}

}
