
package com.ccic.salesapp.noncar.outService.esb.proposalliabinsgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;��/������Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>CoinsDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CoinsDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serialNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mainProposalNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="coinsCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="coinsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="coinsType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="coinsRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoinsDTO", propOrder = {
    "serialNo",
    "mainProposalNo",
    "coinsCode",
    "coinsName",
    "coinsType",
    "coinsRate"
})
public class CoinsDTO {

    protected int serialNo;
    @XmlElement(required = true)
    protected String mainProposalNo;
    @XmlElement(required = true)
    protected String coinsCode;
    @XmlElement(required = true)
    protected String coinsName;
    @XmlElement(required = true)
    protected String coinsType;
    protected double coinsRate;

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
     * ��ȡmainProposalNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainProposalNo() {
        return mainProposalNo;
    }

    /**
     * ����mainProposalNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainProposalNo(String value) {
        this.mainProposalNo = value;
    }

    /**
     * ��ȡcoinsCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoinsCode() {
        return coinsCode;
    }

    /**
     * ����coinsCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoinsCode(String value) {
        this.coinsCode = value;
    }

    /**
     * ��ȡcoinsName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoinsName() {
        return coinsName;
    }

    /**
     * ����coinsName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoinsName(String value) {
        this.coinsName = value;
    }

    /**
     * ��ȡcoinsType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoinsType() {
        return coinsType;
    }

    /**
     * ����coinsType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoinsType(String value) {
        this.coinsType = value;
    }

    /**
     * ��ȡcoinsRate���Ե�ֵ��
     * 
     */
    public double getCoinsRate() {
        return coinsRate;
    }

    /**
     * ����coinsRate���Ե�ֵ��
     * 
     */
    public void setCoinsRate(double value) {
        this.coinsRate = value;
    }

}
