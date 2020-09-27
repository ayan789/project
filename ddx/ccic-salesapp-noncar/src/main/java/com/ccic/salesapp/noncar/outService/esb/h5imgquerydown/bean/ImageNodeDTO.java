
package com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;name xmlns="http://service.ccic.com/h5img/h5imgQueryDown/bean" xmlns:bean="http://service.ccic.com/h5img/h5imgQueryDown/bean" xmlns:common="http://service.ccic.com/common/bean" xmlns:intf="http://service.ccic.com/h5img/h5imgQueryDown/intf" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;Ӱ��ڵ���Ϣ&lt;/name&gt;
 * </pre>
 * 
 * 
 * <p>ImageNodeDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ImageNodeDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="picName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="picClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="picDetails" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="picPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="thumbnailPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orgFileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extendFields" type="{http://service.ccic.com/h5img/h5imgQueryDown/bean}ExtendInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageNodeDTO", propOrder = {
    "picName",
    "picClass",
    "picDetails",
    "picPath",
    "thumbnailPath",
    "pageId",
    "orgFileName",
    "extendFields"
})
public class ImageNodeDTO {

    @XmlElement(required = true)
    protected String picName;
    @XmlElement(required = true)
    protected String picClass;
    @XmlElement(required = true)
    protected String picDetails;
    @XmlElement(required = true)
    protected String picPath;
    @XmlElement(required = true)
    protected String thumbnailPath;
    @XmlElement(required = true)
    protected String pageId;
    @XmlElement(required = true)
    protected String orgFileName;
    protected List<ExtendInfoDTO> extendFields;

    /**
     * ��ȡpicName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicName() {
        return picName;
    }

    /**
     * ����picName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicName(String value) {
        this.picName = value;
    }

    /**
     * ��ȡpicClass���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicClass() {
        return picClass;
    }

    /**
     * ����picClass���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicClass(String value) {
        this.picClass = value;
    }

    /**
     * ��ȡpicDetails���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicDetails() {
        return picDetails;
    }

    /**
     * ����picDetails���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicDetails(String value) {
        this.picDetails = value;
    }

    /**
     * ��ȡpicPath���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * ����picPath���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicPath(String value) {
        this.picPath = value;
    }

    /**
     * ��ȡthumbnailPath���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThumbnailPath() {
        return thumbnailPath;
    }

    /**
     * ����thumbnailPath���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThumbnailPath(String value) {
        this.thumbnailPath = value;
    }

    /**
     * ��ȡpageId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * ����pageId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageId(String value) {
        this.pageId = value;
    }

    /**
     * ��ȡorgFileName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgFileName() {
        return orgFileName;
    }

    /**
     * ����orgFileName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgFileName(String value) {
        this.orgFileName = value;
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
