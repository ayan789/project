package com.ccic.salesapp.noncar.dto;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/lifePolicy/quotePrice/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/lifePolicy/quotePrice/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;返回投保单主信息&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>ProposalDTO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ProposalDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proposalno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="printNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startHour" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endHour" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="premium" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="directPolicyFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reInsured" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}ReInsuredDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reRation" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}ReRationDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="expand" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}ExtendDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProposalDTO", propOrder = {
    "proposalno",
    "printNo",
    "startDate",
    "startHour",
    "endDate",
    "endHour",
    "amount",
    "premium",
    "directPolicyFlag",
    "reInsured",
    "reRation",
    "expand"
})
public class ProposalDTO {

    protected String proposalno;
    protected String printNo;
    protected String startDate;
    protected String startHour;
    protected String endDate;
    protected String endHour;
    protected Double amount;
    protected Double premium;
    protected String directPolicyFlag;
    protected List<ReInsuredDTO> reInsured;
    protected List<ReRationDTO> reRation;
    protected List<ExtendDTO> expand;

    /**
     * 获取proposalno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProposalno() {
        return proposalno;
    }

    /**
     * 设置proposalno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProposalno(String value) {
        this.proposalno = value;
    }

    /**
     * 获取printNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintNo() {
        return printNo;
    }

    /**
     * 设置printNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintNo(String value) {
        this.printNo = value;
    }

    /**
     * 获取startDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 设置startDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * 获取startHour属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartHour() {
        return startHour;
    }

    /**
     * 设置startHour属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartHour(String value) {
        this.startHour = value;
    }

    /**
     * 获取endDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 设置endDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * 获取endHour属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndHour() {
        return endHour;
    }

    /**
     * 设置endHour属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndHour(String value) {
        this.endHour = value;
    }

    /**
     * 获取amount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmount(Double value) {
        this.amount = value;
    }

    /**
     * 获取premium属性的值。
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
     * 设置premium属性的值。
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
     * 获取directPolicyFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectPolicyFlag() {
        return directPolicyFlag;
    }

    /**
     * 设置directPolicyFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectPolicyFlag(String value) {
        this.directPolicyFlag = value;
    }

    /**
     * Gets the value of the reInsured property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reInsured property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReInsured().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReInsuredDTO }
     * 
     * 
     */
    public List<ReInsuredDTO> getReInsured() {
        if (reInsured == null) {
            reInsured = new ArrayList<ReInsuredDTO>();
        }
        return this.reInsured;
    }

    /**
     * Gets the value of the reRation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reRation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReRation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReRationDTO }
     * 
     * 
     */
    public List<ReRationDTO> getReRation() {
        if (reRation == null) {
            reRation = new ArrayList<ReRationDTO>();
        }
        return this.reRation;
    }

    /**
     * Gets the value of the expand property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expand property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpand().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtendDTO }
     * 
     * 
     */
    public List<ExtendDTO> getExpand() {
        if (expand == null) {
            expand = new ArrayList<ExtendDTO>();
        }
        return this.expand;
    }

}
