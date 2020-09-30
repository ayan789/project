
package com.ccic.salesapp.performance.outService.esb.sms.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccic.service.sms.sendsms.bean package. 
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

    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/sms/sendSms/bean", "requestHead");
    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/sms/sendSms/bean", "responseHead");
    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/sms/sendSms/bean", "responseBody");
    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/sms/sendSms/bean", "requestBody");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccic.service.sms.sendsms.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendSmsResponseDTO }
     * 
     */
    public SendSmsResponseDTO createSendSmsResponseDTO() {
        return new SendSmsResponseDTO();
    }

    /**
     * Create an instance of {@link SendSmsRequestDTO }
     * 
     */
    public SendSmsRequestDTO createSendSmsRequestDTO() {
        return new SendSmsRequestDTO();
    }

    /**
     * Create an instance of {@link SendSmsRequest }
     * 
     */
    public SendSmsRequest createSendSmsRequest() {
        return new SendSmsRequest();
    }

    /**
     * Create an instance of {@link SendSmsResponse }
     * 
     */
    public SendSmsResponse createSendSmsResponse() {
        return new SendSmsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/sms/sendSms/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/sms/sendSms/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSmsResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/sms/sendSms/bean", name = "responseBody")
    public JAXBElement<SendSmsResponseDTO> createResponseBody(SendSmsResponseDTO value) {
        return new JAXBElement<SendSmsResponseDTO>(_ResponseBody_QNAME, SendSmsResponseDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSmsRequestDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/sms/sendSms/bean", name = "requestBody")
    public JAXBElement<SendSmsRequestDTO> createRequestBody(SendSmsRequestDTO value) {
        return new JAXBElement<SendSmsRequestDTO>(_RequestBody_QNAME, SendSmsRequestDTO.class, null, value);
    }

}