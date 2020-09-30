
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MainDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="MainDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessNature" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="businessNature2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessAttribute" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="businessContry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="projectSerialNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="projectName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="policyType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operateDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startHour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endHour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sumAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="sumPremium" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="payTimes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="judicalscope" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="argueSolution" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="arbitBoardName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatorCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="makeCom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="handlerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="handler1Code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="handler2Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handler2Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inputDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inputHour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="approverCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coinsFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="coinsOriginRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="reinsFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="jfeeFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="jfeeSpecialFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="argiFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="argiPolicySort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vocationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="agentPermitNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salePolicyAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tniSumPremium" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="vat" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MainDTO", propOrder = {
    "businessNature",
    "businessNature2",
    "businessAttribute",
    "businessContry",
    "projectSerialNo",
    "projectName",
    "language",
    "policyType",
    "operateDate",
    "startDate",
    "startHour",
    "endDate",
    "endHour",
    "currency",
    "sumAmount",
    "sumPremium",
    "payTimes",
    "judicalscope",
    "argueSolution",
    "arbitBoardName",
    "operatorCode",
    "makeCom",
    "handlerCode",
    "handler1Code",
    "handler2Code",
    "handler2Name",
    "comCode",
    "inputDate",
    "inputHour",
    "approverCode",
    "coinsFlag",
    "coinsOriginRate",
    "reinsFlag",
    "jfeeFlag",
    "jfeeSpecialFlag",
    "argiFlag",
    "argiPolicySort",
    "vocationCode",
    "agentPermitNo",
    "salePolicyAddr",
    "tniSumPremium",
    "vat"
})
public class MainDTO {

    @XmlElement(required = true)
    protected String businessNature;
    protected String businessNature2;
    @XmlElement(required = true)
    protected String businessAttribute;
    @XmlElement(required = true)
    protected String businessContry;
    protected String projectSerialNo;
    protected String projectName;
    @XmlElement(required = true)
    protected String language;
    protected String policyType;
    @XmlElement(required = true)
    protected String operateDate;
    @XmlElement(required = true)
    protected String startDate;
    protected int startHour;
    @XmlElement(required = true)
    protected String endDate;
    protected int endHour;
    @XmlElement(required = true)
    protected String currency;
    protected double sumAmount;
    protected Double sumPremium;
    protected int payTimes;
    @XmlElement(required = true)
    protected String judicalscope;
    @XmlElement(required = true)
    protected String argueSolution;
    protected String arbitBoardName;
    @XmlElement(required = true)
    protected String operatorCode;
    @XmlElement(required = true)
    protected String makeCom;
    @XmlElement(required = true)
    protected String handlerCode;
    @XmlElement(required = true)
    protected String handler1Code;
    protected String handler2Code;
    protected String handler2Name;
    @XmlElement(required = true)
    protected String comCode;
    @XmlElement(required = true)
    protected String inputDate;
    protected int inputHour;
    protected String approverCode;
    @XmlElement(required = true)
    protected String coinsFlag;
    protected double coinsOriginRate;
    @XmlElement(required = true)
    protected String reinsFlag;
    @XmlElement(required = true)
    protected String jfeeFlag;
    protected String jfeeSpecialFlag;
    protected String argiFlag;
    protected String argiPolicySort;
    protected String vocationCode;
    protected String agentPermitNo;
    protected String salePolicyAddr;
    protected Double tniSumPremium;
    protected Double vat;

    /**
     * ��ȡbusinessNature���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessNature() {
        return businessNature;
    }

    /**
     * ����businessNature���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessNature(String value) {
        this.businessNature = value;
    }

    /**
     * ��ȡbusinessNature2���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessNature2() {
        return businessNature2;
    }

    /**
     * ����businessNature2���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessNature2(String value) {
        this.businessNature2 = value;
    }

    /**
     * ��ȡbusinessAttribute���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessAttribute() {
        return businessAttribute;
    }

    /**
     * ����businessAttribute���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessAttribute(String value) {
        this.businessAttribute = value;
    }

    /**
     * ��ȡbusinessContry���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessContry() {
        return businessContry;
    }

    /**
     * ����businessContry���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessContry(String value) {
        this.businessContry = value;
    }

    /**
     * ��ȡprojectSerialNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectSerialNo() {
        return projectSerialNo;
    }

    /**
     * ����projectSerialNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectSerialNo(String value) {
        this.projectSerialNo = value;
    }

    /**
     * ��ȡprojectName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * ����projectName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectName(String value) {
        this.projectName = value;
    }

    /**
     * ��ȡlanguage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * ����language���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * ��ȡpolicyType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyType() {
        return policyType;
    }

    /**
     * ����policyType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyType(String value) {
        this.policyType = value;
    }

    /**
     * ��ȡoperateDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateDate() {
        return operateDate;
    }

    /**
     * ����operateDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateDate(String value) {
        this.operateDate = value;
    }

    /**
     * ��ȡstartDate���Ե�ֵ��
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
     * ����startDate���Ե�ֵ��
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
     * ��ȡstartHour���Ե�ֵ��
     * 
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * ����startHour���Ե�ֵ��
     * 
     */
    public void setStartHour(int value) {
        this.startHour = value;
    }

    /**
     * ��ȡendDate���Ե�ֵ��
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
     * ����endDate���Ե�ֵ��
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
     * ��ȡendHour���Ե�ֵ��
     * 
     */
    public int getEndHour() {
        return endHour;
    }

    /**
     * ����endHour���Ե�ֵ��
     * 
     */
    public void setEndHour(int value) {
        this.endHour = value;
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
     * ��ȡsumAmount���Ե�ֵ��
     * 
     */
    public double getSumAmount() {
        return sumAmount;
    }

    /**
     * ����sumAmount���Ե�ֵ��
     * 
     */
    public void setSumAmount(double value) {
        this.sumAmount = value;
    }

    /**
     * ��ȡsumPremium���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSumPremium() {
        return sumPremium;
    }

    /**
     * ����sumPremium���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSumPremium(Double value) {
        this.sumPremium = value;
    }

    /**
     * ��ȡpayTimes���Ե�ֵ��
     * 
     */
    public int getPayTimes() {
        return payTimes;
    }

    /**
     * ����payTimes���Ե�ֵ��
     * 
     */
    public void setPayTimes(int value) {
        this.payTimes = value;
    }

    /**
     * ��ȡjudicalscope���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJudicalscope() {
        return judicalscope;
    }

    /**
     * ����judicalscope���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJudicalscope(String value) {
        this.judicalscope = value;
    }

    /**
     * ��ȡargueSolution���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArgueSolution() {
        return argueSolution;
    }

    /**
     * ����argueSolution���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArgueSolution(String value) {
        this.argueSolution = value;
    }

    /**
     * ��ȡarbitBoardName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArbitBoardName() {
        return arbitBoardName;
    }

    /**
     * ����arbitBoardName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArbitBoardName(String value) {
        this.arbitBoardName = value;
    }

    /**
     * ��ȡoperatorCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorCode() {
        return operatorCode;
    }

    /**
     * ����operatorCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorCode(String value) {
        this.operatorCode = value;
    }

    /**
     * ��ȡmakeCom���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMakeCom() {
        return makeCom;
    }

    /**
     * ����makeCom���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMakeCom(String value) {
        this.makeCom = value;
    }

    /**
     * ��ȡhandlerCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandlerCode() {
        return handlerCode;
    }

    /**
     * ����handlerCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandlerCode(String value) {
        this.handlerCode = value;
    }

    /**
     * ��ȡhandler1Code���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandler1Code() {
        return handler1Code;
    }

    /**
     * ����handler1Code���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandler1Code(String value) {
        this.handler1Code = value;
    }

    /**
     * ��ȡhandler2Code���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandler2Code() {
        return handler2Code;
    }

    /**
     * ����handler2Code���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandler2Code(String value) {
        this.handler2Code = value;
    }

    /**
     * ��ȡhandler2Name���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandler2Name() {
        return handler2Name;
    }

    /**
     * ����handler2Name���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandler2Name(String value) {
        this.handler2Name = value;
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
     * ��ȡinputDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputDate() {
        return inputDate;
    }

    /**
     * ����inputDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputDate(String value) {
        this.inputDate = value;
    }

    /**
     * ��ȡinputHour���Ե�ֵ��
     * 
     */
    public int getInputHour() {
        return inputHour;
    }

    /**
     * ����inputHour���Ե�ֵ��
     * 
     */
    public void setInputHour(int value) {
        this.inputHour = value;
    }

    /**
     * ��ȡapproverCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApproverCode() {
        return approverCode;
    }

    /**
     * ����approverCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApproverCode(String value) {
        this.approverCode = value;
    }

    /**
     * ��ȡcoinsFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoinsFlag() {
        return coinsFlag;
    }

    /**
     * ����coinsFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoinsFlag(String value) {
        this.coinsFlag = value;
    }

    /**
     * ��ȡcoinsOriginRate���Ե�ֵ��
     * 
     */
    public double getCoinsOriginRate() {
        return coinsOriginRate;
    }

    /**
     * ����coinsOriginRate���Ե�ֵ��
     * 
     */
    public void setCoinsOriginRate(double value) {
        this.coinsOriginRate = value;
    }

    /**
     * ��ȡreinsFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReinsFlag() {
        return reinsFlag;
    }

    /**
     * ����reinsFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReinsFlag(String value) {
        this.reinsFlag = value;
    }

    /**
     * ��ȡjfeeFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJfeeFlag() {
        return jfeeFlag;
    }

    /**
     * ����jfeeFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJfeeFlag(String value) {
        this.jfeeFlag = value;
    }

    /**
     * ��ȡjfeeSpecialFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJfeeSpecialFlag() {
        return jfeeSpecialFlag;
    }

    /**
     * ����jfeeSpecialFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJfeeSpecialFlag(String value) {
        this.jfeeSpecialFlag = value;
    }

    /**
     * ��ȡargiFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArgiFlag() {
        return argiFlag;
    }

    /**
     * ����argiFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArgiFlag(String value) {
        this.argiFlag = value;
    }

    /**
     * ��ȡargiPolicySort���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArgiPolicySort() {
        return argiPolicySort;
    }

    /**
     * ����argiPolicySort���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArgiPolicySort(String value) {
        this.argiPolicySort = value;
    }

    /**
     * ��ȡvocationCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVocationCode() {
        return vocationCode;
    }

    /**
     * ����vocationCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVocationCode(String value) {
        this.vocationCode = value;
    }

    /**
     * ��ȡagentPermitNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentPermitNo() {
        return agentPermitNo;
    }

    /**
     * ����agentPermitNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentPermitNo(String value) {
        this.agentPermitNo = value;
    }

    /**
     * ��ȡsalePolicyAddr���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalePolicyAddr() {
        return salePolicyAddr;
    }

    /**
     * ����salePolicyAddr���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalePolicyAddr(String value) {
        this.salePolicyAddr = value;
    }

    /**
     * ��ȡtniSumPremium���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTniSumPremium() {
        return tniSumPremium;
    }

    /**
     * ����tniSumPremium���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTniSumPremium(Double value) {
        this.tniSumPremium = value;
    }

    /**
     * ��ȡvat���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVat() {
        return vat;
    }

    /**
     * ����vat���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVat(Double value) {
        this.vat = value;
    }

}