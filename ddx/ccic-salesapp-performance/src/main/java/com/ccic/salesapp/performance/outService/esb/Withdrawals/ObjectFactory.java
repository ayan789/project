package com.ccic.salesapp.performance.outService.esb.Withdrawals;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tixian.salesapp.ws package. 
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

    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/agent/withdrawals/bean", "responseHead");
    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/agent/withdrawals/bean", "responseBody");
    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/agent/withdrawals/bean", "requestBody");
    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/agent/withdrawals/bean", "requestHead");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tixian.salesapp.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WithdrawalsResponse }
     * 
     */
    public WithdrawalsResponse createWithdrawalsResponse() {
        return new WithdrawalsResponse();
    }

    /**
     * Create an instance of {@link ResponseHeadDTO }
     * 
     */
    public ResponseHeadDTO createResponseHeadDTO() {
        return new ResponseHeadDTO();
    }

    /**
     * Create an instance of {@link WithdrawalsResponseDTO }
     * 
     */
    public WithdrawalsResponseDTO createWithdrawalsResponseDTO() {
        return new WithdrawalsResponseDTO();
    }

    /**
     * Create an instance of {@link WithdrawalsRequestDTO }
     * 
     */
    public WithdrawalsRequestDTO createWithdrawalsRequestDTO() {
        return new WithdrawalsRequestDTO();
    }

    /**
     * Create an instance of {@link WithdrawalsRequest }
     * 
     */
    public WithdrawalsRequest createWithdrawalsRequest() {
        return new WithdrawalsRequest();
    }

    /**
     * Create an instance of {@link RequestHeadDTO }
     * 
     */
    public RequestHeadDTO createRequestHeadDTO() {
        return new RequestHeadDTO();
    }

    /**
     * Create an instance of {@link DetailDTO }
     * 
     */
    public DetailDTO createDetailDTO() {
        return new DetailDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/agent/withdrawals/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WithdrawalsResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/agent/withdrawals/bean", name = "responseBody")
    public JAXBElement<WithdrawalsResponseDTO> createResponseBody(WithdrawalsResponseDTO value) {
        return new JAXBElement<WithdrawalsResponseDTO>(_ResponseBody_QNAME, WithdrawalsResponseDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WithdrawalsRequestDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/agent/withdrawals/bean", name = "requestBody")
    public JAXBElement<WithdrawalsRequestDTO> createRequestBody(WithdrawalsRequestDTO value) {
        return new JAXBElement<WithdrawalsRequestDTO>(_RequestBody_QNAME, WithdrawalsRequestDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/agent/withdrawals/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

}
