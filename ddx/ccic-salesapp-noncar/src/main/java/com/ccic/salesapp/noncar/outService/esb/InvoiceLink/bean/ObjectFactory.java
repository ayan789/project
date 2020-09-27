package com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wp.salesapp.ws package. 
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

    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/payment/InvoiceLinkQuery/bean", "requestHead");
    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/payment/InvoiceLinkQuery/bean", "requestBody");
    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/payment/InvoiceLinkQuery/bean", "responseHead");
    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/payment/InvoiceLinkQuery/bean", "responseBody");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wp.salesapp.ws
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
     * Create an instance of {@link RequestHeadDTO }
     * 
     */
    public RequestHeadDTO createRequestHeadDTO() {
        return new RequestHeadDTO();
    }

    /**
     * Create an instance of {@link InvoiceLinkQueryResponseDTO }
     * 
     */
    public InvoiceLinkQueryResponseDTO createInvoiceLinkQueryResponseDTO() {
        return new InvoiceLinkQueryResponseDTO();
    }

    /**
     * Create an instance of {@link InvoiceLinkQueryResponse }
     * 
     */
    public InvoiceLinkQueryResponse createInvoiceLinkQueryResponse() {
        return new InvoiceLinkQueryResponse();
    }

    /**
     * Create an instance of {@link InvoiceLinkQueryRequestDTO }
     * 
     */
    public InvoiceLinkQueryRequestDTO createInvoiceLinkQueryRequestDTO() {
        return new InvoiceLinkQueryRequestDTO();
    }

    /**
     * Create an instance of {@link InvoiceLinkQueryRequest }
     * 
     */
    public InvoiceLinkQueryRequest createInvoiceLinkQueryRequest() {
        return new InvoiceLinkQueryRequest();
    }

    /**
     * Create an instance of {@link InvoiceLink }
     * 
     */
    public InvoiceLink createInvoiceLink() {
        return new InvoiceLink();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/payment/InvoiceLinkQuery/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvoiceLinkQueryRequestDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/payment/InvoiceLinkQuery/bean", name = "requestBody")
    public JAXBElement<InvoiceLinkQueryRequestDTO> createRequestBody(InvoiceLinkQueryRequestDTO value) {
        return new JAXBElement<InvoiceLinkQueryRequestDTO>(_RequestBody_QNAME, InvoiceLinkQueryRequestDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/payment/InvoiceLinkQuery/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvoiceLinkQueryResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/payment/InvoiceLinkQuery/bean", name = "responseBody")
    public JAXBElement<InvoiceLinkQueryResponseDTO> createResponseBody(InvoiceLinkQueryResponseDTO value) {
        return new JAXBElement<InvoiceLinkQueryResponseDTO>(_ResponseBody_QNAME, InvoiceLinkQueryResponseDTO.class, null, value);
    }

}
