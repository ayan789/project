package com.ccic.salesapp.noncar.outService.esb.userservice.common.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for UserDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="UserDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usercode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userEName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userCodeMapping" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="company" type="{http://service.ccic.com/userservice/common/bean}CompanyDTO" minOccurs="0"/>
 *         &lt;element name="makeCompany" type="{http://service.ccic.com/userservice/common/bean}CompanyDTO" minOccurs="0"/>
 *         &lt;element name="outCompany" type="{http://service.ccic.com/userservice/common/bean}CompanyDTO" minOccurs="0"/>
 *         &lt;element name="syncid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valid" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserDTO", propOrder = { "usercode", "userName", "userEName",
		"userCodeMapping", "extType", "company", "makeCompany", "outCompany",
		"syncid", "phone", "mobile", "address", "postcode", "email", "valid" })
public class UserDTO {

	@XmlElement(required = true)
	protected String usercode;
	@XmlElement(required = true)
	protected String userName;
	protected String userEName;
	protected String userCodeMapping;
	protected String extType;
	protected CompanyDTO company;
	protected CompanyDTO makeCompany;
	protected CompanyDTO outCompany;
	protected String syncid;
	protected String phone;
	protected String mobile;
	protected String address;
	protected String postcode;
	protected String email;
	protected boolean valid;

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
	 * Gets the value of the userName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the value of the userName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserName(String value) {
		this.userName = value;
	}

	/**
	 * Gets the value of the userEName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserEName() {
		return userEName;
	}

	/**
	 * Sets the value of the userEName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserEName(String value) {
		this.userEName = value;
	}

	/**
	 * Gets the value of the userCodeMapping property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserCodeMapping() {
		return userCodeMapping;
	}

	/**
	 * Sets the value of the userCodeMapping property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserCodeMapping(String value) {
		this.userCodeMapping = value;
	}

	/**
	 * Gets the value of the extType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getExtType() {
		return extType;
	}

	/**
	 * Sets the value of the extType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setExtType(String value) {
		this.extType = value;
	}

	/**
	 * Gets the value of the company property.
	 * 
	 * @return possible object is {@link CompanyDTO }
	 * 
	 */
	public CompanyDTO getCompany() {
		return company;
	}

	/**
	 * Sets the value of the company property.
	 * 
	 * @param value
	 *            allowed object is {@link CompanyDTO }
	 * 
	 */
	public void setCompany(CompanyDTO value) {
		this.company = value;
	}

	/**
	 * Gets the value of the makeCompany property.
	 * 
	 * @return possible object is {@link CompanyDTO }
	 * 
	 */
	public CompanyDTO getMakeCompany() {
		return makeCompany;
	}

	/**
	 * Sets the value of the makeCompany property.
	 * 
	 * @param value
	 *            allowed object is {@link CompanyDTO }
	 * 
	 */
	public void setMakeCompany(CompanyDTO value) {
		this.makeCompany = value;
	}

	/**
	 * Gets the value of the outCompany property.
	 * 
	 * @return possible object is {@link CompanyDTO }
	 * 
	 */
	public CompanyDTO getOutCompany() {
		return outCompany;
	}

	/**
	 * Sets the value of the outCompany property.
	 * 
	 * @param value
	 *            allowed object is {@link CompanyDTO }
	 * 
	 */
	public void setOutCompany(CompanyDTO value) {
		this.outCompany = value;
	}

	/**
	 * Gets the value of the syncid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSyncid() {
		return syncid;
	}

	/**
	 * Sets the value of the syncid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSyncid(String value) {
		this.syncid = value;
	}

	/**
	 * Gets the value of the phone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the value of the phone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPhone(String value) {
		this.phone = value;
	}

	/**
	 * Gets the value of the mobile property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the value of the mobile property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMobile(String value) {
		this.mobile = value;
	}

	/**
	 * Gets the value of the address property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the value of the address property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAddress(String value) {
		this.address = value;
	}

	/**
	 * Gets the value of the postcode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * Sets the value of the postcode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPostcode(String value) {
		this.postcode = value;
	}

	/**
	 * Gets the value of the email property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the value of the email property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEmail(String value) {
		this.email = value;
	}

	/**
	 * Gets the value of the valid property.
	 * 
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Sets the value of the valid property.
	 * 
	 */
	public void setValid(boolean value) {
		this.valid = value;
	}

}
