
package com.ccic.salesapp.noncar.outService.esb.imgprevupload.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/h5img/imgPrevUpload/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/h5img/imgPrevUpload/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣ��&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>ImgPrevUploadRequestDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡnetType���Ե�ֵ��
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
     * ����netType���Ե�ֵ��
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
     * ��ȡcomCode���Ե�ֵ��
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
     * ����comCode���Ե�ֵ��
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
     * ��ȡoperator���Ե�ֵ��
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
     * ����operator���Ե�ֵ��
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
     * ��ȡoperatorName���Ե�ֵ��
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
     * ����operatorName���Ե�ֵ��
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
     * ��ȡoperatorRole���Ե�ֵ��
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
     * ����operatorRole���Ե�ֵ��
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
     * ��ȡimgBusiDto���Ե�ֵ��
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
     * ����imgBusiDto���Ե�ֵ��
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
     * ��ȡuploadStep���Ե�ֵ��
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
     * ����uploadStep���Ե�ֵ��
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
