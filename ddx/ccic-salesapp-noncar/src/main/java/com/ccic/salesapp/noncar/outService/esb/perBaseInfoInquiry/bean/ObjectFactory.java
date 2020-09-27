
package com.ccic.salesapp.noncar.outService.esb.perBaseInfoInquiry.bean;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccic.salesapp.customer.outService.esb.ecif.perBaseInfoInquiry package. 
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

    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/ecif/perbaseinfoinquiry/bean", "requestBody");
    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/ecif/perbaseinfoinquiry/bean", "requestHead");
    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/ecif/perbaseinfoinquiry/bean", "responseBody");
    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/ecif/perbaseinfoinquiry/bean", "responseHead");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccic.salesapp.customer.outService.esb.ecif.perBaseInfoInquiry
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PerBaseInfoInquiryRequest }
     * 
     */
    public PerBaseInfoInquiryRequest createPerBaseInfoInquiryRequest() {
        return new PerBaseInfoInquiryRequest();
    }

    /**
     * Create an instance of {@link RequestHeadDTO }
     * 
     */
    public RequestHeadDTO createRequestHeadDTO() {
        return new RequestHeadDTO();
    }

    /**
     * Create an instance of {@link PerBaseInfoInquiryRequestDTO }
     * 
     */
    public PerBaseInfoInquiryRequestDTO createPerBaseInfoInquiryRequestDTO() {
        return new PerBaseInfoInquiryRequestDTO();
    }

    /**
     * Create an instance of {@link PerBaseInfoInquiryResponse }
     * 
     */
    public PerBaseInfoInquiryResponse createPerBaseInfoInquiryResponse() {
        return new PerBaseInfoInquiryResponse();
    }

    /**
     * Create an instance of {@link ResponseHeadDTO }
     * 
     */
    public ResponseHeadDTO createResponseHeadDTO() {
        return new ResponseHeadDTO();
    }

    /**
     * Create an instance of {@link PerBaseInfoInquiryResponseDTO }
     * 
     */
    public PerBaseInfoInquiryResponseDTO createPerBaseInfoInquiryResponseDTO() {
        return new PerBaseInfoInquiryResponseDTO();
    }

    /**
     * Create an instance of {@link PerCustBaseInfoDTO }
     * 
     */
    public PerCustBaseInfoDTO createPerCustBaseInfoDTO() {
        return new PerCustBaseInfoDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerBaseInfoInquiryRequestDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/ecif/perbaseinfoinquiry/bean", name = "requestBody")
    public JAXBElement<PerBaseInfoInquiryRequestDTO> createRequestBody(PerBaseInfoInquiryRequestDTO value) {
        return new JAXBElement<PerBaseInfoInquiryRequestDTO>(_RequestBody_QNAME, PerBaseInfoInquiryRequestDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/ecif/perbaseinfoinquiry/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerBaseInfoInquiryResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/ecif/perbaseinfoinquiry/bean", name = "responseBody")
    public JAXBElement<PerBaseInfoInquiryResponseDTO> createResponseBody(PerBaseInfoInquiryResponseDTO value) {
        return new JAXBElement<PerBaseInfoInquiryResponseDTO>(_ResponseBody_QNAME, PerBaseInfoInquiryResponseDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/ecif/perbaseinfoinquiry/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

}
