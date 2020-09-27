
package com.ccic.salesapp.noncar.outService.esb.withholdstatus;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccic.service.pss.withholdstatus.bean package. 
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

    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/pss/withholdStatus/bean", "requestHead");
    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/pss/withholdStatus/bean", "requestBody");
    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/pss/withholdStatus/bean", "responseHead");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccic.service.pss.withholdstatus.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WithholdStatusReqDTO }
     * 
     */
    public WithholdStatusReqDTO createWithholdStatusReqDTO() {
        return new WithholdStatusReqDTO();
    }

    /**
     * Create an instance of {@link WithholdStatusRequest }
     * 
     */
    public WithholdStatusRequest createWithholdStatusRequest() {
        return new WithholdStatusRequest();
    }

    /**
     * Create an instance of {@link WithholdStatusResponse }
     * 
     */
    public WithholdStatusResponse createWithholdStatusResponse() {
        return new WithholdStatusResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/pss/withholdStatus/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WithholdStatusReqDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/pss/withholdStatus/bean", name = "requestBody")
    public JAXBElement<WithholdStatusReqDTO> createRequestBody(WithholdStatusReqDTO value) {
        return new JAXBElement<WithholdStatusReqDTO>(_RequestBody_QNAME, WithholdStatusReqDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/pss/withholdStatus/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

}
