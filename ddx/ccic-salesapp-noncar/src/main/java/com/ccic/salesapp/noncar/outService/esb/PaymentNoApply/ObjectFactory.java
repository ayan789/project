package com.ccic.salesapp.noncar.outService.esb.PaymentNoApply;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;


/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * com.ccic.service.payment.paymentnoapply.bean package.
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
			"http://service.ccic.com/payment/paymentnoapply/bean",
			"responseHead");
	private final static QName _ResponseBody_QNAME = new QName(
			"http://service.ccic.com/payment/paymentnoapply/bean",
			"responseBody");
	private final static QName _RequestHead_QNAME = new QName(
			"http://service.ccic.com/payment/paymentnoapply/bean",
			"requestHead");
	private final static QName _RequestBody_QNAME = new QName(
			"http://service.ccic.com/payment/paymentnoapply/bean",
			"requestBody");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.ccic.service.payment.paymentnoapply.bean
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link PaymentNoApplyResponseDTO }
	 * 
	 */
	public PaymentNoApplyResponseDTO createPaymentNoApplyResponseDTO() {
		return new PaymentNoApplyResponseDTO();
	}

	/**
	 * Create an instance of {@link ProposalInfoDTO }
	 * 
	 */
	public ProposalInfoDTO createProposalInfoDTO() {
		return new ProposalInfoDTO();
	}

	/**
	 * Create an instance of {@link PaymentNoApplyRequest }
	 * 
	 */
	public PaymentNoApplyRequest createPaymentNoApplyRequest() {
		return new PaymentNoApplyRequest();
	}

	/**
	 * Create an instance of {@link PaymentNoApplyRequestDTO }
	 * 
	 */
	public PaymentNoApplyRequestDTO createPaymentNoApplyRequestDTO() {
		return new PaymentNoApplyRequestDTO();
	}

	/**
	 * Create an instance of {@link PaymentNoApplyResponse }
	 * 
	 */
	public PaymentNoApplyResponse createPaymentNoApplyResponse() {
		return new PaymentNoApplyResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.ccic.com/payment/paymentnoapply/bean", name = "responseHead")
	public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
		return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME,
				ResponseHeadDTO.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PaymentNoApplyResponseDTO }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.ccic.com/payment/paymentnoapply/bean", name = "responseBody")
	public JAXBElement<PaymentNoApplyResponseDTO> createResponseBody(
			PaymentNoApplyResponseDTO value) {
		return new JAXBElement<PaymentNoApplyResponseDTO>(_ResponseBody_QNAME,
				PaymentNoApplyResponseDTO.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.ccic.com/payment/paymentnoapply/bean", name = "requestHead")
	public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
		return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME,
				RequestHeadDTO.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PaymentNoApplyRequestDTO }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.ccic.com/payment/paymentnoapply/bean", name = "requestBody")
	public JAXBElement<PaymentNoApplyRequestDTO> createRequestBody(
			PaymentNoApplyRequestDTO value) {
		return new JAXBElement<PaymentNoApplyRequestDTO>(_RequestBody_QNAME,
				PaymentNoApplyRequestDTO.class, null, value);
	}

}
