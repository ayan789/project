
package com.ccic.salesapp.noncar.outService.esb.quoteprice.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/lifePolicy/quotePrice/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/lifePolicy/quotePrice/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;ũҵ������Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>SubsidyDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SubsidyDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agriPolicySort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subsidyNature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subsidyNatureName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subsidySort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subsidySortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supportSort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supportSortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subsidyDepartment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="subsidyRate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="subsidyPremium" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="basePremium" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubsidyDTO", propOrder = {
    "agriPolicySort",
    "subsidyNature",
    "subsidyNatureName",
    "subsidySort",
    "subsidySortName",
    "supportSort",
    "supportSortName",
    "subsidyDepartment",
    "quantity",
    "subsidyRate",
    "subsidyPremium",
    "basePremium"
})
public class SubsidyDTO {

    protected String agriPolicySort;
    protected String subsidyNature;
    protected String subsidyNatureName;
    protected String subsidySort;
    protected String subsidySortName;
    protected String supportSort;
    protected String supportSortName;
    protected String subsidyDepartment;
    protected Double quantity;
    protected Double subsidyRate;
    protected Double subsidyPremium;
    protected Double basePremium;

    /**
     * ��ȡagriPolicySort���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgriPolicySort() {
        return agriPolicySort;
    }

    /**
     * ����agriPolicySort���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgriPolicySort(String value) {
        this.agriPolicySort = value;
    }

    /**
     * ��ȡsubsidyNature���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsidyNature() {
        return subsidyNature;
    }

    /**
     * ����subsidyNature���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsidyNature(String value) {
        this.subsidyNature = value;
    }

    /**
     * ��ȡsubsidyNatureName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsidyNatureName() {
        return subsidyNatureName;
    }

    /**
     * ����subsidyNatureName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsidyNatureName(String value) {
        this.subsidyNatureName = value;
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
     * ��ȡsupportSort���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportSort() {
        return supportSort;
    }

    /**
     * ����supportSort���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportSort(String value) {
        this.supportSort = value;
    }

    /**
     * ��ȡsupportSortName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportSortName() {
        return supportSortName;
    }

    /**
     * ����supportSortName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportSortName(String value) {
        this.supportSortName = value;
    }

    /**
     * ��ȡsubsidyDepartment���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsidyDepartment() {
        return subsidyDepartment;
    }

    /**
     * ����subsidyDepartment���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsidyDepartment(String value) {
        this.subsidyDepartment = value;
    }

    /**
     * ��ȡquantity���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * ����quantity���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantity(Double value) {
        this.quantity = value;
    }

    /**
     * ��ȡsubsidyRate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSubsidyRate() {
        return subsidyRate;
    }

    /**
     * ����subsidyRate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSubsidyRate(Double value) {
        this.subsidyRate = value;
    }

    /**
     * ��ȡsubsidyPremium���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSubsidyPremium() {
        return subsidyPremium;
    }

    /**
     * ����subsidyPremium���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSubsidyPremium(Double value) {
        this.subsidyPremium = value;
    }

    /**
     * ��ȡbasePremium���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBasePremium() {
        return basePremium;
    }

    /**
     * ����basePremium���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBasePremium(Double value) {
        this.basePremium = value;
    }

}
