
package com.ccic.salesapp.noncar.outService.esb.proposalliabinsgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;ũҵ����(ũҵ����)��Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>AgriSubsidyDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="AgriSubsidyDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serialNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="subsidySort" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subsidySortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subsidyRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AgriSubsidyDTO", propOrder = {
    "serialNo",
    "subsidySort",
    "subsidySortName",
    "subsidyRate"
})
public class AgriSubsidyDTO {

    protected Integer serialNo;
    @XmlElement(required = true)
    protected String subsidySort;
    protected String subsidySortName;
    protected double subsidyRate;

    /**
     * ��ȡserialNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSerialNo() {
        return serialNo;
    }

    /**
     * ����serialNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSerialNo(Integer value) {
        this.serialNo = value;
    }

    /**
     * ��ȡsubsidySort���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsidySort() {
        return subsidySort;
    }

    /**
     * ����subsidySort���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsidySort(String value) {
        this.subsidySort = value;
    }

    /**
     * ��ȡsubsidySortName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsidySortName() {
        return subsidySortName;
    }

    /**
     * ����subsidySortName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsidySortName(String value) {
        this.subsidySortName = value;
    }

    /**
     * ��ȡsubsidyRate���Ե�ֵ��
     * 
     */
    public double getSubsidyRate() {
        return subsidyRate;
    }

    /**
     * ����subsidyRate���Ե�ֵ��
     * 
     */
    public void setSubsidyRate(double value) {
        this.subsidyRate = value;
    }

}
