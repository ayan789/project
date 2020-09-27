
package com.ccic.salesapp.noncar.outService.esb.quoteprice.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/lifePolicy/quotePrice/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/lifePolicy/quotePrice/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;���鷽����Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>RationDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="RationDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="modeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rationCount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="assuranceTypeList" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}AssuranceTypeDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="itemKinds" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}ItemKindDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RationDTO", propOrder = {
    "modeCode",
    "rationCode",
    "rationCount",
    "quantity",
    "assuranceTypeList",
    "itemKinds"
})
public class RationDTO {

    protected String modeCode;
    protected String rationCode;
    protected Double rationCount;
    protected Double quantity;
    protected List<AssuranceTypeDTO> assuranceTypeList;
    protected List<ItemKindDTO> itemKinds;

    /**
     * ��ȡmodeCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModeCode() {
        return modeCode;
    }

    /**
     * ����modeCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModeCode(String value) {
        this.modeCode = value;
    }

    /**
     * ��ȡrationCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRationCode() {
        return rationCode;
    }

    /**
     * ����rationCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRationCode(String value) {
        this.rationCode = value;
    }

    /**
     * ��ȡrationCount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRationCount() {
        return rationCount;
    }

    /**
     * ����rationCount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRationCount(Double value) {
        this.rationCount = value;
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
     * Gets the value of the assuranceTypeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assuranceTypeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssuranceTypeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssuranceTypeDTO }
     * 
     * 
     */
    public List<AssuranceTypeDTO> getAssuranceTypeList() {
        if (assuranceTypeList == null) {
            assuranceTypeList = new ArrayList<AssuranceTypeDTO>();
        }
        return this.assuranceTypeList;
    }

    /**
     * Gets the value of the itemKinds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemKinds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemKinds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemKindDTO }
     * 
     * 
     */
    public List<ItemKindDTO> getItemKinds() {
        if (itemKinds == null) {
            itemKinds = new ArrayList<ItemKindDTO>();
        }
        return this.itemKinds;
    }

}
