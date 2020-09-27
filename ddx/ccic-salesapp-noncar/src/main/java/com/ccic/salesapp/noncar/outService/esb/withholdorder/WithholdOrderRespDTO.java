
package com.ccic.salesapp.noncar.outService.esb.withholdorder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;name xmlns="http://service.ccic.com/pss/withholdOrder/bean" xmlns:bean="http://service.ccic.com/pss/withholdOrder/bean" xmlns:common="http://service.ccic.com/common/bean" xmlns:intf="http://service.ccic.com/pss/withholdOrder/intf" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣ��&lt;/name&gt;
 * </pre>
 * 
 * 
 * <p>WithholdOrderRespDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="WithholdOrderRespDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="withholdNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WithholdOrderRespDTO", propOrder = {
    "withholdNo",
    "paymentNo"
})
public class WithholdOrderRespDTO {

    protected String withholdNo;
    protected String paymentNo;

    /**
     * ��ȡwithholdNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWithholdNo() {
        return withholdNo;
    }

    /**
     * ����withholdNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWithholdNo(String value) {
        this.withholdNo = value;
    }

    /**
     * ��ȡpaymentNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentNo() {
        return paymentNo;
    }

    /**
     * ����paymentNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentNo(String value) {
        this.paymentNo = value;
    }

}
