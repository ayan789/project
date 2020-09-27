package com.ccic.salesapp.noncar.dto;

import java.io.Serializable;



public class AgreementInfoVO extends DataVO implements Serializable {
	  private static final long serialVersionUID = 1L;
	    
	    private String agreementNo;//Э�����
	    private String agreementName;//Э������
	    
		public String getAgreementNo() {
			return agreementNo;
		}
		public void setAgreementNo(String agreementNo) {
			this.agreementNo = agreementNo;
		}
		public String getAgreementName() {
			return agreementName;
		}
		public void setAgreementName(String agreementName) {
			this.agreementName = agreementName;
		}
}
