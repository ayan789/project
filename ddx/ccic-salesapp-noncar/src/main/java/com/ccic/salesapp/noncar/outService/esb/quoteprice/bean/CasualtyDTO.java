
package com.ccic.salesapp.noncar.outService.esb.quoteprice.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/lifePolicy/quotePrice/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/lifePolicy/quotePrice/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;�����ձ�����Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>CasualtyDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="CasualtyDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessGrade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessGradeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unitCount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="mainInsuredCount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="subInsuredCount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="officeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="avgAge" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="sexRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="setireRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smallAmountFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siddingFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sovernFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bigAmountFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="settleMentFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="settleWayCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="settleDay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="settleLastDay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aveSubInsureChildCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="insuranceRate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="childbearingRate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ageBegin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ageEnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CasualtyDTO", propOrder = {
    "businessGrade",
    "businessGradeDesc",
    "unitCount",
    "mainInsuredCount",
    "subInsuredCount",
    "officeType",
    "operateType",
    "avgAge",
    "sexRate",
    "setireRate",
    "smallAmountFlag",
    "siddingFlag",
    "sovernFlag",
    "bigAmountFlag",
    "settleMentFlag",
    "settleWayCode",
    "settleDay",
    "settleLastDay",
    "aveSubInsureChildCount",
    "insuranceRate",
    "childbearingRate",
    "ageBegin",
    "ageEnd"
})
public class CasualtyDTO {

    protected String businessGrade;
    protected String businessGradeDesc;
    protected Double unitCount;
    protected Double mainInsuredCount;
    protected Double subInsuredCount;
    protected String officeType;
    protected String operateType;
    protected Double avgAge;
    protected String sexRate;
    protected String setireRate;
    protected String smallAmountFlag;
    protected String siddingFlag;
    protected String sovernFlag;
    protected String bigAmountFlag;
    protected String settleMentFlag;
    protected String settleWayCode;
    protected String settleDay;
    protected String settleLastDay;
    protected String aveSubInsureChildCount;
    protected Double insuranceRate;
    protected Double childbearingRate;
    protected String ageBegin;
    protected String ageEnd;

    /**
     * ��ȡbusinessGrade���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessGrade() {
        return businessGrade;
    }

    /**
     * ����businessGrade���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessGrade(String value) {
        this.businessGrade = value;
    }

    /**
     * ��ȡbusinessGradeDesc���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessGradeDesc() {
        return businessGradeDesc;
    }

    /**
     * ����businessGradeDesc���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessGradeDesc(String value) {
        this.businessGradeDesc = value;
    }

    /**
     * ��ȡunitCount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getUnitCount() {
        return unitCount;
    }

    /**
     * ����unitCount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setUnitCount(Double value) {
        this.unitCount = value;
    }

    /**
     * ��ȡmainInsuredCount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMainInsuredCount() {
        return mainInsuredCount;
    }

    /**
     * ����mainInsuredCount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMainInsuredCount(Double value) {
        this.mainInsuredCount = value;
    }

    /**
     * ��ȡsubInsuredCount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSubInsuredCount() {
        return subInsuredCount;
    }

    /**
     * ����subInsuredCount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSubInsuredCount(Double value) {
        this.subInsuredCount = value;
    }

    /**
     * ��ȡofficeType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeType() {
        return officeType;
    }

    /**
     * ����officeType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeType(String value) {
        this.officeType = value;
    }

    /**
     * ��ȡoperateType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * ����operateType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateType(String value) {
        this.operateType = value;
    }

    /**
     * ��ȡavgAge���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAvgAge() {
        return avgAge;
    }

    /**
     * ����avgAge���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAvgAge(Double value) {
        this.avgAge = value;
    }

    /**
     * ��ȡsexRate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexRate() {
        return sexRate;
    }

    /**
     * ����sexRate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexRate(String value) {
        this.sexRate = value;
    }

    /**
     * ��ȡsetireRate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetireRate() {
        return setireRate;
    }

    /**
     * ����setireRate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetireRate(String value) {
        this.setireRate = value;
    }

    /**
     * ��ȡsmallAmountFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmallAmountFlag() {
        return smallAmountFlag;
    }

    /**
     * ����smallAmountFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmallAmountFlag(String value) {
        this.smallAmountFlag = value;
    }

    /**
     * ��ȡsiddingFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiddingFlag() {
        return siddingFlag;
    }

    /**
     * ����siddingFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiddingFlag(String value) {
        this.siddingFlag = value;
    }

    /**
     * ��ȡsovernFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSovernFlag() {
        return sovernFlag;
    }

    /**
     * ����sovernFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSovernFlag(String value) {
        this.sovernFlag = value;
    }

    /**
     * ��ȡbigAmountFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBigAmountFlag() {
        return bigAmountFlag;
    }

    /**
     * ����bigAmountFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBigAmountFlag(String value) {
        this.bigAmountFlag = value;
    }

    /**
     * ��ȡsettleMentFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettleMentFlag() {
        return settleMentFlag;
    }

    /**
     * ����settleMentFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettleMentFlag(String value) {
        this.settleMentFlag = value;
    }

    /**
     * ��ȡsettleWayCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettleWayCode() {
        return settleWayCode;
    }

    /**
     * ����settleWayCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettleWayCode(String value) {
        this.settleWayCode = value;
    }

    /**
     * ��ȡsettleDay���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettleDay() {
        return settleDay;
    }

    /**
     * ����settleDay���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettleDay(String value) {
        this.settleDay = value;
    }

    /**
     * ��ȡsettleLastDay���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettleLastDay() {
        return settleLastDay;
    }

    /**
     * ����settleLastDay���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettleLastDay(String value) {
        this.settleLastDay = value;
    }

    /**
     * ��ȡaveSubInsureChildCount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAveSubInsureChildCount() {
        return aveSubInsureChildCount;
    }

    /**
     * ����aveSubInsureChildCount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAveSubInsureChildCount(String value) {
        this.aveSubInsureChildCount = value;
    }

    /**
     * ��ȡinsuranceRate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getInsuranceRate() {
        return insuranceRate;
    }

    /**
     * ����insuranceRate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setInsuranceRate(Double value) {
        this.insuranceRate = value;
    }

    /**
     * ��ȡchildbearingRate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getChildbearingRate() {
        return childbearingRate;
    }

    /**
     * ����childbearingRate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setChildbearingRate(Double value) {
        this.childbearingRate = value;
    }

    /**
     * ��ȡageBegin���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgeBegin() {
        return ageBegin;
    }

    /**
     * ����ageBegin���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgeBegin(String value) {
        this.ageBegin = value;
    }

    /**
     * ��ȡageEnd���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgeEnd() {
        return ageEnd;
    }

    /**
     * ����ageEnd���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgeEnd(String value) {
        this.ageEnd = value;
    }

}
