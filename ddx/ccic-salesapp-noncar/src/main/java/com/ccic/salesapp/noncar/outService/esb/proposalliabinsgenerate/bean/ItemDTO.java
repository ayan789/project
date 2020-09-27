
package com.ccic.salesapp.noncar.outService.esb.proposalliabinsgenerate.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;���α����Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>ItemDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ItemDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="itemCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemDetailName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="itemAttrList" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}ExpandDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="itemAddress" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}AddressDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemDTO", propOrder = {
    "itemCode",
    "itemName",
    "itemDetailName",
    "itemAttrList",
    "itemAddress"
})
public class ItemDTO {

    @XmlElement(required = true)
    protected String itemCode;
    protected String itemName;
    @XmlElement(required = true)
    protected String itemDetailName;
    protected List<ExpandDTO> itemAttrList;
    protected AddressDTO itemAddress;

    /**
     * ��ȡitemCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * ����itemCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemCode(String value) {
        this.itemCode = value;
    }

    /**
     * ��ȡitemName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * ����itemName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemName(String value) {
        this.itemName = value;
    }

    /**
     * ��ȡitemDetailName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemDetailName() {
        return itemDetailName;
    }

    /**
     * ����itemDetailName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemDetailName(String value) {
        this.itemDetailName = value;
    }

    /**
     * Gets the value of the itemAttrList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemAttrList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemAttrList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExpandDTO }
     * 
     * 
     */
    public List<ExpandDTO> getItemAttrList() {
        if (itemAttrList == null) {
            itemAttrList = new ArrayList<ExpandDTO>();
        }
        return this.itemAttrList;
    }

    /**
     * ��ȡitemAddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link AddressDTO }
     *     
     */
    public AddressDTO getItemAddress() {
        return itemAddress;
    }

    /**
     * ����itemAddress���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link AddressDTO }
     *     
     */
    public void setItemAddress(AddressDTO value) {
        this.itemAddress = value;
    }

}
