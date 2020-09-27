package com.ccic.salesapp.noncar.dto.request;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salesapp.noncar.dto.PaymentNoApplyRequestDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;


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
 *         &lt;element ref="{http://service.ccic.com/payment/paymentnoapply/bean}requestHead"/>
 *         &lt;element ref="{http://service.ccic.com/payment/paymentnoapply/bean}requestBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "requestHead", "requestBody" })
@XmlRootElement(name = "PaymentNoApplyRequest")
public class PaymentNoApplyRequest {

	@XmlElement(required = true)
	protected RequestHeadDTO requestHead;
	@XmlElement(required = true)
	protected PaymentNoApplyRequestDTO requestBody;

	/**
	 * Gets the value of the requestHead property.
	 * 
	 * @return possible object is {@link RequestHeadDTO }
	 * 
	 */
	public RequestHeadDTO getRequestHead() {
		return requestHead;
	}

	/**
	 * Sets the value of the requestHead property.
	 * 
	 * @param value
	 *            allowed object is {@link RequestHeadDTO }
	 * 
	 */
	public void setRequestHead(RequestHeadDTO value) {
		this.requestHead = value;
	}

	/**
	 * Gets the value of the requestBody property.
	 * 
	 * @return possible object is {@link PaymentNoApplyRequestDTO }
	 * 
	 */
	public PaymentNoApplyRequestDTO getRequestBody() {
		return requestBody;
	}

	/**
	 * Sets the value of the requestBody property.
	 * 
	 * @param value
	 *            allowed object is {@link PaymentNoApplyRequestDTO }
	 * 
	 */
	public void setRequestBody(PaymentNoApplyRequestDTO value) {
		this.requestBody = value;
	}

}
