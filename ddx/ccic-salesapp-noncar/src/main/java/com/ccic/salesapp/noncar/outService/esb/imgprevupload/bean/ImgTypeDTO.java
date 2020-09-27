
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
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/h5img/imgPrevUpload/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/h5img/imgPrevUpload/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;��֤������Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>ImgTypeDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ImgTypeDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="imgType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imgTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="childrens" type="{http://service.ccic.com/h5img/imgPrevUpload/bean}ImgTypeDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ImgTypeDTO", propOrder = {
    "imgType",
    "imgTypeName",
    "childrens",
    "extendFields"
})
public class ImgTypeDTO {

    @XmlElement(required = true)
    protected String imgType;
    protected String imgTypeName;
    protected List<ImgTypeDTO> childrens;
    protected List<ExtendInfoDTO> extendFields;

    /**
     * ��ȡimgType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgType() {
        return imgType;
    }

    /**
     * ����imgType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgType(String value) {
        this.imgType = value;
    }

    /**
     * ��ȡimgTypeName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgTypeName() {
        return imgTypeName;
    }

    /**
     * ����imgTypeName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgTypeName(String value) {
        this.imgTypeName = value;
    }

    /**
     * Gets the value of the childrens property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childrens property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildrens().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImgTypeDTO }
     * 
     * 
     */
    public List<ImgTypeDTO> getChildrens() {
        if (childrens == null) {
            childrens = new ArrayList<ImgTypeDTO>();
        }
        return this.childrens;
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
