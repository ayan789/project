
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
 *         &lt;element ref="{http://service.ccic.com/pss/paySuccess/bean}requestHead"/>
 *         &lt;element ref="{http://service.ccic.com/pss/paySuccess/bean}requestBody"/>
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
@XmlRootElement(name = "PaySuccessNoticeRequest")
public class PaySuccessNoticeRequest {

    @XmlElement(required = true)
    protected RequestHeadDTO requestHead;
    @XmlElement(required = true)
    protected PaySuccessNoticeReqDTO requestBody;

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
     *     {@link PaySuccessNoticeReqDTO }
     *     
     */
    public PaySuccessNoticeReqDTO getRequestBody() {
        return requestBody;
    }

    /**
     * 设置requestBody属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaySuccessNoticeReqDTO }
     *     
     */
    public void setRequestBody(PaySuccessNoticeReqDTO value) {
        this.requestBody = value;
    }

}
