
package com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.salesapp.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/payment/eInvoiceInquiry/bean", "requestHead");
    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/payment/eInvoiceInquiry/bean", "responseBody");
    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/payment/eInvoiceInquiry/bean", "responseHead");
    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/payment/eInvoiceInquiry/bean", "requestBody");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.salesapp.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseHeadDTO }
     * 
     */
    public ResponseHeadDTO createResponseHeadDTO() {
        return new ResponseHeadDTO();
    }

    /**
     * Create an instance of {@link EinvoiceInquiryResponseDTO }
     * 
     */
    public EinvoiceInquiryResponseDTO createEinvoiceInquiryResponseDTO() {
        return new EinvoiceInquiryResponseDTO();
    }

    /**
     * Create an instance of {@link EinvoiceInquiryRequestBodyDTO }
     * 
     */
    public EinvoiceInquiryRequestBodyDTO createEinvoiceInquiryRequestBodyDTO() {
        return new EinvoiceInquiryRequestBodyDTO();
    }

    /**
     * Create an instance of {@link RequestHeadDTO }
     * 
     */
    public RequestHeadDTO createRequestHeadDTO() {
        return new RequestHeadDTO();
    }

    /**
     * Create an instance of {@link EinvoiceInquiryRequest }
     * 
     */
    public EinvoiceInquiryRequest createEinvoiceInquiryRequest() {
        return new EinvoiceInquiryRequest();
    }

    /**
     * Create an instance of {@link EinvoiceInquiryResponse }
     * 
     */
    public EinvoiceInquiryResponse createEinvoiceInquiryResponse() {
        return new EinvoiceInquiryResponse();
    }

    /**
     * Create an instance of {@link SuccessDetailDto }
     * 
     */
    public SuccessDetailDto createSuccessDetailDto() {
        return new SuccessDetailDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/payment/eInvoiceInquiry/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EinvoiceInquiryResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/payment/eInvoiceInquiry/bean", name = "responseBody")
    public JAXBElement<EinvoiceInquiryResponseDTO> createResponseBody(EinvoiceInquiryResponseDTO value) {
        return new JAXBElement<EinvoiceInquiryResponseDTO>(_ResponseBody_QNAME, EinvoiceInquiryResponseDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/payment/eInvoiceInquiry/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EinvoiceInquiryRequestBodyDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/payment/eInvoiceInquiry/bean", name = "requestBody")
    public JAXBElement<EinvoiceInquiryRequestBodyDTO> createRequestBody(EinvoiceInquiryRequestBodyDTO value) {
        return new JAXBElement<EinvoiceInquiryRequestBodyDTO>(_RequestBody_QNAME, EinvoiceInquiryRequestBodyDTO.class, null, value);
    }

}
