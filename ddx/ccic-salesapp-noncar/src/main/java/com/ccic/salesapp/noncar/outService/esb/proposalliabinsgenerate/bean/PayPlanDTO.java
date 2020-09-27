
package com.ccic.salesapp.noncar.outService.esb.proposalliabinsgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;�ɷѼƻ���Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>PayPlanDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="PayPlanDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="payReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="planDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="planFee" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PayPlanDTO", propOrder = {
    "payNo",
    "payReason",
    "planDate",
    "currency",
    "planFee"
})
public class PayPlanDTO {

    protected int payNo;
    @XmlElement(required = true)
    protected String payReason;
    @XmlElement(required = true)
    protected String planDate;
    @XmlElement(required = true)
    protected String currency;
    protected Double planFee;

    /**
     * ��ȡpayNo���Ե�ֵ��
     * 
     */
    public int getPayNo() {
        return payNo;
    }

    /**
     * ����payNo���Ե�ֵ��
     * 
     */
    public void setPayNo(int value) {
        this.payNo = value;
    }

    /**
     * ��ȡpayReason���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayReason() {
        return payReason;
    }

    /**
     * ����payReason���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayReason(String value) {
        this.payReason = value;
    }

    /**
     * ��ȡplanDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanDate() {
        return planDate;
    }

    /**
     * ����planDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanDate(String value) {
        this.planDate = value;
    }

    /**
     * ��ȡcurrency���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * ����currency���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * ��ȡplanFee���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPlanFee() {
        return planFee;
    }

    /**
     * ����planFee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPlanFee(Double value) {
        this.planFee = value;
    }

}
