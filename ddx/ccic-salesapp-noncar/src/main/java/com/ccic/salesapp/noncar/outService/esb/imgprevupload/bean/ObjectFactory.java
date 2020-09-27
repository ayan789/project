
package com.ccic.salesapp.noncar.outService.esb.imgprevupload.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccic.service.h5img.imgprevupload.bean package. 
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

    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/h5img/imgPrevUpload/bean", "requestBody");
    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/h5img/imgPrevUpload/bean", "requestHead");
    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/h5img/imgPrevUpload/bean", "responseBody");
    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/h5img/imgPrevUpload/bean", "responseHead");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccic.service.h5img.imgprevupload.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ImgPrevUploadResponse }
     * 
     */
    public ImgPrevUploadResponse createImgPrevUploadResponse() {
        return new ImgPrevUploadResponse();
    }

    /**
     * Create an instance of {@link ImgPrevUploadResponseDTO }
     * 
     */
    public ImgPrevUploadResponseDTO createImgPrevUploadResponseDTO() {
        return new ImgPrevUploadResponseDTO();
    }

    /**
     * Create an instance of {@link ImgPrevUploadRequestDTO }
     * 
     */
    public ImgPrevUploadRequestDTO createImgPrevUploadRequestDTO() {
        return new ImgPrevUploadRequestDTO();
    }

    /**
     * Create an instance of {@link ImgPrevUploadRequest }
     * 
     */
    public ImgPrevUploadRequest createImgPrevUploadRequest() {
        return new ImgPrevUploadRequest();
    }

    /**
     * Create an instance of {@link ExtendInfoDTO }
     * 
     */
    public ExtendInfoDTO createExtendInfoDTO() {
        return new ExtendInfoDTO();
    }

    /**
     * Create an instance of {@link ImgBusiDTO }
     * 
     */
    public ImgBusiDTO createImgBusiDTO() {
        return new ImgBusiDTO();
    }

    /**
     * Create an instance of {@link ImgItemDTO }
     * 
     */
    public ImgItemDTO createImgItemDTO() {
        return new ImgItemDTO();
    }

    /**
     * Create an instance of {@link ImgTypeDTO }
     * 
     */
    public ImgTypeDTO createImgTypeDTO() {
        return new ImgTypeDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImgPrevUploadRequestDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/h5img/imgPrevUpload/bean", name = "requestBody")
    public JAXBElement<ImgPrevUploadRequestDTO> createRequestBody(ImgPrevUploadRequestDTO value) {
        return new JAXBElement<ImgPrevUploadRequestDTO>(_RequestBody_QNAME, ImgPrevUploadRequestDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/h5img/imgPrevUpload/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImgPrevUploadResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/h5img/imgPrevUpload/bean", name = "responseBody")
    public JAXBElement<ImgPrevUploadResponseDTO> createResponseBody(ImgPrevUploadResponseDTO value) {
        return new JAXBElement<ImgPrevUploadResponseDTO>(_ResponseBody_QNAME, ImgPrevUploadResponseDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/h5img/imgPrevUpload/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

}
