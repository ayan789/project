package com.ccic.salesapp.performance.domain.vo.response;
import java.util.List;
import com.ccic.salesapp.performance.domain.vo.dto.DataVO;
import com.ccic.salesapp.performance.domain.vo.request.PlanFeePayVO;



public class EnchashmentResponseVO extends DataVO {
	private String sumPlanFee;//手续费
	private String allFee;//手续费
	private List <PlanFeePayVO> policyList;//可提现保单列表
	
	
	public String getSumPlanFee() {
		return sumPlanFee;
	}
	public void setSumPlanFee(String sumPlanFee) {
		this.sumPlanFee = sumPlanFee;
	}
	public List<PlanFeePayVO> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<PlanFeePayVO> policyList) {
		this.policyList = policyList;
	}
	public String getAllFee() {
		return allFee;
	}
	public void setAllFee(String allFee) {
		this.allFee = allFee;
	}
	
	
     
}
