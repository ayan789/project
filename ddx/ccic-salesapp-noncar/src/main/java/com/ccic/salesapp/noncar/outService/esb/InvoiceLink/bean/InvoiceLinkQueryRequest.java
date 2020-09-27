package com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean;
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
 *         &lt;element ref="{http://service.ccic.com/payment/InvoiceLinkQuery/bean}requestHead"/>
 *         &lt;element ref="{http://service.ccic.com/payment/InvoiceLinkQuery/bean}requestBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceLinkQueryRequest", propOrder = {
    "requestHead",
    "requestBody"
})
@XmlRootElement(name = "InvoiceLinkQueryRequest")
public class InvoiceLinkQueryRequest {

    @XmlElement(required = true)
    protected RequestHeadDTO requestHead;
    @XmlElement(required = true)
    protected InvoiceLinkQueryRequestDTO requestBody;

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
     *     {@link InvoiceLinkQueryRequestDTO }
     *     
     */
    public InvoiceLinkQueryRequestDTO getRequestBody() {
        return requestBody;
    }

    /**
     * ����requestBody���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceLinkQueryRequestDTO }
     *     
     */
    public void setRequestBody(InvoiceLinkQueryRequestDTO value) {
        this.requestBody = value;
    }

}
