package com.ccic.salesapp.noncar.dto.request;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salesapp.noncar.dto.CarApplicationSearchRequestDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;

/**
 * <p>
 * Java class for CarApplicationSearchRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;CarApplicationSearchRequest&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;requestHead&quot; type=&quot;{http://service.ccic.com/common/bean}RequestHeadDTO&quot;/&gt;
 *         &lt;element name=&quot;requestBody&quot; type=&quot;{http://service.ccic.com/mss/carapplication/bean}CarApplicationSearchRequestDTO&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarApplicationSearchRequest", propOrder = { "requestHead",
		"requestBody" })
public class CarApplicationSearchRequest {

	@XmlElement(required = true)
	protected RequestHeadDTO requestHead;
	@XmlElement(required = true)
	protected CarApplicationSearchRequestDTO requestBody;

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
	 * @return possible object is {@link CarApplicationSearchRequestDTO }
	 * 
	 */
	public CarApplicationSearchRequestDTO getRequestBody() {
		return requestBody;
	}

	/**
	 * Sets the value of the requestBody property.
	 * 
	 * @param value
	 *            allowed object is {@link CarApplicationSearchRequestDTO }
	 * 
	 */
	public void setRequestBody(CarApplicationSearchRequestDTO value) {
		this.requestBody = value;
	}

}
