
package com.ccic.salesapp.noncar.outService.esb.sms.bean;

import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;

import javax.xml.bind.annotation.*;


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
 *         &lt;element ref="{http://service.ccic.com/sms/sendSms/bean}responseHead"/>
 *         &lt;element ref="{http://service.ccic.com/sms/sendSms/bean}responseBody" minOccurs="0"/>
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
@XmlRootElement(name = "SendSmsResponse")
public class SendSmsResponse {

    @XmlElement(required = true)
    protected ResponseHeadDTO responseHead;
    protected SendSmsResponseDTO responseBody;

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
     *     {@link SendSmsResponseDTO }
     *     
     */
    public SendSmsResponseDTO getResponseBody() {
        return responseBody;
    }

    /**
     * ����responseBody���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link SendSmsResponseDTO }
     *     
     */
    public void setResponseBody(SendSmsResponseDTO value) {
        this.responseBody = value;
    }

}
