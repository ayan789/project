package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;





/**
 * 投保单查询接口
 * @author wangpeng
 *
 */
public class ICarApplicationSearchRequestVO extends DataVO{

	private String staffCode; //业务员代码
	private String applicationNo;//投保单号
	private String insuredName;//被保人姓名
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
}
