
package com.ccic.salesapp.noncar.outService.esb.paysuccess;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PaySuccessNoticeReqDTO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PaySuccessNoticeReqDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="certiNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="payApplyNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="realPayFee" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="accountCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaySuccessNoticeReqDTO", propOrder = {
    "certiNo",
    "payApplyNo",
    "realPayFee",
    "accountCode"
})
public class PaySuccessNoticeReqDTO {

    @XmlElement(required = true)
    protected String certiNo;
    @XmlElement(required = true)
    protected String payApplyNo;
    protected double realPayFee;
    @XmlElement(required = true)
    protected String accountCode;

    /**
     * 获取certiNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertiNo() {
        return certiNo;
    }

    /**
     * 设置certiNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertiNo(String value) {
        this.certiNo = value;
    }

    /**
     * 获取payApplyNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayApplyNo() {
        return payApplyNo;
    }

    /**
     * 设置payApplyNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayApplyNo(String value) {
        this.payApplyNo = value;
    }

    /**
     * 获取realPayFee属性的值。
     * 
     */
    public double getRealPayFee() {
        return realPayFee;
    }

    /**
     * 设置realPayFee属性的值。
     * 
     */
    public void setRealPayFee(double value) {
        this.realPayFee = value;
    }

    /**
     * 获取accountCode属性的值。
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
     * 设置accountCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCode(String value) {
        this.accountCode = value;
    }

}
