package com.example.tlyancustomer;

import java.io.Serializable;
import java.util.List;

public class MessageSendVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String userCode;

    private String message;

    private String messageType;

    private String source = "5";

    private String extendMessage;

    private String bizNo;

    private String title;

    private String subTitle;

    private List<String> userCodes;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getExtendMessage() {
		return extendMessage;
	}

	public void setExtendMessage(String extendMessage) {
		this.extendMessage = extendMessage;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public List<String> getUserCodes() {
		return userCodes;
	}

	public void setUserCodes(List<String> userCodes) {
		this.userCodes = userCodes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
