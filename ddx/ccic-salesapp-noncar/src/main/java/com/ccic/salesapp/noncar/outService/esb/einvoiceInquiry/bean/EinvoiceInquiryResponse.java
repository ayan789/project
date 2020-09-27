
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
 *         &lt;element ref="{http://service.ccic.com/payment/eInvoiceInquiry/bean}responseHead"/>
 *         &lt;element ref="{http://service.ccic.com/payment/eInvoiceInquiry/bean}responseBody" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EinvoiceInquiryResponse", propOrder = {
    "responseHead",
    "responseBody"
})
@XmlRootElement(name = "EinvoiceInquiryResponse")
public class EinvoiceInquiryResponse {

    @XmlElement(required = true)
    protected ResponseHeadDTO responseHead;
    @XmlElement(required = true)
    protected EinvoiceInquiryResponseDTO responseBody;

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
     *     {@link EinvoiceInquiryResponseDTO }
     *     
     */
    public EinvoiceInquiryResponseDTO getResponseBody() {
        return responseBody;
    }

    /**
     * ����responseBody���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link EinvoiceInquiryResponseDTO }
     *     
     */
    public void setResponseBody(EinvoiceInquiryResponseDTO value) {
        this.responseBody = value;
    }

}
