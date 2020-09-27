
package com.ccic.salesapp.noncar.outService.esb.withholdorder;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccic.service.pss.withholdorder.bean package. 
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

    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/pss/withholdOrder/bean", "responseHead");
    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/pss/withholdOrder/bean", "responseBody");
    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/pss/withholdOrder/bean", "requestHead");
    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/pss/withholdOrder/bean", "requestBody");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccic.service.pss.withholdorder.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WithholdOrderReqDTO }
     * 
     */
    public WithholdOrderReqDTO createWithholdOrderReqDTO() {
        return new WithholdOrderReqDTO();
    }

    /**
     * Create an instance of {@link WithholdOrderRequest }
     * 
     */
    public WithholdOrderRequest createWithholdOrderRequest() {
        return new WithholdOrderRequest();
    }

    /**
     * Create an instance of {@link WithholdOrderRespDTO }
     * 
     */
    public WithholdOrderRespDTO createWithholdOrderRespDTO() {
        return new WithholdOrderRespDTO();
    }

    /**
     * Create an instance of {@link WithholdOrderResponse }
     * 
     */
    public WithholdOrderResponse createWithholdOrderResponse() {
        return new WithholdOrderResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/pss/withholdOrder/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WithholdOrderRespDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/pss/withholdOrder/bean", name = "responseBody")
    public JAXBElement<WithholdOrderRespDTO> createResponseBody(WithholdOrderRespDTO value) {
        return new JAXBElement<WithholdOrderRespDTO>(_ResponseBody_QNAME, WithholdOrderRespDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/pss/withholdOrder/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WithholdOrderReqDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/pss/withholdOrder/bean", name = "requestBody")
    public JAXBElement<WithholdOrderReqDTO> createRequestBody(WithholdOrderReqDTO value) {
        return new JAXBElement<WithholdOrderReqDTO>(_RequestBody_QNAME, WithholdOrderReqDTO.class, null, value);
    }

}
