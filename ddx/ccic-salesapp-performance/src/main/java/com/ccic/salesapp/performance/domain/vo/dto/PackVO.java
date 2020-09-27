package com.ccic.salesapp.performance.domain.vo.dto;
import java.util.List;
import com.ccic.salesapp.performance.domain.vo.request.PlanFeePayVO;

public class PackVO {
	private String packSumFee;
	private String packNo;
	private String packageDate;
	private List<PlanFeePayVO> planFeePays;
	

	public String getPackSumFee() {
		return packSumFee;
	}

	public void setPackSumFee(String packSumFee) {
		this.packSumFee = packSumFee;
	}

	public String getPackNo() {
		return packNo;
	}

	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}

	public String getPackageDate() {
		return packageDate;
	}

	public void setPackageDate(String packageDate) {
		this.packageDate = packageDate;
	}

	public List<PlanFeePayVO> getPlanFeePays() {
		return planFeePays;
	}

	public void setPlanFeePays(List<PlanFeePayVO> planFeePays) {
		this.planFeePays = planFeePays;
	}
    
}
