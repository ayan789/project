package com.ccic.salesapp.performance.dto.request;
import com.ccic.salesapp.performance.domain.vo.dto.DataVO;

public class CustCoreInfoQuoteRequestVO extends DataVO{
	private String orderNo;//������
	private String desForwardDate;//ת��ʱ��
	
	private String custNo;	//�ͻ���
	private String custName;//�ͻ�����
	private String startDate;		//������
	
	private String riskCode;		//�������ִ���
	private String primium;			//���ձ���
	private String riskCodeE;		//���������ִ���
	private String primiumE;		//�����ձ���
	private String riskCodeY;		//�泵�����ִ���
	private String primiumY;		//�泵�ձ���
	
	private String comCode;//��������
	
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getDesForwardDate() {
		return desForwardDate;
	}
	public void setDesForwardDate(String desForwardDate) {
		this.desForwardDate = desForwardDate;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getPrimium() {
		return primium;
	}
	public void setPrimium(String primium) {
		this.primium = primium;
	}
	public String getRiskCodeE() {
		return riskCodeE;
	}
	public void setRiskCodeE(String riskCodeE) {
		this.riskCodeE = riskCodeE;
	}
	public String getPrimiumE() {
		return primiumE;
	}
	public void setPrimiumE(String primiumE) {
		this.primiumE = primiumE;
	}
	public String getRiskCodeY() {
		return riskCodeY;
	}
	public void setRiskCodeY(String riskCodeY) {
		this.riskCodeY = riskCodeY;
	}
	public String getPrimiumY() {
		return primiumY;
	}
	public void setPrimiumY(String primiumY) {
		this.primiumY = primiumY;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	
}
