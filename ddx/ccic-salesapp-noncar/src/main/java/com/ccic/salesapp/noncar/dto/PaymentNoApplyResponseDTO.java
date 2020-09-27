package com.ccic.salesapp.noncar.dto;
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
 * &lt;complexType name=&quot;PaymentNoApplyResponseDTO&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;payApplyNo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;identityCode&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;payFee&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentNoApplyResponseDTO", propOrder = { "payApplyNo",
		"identityCode", "payFee" })
public class PaymentNoApplyResponseDTO {

	protected String payApplyNo;
	protected String identityCode;
	protected Double payFee;

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
	 * Gets the value of the payFee property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getPayFee() {
		return payFee;
	}

	/**
	 * Sets the value of the payFee property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setPayFee(Double value) {
		this.payFee = value;
	}

}
