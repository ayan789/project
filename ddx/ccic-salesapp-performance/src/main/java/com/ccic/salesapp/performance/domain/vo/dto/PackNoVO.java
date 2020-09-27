package com.ccic.salesapp.performance.domain.vo.dto;
import java.util.Date;

public class PackNoVO {
	private String packNo;//批次号
	private Date packageDate;//打包日期
	private Double packSumFee;//批次合计

	public Double getPackSumFee() {
		return packSumFee;
	}
	public void setPackSumFee(Double packSumFee) {
		this.packSumFee = packSumFee;
	}
	public String getPackNo() {
		return packNo;
	}
	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}
	public Date getPackageDate() {
		return packageDate;
	}
	public void setPackageDate(Date packageDate) {
		this.packageDate = packageDate;
	}

}
