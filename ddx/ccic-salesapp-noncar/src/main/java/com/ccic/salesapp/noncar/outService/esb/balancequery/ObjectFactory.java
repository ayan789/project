
package com.ccic.salesapp.noncar.outService.esb.balancequery;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccic.service.pss.balancequery.bean package. 
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

    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/pss/balanceQuery/bean", "responseBody");
    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/pss/balanceQuery/bean", "responseHead");
    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/pss/balanceQuery/bean", "requestBody");
    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/pss/balanceQuery/bean", "requestHead");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccic.service.pss.balancequery.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BalanceQueryReqDTO }
     * 
     */
    public BalanceQueryReqDTO createBalanceQueryReqDTO() {
        return new BalanceQueryReqDTO();
    }

    /**
     * Create an instance of {@link BalanceQueryRequest }
     * 
     */
    public BalanceQueryRequest createBalanceQueryRequest() {
        return new BalanceQueryRequest();
    }

    /**
     * Create an instance of {@link BalanceQueryResponse }
     * 
     */
    public BalanceQueryResponse createBalanceQueryResponse() {
        return new BalanceQueryResponse();
    }

    /**
     * Create an instance of {@link BalanceQueryRespDTO }
     * 
     */
    public BalanceQueryRespDTO createBalanceQueryRespDTO() {
        return new BalanceQueryRespDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BalanceQueryRespDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/pss/balanceQuery/bean", name = "responseBody")
    public JAXBElement<BalanceQueryRespDTO> createResponseBody(BalanceQueryRespDTO value) {
        return new JAXBElement<BalanceQueryRespDTO>(_ResponseBody_QNAME, BalanceQueryRespDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/pss/balanceQuery/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BalanceQueryReqDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/pss/balanceQuery/bean", name = "requestBody")
    public JAXBElement<BalanceQueryReqDTO> createRequestBody(BalanceQueryReqDTO value) {
        return new JAXBElement<BalanceQueryReqDTO>(_RequestBody_QNAME, BalanceQueryReqDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/pss/balanceQuery/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

}
