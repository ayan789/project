
package com.ccic.salesapp.noncar.outService.esb.withholdstatus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;name xmlns="http://service.ccic.com/pss/withholdStatus/bean" xmlns:bean="http://service.ccic.com/pss/withholdStatus/bean" xmlns:common="http://service.ccic.com/common/bean" xmlns:intf="http://service.ccic.com/pss/withholdStatus/intf" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣ��&lt;/name&gt;
 * </pre>
 * 
 * 
 * <p>WithholdStatusReqDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="WithholdStatusReqDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operatorcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="withholdNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="withholdStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WithholdStatusReqDTO", propOrder = {
    "operatorcode",
    "accountCode",
    "withholdNo",
    "withholdStatus"
})
public class WithholdStatusReqDTO {

    @XmlElement(required = true)
    protected String operatorcode;
    @XmlElement(required = true)
    protected String accountCode;
    @XmlElement(required = true)
    protected String withholdNo;
    @XmlElement(required = true)
    protected String withholdStatus;

    /**
     * ��ȡoperatorcode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorcode() {
        return operatorcode;
    }

    /**
     * ����operatorcode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorcode(String value) {
        this.operatorcode = value;
    }

    /**
     * ��ȡaccountCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * ����accountCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCode(String value) {
        this.accountCode = value;
    }

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
     * ��ȡwithholdStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWithholdStatus() {
        return withholdStatus;
    }

    /**
     * ����withholdStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWithholdStatus(String value) {
        this.withholdStatus = value;
    }

}
