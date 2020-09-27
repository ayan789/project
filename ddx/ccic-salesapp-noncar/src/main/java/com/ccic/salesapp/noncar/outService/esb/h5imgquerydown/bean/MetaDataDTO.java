
package com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;name xmlns="http://service.ccic.com/h5img/h5imgQueryDown/bean" xmlns:bean="http://service.ccic.com/h5img/h5imgQueryDown/bean" xmlns:common="http://service.ccic.com/common/bean" xmlns:intf="http://service.ccic.com/h5img/h5imgQueryDown/intf" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;��չ��Ϣ&lt;/name&gt;
 * </pre>
 * 
 * 
 * <p>MetaDataDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="MetaDataDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="busiDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageIds" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="validMinutes" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
@XmlType(name = "MetaDataDTO", propOrder = {
    "appCode",
    "classCode",
    "busiDate",
    "businessNo",
    "pageIds",
    "validMinutes",
    "extendFields"
})
public class MetaDataDTO {

    @XmlElement(required = true)
    protected String appCode;
    protected String classCode;
    protected String busiDate;
    @XmlElement(required = true)
    protected String businessNo;
    protected List<String> pageIds;
    protected BigInteger validMinutes;
    protected List<ExtendInfoDTO> extendFields;

    /**
     * ��ȡappCode���Ե�ֵ��
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
     * ����appCode���Ե�ֵ��
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
     * ��ȡclassCode���Ե�ֵ��
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
     * ����classCode���Ե�ֵ��
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
     * ��ȡbusiDate���Ե�ֵ��
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
     * ����busiDate���Ե�ֵ��
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
     * ��ȡbusinessNo���Ե�ֵ��
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
     * ����businessNo���Ե�ֵ��
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
     * Gets the value of the pageIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pageIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPageIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPageIds() {
        if (pageIds == null) {
            pageIds = new ArrayList<String>();
        }
        return this.pageIds;
    }

    /**
     * ��ȡvalidMinutes���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getValidMinutes() {
        return validMinutes;
    }

    /**
     * ����validMinutes���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValidMinutes(BigInteger value) {
        this.validMinutes = value;
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
