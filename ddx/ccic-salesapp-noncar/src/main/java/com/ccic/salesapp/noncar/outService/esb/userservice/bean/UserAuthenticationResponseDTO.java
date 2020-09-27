package com.ccic.salesapp.noncar.outService.esb.userservice.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salesapp.noncar.outService.esb.userservice.common.bean.UserDTO;

/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/userservice/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:ns2="http://service.ccic.com/userservice/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/userservice/userAuthentication/intf/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;�û���Ȩ��Ӧ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>
 * Java class for UserAuthenticationResponseDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="UserAuthenticationResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://service.ccic.com/userservice/common/bean}UserDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserAuthenticationResponseDTO", propOrder = { "user" })
public class UserAuthenticationResponseDTO {

	@XmlElement(required = true)
	protected UserDTO user;

	/**
	 * Gets the value of the user property.
	 * 
	 * @return possible object is {@link UserDTO }
	 * 
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * Sets the value of the user property.
	 * 
	 * @param value
	 *            allowed object is {@link UserDTO }
	 * 
	 */
	public void setUser(UserDTO value) {
		this.user = value;
	}

}
