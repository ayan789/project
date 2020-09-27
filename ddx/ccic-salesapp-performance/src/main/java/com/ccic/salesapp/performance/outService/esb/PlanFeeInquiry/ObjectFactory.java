package com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xufei.salesapp.ws package. 
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

    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/agent/planFee/bean", "responseHead");
    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/agent/planFee/bean", "responseBody");
    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/agent/planFee/bean", "requestBody");
    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/agent/planFee/bean", "requestHead");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xufei.salesapp.ws
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
     * Create an instance of {@link PlanFeeResponseDTO }
     * 
     */
    public PlanFeeResponseDTO createPlanFeeResponseDTO() {
        return new PlanFeeResponseDTO();
    }

    /**
     * Create an instance of {@link PlanFeeInquiryResponse }
     * 
     */
    public PlanFeeInquiryResponse createPlanFeeInquiryResponse() {
        return new PlanFeeInquiryResponse();
    }

    /**
     * Create an instance of {@link PlanFeeRequestDTO }
     * 
     */
    public PlanFeeRequestDTO createPlanFeeRequestDTO() {
        return new PlanFeeRequestDTO();
    }

    /**
     * Create an instance of {@link PlanFeeInquiryRequest }
     * 
     */
    public PlanFeeInquiryRequest createPlanFeeInquiryRequest() {
        return new PlanFeeInquiryRequest();
    }

    /**
     * Create an instance of {@link PlanFeeDTO }
     * 
     */
    public PlanFeeDTO createPlanFeeDTO() {
        return new PlanFeeDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/agent/planFee/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlanFeeResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/agent/planFee/bean", name = "responseBody")
    public JAXBElement<PlanFeeResponseDTO> createResponseBody(PlanFeeResponseDTO value) {
        return new JAXBElement<PlanFeeResponseDTO>(_ResponseBody_QNAME, PlanFeeResponseDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlanFeeRequestDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/agent/planFee/bean", name = "requestBody")
    public JAXBElement<PlanFeeRequestDTO> createRequestBody(PlanFeeRequestDTO value) {
        return new JAXBElement<PlanFeeRequestDTO>(_RequestBody_QNAME, PlanFeeRequestDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/agent/planFee/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

}
