
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ExchangeDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ExchangeDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="baseCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="exchCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="exchangeRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExchangeDTO", propOrder = {
    "baseCurrency",
    "exchCurrency",
    "exchangeRate"
})
public class ExchangeDTO {

    @XmlElement(required = true)
    protected String baseCurrency;
    @XmlElement(required = true)
    protected String exchCurrency;
    protected double exchangeRate;

    /**
     * ��ȡbaseCurrency���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * ����baseCurrency���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseCurrency(String value) {
        this.baseCurrency = value;
    }

    /**
     * ��ȡexchCurrency���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchCurrency() {
        return exchCurrency;
    }

    /**
     * ����exchCurrency���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchCurrency(String value) {
        this.exchCurrency = value;
    }

    /**
     * ��ȡexchangeRate���Ե�ֵ��
     * 
     */
    public double getExchangeRate() {
        return exchangeRate;
    }

    /**
     * ����exchangeRate���Ե�ֵ��
     * 
     */
    public void setExchangeRate(double value) {
        this.exchangeRate = value;
    }

}
