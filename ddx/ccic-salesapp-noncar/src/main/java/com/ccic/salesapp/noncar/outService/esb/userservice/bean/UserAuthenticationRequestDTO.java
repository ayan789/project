package com.ccic.salesapp.noncar.outService.esb.userservice.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/userservice/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:ns2="http://service.ccic.com/userservice/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/userservice/userAuthentication/intf/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣ��&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>
 * Java class for UserAuthenticationRequestDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="UserAuthenticationRequestDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usercode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authtype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserAuthenticationRequestDTO", propOrder = { "usercode",
		"password", "authtype", "ip", "mac" })
public class UserAuthenticationRequestDTO {

	@XmlElement(required = true)
	protected String usercode;
	@XmlElement(required = true)
	protected String password;
	protected String authtype;
	protected String ip;
	protected String mac;

	/**
	 * Gets the value of the usercode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUsercode() {
		return usercode;
	}

	/**
	 * Sets the value of the usercode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUsercode(String value) {
		this.usercode = value;
	}

	/**
	 * Gets the value of the password property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the value of the password property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPassword(String value) {
		this.password = value;
	}

	/**
	 * Gets the value of the authtype property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAuthtype() {
		return authtype;
	}

	/**
	 * Sets the value of the authtype property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAuthtype(String value) {
		this.authtype = value;
	}

	/**
	 * Gets the value of the ip property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the value of the ip property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIp(String value) {
		this.ip = value;
	}

	/**
	 * Gets the value of the mac property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * Sets the value of the mac property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMac(String value) {
		this.mac = value;
	}

}
