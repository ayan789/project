
package com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salesapp.noncar.dto.request.ResponseHeadDTO;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://service.ccic.com/h5img/h5imgQueryDown/bean}responseHead"/>
 *         &lt;element ref="{http://service.ccic.com/h5img/h5imgQueryDown/bean}responseBody" minOccurs="0"/>
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
    "responseHead",
    "responseBody"
})
@XmlRootElement(name = "H5imgQueryDownResponse")
public class H5ImgQueryDownResponse {

    @XmlElement(required = true)
    protected ResponseHeadDTO responseHead;
    protected H5ImgQueryDownResponseDTO responseBody;

    /**
     * ��ȡresponseHead���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ResponseHeadDTO }
     *     
     */
    public ResponseHeadDTO getResponseHead() {
        return responseHead;
    }

    /**
     * ����responseHead���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseHeadDTO }
     *     
     */
    public void setResponseHead(ResponseHeadDTO value) {
        this.responseHead = value;
    }

    /**
     * ��ȡresponseBody���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link H5ImgQueryDownResponseDTO }
     *     
     */
    public H5ImgQueryDownResponseDTO getResponseBody() {
        return responseBody;
    }

    /**
     * ����responseBody���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link H5ImgQueryDownResponseDTO }
     *     
     */
    public void setResponseBody(H5ImgQueryDownResponseDTO value) {
        this.responseBody = value;
    }

}
