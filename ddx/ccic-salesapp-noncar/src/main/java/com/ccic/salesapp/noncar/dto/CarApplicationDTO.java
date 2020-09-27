package com.ccic.salesapp.noncar.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for CarApplicationDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;CarApplicationDTO&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;applicationNo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;payNo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;insuredName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;insuredCertNo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;applicantName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;respStartDate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;respEndDate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sumAmount&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sumPremium&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;status&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarApplicationDTO", propOrder = { "applicationNo", "payNo",
		"insuredName", "insuredCertNo", "applicantName", "respStartDate",
		"respEndDate", "sumAmount", "sumPremium", "status" })
public class CarApplicationDTO {

	protected String applicationNo;
	protected String payNo;
	protected String insuredName;
	protected String insuredCertNo;
	protected String applicantName;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar respStartDate;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar respEndDate;
	protected Double sumAmount;
	protected Double sumPremium;
	protected String status;

	/**
	 * Gets the value of the applicationNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getApplicationNo() {
		return applicationNo;
	}

	/**
	 * Sets the value of the applicationNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setApplicationNo(String value) {
		this.applicationNo = value;
	}

	/**
	 * Gets the value of the payNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPayNo() {
		return payNo;
	}

	/**
	 * Sets the value of the payNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPayNo(String value) {
		this.payNo = value;
	}

	/**
	 * Gets the value of the insuredName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInsuredName() {
		return insuredName;
	}

	/**
	 * Sets the value of the insuredName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInsuredName(String value) {
		this.insuredName = value;
	}

	/**
	 * Gets the value of the insuredCertNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInsuredCertNo() {
		return insuredCertNo;
	}

	/**
	 * Sets the value of the insuredCertNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInsuredCertNo(String value) {
		this.insuredCertNo = value;
	}

	/**
	 * Gets the value of the applicantName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getApplicantName() {
		return applicantName;
	}

	/**
	 * Sets the value of the applicantName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setApplicantName(String value) {
		this.applicantName = value;
	}

	/**
	 * Gets the value of the respStartDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getRespStartDate() {
		return respStartDate;
	}

	/**
	 * Sets the value of the respStartDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setRespStartDate(XMLGregorianCalendar value) {
		this.respStartDate = value;
	}

	/**
	 * Gets the value of the respEndDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getRespEndDate() {
		return respEndDate;
	}

	/**
	 * Sets the value of the respEndDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setRespEndDate(XMLGregorianCalendar value) {
		this.respEndDate = value;
	}

	/**
	 * Gets the value of the sumAmount property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getSumAmount() {
		return sumAmount;
	}

	/**
	 * Sets the value of the sumAmount property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setSumAmount(Double value) {
		this.sumAmount = value;
	}

	/**
	 * Gets the value of the sumPremium property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getSumPremium() {
		return sumPremium;
	}

	/**
	 * Sets the value of the sumPremium property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setSumPremium(Double value) {
		this.sumPremium = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus(String value) {
		this.status = value;
	}

}
