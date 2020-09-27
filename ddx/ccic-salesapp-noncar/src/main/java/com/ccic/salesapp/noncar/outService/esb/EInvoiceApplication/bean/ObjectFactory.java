package com.ccic.salesapp.noncar.outService.esb.EInvoiceApplication.bean;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.salesapp.ws package. 
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

    private final static QName _EInvoiceApplicationForAppResponse_QNAME = new QName("http://service.ccic.com/einvoiceapp/einvoiceApplicationForApp/bean", "EInvoiceApplicationForAppResponse");
    private final static QName _EInvoiceApplicationForAppRequest_QNAME = new QName("http://service.ccic.com/einvoiceapp/einvoiceApplicationForApp/bean", "EInvoiceApplicationForAppRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.salesapp.ws
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
     * Create an instance of {@link EInvoiceApplicationForAppRequest }
     * 
     */
    public EInvoiceApplicationForAppRequest createEInvoiceApplicationForAppRequest() {
        return new EInvoiceApplicationForAppRequest();
    }

    /**
     * Create an instance of {@link EInvoiceApplicationForAppResponse }
     * 
     */
    public EInvoiceApplicationForAppResponse createEInvoiceApplicationForAppResponse() {
        return new EInvoiceApplicationForAppResponse();
    }

    /**
     * Create an instance of {@link EInvoiceApplicationForAppResponseBodyDTO }
     * 
     */
    public EInvoiceApplicationForAppResponseBodyDTO createEInvoiceApplicationForAppResponseBodyDTO() {
        return new EInvoiceApplicationForAppResponseBodyDTO();
    }

    /**
     * Create an instance of {@link EInvoiceApplicationForAppRequestBodyDTO }
     * 
     */
    public EInvoiceApplicationForAppRequestBodyDTO createEInvoiceApplicationForAppRequestBodyDTO() {
        return new EInvoiceApplicationForAppRequestBodyDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EInvoiceApplicationForAppResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/einvoiceapp/einvoiceApplicationForApp/bean", name = "EInvoiceApplicationForAppResponse")
    public JAXBElement<EInvoiceApplicationForAppResponse> createEInvoiceApplicationForAppResponse(EInvoiceApplicationForAppResponse value) {
        return new JAXBElement<EInvoiceApplicationForAppResponse>(_EInvoiceApplicationForAppResponse_QNAME, EInvoiceApplicationForAppResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EInvoiceApplicationForAppRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/einvoiceapp/einvoiceApplicationForApp/bean", name = "EInvoiceApplicationForAppRequest")
    public JAXBElement<EInvoiceApplicationForAppRequest> createEInvoiceApplicationForAppRequest(EInvoiceApplicationForAppRequest value) {
        return new JAXBElement<EInvoiceApplicationForAppRequest>(_EInvoiceApplicationForAppRequest_QNAME, EInvoiceApplicationForAppRequest.class, null, value);
    }

}
