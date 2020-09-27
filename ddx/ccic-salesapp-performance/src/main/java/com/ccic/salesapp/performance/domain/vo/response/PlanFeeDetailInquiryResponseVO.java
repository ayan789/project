package com.ccic.salesapp.performance.domain.vo.response;
import java.util.List;

import com.ccic.salesapp.performance.domain.vo.dto.DataVO;
import com.ccic.salesapp.performance.domain.vo.dto.PlanFeeDetailVO;
import com.ccic.salesapp.performance.domain.vo.request.PageVO;




public class PlanFeeDetailInquiryResponseVO extends DataVO {
	
	private List <PlanFeeDetailVO> planFeeDetails;//手续费明细信息
	private String planFeeSum;//手续费明细合计
	private PageVO page;
	public List<PlanFeeDetailVO> getPlanFeeDetails() {
		return planFeeDetails;
	}
	public void setPlanFeeDetails(List<PlanFeeDetailVO> planFeeDetails) {
		this.planFeeDetails = planFeeDetails;
	}
	public String getPlanFeeSum() {
		return planFeeSum;
	}
	public void setPlanFeeSum(String planFeeSum) {
		this.planFeeSum = planFeeSum;
	}
	public PageVO getPage() {
		return page;
	}
	public void setPage(PageVO page) {
		this.page = page;
	}
	


	
		
}
