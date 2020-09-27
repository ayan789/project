
package com.ccic.salesapp.noncar.dto.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salesapp.noncar.dto.ImgPrevUploadRequestDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://service.ccic.com/h5img/imgPrevUpload/bean}requestHead"/>
 *         &lt;element ref="{http://service.ccic.com/h5img/imgPrevUpload/bean}requestBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestHead",
    "requestBody"
})
@XmlRootElement(name = "ImgPrevUploadRequest")
public class ImgPrevUploadRequest {

    @XmlElement(required = true)
    protected RequestHeadDTO requestHead;
    @XmlElement(required = true)
    protected ImgPrevUploadRequestDTO requestBody;

    /**
     * 获取requestHead属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RequestHeadDTO }
     *     
     */
    public RequestHeadDTO getRequestHead() {
        return requestHead;
    }

    /**
     * 设置requestHead属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RequestHeadDTO }
     *     
     */
    public void setRequestHead(RequestHeadDTO value) {
        this.requestHead = value;
    }

    /**
     * 获取requestBody属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ImgPrevUploadRequestDTO }
     *     
     */
    public ImgPrevUploadRequestDTO getRequestBody() {
        return requestBody;
    }

    /**
     * 设置requestBody属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ImgPrevUploadRequestDTO }
     *     
     */
    public void setRequestBody(ImgPrevUploadRequestDTO value) {
        this.requestBody = value;
    }

}
