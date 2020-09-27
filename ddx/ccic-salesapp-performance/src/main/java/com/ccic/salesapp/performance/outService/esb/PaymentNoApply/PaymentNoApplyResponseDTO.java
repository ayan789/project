package com.ccic.salesapp.performance.outService.esb.PaymentNoApply;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PaymentNoApplyResponseDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentNoApplyResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payApplyNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentNoApplyResponseDTO", propOrder = { "payApplyNo",
		"identityCode", "paymentStartTime", "paymentEndTime" })
public class PaymentNoApplyResponseDTO {

	protected String payApplyNo;
	protected String identityCode;
	protected String paymentStartTime;
	protected String paymentEndTime;

	/**
	 * Gets the value of the payApplyNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPayApplyNo() {
		return payApplyNo;
	}

	/**
	 * Sets the value of the payApplyNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPayApplyNo(String value) {
		this.payApplyNo = value;
	}

	/**
	 * Gets the value of the identityCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdentityCode() {
		return identityCode;
	}

	/**
	 * Sets the value of the identityCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdentityCode(String value) {
		this.identityCode = value;
	}

	/**
	 * Gets the value of the paymentStartTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPaymentStartTime() {
		return paymentStartTime;
	}

	/**
	 * Sets the value of the paymentStartTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPaymentStartTime(String value) {
		this.paymentStartTime = value;
	}

	/**
	 * Gets the value of the paymentEndTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPaymentEndTime() {
		return paymentEndTime;
	}

	/**
	 * Sets the value of the paymentEndTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPaymentEndTime(String value) {
		this.paymentEndTime = value;
	}

}
