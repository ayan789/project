package com.ccic.salesapp.performance.outService.esb.PaymentNoApply;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ProposalInfoDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ProposalInfoDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="certiNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="insuranceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="mobilePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProposalInfoDTO", propOrder = { "certiNo", "insuranceCode",
		"amount", "mobilePhone", "effectDate", "comCode" })
public class ProposalInfoDTO {

	@XmlElement(required = true)
	protected String certiNo;
	@XmlElement(required = true)
	protected String insuranceCode;
	protected double amount;
	protected String mobilePhone;
	@XmlElement(required = true)
	protected String effectDate;
	@XmlElement(required = true)
	protected String comCode;

	/**
	 * Gets the value of the certiNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCertiNo() {
		return certiNo;
	}

	/**
	 * Sets the value of the certiNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCertiNo(String value) {
		this.certiNo = value;
	}

	/**
	 * Gets the value of the insuranceCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInsuranceCode() {
		return insuranceCode;
	}

	/**
	 * Sets the value of the insuranceCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInsuranceCode(String value) {
		this.insuranceCode = value;
	}

	/**
	 * Gets the value of the amount property.
	 * 
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the value of the amount property.
	 * 
	 */
	public void setAmount(double value) {
		this.amount = value;
	}

	/**
	 * Gets the value of the mobilePhone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * Sets the value of the mobilePhone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMobilePhone(String value) {
		this.mobilePhone = value;
	}

	/**
	 * Gets the value of the effectDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEffectDate() {
		return effectDate;
	}

	/**
	 * Sets the value of the effectDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEffectDate(String value) {
		this.effectDate = value;
	}

	/**
	 * Gets the value of the comCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getComCode() {
		return comCode;
	}

	/**
	 * Sets the value of the comCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setComCode(String value) {
		this.comCode = value;
	}

}
