package com.ccic.salesapp.noncar.outService.esb.userservice.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;

/**
 * <p>
 * Java class for UserInquiryResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;UserInquiryResponse&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref=&quot;{http://service.ccic.com/userservice/bean}responseHead&quot;/&gt;
 *         &lt;element ref=&quot;{http://service.ccic.com/userservice/bean}responseBody&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserInquiryResponse", propOrder = { "responseHead",
		"responseBody" })
public class UserInquiryResponse {

	@XmlElement(required = true)
	protected ResponseHeadDTO responseHead;
	protected UserInquiryResponseDTO responseBody;

	/**
	 * Gets the value of the responseHead property.
	 * 
	 * @return possible object is {@link ResponseHeadDTO }
	 * 
	 */
	public ResponseHeadDTO getResponseHead() {
		return responseHead;
	}

	/**
	 * Sets the value of the responseHead property.
	 * 
	 * @param value
	 *            allowed object is {@link ResponseHeadDTO }
	 * 
	 */
	public void setResponseHead(ResponseHeadDTO value) {
		this.responseHead = value;
	}

	/**
	 * Gets the value of the responseBody property.
	 * 
	 * @return possible object is {@link UserInquiryResponseDTO }
	 * 
	 */
	public UserInquiryResponseDTO getResponseBody() {
		return responseBody;
	}

	/**
	 * Sets the value of the responseBody property.
	 * 
	 * @param value
	 *            allowed object is {@link UserInquiryResponseDTO }
	 * 
	 */
	public void setResponseBody(UserInquiryResponseDTO value) {
		this.responseBody = value;
	}

}
