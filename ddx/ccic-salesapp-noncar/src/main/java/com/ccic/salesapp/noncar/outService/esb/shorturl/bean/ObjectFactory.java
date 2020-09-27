
package com.ccic.salesapp.noncar.outService.esb.shorturl.bean;

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

    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/netMarketing/shortUrl/bean", "requestHead");
    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/netMarketing/shortUrl/bean", "requestBody");
    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/netMarketing/shortUrl/bean", "responseHead");
    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/netMarketing/shortUrl/bean", "responseBody");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.salesapp.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShortUrlRequest }
     * 
     */
    public ShortUrlRequest createShortUrlRequest() {
        return new ShortUrlRequest();
    }

    /**
     * Create an instance of {@link RequestHeadDTO }
     * 
     */
    public RequestHeadDTO createRequestHeadDTO() {
        return new RequestHeadDTO();
    }

    /**
     * Create an instance of {@link ShortUrlRequestDTO }
     * 
     */
    public ShortUrlRequestDTO createShortUrlRequestDTO() {
        return new ShortUrlRequestDTO();
    }

    /**
     * Create an instance of {@link ShortUrlResponse }
     * 
     */
    public ShortUrlResponse createShortUrlResponse() {
        return new ShortUrlResponse();
    }

    /**
     * Create an instance of {@link ResponseHeadDTO }
     * 
     */
    public ResponseHeadDTO createResponseHeadDTO() {
        return new ResponseHeadDTO();
    }

    /**
     * Create an instance of {@link ShortUrlResponseDTO }
     * 
     */
    public ShortUrlResponseDTO createShortUrlResponseDTO() {
        return new ShortUrlResponseDTO();
    }

    /**
     * Create an instance of {@link ExpandDTO }
     * 
     */
    public ExpandDTO createExpandDTO() {
        return new ExpandDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/netMarketing/shortUrl/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShortUrlRequestDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/netMarketing/shortUrl/bean", name = "requestBody")
    public JAXBElement<ShortUrlRequestDTO> createRequestBody(ShortUrlRequestDTO value) {
        return new JAXBElement<ShortUrlRequestDTO>(_RequestBody_QNAME, ShortUrlRequestDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/netMarketing/shortUrl/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShortUrlResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/netMarketing/shortUrl/bean", name = "responseBody")
    public JAXBElement<ShortUrlResponseDTO> createResponseBody(ShortUrlResponseDTO value) {
        return new JAXBElement<ShortUrlResponseDTO>(_ResponseBody_QNAME, ShortUrlResponseDTO.class, null, value);
    }

}
