package com.ccic.salesapp.noncar.outService.esb.userservice.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;

/**
 * <p>
 * Java class for UserAuthenticationRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="UserAuthenticationRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://service.ccic.com/userservice/bean}requestHead"/>
 *         &lt;element ref="{http://service.ccic.com/userservice/bean}requestBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserAuthenticationRequest", propOrder = { "requestHead",
		"requestBody" })
public class UserAuthenticationRequest {

	@XmlElement(required = true)
	protected RequestHeadDTO requestHead;
	@XmlElement(required = true)
	protected UserAuthenticationRequestDTO requestBody;

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
	 * @return possible object is {@link UserAuthenticationRequestDTO }
	 * 
	 */
	public UserAuthenticationRequestDTO getRequestBody() {
		return requestBody;
	}

	/**
	 * Sets the value of the requestBody property.
	 * 
	 * @param value
	 *            allowed object is {@link UserAuthenticationRequestDTO }
	 * 
	 */
	public void setRequestBody(UserAuthenticationRequestDTO value) {
		this.requestBody = value;
	}

}
