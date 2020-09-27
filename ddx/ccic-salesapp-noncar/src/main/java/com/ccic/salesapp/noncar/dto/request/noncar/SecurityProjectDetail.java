package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * ֤ȯ��������Ŀ
 * @author Administrator
 *
 */
public class SecurityProjectDetail {
	private Date listDate ;//��ʱ��
	private String listPlace ;//�еص�
	private Double minSponsorFee ;//�ͳ�����������
	private String projectName ;//������/������/��Ŀ����
	private Double raiseAmount ;//���ʽ��
	private Double sponsorFee ;//����������
	private String stockCode ;//Ʊ����
	private Long sequenceNumber ;//���
	private Map<String, String> tempData;// ��ʱ����
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Date getListDate() {
		return listDate;
	}
	public void setListDate(Date listDate) {
		this.listDate = listDate;
	}
	public String getListPlace() {
		return listPlace;
	}
	public void setListPlace(String listPlace) {
		this.listPlace = listPlace;
	}
	public Double getMinSponsorFee() {
		return minSponsorFee;
	}
	public void setMinSponsorFee(Double minSponsorFee) {
		this.minSponsorFee = minSponsorFee;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Double getRaiseAmount() {
		return raiseAmount;
	}
	public void setRaiseAmount(Double raiseAmount) {
		this.raiseAmount = raiseAmount;
	}
	public Double getSponsorFee() {
		return sponsorFee;
	}
	public void setSponsorFee(Double sponsorFee) {
		this.sponsorFee = sponsorFee;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	

}
