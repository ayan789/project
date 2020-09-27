package com.ccic.salesapp.noncar.outService.esb.userservice.common.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CompanyDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CompanyDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comCName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comEName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressCName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressEName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faxNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upperComcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="insurerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="manager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountLeader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cashier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "CompanyDTO", propOrder = { "comcode", "comCName", "comEName",
		"addressCName", "addressEName", "postcode", "phoneNumber", "taxNumber",
		"faxNumber", "upperComcode", "insurerName", "manager", "accountLeader",
		"cashier", "comLevel", "valid" })
public class CompanyDTO {

	@XmlElement(required = true)
	protected String comcode;
	@XmlElement(required = true)
	protected String comCName;
	protected String comEName;
	protected String addressCName;
	protected String addressEName;
	protected String postcode;
	protected String phoneNumber;
	protected String taxNumber;
	protected String faxNumber;
	protected String upperComcode;
	protected String insurerName;
	protected String manager;
	protected String accountLeader;
	protected String cashier;
	protected String comLevel;
	protected boolean valid;

	/**
	 * Gets the value of the comcode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getComcode() {
		return comcode;
	}

	/**
	 * Sets the value of the comcode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setComcode(String value) {
		this.comcode = value;
	}

	/**
	 * Gets the value of the comCName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getComCName() {
		return comCName;
	}

	/**
	 * Sets the value of the comCName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setComCName(String value) {
		this.comCName = value;
	}

	/**
	 * Gets the value of the comEName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getComEName() {
		return comEName;
	}

	/**
	 * Sets the value of the comEName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setComEName(String value) {
		this.comEName = value;
	}

	/**
	 * Gets the value of the addressCName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAddressCName() {
		return addressCName;
	}

	/**
	 * Sets the value of the addressCName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAddressCName(String value) {
		this.addressCName = value;
	}

	/**
	 * Gets the value of the addressEName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAddressEName() {
		return addressEName;
	}

	/**
	 * Sets the value of the addressEName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAddressEName(String value) {
		this.addressEName = value;
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
	 * Gets the value of the phoneNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the value of the phoneNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}

	/**
	 * Gets the value of the taxNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaxNumber() {
		return taxNumber;
	}

	/**
	 * Sets the value of the taxNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaxNumber(String value) {
		this.taxNumber = value;
	}

	/**
	 * Gets the value of the faxNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * Sets the value of the faxNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFaxNumber(String value) {
		this.faxNumber = value;
	}

	/**
	 * Gets the value of the upperComcode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUpperComcode() {
		return upperComcode;
	}

	/**
	 * Sets the value of the upperComcode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUpperComcode(String value) {
		this.upperComcode = value;
	}

	/**
	 * Gets the value of the insurerName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInsurerName() {
		return insurerName;
	}

	/**
	 * Sets the value of the insurerName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInsurerName(String value) {
		this.insurerName = value;
	}

	/**
	 * Gets the value of the manager property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getManager() {
		return manager;
	}

	/**
	 * Sets the value of the manager property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setManager(String value) {
		this.manager = value;
	}

	/**
	 * Gets the value of the accountLeader property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAccountLeader() {
		return accountLeader;
	}

	/**
	 * Sets the value of the accountLeader property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAccountLeader(String value) {
		this.accountLeader = value;
	}

	/**
	 * Gets the value of the cashier property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCashier() {
		return cashier;
	}

	/**
	 * Sets the value of the cashier property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCashier(String value) {
		this.cashier = value;
	}

	/**
	 * Gets the value of the comLevel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getComLevel() {
		return comLevel;
	}

	/**
	 * Sets the value of the comLevel property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setComLevel(String value) {
		this.comLevel = value;
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
