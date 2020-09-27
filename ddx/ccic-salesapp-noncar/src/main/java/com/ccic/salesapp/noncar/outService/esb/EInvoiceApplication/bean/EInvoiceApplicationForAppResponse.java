package com.ccic.salesapp.noncar.outService.esb.EInvoiceApplication.bean;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EInvoiceApplicationForAppResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="EInvoiceApplicationForAppResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseHead" type="{http://service.ccic.com/common/bean}ResponseHeadDTO"/>
 *         &lt;element name="responseBody" type="{http://service.ccic.com/einvoiceapp/einvoiceApplicationForApp/bean}EInvoiceApplicationForAppResponseBodyDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EInvoiceApplicationForAppResponse", propOrder = {
    "responseHead",
    "responseBody"
})
public class EInvoiceApplicationForAppResponse {

    @XmlElement(required = true)
    protected ResponseHeadDTO responseHead;
    @XmlElement(required = true)
    protected EInvoiceApplicationForAppResponseBodyDTO responseBody;

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
     *     {@link EInvoiceApplicationForAppResponseBodyDTO }
     *     
     */
    public EInvoiceApplicationForAppResponseBodyDTO getResponseBody() {
        return responseBody;
    }

    /**
     * ����responseBody���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link EInvoiceApplicationForAppResponseBodyDTO }
     *     
     */
    public void setResponseBody(EInvoiceApplicationForAppResponseBodyDTO value) {
        this.responseBody = value;
    }

}
