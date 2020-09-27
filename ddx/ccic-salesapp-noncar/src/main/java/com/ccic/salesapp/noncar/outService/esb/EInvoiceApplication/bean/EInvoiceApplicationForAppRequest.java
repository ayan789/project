package com.ccic.salesapp.noncar.outService.esb.EInvoiceApplication.bean;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EInvoiceApplicationForAppRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="EInvoiceApplicationForAppRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestHead" type="{http://service.ccic.com/common/bean}RequestHeadDTO"/>
 *         &lt;element name="requestBody" type="{http://service.ccic.com/einvoiceapp/einvoiceApplicationForApp/bean}EInvoiceApplicationForAppRequestBodyDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EInvoiceApplicationForAppRequest", propOrder = {
    "requestHead",
    "requestBody"
})
public class EInvoiceApplicationForAppRequest {

    @XmlElement(required = true)
    protected RequestHeadDTO requestHead;
    @XmlElement(required = true)
    protected EInvoiceApplicationForAppRequestBodyDTO requestBody;

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
     *     {@link EInvoiceApplicationForAppRequestBodyDTO }
     *     
     */
    public EInvoiceApplicationForAppRequestBodyDTO getRequestBody() {
        return requestBody;
    }

    /**
     * ����requestBody���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link EInvoiceApplicationForAppRequestBodyDTO }
     *     
     */
    public void setRequestBody(EInvoiceApplicationForAppRequestBodyDTO value) {
        this.requestBody = value;
    }

}
