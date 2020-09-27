package com.ccic.salesapp.noncar.dto;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestDTOCommAttr;

/**
 * <p>
 * Java class for CarApplicationSearchRequestDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;CarApplicationSearchRequestDTO&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://service.ccic.com/mss/common/bean}RequestDTOCommAttr&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;staffCode&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot;/&gt;
 *         &lt;element name=&quot;applicationNo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;applicantName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;insuredName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;minRespEndDate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;maxRespEndDate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}dateTime&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarApplicationSearchRequestDTO", propOrder = { "staffCode",
		"applicationNo", "applicantName", "insuredName", "minRespEndDate",
		"maxRespEndDate" })
public class CarApplicationSearchRequestDTO extends RequestDTOCommAttr {

	@XmlElement(required = true)
	protected String staffCode;
	protected String applicationNo;
	protected String applicantName;
	protected String insuredName;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar minRespEndDate;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar maxRespEndDate;

	/**
	 * Gets the value of the staffCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStaffCode() {
		return staffCode;
	}

	/**
	 * Sets the value of the staffCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStaffCode(String value) {
		this.staffCode = value;
	}

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
	 * Gets the value of the minRespEndDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getMinRespEndDate() {
		return minRespEndDate;
	}

	/**
	 * Sets the value of the minRespEndDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setMinRespEndDate(XMLGregorianCalendar value) {
		this.minRespEndDate = value;
	}

	/**
	 * Gets the value of the maxRespEndDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getMaxRespEndDate() {
		return maxRespEndDate;
	}

	/**
	 * Sets the value of the maxRespEndDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setMaxRespEndDate(XMLGregorianCalendar value) {
		this.maxRespEndDate = value;
	}

}
