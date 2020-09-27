package com.ccic.salesapp.performance.domain.vo.response;

import java.util.List;

import com.ccic.salesapp.performance.domain.vo.dto.DataVO;
import com.ccic.salesapp.performance.domain.vo.dto.PackVO;



public class FeePayDetailResponseVO extends DataVO {
    private List<PackVO> packs;
    
	private String sumFeePayMonth;		//月提现总额
	private String sumFeeAccountMonth;	//月到账总额
	private String sumTaxDeductionMonth;//月扣税总额
	
	
	
	public String getSumFeePayMonth() {
		return sumFeePayMonth;
	}
	public void setSumFeePayMonth(String sumFeePayMonth) {
		this.sumFeePayMonth = sumFeePayMonth;
	}
	public String getSumFeeAccountMonth() {
		return sumFeeAccountMonth;
	}
	public void setSumFeeAccountMonth(String sumFeeAccountMonth) {
		this.sumFeeAccountMonth = sumFeeAccountMonth;
	}
	public String getSumTaxDeductionMonth() {
		return sumTaxDeductionMonth;
	}
	public void setSumTaxDeductionMonth(String sumTaxDeductionMonth) {
		this.sumTaxDeductionMonth = sumTaxDeductionMonth;
	}
	public List<PackVO> getPacks() {
		return packs;
	}
	public void setPacks(List<PackVO> packs) {
		this.packs = packs;
	}
    
}
