package com.ccic.salesapp.noncar.outService.esb.userservice.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.ccic.service.userservice.bean package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _ResponseHead_QNAME = new QName(
			"http://service.ccic.com/userservice/bean", "responseHead");
	private final static QName _ResponseBody_QNAME = new QName(
			"http://service.ccic.com/userservice/bean", "responseBody");
	private final static QName _UserAuthenticationResponse_QNAME = new QName(
			"http://service.ccic.com/userservice/bean",
			"UserAuthenticationResponse");
	private final static QName _RequestHead_QNAME = new QName(
			"http://service.ccic.com/userservice/bean", "requestHead");
	private final static QName _UserAuthenticationRequest_QNAME = new QName(
			"http://service.ccic.com/userservice/bean",
			"UserAuthenticationRequest");
	private final static QName _RequestBody_QNAME = new QName(
			"http://service.ccic.com/userservice/bean", "requestBody");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.ccic.service.userservice.bean
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link UserAuthenticationResponseDTO }
	 * 
	 */
	public UserAuthenticationResponseDTO createUserAuthenticationResponseDTO() {
		return new UserAuthenticationResponseDTO();
	}

	/**
	 * Create an instance of {@link UserAuthenticationResponse }
	 * 
	 */
	public UserAuthenticationResponse createUserAuthenticationResponse() {
		return new UserAuthenticationResponse();
	}

	/**
	 * Create an instance of {@link UserAuthenticationRequestDTO }
	 * 
	 */
	public UserAuthenticationRequestDTO createUserAuthenticationRequestDTO() {
		return new UserAuthenticationRequestDTO();
	}

	/**
	 * Create an instance of {@link UserAuthenticationRequest }
	 * 
	 */
	public UserAuthenticationRequest createUserAuthenticationRequest() {
		return new UserAuthenticationRequest();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.ccic.com/userservice/bean", name = "responseHead")
	public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
		return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME,
				ResponseHeadDTO.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link UserAuthenticationResponseDTO }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.ccic.com/userservice/bean", name = "responseBody")
	public JAXBElement<UserAuthenticationResponseDTO> createResponseBody(
			UserAuthenticationResponseDTO value) {
		return new JAXBElement<UserAuthenticationResponseDTO>(
				_ResponseBody_QNAME, UserAuthenticationResponseDTO.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link UserAuthenticationResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.ccic.com/userservice/bean", name = "UserAuthenticationResponse")
	public JAXBElement<UserAuthenticationResponse> createUserAuthenticationResponse(
			UserAuthenticationResponse value) {
		return new JAXBElement<UserAuthenticationResponse>(
				_UserAuthenticationResponse_QNAME,
				UserAuthenticationResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.ccic.com/userservice/bean", name = "requestHead")
	public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
		return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME,
				RequestHeadDTO.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link UserAuthenticationRequest }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.ccic.com/userservice/bean", name = "UserAuthenticationRequest")
	public JAXBElement<UserAuthenticationRequest> createUserAuthenticationRequest(
			UserAuthenticationRequest value) {
		return new JAXBElement<UserAuthenticationRequest>(
				_UserAuthenticationRequest_QNAME,
				UserAuthenticationRequest.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link UserAuthenticationRequestDTO }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.ccic.com/userservice/bean", name = "requestBody")
	public JAXBElement<UserAuthenticationRequestDTO> createRequestBody(
			UserAuthenticationRequestDTO value) {
		return new JAXBElement<UserAuthenticationRequestDTO>(
				_RequestBody_QNAME, UserAuthenticationRequestDTO.class, null,
				value);
	}

}
