
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
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;�ձ�����Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>ItemKindDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ItemKindDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kindCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kindName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kindType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="itemCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemDetailName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="premium" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="calculateFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shortRateFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shortRate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="unitAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="basePremium" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="deductible" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="deductblerate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="limitList" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}LimitDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="expandList" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}ExpandDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemKindDTO", propOrder = {
    "kindCode",
    "kindName",
    "kindType",
    "itemCode",
    "itemDetailName",
    "currency",
    "amount",
    "rate",
    "premium",
    "quantity",
    "calculateFlag",
    "shortRateFlag",
    "shortRate",
    "discount",
    "unitAmount",
    "basePremium",
    "deductible",
    "deductblerate",
    "value",
    "limitList",
    "expandList"
})
public class ItemKindDTO {

    @XmlElement(required = true)
    protected String kindCode;
    @XmlElement(required = true)
    protected String kindName;
    @XmlElement(required = true)
    protected String kindType;
    protected String itemCode;
    protected String itemDetailName;
    @XmlElement(required = true)
    protected String currency;
    protected double amount;
    protected Double rate;
    protected Double premium;
    protected Double quantity;
    @XmlElement(required = true)
    protected String calculateFlag;
    protected String shortRateFlag;
    protected Double shortRate;
    protected Double discount;
    protected Double unitAmount;
    protected Double basePremium;
    protected Double deductible;
    protected Double deductblerate;
    protected Double value;
    protected List<LimitDTO> limitList;
    protected List<ExpandDTO> expandList;

    /**
     * ��ȡkindCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKindCode() {
        return kindCode;
    }

    /**
     * ����kindCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKindCode(String value) {
        this.kindCode = value;
    }

    /**
     * ��ȡkindName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKindName() {
        return kindName;
    }

    /**
     * ����kindName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKindName(String value) {
        this.kindName = value;
    }

    /**
     * ��ȡkindType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKindType() {
        return kindType;
    }

    /**
     * ����kindType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKindType(String value) {
        this.kindType = value;
    }

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
     * ��ȡcurrency���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * ����currency���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * ��ȡamount���Ե�ֵ��
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * ����amount���Ե�ֵ��
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * ��ȡrate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRate() {
        return rate;
    }

    /**
     * ����rate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRate(Double value) {
        this.rate = value;
    }

    /**
     * ��ȡpremium���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPremium() {
        return premium;
    }

    /**
     * ����premium���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPremium(Double value) {
        this.premium = value;
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
     * ��ȡcalculateFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalculateFlag() {
        return calculateFlag;
    }

    /**
     * ����calculateFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalculateFlag(String value) {
        this.calculateFlag = value;
    }

    /**
     * ��ȡshortRateFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortRateFlag() {
        return shortRateFlag;
    }

    /**
     * ����shortRateFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortRateFlag(String value) {
        this.shortRateFlag = value;
    }

    /**
     * ��ȡshortRate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getShortRate() {
        return shortRate;
    }

    /**
     * ����shortRate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setShortRate(Double value) {
        this.shortRate = value;
    }

    /**
     * ��ȡdiscount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * ����discount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDiscount(Double value) {
        this.discount = value;
    }

    /**
     * ��ȡunitAmount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getUnitAmount() {
        return unitAmount;
    }

    /**
     * ����unitAmount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setUnitAmount(Double value) {
        this.unitAmount = value;
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

    /**
     * ��ȡdeductible���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDeductible() {
        return deductible;
    }

    /**
     * ����deductible���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDeductible(Double value) {
        this.deductible = value;
    }

    /**
     * ��ȡdeductblerate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDeductblerate() {
        return deductblerate;
    }

    /**
     * ����deductblerate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDeductblerate(Double value) {
        this.deductblerate = value;
    }

    /**
     * ��ȡvalue���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getValue() {
        return value;
    }

    /**
     * ����value���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Gets the value of the limitList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the limitList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLimitList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LimitDTO }
     * 
     * 
     */
    public List<LimitDTO> getLimitList() {
        if (limitList == null) {
            limitList = new ArrayList<LimitDTO>();
        }
        return this.limitList;
    }

    /**
     * Gets the value of the expandList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expandList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpandList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExpandDTO }
     * 
     * 
     */
    public List<ExpandDTO> getExpandList() {
        if (expandList == null) {
            expandList = new ArrayList<ExpandDTO>();
        }
        return this.expandList;
    }

}
