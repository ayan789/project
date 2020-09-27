package com.ccic.salesapp.noncar.dto.request.accidentquote;

/**
 * �������ַ��Ϣ�б�
 * @author Administrator
 *
 */
public class AddressDetail {
	private Long  sequenceNumber;//���
	private String  regionCode;//��������
	private String  fullAddress;//��ַ
	
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

}
