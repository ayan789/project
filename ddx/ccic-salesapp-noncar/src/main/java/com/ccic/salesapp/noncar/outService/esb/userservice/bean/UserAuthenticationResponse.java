package com.ccic.salesapp.noncar.outService.esb.userservice.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;

/**
 * <p>
 * Java class for UserAuthenticationResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="UserAuthenticationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://service.ccic.com/userservice/bean}responseHead"/>
 *         &lt;element ref="{http://service.ccic.com/userservice/bean}responseBody" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserAuthenticationResponse", propOrder = { "responseHead",
		"responseBody" })
public class UserAuthenticationResponse {

	@XmlElement(required = true)
	protected ResponseHeadDTO responseHead;
	protected UserAuthenticationResponseDTO responseBody;

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
	 * @return possible object is {@link UserAuthenticationResponseDTO }
	 * 
	 */
	public UserAuthenticationResponseDTO getResponseBody() {
		return responseBody;
	}

	/**
	 * Sets the value of the responseBody property.
	 * 
	 * @param value
	 *            allowed object is {@link UserAuthenticationResponseDTO }
	 * 
	 */
	public void setResponseBody(UserAuthenticationResponseDTO value) {
		this.responseBody = value;
	}

}
