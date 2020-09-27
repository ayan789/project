
package com.ccic.salesapp.noncar.outService.esb.quoteprice.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/lifePolicy/quotePrice/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/lifePolicy/quotePrice/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>ConstructDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ConstructDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="constructName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constructType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constructAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constructStruct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constructValue" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="constructionArea" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="premiumMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constructUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buildingUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="superVisionUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="designUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConstructDTO", propOrder = {
    "constructName",
    "constructType",
    "constructAddress",
    "constructStruct",
    "constructValue",
    "constructionArea",
    "premiumMode",
    "constructUnit",
    "buildingUnit",
    "superVisionUnit",
    "designUnit"
})
public class ConstructDTO {

    protected String constructName;
    protected String constructType;
    protected String constructAddress;
    protected String constructStruct;
    protected Double constructValue;
    protected Double constructionArea;
    protected String premiumMode;
    protected String constructUnit;
    protected String buildingUnit;
    protected String superVisionUnit;
    protected String designUnit;

    /**
     * ��ȡconstructName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstructName() {
        return constructName;
    }

    /**
     * ����constructName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstructName(String value) {
        this.constructName = value;
    }

    /**
     * ��ȡconstructType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstructType() {
        return constructType;
    }

    /**
     * ����constructType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstructType(String value) {
        this.constructType = value;
    }

    /**
     * ��ȡconstructAddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstructAddress() {
        return constructAddress;
    }

    /**
     * ����constructAddress���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstructAddress(String value) {
        this.constructAddress = value;
    }

    /**
     * ��ȡconstructStruct���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstructStruct() {
        return constructStruct;
    }

    /**
     * ����constructStruct���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstructStruct(String value) {
        this.constructStruct = value;
    }

    /**
     * ��ȡconstructValue���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getConstructValue() {
        return constructValue;
    }

    /**
     * ����constructValue���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setConstructValue(Double value) {
        this.constructValue = value;
    }

    /**
     * ��ȡconstructionArea���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getConstructionArea() {
        return constructionArea;
    }

    /**
     * ����constructionArea���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setConstructionArea(Double value) {
        this.constructionArea = value;
    }

    /**
     * ��ȡpremiumMode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPremiumMode() {
        return premiumMode;
    }

    /**
     * ����premiumMode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPremiumMode(String value) {
        this.premiumMode = value;
    }

    /**
     * ��ȡconstructUnit���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstructUnit() {
        return constructUnit;
    }

    /**
     * ����constructUnit���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstructUnit(String value) {
        this.constructUnit = value;
    }

    /**
     * ��ȡbuildingUnit���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingUnit() {
        return buildingUnit;
    }

    /**
     * ����buildingUnit���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingUnit(String value) {
        this.buildingUnit = value;
    }

    /**
     * ��ȡsuperVisionUnit���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuperVisionUnit() {
        return superVisionUnit;
    }

    /**
     * ����superVisionUnit���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuperVisionUnit(String value) {
        this.superVisionUnit = value;
    }

    /**
     * ��ȡdesignUnit���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesignUnit() {
        return designUnit;
    }

    /**
     * ����designUnit���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesignUnit(String value) {
        this.designUnit = value;
    }

}
