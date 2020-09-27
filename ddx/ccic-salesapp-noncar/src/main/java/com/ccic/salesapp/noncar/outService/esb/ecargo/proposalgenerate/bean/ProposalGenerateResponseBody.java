
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ProposalGenerateResponseBody complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ProposalGenerateResponseBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proposalNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="riskCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="riskCodeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessContry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startHour" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endHour" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sumAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="sumPremium" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="recCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recSumPremium" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="payTimes" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="judicalscope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="argueSolution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arbitBoardName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arbitBoardDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="makeCom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="underWriteFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coinsFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coinsOriginRate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="reinsFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="argiPolicySort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salePolicyAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reCargo" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ReCargoDTO" minOccurs="0"/>
 *         &lt;element name="reMainSub" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ReMainSubDTO" minOccurs="0"/>
 *         &lt;element name="reMainPlus" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ReMainPlus" minOccurs="0"/>
 *         &lt;element name="reAddressList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ReAddressDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reItemkindList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ReItemkindDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reInsuredList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ReInsuredDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RePayPlanList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}RePayPlanDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reEngageList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ReEngageDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reAgent" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ReAgentDTO" minOccurs="0"/>
 *         &lt;element name="reCoinsList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ReCoinsDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reAgriSubsidyList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ReAgriSubsidy" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reExpandList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ReExpandDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProposalGenerateResponseBody", propOrder = {
    "proposalNo",
    "riskCode",
    "riskCodeName",
    "businessContry",
    "language",
    "startDate",
    "startHour",
    "endDate",
    "endHour",
    "currency",
    "sumAmount",
    "sumPremium",
    "recCurrency",
    "recSumPremium",
    "payTimes",
    "judicalscope",
    "argueSolution",
    "arbitBoardName",
    "arbitBoardDesc",
    "makeCom",
    "underWriteFlag",
    "coinsFlag",
    "coinsOriginRate",
    "reinsFlag",
    "argiPolicySort",
    "salePolicyAddr",
    "reCargo",
    "reMainSub",
    "reMainPlus",
    "reAddressList",
    "reItemkindList",
    "reInsuredList",
    "rePayPlanList",
    "reEngageList",
    "reAgent",
    "reCoinsList",
    "reAgriSubsidyList",
    "reExpandList"
})
public class ProposalGenerateResponseBody {

    @XmlElement(required = true)
    protected String proposalNo;
    protected String riskCode;
    protected String riskCodeName;
    protected String businessContry;
    protected String language;
    protected String startDate;
    protected Integer startHour;
    protected String endDate;
    protected Integer endHour;
    protected String currency;
    protected Double sumAmount;
    protected Double sumPremium;
    protected String recCurrency;
    protected Double recSumPremium;
    protected Integer payTimes;
    protected String judicalscope;
    protected String argueSolution;
    protected String arbitBoardName;
    protected String arbitBoardDesc;
    protected String makeCom;
    protected String underWriteFlag;
    protected String coinsFlag;
    protected Double coinsOriginRate;
    protected String reinsFlag;
    protected String argiPolicySort;
    protected String salePolicyAddr;
    protected ReCargoDTO reCargo;
    protected ReMainSubDTO reMainSub;
    protected ReMainPlus reMainPlus;
    protected List<ReAddressDTO> reAddressList;
    protected List<ReItemkindDTO> reItemkindList;
    protected List<ReInsuredDTO> reInsuredList;
    @XmlElement(name = "RePayPlanList")
    protected List<RePayPlanDTO> rePayPlanList;
    protected List<ReEngageDTO> reEngageList;
    protected ReAgentDTO reAgent;
    protected List<ReCoinsDTO> reCoinsList;
    protected List<ReAgriSubsidy> reAgriSubsidyList;
    protected List<ReExpandDTO> reExpandList;

    /**
     * ��ȡproposalNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProposalNo() {
        return proposalNo;
    }

    /**
     * ����proposalNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProposalNo(String value) {
        this.proposalNo = value;
    }

    /**
     * ��ȡriskCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiskCode() {
        return riskCode;
    }

    /**
     * ����riskCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiskCode(String value) {
        this.riskCode = value;
    }

    /**
     * ��ȡriskCodeName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiskCodeName() {
        return riskCodeName;
    }

    /**
     * ����riskCodeName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiskCodeName(String value) {
        this.riskCodeName = value;
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
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStartHour() {
        return startHour;
    }

    /**
     * ����startHour���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStartHour(Integer value) {
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
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEndHour() {
        return endHour;
    }

    /**
     * ����endHour���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEndHour(Integer value) {
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
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSumAmount() {
        return sumAmount;
    }

    /**
     * ����sumAmount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSumAmount(Double value) {
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
     * ��ȡrecCurrency���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecCurrency() {
        return recCurrency;
    }

    /**
     * ����recCurrency���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecCurrency(String value) {
        this.recCurrency = value;
    }

    /**
     * ��ȡrecSumPremium���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRecSumPremium() {
        return recSumPremium;
    }

    /**
     * ����recSumPremium���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRecSumPremium(Double value) {
        this.recSumPremium = value;
    }

    /**
     * ��ȡpayTimes���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPayTimes() {
        return payTimes;
    }

    /**
     * ����payTimes���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPayTimes(Integer value) {
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
     * ��ȡarbitBoardDesc���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArbitBoardDesc() {
        return arbitBoardDesc;
    }

    /**
     * ����arbitBoardDesc���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArbitBoardDesc(String value) {
        this.arbitBoardDesc = value;
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
     * ��ȡunderWriteFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnderWriteFlag() {
        return underWriteFlag;
    }

    /**
     * ����underWriteFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnderWriteFlag(String value) {
        this.underWriteFlag = value;
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
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCoinsOriginRate() {
        return coinsOriginRate;
    }

    /**
     * ����coinsOriginRate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCoinsOriginRate(Double value) {
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
     * ��ȡreCargo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ReCargoDTO }
     *     
     */
    public ReCargoDTO getReCargo() {
        return reCargo;
    }

    /**
     * ����reCargo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ReCargoDTO }
     *     
     */
    public void setReCargo(ReCargoDTO value) {
        this.reCargo = value;
    }

    /**
     * ��ȡreMainSub���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ReMainSubDTO }
     *     
     */
    public ReMainSubDTO getReMainSub() {
        return reMainSub;
    }

    /**
     * ����reMainSub���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ReMainSubDTO }
     *     
     */
    public void setReMainSub(ReMainSubDTO value) {
        this.reMainSub = value;
    }

    /**
     * ��ȡreMainPlus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ReMainPlus }
     *     
     */
    public ReMainPlus getReMainPlus() {
        return reMainPlus;
    }

    /**
     * ����reMainPlus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ReMainPlus }
     *     
     */
    public void setReMainPlus(ReMainPlus value) {
        this.reMainPlus = value;
    }

    /**
     * Gets the value of the reAddressList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reAddressList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReAddressList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReAddressDTO }
     * 
     * 
     */
    public List<ReAddressDTO> getReAddressList() {
        if (reAddressList == null) {
            reAddressList = new ArrayList<ReAddressDTO>();
        }
        return this.reAddressList;
    }

    /**
     * Gets the value of the reItemkindList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reItemkindList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReItemkindList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReItemkindDTO }
     * 
     * 
     */
    public List<ReItemkindDTO> getReItemkindList() {
        if (reItemkindList == null) {
            reItemkindList = new ArrayList<ReItemkindDTO>();
        }
        return this.reItemkindList;
    }

    /**
     * Gets the value of the reInsuredList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reInsuredList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReInsuredList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReInsuredDTO }
     * 
     * 
     */
    public List<ReInsuredDTO> getReInsuredList() {
        if (reInsuredList == null) {
            reInsuredList = new ArrayList<ReInsuredDTO>();
        }
        return this.reInsuredList;
    }

    /**
     * Gets the value of the rePayPlanList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rePayPlanList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRePayPlanList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RePayPlanDTO }
     * 
     * 
     */
    public List<RePayPlanDTO> getRePayPlanList() {
        if (rePayPlanList == null) {
            rePayPlanList = new ArrayList<RePayPlanDTO>();
        }
        return this.rePayPlanList;
    }

    /**
     * Gets the value of the reEngageList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reEngageList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReEngageList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReEngageDTO }
     * 
     * 
     */
    public List<ReEngageDTO> getReEngageList() {
        if (reEngageList == null) {
            reEngageList = new ArrayList<ReEngageDTO>();
        }
        return this.reEngageList;
    }

    /**
     * ��ȡreAgent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ReAgentDTO }
     *     
     */
    public ReAgentDTO getReAgent() {
        return reAgent;
    }

    /**
     * ����reAgent���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ReAgentDTO }
     *     
     */
    public void setReAgent(ReAgentDTO value) {
        this.reAgent = value;
    }

    /**
     * Gets the value of the reCoinsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reCoinsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReCoinsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReCoinsDTO }
     * 
     * 
     */
    public List<ReCoinsDTO> getReCoinsList() {
        if (reCoinsList == null) {
            reCoinsList = new ArrayList<ReCoinsDTO>();
        }
        return this.reCoinsList;
    }

    /**
     * Gets the value of the reAgriSubsidyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reAgriSubsidyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReAgriSubsidyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReAgriSubsidy }
     * 
     * 
     */
    public List<ReAgriSubsidy> getReAgriSubsidyList() {
        if (reAgriSubsidyList == null) {
            reAgriSubsidyList = new ArrayList<ReAgriSubsidy>();
        }
        return this.reAgriSubsidyList;
    }

    /**
     * Gets the value of the reExpandList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reExpandList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReExpandList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReExpandDTO }
     * 
     * 
     */
    public List<ReExpandDTO> getReExpandList() {
        if (reExpandList == null) {
            reExpandList = new ArrayList<ReExpandDTO>();
        }
        return this.reExpandList;
    }

}
