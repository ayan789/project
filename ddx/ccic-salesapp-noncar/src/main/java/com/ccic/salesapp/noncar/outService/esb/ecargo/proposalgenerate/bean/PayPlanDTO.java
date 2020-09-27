
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PayPlanDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="PayPlanDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serialNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="payNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="payReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="planDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="planFee" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tniPlanFee" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="vatPlanFee" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
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
    "serialNo",
    "payNo",
    "payReason",
    "planDate",
    "currency",
    "planFee",
    "tniPlanFee",
    "vatPlanFee"
})
public class PayPlanDTO {

    protected int serialNo;
    protected int payNo;
    @XmlElement(required = true)
    protected String payReason;
    @XmlElement(required = true)
    protected String planDate;
    @XmlElement(required = true)
    protected String currency;
    protected double planFee;
    protected Double tniPlanFee;
    protected Double vatPlanFee;

    /**
     * ��ȡserialNo���Ե�ֵ��
     * 
     */
    public int getSerialNo() {
        return serialNo;
    }

    /**
     * ����serialNo���Ե�ֵ��
     * 
     */
    public void setSerialNo(int value) {
        this.serialNo = value;
    }

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
     */
    public double getPlanFee() {
        return planFee;
    }

    /**
     * ����planFee���Ե�ֵ��
     * 
     */
    public void setPlanFee(double value) {
        this.planFee = value;
    }

    /**
     * ��ȡtniPlanFee���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTniPlanFee() {
        return tniPlanFee;
    }

    /**
     * ����tniPlanFee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTniPlanFee(Double value) {
        this.tniPlanFee = value;
    }

    /**
     * ��ȡvatPlanFee���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVatPlanFee() {
        return vatPlanFee;
    }

    /**
     * ����vatPlanFee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVatPlanFee(Double value) {
        this.vatPlanFee = value;
    }

}
