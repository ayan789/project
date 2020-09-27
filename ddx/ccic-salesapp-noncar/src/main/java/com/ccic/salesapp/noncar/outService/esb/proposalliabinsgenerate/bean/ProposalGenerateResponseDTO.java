
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
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣ��&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>ProposalGenerateResponseDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ProposalGenerateResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proposalNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="riskCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="riskCodeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="underWriteFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="main" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}MainDTO" minOccurs="0"/>
 *         &lt;element name="insuredList" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}InsuredDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="itemList" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}ItemDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="itemKindList" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}ItemKindDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="payPlanList" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}PayPlanDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="engageList" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}EngageDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="agent" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}AgentDTO" minOccurs="0"/>
 *         &lt;element name="coinsList" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}CoinsDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="exchangeList" type="{http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean}ExchangeDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ProposalGenerateResponseDTO", propOrder = {
    "proposalNo",
    "riskCode",
    "riskCodeName",
    "underWriteFlag",
    "main",
    "insuredList",
    "itemList",
    "itemKindList",
    "payPlanList",
    "engageList",
    "agent",
    "coinsList",
    "exchangeList",
    "expandList"
})
public class ProposalGenerateResponseDTO {

    @XmlElement(required = true)
    protected String proposalNo;
    protected String riskCode;
    protected String riskCodeName;
    protected String underWriteFlag;
    protected MainDTO main;
    protected List<InsuredDTO> insuredList;
    protected List<ItemDTO> itemList;
    protected List<ItemKindDTO> itemKindList;
    protected List<PayPlanDTO> payPlanList;
    protected List<EngageDTO> engageList;
    protected AgentDTO agent;
    protected List<CoinsDTO> coinsList;
    protected List<ExchangeDTO> exchangeList;
    protected List<ExpandDTO> expandList;

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
     * ��ȡmain���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link MainDTO }
     *     
     */
    public MainDTO getMain() {
        return main;
    }

    /**
     * ����main���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link MainDTO }
     *     
     */
    public void setMain(MainDTO value) {
        this.main = value;
    }

    /**
     * Gets the value of the insuredList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the insuredList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInsuredList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InsuredDTO }
     * 
     * 
     */
    public List<InsuredDTO> getInsuredList() {
        if (insuredList == null) {
            insuredList = new ArrayList<InsuredDTO>();
        }
        return this.insuredList;
    }

    /**
     * Gets the value of the itemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemDTO }
     * 
     * 
     */
    public List<ItemDTO> getItemList() {
        if (itemList == null) {
            itemList = new ArrayList<ItemDTO>();
        }
        return this.itemList;
    }

    /**
     * Gets the value of the itemKindList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemKindList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemKindList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemKindDTO }
     * 
     * 
     */
    public List<ItemKindDTO> getItemKindList() {
        if (itemKindList == null) {
            itemKindList = new ArrayList<ItemKindDTO>();
        }
        return this.itemKindList;
    }

    /**
     * Gets the value of the payPlanList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payPlanList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayPlanList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PayPlanDTO }
     * 
     * 
     */
    public List<PayPlanDTO> getPayPlanList() {
        if (payPlanList == null) {
            payPlanList = new ArrayList<PayPlanDTO>();
        }
        return this.payPlanList;
    }

    /**
     * Gets the value of the engageList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the engageList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEngageList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EngageDTO }
     * 
     * 
     */
    public List<EngageDTO> getEngageList() {
        if (engageList == null) {
            engageList = new ArrayList<EngageDTO>();
        }
        return this.engageList;
    }

    /**
     * ��ȡagent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link AgentDTO }
     *     
     */
    public AgentDTO getAgent() {
        return agent;
    }

    /**
     * ����agent���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link AgentDTO }
     *     
     */
    public void setAgent(AgentDTO value) {
        this.agent = value;
    }

    /**
     * Gets the value of the coinsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coinsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoinsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoinsDTO }
     * 
     * 
     */
    public List<CoinsDTO> getCoinsList() {
        if (coinsList == null) {
            coinsList = new ArrayList<CoinsDTO>();
        }
        return this.coinsList;
    }

    /**
     * Gets the value of the exchangeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the exchangeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExchangeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExchangeDTO }
     * 
     * 
     */
    public List<ExchangeDTO> getExchangeList() {
        if (exchangeList == null) {
            exchangeList = new ArrayList<ExchangeDTO>();
        }
        return this.exchangeList;
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
