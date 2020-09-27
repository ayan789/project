package com.ccic.salesapp.performance.domain.vo.dto;



public class FeeQueryVO {
	Double sumBillFee;//提现总金额
	Double sumBillTax;//扣税总额
	Double sumAccountFee;//到账总金额

	public Double getSumBillFee() {
		return sumBillFee;
	}
	public void setSumBillFee(Double sumBillFee) {
		this.sumBillFee = sumBillFee;
	}
	public Double getSumBillTax() {
		return sumBillTax;
	}
	public void setSumBillTax(Double sumBillTax) {
		this.sumBillTax = sumBillTax;
	}
	public Double getSumAccountFee() {
		return sumAccountFee;
	}
	public void setSumAccountFee(Double sumAccountFee) {
		this.sumAccountFee = sumAccountFee;
	}
	
	
	public static void main(String[] args) {
		double d = 1.955555555;
//		String result = String.format("%.2f", d);
//		System.out.println(result);
		
		java.math.BigDecimal b = new java.math.BigDecimal(d);
		double myNum3 = b.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(myNum3);
	}

}
