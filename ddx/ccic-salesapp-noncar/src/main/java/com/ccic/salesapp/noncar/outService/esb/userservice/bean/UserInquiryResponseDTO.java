package com.ccic.salesapp.noncar.outService.esb.userservice.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salesapp.noncar.outService.esb.userservice.common.bean.UserDTO;

/**
 * 
 * <pre>
 * &lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;&lt;ns0:name xmlns:ns0=&quot;http://service.ccic.com/userservice/bean&quot; xmlns:ns1=&quot;http://service.ccic.com/common/bean&quot; xmlns:ns2=&quot;http://service.ccic.com/userservice/common/bean&quot; xmlns:soap=&quot;http://schemas.xmlsoap.org/wsdl/soap/&quot; xmlns:tns=&quot;http://service.ccic.com/userservice/userInquiry/intf/&quot; xmlns:wsdl=&quot;http://schemas.xmlsoap.org/wsdl/&quot; xmlns:xs=&quot;http://www.w3.org/2001/XMLSchema&quot;&gt;�û���ѯ��Ӧ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>
 * Java class for UserInquiryResponseDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;UserInquiryResponseDTO&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;user&quot; type=&quot;{http://service.ccic.com/userservice/common/bean}UserDTO&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserInquiryResponseDTO", propOrder = { "user" })
public class UserInquiryResponseDTO {

	@XmlElement(required = true)
	protected UserDTO user;

	/**
	 * Gets the value of the user property.
	 * 
	 * @return possible object is {@link UserDTO }
	 * 
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * Sets the value of the user property.
	 * 
	 * @param value
	 *            allowed object is {@link UserDTO }
	 * 
	 */
	public void setUser(UserDTO value) {
		this.user = value;
	}

}
