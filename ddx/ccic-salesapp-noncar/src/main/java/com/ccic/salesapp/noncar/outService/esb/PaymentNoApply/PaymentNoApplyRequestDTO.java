package com.ccic.salesapp.noncar.outService.esb.PaymentNoApply;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PaymentNoApplyRequestDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentNoApplyRequestDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="systemCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authChannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sumFee" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="payApplyNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proposalInfoDTO" type="{http://service.ccic.com/payment/paymentnoapply/bean}ProposalInfoDTO" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentNoApplyRequestDTO", propOrder = { "payType",
		"systemCode", "authChannel", "sumFee", "payApplyNo",
		"paymentStartTime", "paymentEndTime", "operateType", "proposalInfoDTO" })
public class PaymentNoApplyRequestDTO {

	@XmlElement(required = true)
	protected String payType;
	@XmlElement(required = true)
	protected String systemCode;
	protected String authChannel;
	protected double sumFee;
	protected String payApplyNo;
	protected String paymentStartTime;
	protected String paymentEndTime;
	protected String operateType;
	@XmlElement(required = true)
	protected List<ProposalInfoDTO> proposalInfoDTO;

	/**
	 * Gets the value of the payType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * Sets the value of the payType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPayType(String value) {
		this.payType = value;
	}

	/**
	 * Gets the value of the systemCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSystemCode() {
		return systemCode;
	}

	/**
	 * Sets the value of the systemCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSystemCode(String value) {
		this.systemCode = value;
	}

	/**
	 * Gets the value of the authChannel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAuthChannel() {
		return authChannel;
	}

	/**
	 * Sets the value of the authChannel property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAuthChannel(String value) {
		this.authChannel = value;
	}

	/**
	 * Gets the value of the sumFee property.
	 * 
	 */
	public double getSumFee() {
		return sumFee;
	}

	/**
	 * Sets the value of the sumFee property.
	 * 
	 */
	public void setSumFee(double value) {
		this.sumFee = value;
	}

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

	/**
	 * Gets the value of the operateType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOperateType() {
		return operateType;
	}

	/**
	 * Sets the value of the operateType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOperateType(String value) {
		this.operateType = value;
	}

	/**
	 * Gets the value of the proposalInfoDTO property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the proposalInfoDTO property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getProposalInfoDTO().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ProposalInfoDTO }
	 * 
	 * 
	 */
	public List<ProposalInfoDTO> getProposalInfoDTO() {
		if (proposalInfoDTO == null) {
			proposalInfoDTO = new ArrayList<ProposalInfoDTO>();
		}
		return this.proposalInfoDTO;
	}

	public void setProposalInfoDTO(List<ProposalInfoDTO> proposalInfoDTO) {
		this.proposalInfoDTO = proposalInfoDTO;
	}

}
