package com.ccic.salesapp.performance.outService.esb.PaymentNoApply;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;


/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://service.ccic.com/payment/paymentnoapply/bean}responseHead"/>
 *         &lt;element ref="{http://service.ccic.com/payment/paymentnoapply/bean}responseBody" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "responseHead", "responseBody" })
@XmlRootElement(name = "PaymentNoApplyResponse")
public class PaymentNoApplyResponse {

	@XmlElement(required = true)
	protected ResponseHeadDTO responseHead;
	protected PaymentNoApplyResponseDTO responseBody;

	/**
	 * Gets the value of the responseHead property.
	 * 
	 * @return possible object is {@link ResponseHeadDTO }
	 * 
	 */
	public ResponseHeadDTO getResponseHead() {
		return responseHead;
	}

	/**
	 * Sets the value of the responseHead property.
	 * 
	 * @param value
	 *            allowed object is {@link ResponseHeadDTO }
	 * 
	 */
	public void setResponseHead(ResponseHeadDTO value) {
		this.responseHead = value;
	}

	/**
	 * Gets the value of the responseBody property.
	 * 
	 * @return possible object is {@link PaymentNoApplyResponseDTO }
	 * 
	 */
	public PaymentNoApplyResponseDTO getResponseBody() {
		return responseBody;
	}

	/**
	 * Sets the value of the responseBody property.
	 * 
	 * @param value
	 *            allowed object is {@link PaymentNoApplyResponseDTO }
	 * 
	 */
	public void setResponseBody(PaymentNoApplyResponseDTO value) {
		this.responseBody = value;
	}

}
