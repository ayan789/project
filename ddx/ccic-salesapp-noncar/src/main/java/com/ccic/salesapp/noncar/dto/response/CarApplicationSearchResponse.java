package com.ccic.salesapp.noncar.dto.response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salesapp.noncar.dto.CarApplicationSearchResponseDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;


/**
 * <p>
 * Java class for CarApplicationSearchResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;CarApplicationSearchResponse&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;responseHead&quot; type=&quot;{http://service.ccic.com/common/bean}ResponseHeadDTO&quot;/&gt;
 *         &lt;element name=&quot;responseBody&quot; type=&quot;{http://service.ccic.com/mss/carapplication/bean}CarApplicationSearchResponseDTO&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarApplicationSearchResponse", propOrder = { "responseHead",
		"responseBody" })
public class CarApplicationSearchResponse {

	@XmlElement(required = true)
	protected ResponseHeadDTO responseHead;
	protected CarApplicationSearchResponseDTO responseBody;

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
	 * @return possible object is {@link CarApplicationSearchResponseDTO }
	 * 
	 */
	public CarApplicationSearchResponseDTO getResponseBody() {
		return responseBody;
	}

	/**
	 * Sets the value of the responseBody property.
	 * 
	 * @param value
	 *            allowed object is {@link CarApplicationSearchResponseDTO }
	 * 
	 */
	public void setResponseBody(CarApplicationSearchResponseDTO value) {
		this.responseBody = value;
	}

}
