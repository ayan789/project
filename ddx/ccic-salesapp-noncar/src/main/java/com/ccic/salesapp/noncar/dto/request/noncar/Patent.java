package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.Date;
import java.util.Map;

/**
 * ר���嵥��Ϣ
 * @author Administrator
 *
 */
public class Patent {
	private String paten ;//ר��Ȩ��
	private Date patentAnnouncedDate ;//ר��������
	private Date patentApplyDate ;//ר��������
	private String patentInventor ;//������/�����
	private String patentName ;//ר������
	private String patentNo ;//ר����
	private String patentType ;//ר������
	private Long sequenceNumber ;//���
	private Map<String, String> tempData;// ��ʱ����
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getPaten() {
		return paten;
	}
	public void setPaten(String paten) {
		this.paten = paten;
	}
	public Date getPatentAnnouncedDate() {
		return patentAnnouncedDate;
	}
	public void setPatentAnnouncedDate(Date patentAnnouncedDate) {
		this.patentAnnouncedDate = patentAnnouncedDate;
	}
	public Date getPatentApplyDate() {
		return patentApplyDate;
	}
	public void setPatentApplyDate(Date patentApplyDate) {
		this.patentApplyDate = patentApplyDate;
	}
	public String getPatentInventor() {
		return patentInventor;
	}
	public void setPatentInventor(String patentInventor) {
		this.patentInventor = patentInventor;
	}
	public String getPatentName() {
		return patentName;
	}
	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}
	public String getPatentNo() {
		return patentNo;
	}
	public void setPatentNo(String patentNo) {
		this.patentNo = patentNo;
	}
	public String getPatentType() {
		return patentType;
	}
	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}
	public Map<String, String> getTempData() {
		return tempData;
	}
	public void setTempData(Map<String, String> tempData) {
		this.tempData = tempData;
	}
	

}
