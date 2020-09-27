
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
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/h5img/imgPrevUpload/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/h5img/imgPrevUpload/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;请求消息体&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>ImgPrevUploadRequestDTO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ImgPrevUploadRequestDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="netType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatorRole" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imgBusiDto" type="{http://service.ccic.com/h5img/imgPrevUpload/bean}ImgBusiDTO"/>
 *         &lt;element name="uploadStep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imgItems" type="{http://service.ccic.com/h5img/imgPrevUpload/bean}ImgItemDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="imgTypes" type="{http://service.ccic.com/h5img/imgPrevUpload/bean}ImgTypeDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ImgPrevUploadRequestDTO", propOrder = {
    "netType",
    "comCode",
    "operator",
    "operatorName",
    "operatorRole",
    "imgBusiDto",
    "uploadStep",
    "imgItems",
    "imgTypes",
    "extendFields"
})
public class ImgPrevUploadRequestDTO {

    @XmlElement(required = true)
    protected String netType;
    @XmlElement(required = true)
    protected String comCode;
    @XmlElement(required = true)
    protected String operator;
    protected String operatorName;
    @XmlElement(required = true)
    protected String operatorRole;
    @XmlElement(required = true)
    protected ImgBusiDTO imgBusiDto;
    protected String uploadStep;
    protected List<ImgItemDTO> imgItems;
    protected List<ImgTypeDTO> imgTypes;
    protected List<ExtendInfoDTO> extendFields;

    /**
     * 获取netType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetType() {
        return netType;
    }

    /**
     * 设置netType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetType(String value) {
        this.netType = value;
    }

    /**
     * 获取comCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComCode() {
        return comCode;
    }

    /**
     * 设置comCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComCode(String value) {
        this.comCode = value;
    }

    /**
     * 获取operator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置operator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperator(String value) {
        this.operator = value;
    }

    /**
     * 获取operatorName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 设置operatorName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorName(String value) {
        this.operatorName = value;
    }

    /**
     * 获取operatorRole属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorRole() {
        return operatorRole;
    }

    /**
     * 设置operatorRole属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorRole(String value) {
        this.operatorRole = value;
    }

    /**
     * 获取imgBusiDto属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ImgBusiDTO }
     *     
     */
    public ImgBusiDTO getImgBusiDto() {
        return imgBusiDto;
    }

    /**
     * 设置imgBusiDto属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ImgBusiDTO }
     *     
     */
    public void setImgBusiDto(ImgBusiDTO value) {
        this.imgBusiDto = value;
    }

    /**
     * 获取uploadStep属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadStep() {
        return uploadStep;
    }

    /**
     * 设置uploadStep属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadStep(String value) {
        this.uploadStep = value;
    }

    /**
     * Gets the value of the imgItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imgItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImgItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImgItemDTO }
     * 
     * 
     */
    public List<ImgItemDTO> getImgItems() {
        if (imgItems == null) {
            imgItems = new ArrayList<ImgItemDTO>();
        }
        return this.imgItems;
    }

    /**
     * Gets the value of the imgTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imgTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImgTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImgTypeDTO }
     * 
     * 
     */
    public List<ImgTypeDTO> getImgTypes() {
        if (imgTypes == null) {
            imgTypes = new ArrayList<ImgTypeDTO>();
        }
        return this.imgTypes;
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
