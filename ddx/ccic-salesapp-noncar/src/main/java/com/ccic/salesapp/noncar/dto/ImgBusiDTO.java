
package com.ccic.salesapp.noncar.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/h5img/imgPrevUpload/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/h5img/imgPrevUpload/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;业务信息&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>ImgBusiDTO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ImgBusiDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="busiDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="busiComCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extendFields" type="{http://service.ccic.com/h5img/imgPrevUpload/bean}ExtendInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImgBusiDTO", propOrder = {
    "appCode",
    "classCode",
    "businessNo",
    "busiDate",
    "busiComCode",
    "extendFields"
})
public class ImgBusiDTO {

    @XmlElement(required = true)
    protected String appCode;
    protected String classCode;
    @XmlElement(required = true)
    protected String businessNo;
    @XmlElement(required = true)
    protected String busiDate;
    @XmlElement(required = true)
    protected String busiComCode;
    protected List<ExtendInfoDTO> extendFields;

    /**
     * 获取appCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * 设置appCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppCode(String value) {
        this.appCode = value;
    }

    /**
     * 获取classCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * 设置classCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassCode(String value) {
        this.classCode = value;
    }

    /**
     * 获取businessNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessNo() {
        return businessNo;
    }

    /**
     * 设置businessNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessNo(String value) {
        this.businessNo = value;
    }

    /**
     * 获取busiDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiDate() {
        return busiDate;
    }

    /**
     * 设置busiDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiDate(String value) {
        this.busiDate = value;
    }

    /**
     * 获取busiComCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiComCode() {
        return busiComCode;
    }

    /**
     * 设置busiComCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiComCode(String value) {
        this.busiComCode = value;
    }

    /**
     * Gets the value of the extendFields property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extendFields property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtendFields().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtendInfoDTO }
     * 
     * 
     */
    public List<ExtendInfoDTO> getExtendFields() {
        if (extendFields == null) {
            extendFields = new ArrayList<ExtendInfoDTO>();
        }
        return this.extendFields;
    }

}