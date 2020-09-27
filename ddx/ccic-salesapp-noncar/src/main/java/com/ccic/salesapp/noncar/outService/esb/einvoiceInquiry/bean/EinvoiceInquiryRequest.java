
package com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://service.ccic.com/payment/eInvoiceInquiry/bean}requestHead"/>
 *         &lt;element ref="{http://service.ccic.com/payment/eInvoiceInquiry/bean}requestBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EinvoiceInquiryRequest", propOrder = {
    "requestHead",
    "requestBody"
})
@XmlRootElement(name = "EinvoiceInquiryRequest")
public class EinvoiceInquiryRequest {

    @XmlElement(required = true)
    protected RequestHeadDTO requestHead;
    @XmlElement(required = true)
    protected EinvoiceInquiryRequestBodyDTO requestBody;

    /**
     * ��ȡrequestHead���Ե�ֵ��
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
     * ����requestHead���Ե�ֵ��
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
     * ��ȡrequestBody���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link EinvoiceInquiryRequestBodyDTO }
     *     
     */
    public EinvoiceInquiryRequestBodyDTO getRequestBody() {
        return requestBody;
    }

    /**
     * ����requestBody���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link EinvoiceInquiryRequestBodyDTO }
     *     
     */
    public void setRequestBody(EinvoiceInquiryRequestBodyDTO value) {
        this.requestBody = value;
    }

}
