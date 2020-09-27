
package com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.ccic.salesapp.noncar.dto.request.RequestHeadDTO;
import com.ccic.salesapp.noncar.dto.request.ResponseHeadDTO;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccic.service.h5img.h5imgquerydown.bean package. 
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

    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/h5img/h5imgQueryDown/bean", "responseBody");
    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/h5img/h5imgQueryDown/bean", "responseHead");
    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/h5img/h5imgQueryDown/bean", "requestBody");
    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/h5img/h5imgQueryDown/bean", "requestHead");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccic.service.h5img.h5imgquerydown.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link H5ImgQueryDownResponseDTO }
     * 
     */
    public H5ImgQueryDownResponseDTO createH5ImgQueryDownResponseDTO() {
        return new H5ImgQueryDownResponseDTO();
    }

    /**
     * Create an instance of {@link H5ImgQueryDownRequestDTO }
     * 
     */
    public H5ImgQueryDownRequestDTO createH5ImgQueryDownRequestDTO() {
        return new H5ImgQueryDownRequestDTO();
    }

    /**
     * Create an instance of {@link H5ImgQueryDownRequest }
     * 
     */
    public H5ImgQueryDownRequest createH5ImgQueryDownRequest() {
        return new H5ImgQueryDownRequest();
    }

    /**
     * Create an instance of {@link H5ImgQueryDownResponse }
     * 
     */
    public H5ImgQueryDownResponse createH5ImgQueryDownResponse() {
        return new H5ImgQueryDownResponse();
    }

    /**
     * Create an instance of {@link MetaDataDTO }
     * 
     */
    public MetaDataDTO createMetaDataDTO() {
        return new MetaDataDTO();
    }

    /**
     * Create an instance of {@link BaseDataDTO }
     * 
     */
    public BaseDataDTO createBaseDataDTO() {
        return new BaseDataDTO();
    }

    /**
     * Create an instance of {@link ImageNodeDTO }
     * 
     */
    public ImageNodeDTO createImageNodeDTO() {
        return new ImageNodeDTO();
    }

    /**
     * Create an instance of {@link ExtendInfoDTO }
     * 
     */
    public ExtendInfoDTO createExtendInfoDTO() {
        return new ExtendInfoDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link H5ImgQueryDownResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/h5img/h5imgQueryDown/bean", name = "responseBody")
    public JAXBElement<H5ImgQueryDownResponseDTO> createResponseBody(H5ImgQueryDownResponseDTO value) {
        return new JAXBElement<H5ImgQueryDownResponseDTO>(_ResponseBody_QNAME, H5ImgQueryDownResponseDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/h5img/h5imgQueryDown/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link H5ImgQueryDownRequestDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/h5img/h5imgQueryDown/bean", name = "requestBody")
    public JAXBElement<H5ImgQueryDownRequestDTO> createRequestBody(H5ImgQueryDownRequestDTO value) {
        return new JAXBElement<H5ImgQueryDownRequestDTO>(_RequestBody_QNAME, H5ImgQueryDownRequestDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/h5img/h5imgQueryDown/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

}
