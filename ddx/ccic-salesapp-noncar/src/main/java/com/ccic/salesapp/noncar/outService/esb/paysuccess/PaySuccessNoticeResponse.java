
package com.ccic.salesapp.noncar.outService.esb.paysuccess;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://service.ccic.com/pss/paySuccess/bean}responseHead"/>
 *         &lt;element ref="{http://service.ccic.com/pss/paySuccess/bean}responseBody" minOccurs="0"/>
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
@XmlRootElement(name = "PaySuccessNoticeResponse")
public class PaySuccessNoticeResponse {

    @XmlElement(required = true)
    protected ResponseHeadDTO responseHead;
    protected PaySuccessNoticeRespDTO responseBody;

    /**
     * 获取responseHead属性的值。
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
     * 设置responseHead属性的值。
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
     * 获取responseBody属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaySuccessNoticeRespDTO }
     *     
     */
    public PaySuccessNoticeRespDTO getResponseBody() {
        return responseBody;
    }

    /**
     * 设置responseBody属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaySuccessNoticeRespDTO }
     *     
     */
    public void setResponseBody(PaySuccessNoticeRespDTO value) {
        this.responseBody = value;
    }

}
