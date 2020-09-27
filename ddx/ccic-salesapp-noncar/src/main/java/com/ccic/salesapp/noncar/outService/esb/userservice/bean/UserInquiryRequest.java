package com.ccic.salesapp.noncar.outService.esb.userservice.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;

/**
 * <p>
 * Java class for UserInquiryRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;UserInquiryRequest&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref=&quot;{http://service.ccic.com/userservice/bean}requestHead&quot;/&gt;
 *         &lt;element ref=&quot;{http://service.ccic.com/userservice/bean}requestBody&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserInquiryRequest", propOrder = { "requestHead",
		"requestBody" })
public class UserInquiryRequest {

	@XmlElement(required = true)
	protected RequestHeadDTO requestHead;
	@XmlElement(required = true)
	protected UserInquiryRequestDTO requestBody;

	/**
	 * Gets the value of the requestHead property.
	 * 
	 * @return possible object is {@link RequestHeadDTO }
	 * 
	 */
	public RequestHeadDTO getRequestHead() {
		return requestHead;
	}

	/**
	 * Sets the value of the requestHead property.
	 * 
	 * @param value
	 *            allowed object is {@link RequestHeadDTO }
	 * 
	 */
	public void setRequestHead(RequestHeadDTO value) {
		this.requestHead = value;
	}

	/**
	 * Gets the value of the requestBody property.
	 * 
	 * @return possible object is {@link UserInquiryRequestDTO }
	 * 
	 */
	public UserInquiryRequestDTO getRequestBody() {
		return requestBody;
	}

	/**
	 * Sets the value of the requestBody property.
	 * 
	 * @param value
	 *            allowed object is {@link UserInquiryRequestDTO }
	 * 
	 */
	public void setRequestBody(UserInquiryRequestDTO value) {
		this.requestBody = value;
	}

}
