
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ProposalGenerateRequestBody complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ProposalGenerateRequestBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tradeType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tradeTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="riskCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="proposalNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="underwriteType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="main" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}MainDTO"/>
 *         &lt;element name="mainPlus" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}MainPlusDTO"/>
 *         &lt;element name="mainSub" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}MainSubDTO" minOccurs="0"/>
 *         &lt;element name="cargo" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}CargoDTO" minOccurs="0"/>
 *         &lt;element name="addressList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}AddressDTO" maxOccurs="unbounded"/>
 *         &lt;element name="itemkindList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ItemkindDTO" maxOccurs="unbounded"/>
 *         &lt;element name="itemCargoList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ItemCargoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="payPlanList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}PayPlanDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="planDetailList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}PlanDetailDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="engageList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}EngageDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="insuredList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}InsuredDTO" maxOccurs="unbounded"/>
 *         &lt;element name="agent" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}AgentDTO" minOccurs="0"/>
 *         &lt;element name="coinsList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}CoinsDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="agriSubsidyList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}AgriSubsidyDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="exchangeList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ExchangeDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="expandList" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ExpandDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="channelDTO" type="{http://service.ccic.com/ecargo/proposalgenerate/bean}ChannelDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProposalGenerateRequestBody", propOrder = {
    "tradeType",
    "tradeTime",
    "classCode",
    "riskCode",
    "proposalNo",
    "underwriteType",
    "main",
    "mainPlus",
    "mainSub",
    "cargo",
    "addressList",
    "itemkindList",
    "itemCargoList",
    "payPlanList",
    "planDetailList",
    "engageList",
    "insuredList",
    "agent",
    "coinsList",
    "agriSubsidyList",
    "exchangeList",
    "expandList",
    "channelDTO"
})
public class ProposalGenerateRequestBody {

    @XmlElement(required = true)
    protected String tradeType;
    @XmlElement(required = true)
    protected String tradeTime;
    @XmlElement(required = true)
    protected String classCode;
    @XmlElement(required = true)
    protected String riskCode;
    protected String proposalNo;
    @XmlElement(required = true)
    protected String underwriteType;
    @XmlElement(required = true)
    protected MainDTO main;
    @XmlElement(required = true)
    protected MainPlusDTO mainPlus;
    protected MainSubDTO mainSub;
    protected CargoDTO cargo;
    @XmlElement(required = true)
    protected List<AddressDTO> addressList;
    @XmlElement(required = true)
    protected List<ItemkindDTO> itemkindList;
    protected List<ItemCargoDTO> itemCargoList;
    protected List<PayPlanDTO> payPlanList;
    protected List<PlanDetailDTO> planDetailList;
    protected List<EngageDTO> engageList;
    @XmlElement(required = true)
    protected List<InsuredDTO> insuredList;
    protected AgentDTO agent;
    protected List<CoinsDTO> coinsList;
    protected List<AgriSubsidyDTO> agriSubsidyList;
    protected List<ExchangeDTO> exchangeList;
    protected List<ExpandDTO> expandList;
    protected ChannelDTO channelDTO;

    /**
     * ��ȡtradeType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * ����tradeType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradeType(String value) {
        this.tradeType = value;
    }

    /**
     * ��ȡtradeTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradeTime() {
        return tradeTime;
    }

    /**
     * ����tradeTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradeTime(String value) {
        this.tradeTime = value;
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
     * ��ȡunderwriteType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnderwriteType() {
        return underwriteType;
    }

    /**
     * ����underwriteType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnderwriteType(String value) {
        this.underwriteType = value;
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
     * ��ȡmainPlus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link MainPlusDTO }
     *     
     */
    public MainPlusDTO getMainPlus() {
        return mainPlus;
    }

    /**
     * ����mainPlus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link MainPlusDTO }
     *     
     */
    public void setMainPlus(MainPlusDTO value) {
        this.mainPlus = value;
    }

    /**
     * ��ȡmainSub���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link MainSubDTO }
     *     
     */
    public MainSubDTO getMainSub() {
        return mainSub;
    }

    /**
     * ����mainSub���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link MainSubDTO }
     *     
     */
    public void setMainSub(MainSubDTO value) {
        this.mainSub = value;
    }

    /**
     * ��ȡcargo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CargoDTO }
     *     
     */
    public CargoDTO getCargo() {
        return cargo;
    }

    /**
     * ����cargo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CargoDTO }
     *     
     */
    public void setCargo(CargoDTO value) {
        this.cargo = value;
    }

    /**
     * Gets the value of the addressList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressDTO }
     * 
     * 
     */
    public List<AddressDTO> getAddressList() {
        if (addressList == null) {
            addressList = new ArrayList<AddressDTO>();
        }
        return this.addressList;
    }

    /**
     * Gets the value of the itemkindList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemkindList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemkindList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemkindDTO }
     * 
     * 
     */
    public List<ItemkindDTO> getItemkindList() {
        if (itemkindList == null) {
            itemkindList = new ArrayList<ItemkindDTO>();
        }
        return this.itemkindList;
    }

    /**
     * Gets the value of the itemCargoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemCargoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemCargoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemCargoDTO }
     * 
     * 
     */
    public List<ItemCargoDTO> getItemCargoList() {
        if (itemCargoList == null) {
            itemCargoList = new ArrayList<ItemCargoDTO>();
        }
        return this.itemCargoList;
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
     * Gets the value of the planDetailList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the planDetailList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlanDetailList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanDetailDTO }
     * 
     * 
     */
    public List<PlanDetailDTO> getPlanDetailList() {
        if (planDetailList == null) {
            planDetailList = new ArrayList<PlanDetailDTO>();
        }
        return this.planDetailList;
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
     * Gets the value of the agriSubsidyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agriSubsidyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgriSubsidyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AgriSubsidyDTO }
     * 
     * 
     */
    public List<AgriSubsidyDTO> getAgriSubsidyList() {
        if (agriSubsidyList == null) {
            agriSubsidyList = new ArrayList<AgriSubsidyDTO>();
        }
        return this.agriSubsidyList;
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

    /**
     * ��ȡchannelDTO���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ChannelDTO }
     *     
     */
    public ChannelDTO getChannelDTO() {
        return channelDTO;
    }

    /**
     * ����channelDTO���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelDTO }
     *     
     */
    public void setChannelDTO(ChannelDTO value) {
        this.channelDTO = value;
    }

}
