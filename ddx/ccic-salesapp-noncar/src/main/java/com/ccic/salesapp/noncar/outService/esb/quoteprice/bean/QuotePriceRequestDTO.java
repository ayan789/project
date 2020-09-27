
package com.ccic.salesapp.noncar.outService.esb.quoteprice.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/lifePolicy/quotePrice/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/lifePolicy/quotePrice/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;请求消息体&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>QuotePriceRequestDTO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="QuotePriceRequestDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="channel" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}ChannelDTO" minOccurs="0"/>
 *         &lt;element name="main" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}MainDTO" minOccurs="0"/>
 *         &lt;element name="address" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}AddressDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subsidy" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}SubsidyDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="casualty" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}CasualtyDTO" minOccurs="0"/>
 *         &lt;element name="applicant" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}ApplicantDTO" minOccurs="0"/>
 *         &lt;element name="insured" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}InsuredDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ration" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}RationDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mainLoan" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}MainLoanDTO" minOccurs="0"/>
 *         &lt;element name="itemCars" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}ItemCarDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="travels" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}TravelDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="construct" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}ConstructDTO" minOccurs="0"/>
 *         &lt;element name="personality" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}PersonalityDTO" minOccurs="0"/>
 *         &lt;element name="limit" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}LimitDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="visa" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}VisaDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="plan" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}PlanDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="engage" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}EngageDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="agent" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}AgentDTO" minOccurs="0"/>
 *         &lt;element name="vatInfo" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}VatInfoDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuotePriceRequestDTO", propOrder = {
    "channel",
    "main",
    "address",
    "subsidy",
    "casualty",
    "applicant",
    "insured",
    "ration",
    "mainLoan",
    "itemCars",
    "travels",
    "construct",
    "personality",
    "limit",
    "visa",
    "plan",
    "engage",
    "agent",
    "vatInfo"
})
public class QuotePriceRequestDTO {

    protected ChannelDTO channel;
    protected MainDTO main;
    protected List<AddressDTO> address;
    protected List<SubsidyDTO> subsidy;
    protected CasualtyDTO casualty;
    protected ApplicantDTO applicant;
    protected List<InsuredDTO> insured;
    protected List<RationDTO> ration;
    protected MainLoanDTO mainLoan;
    protected List<ItemCarDTO> itemCars;
    protected List<TravelDTO> travels;
    protected ConstructDTO construct;
    protected PersonalityDTO personality;
    protected List<LimitDTO> limit;
    protected List<VisaDTO> visa;
    protected List<PlanDTO> plan;
    protected List<EngageDTO> engage;
    protected AgentDTO agent;
    protected VatInfoDTO vatInfo;

    /**
     * 获取channel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ChannelDTO }
     *     
     */
    public ChannelDTO getChannel() {
        return channel;
    }

    /**
     * 设置channel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelDTO }
     *     
     */
    public void setChannel(ChannelDTO value) {
        this.channel = value;
    }

    /**
     * 获取main属性的值。
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
     * 设置main属性的值。
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
     * Gets the value of the address property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressDTO }
     * 
     * 
     */
    public List<AddressDTO> getAddress() {
        if (address == null) {
            address = new ArrayList<AddressDTO>();
        }
        return this.address;
    }

    /**
     * Gets the value of the subsidy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subsidy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubsidy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubsidyDTO }
     * 
     * 
     */
    public List<SubsidyDTO> getSubsidy() {
        if (subsidy == null) {
            subsidy = new ArrayList<SubsidyDTO>();
        }
        return this.subsidy;
    }

    /**
     * 获取casualty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CasualtyDTO }
     *     
     */
    public CasualtyDTO getCasualty() {
        return casualty;
    }

    /**
     * 设置casualty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CasualtyDTO }
     *     
     */
    public void setCasualty(CasualtyDTO value) {
        this.casualty = value;
    }

    /**
     * 获取applicant属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ApplicantDTO }
     *     
     */
    public ApplicantDTO getApplicant() {
        return applicant;
    }

    /**
     * 设置applicant属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicantDTO }
     *     
     */
    public void setApplicant(ApplicantDTO value) {
        this.applicant = value;
    }

    /**
     * Gets the value of the insured property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the insured property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInsured().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InsuredDTO }
     * 
     * 
     */
    public List<InsuredDTO> getInsured() {
        if (insured == null) {
            insured = new ArrayList<InsuredDTO>();
        }
        return this.insured;
    }

    /**
     * Gets the value of the ration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RationDTO }
     * 
     * 
     */
    public List<RationDTO> getRation() {
        if (ration == null) {
            ration = new ArrayList<RationDTO>();
        }
        return this.ration;
    }

    /**
     * 获取mainLoan属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MainLoanDTO }
     *     
     */
    public MainLoanDTO getMainLoan() {
        return mainLoan;
    }

    /**
     * 设置mainLoan属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MainLoanDTO }
     *     
     */
    public void setMainLoan(MainLoanDTO value) {
        this.mainLoan = value;
    }

    /**
     * Gets the value of the itemCars property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemCars property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemCars().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemCarDTO }
     * 
     * 
     */
    public List<ItemCarDTO> getItemCars() {
        if (itemCars == null) {
            itemCars = new ArrayList<ItemCarDTO>();
        }
        return this.itemCars;
    }

    /**
     * Gets the value of the travels property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the travels property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTravels().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TravelDTO }
     * 
     * 
     */
    public List<TravelDTO> getTravels() {
        if (travels == null) {
            travels = new ArrayList<TravelDTO>();
        }
        return this.travels;
    }

    /**
     * 获取construct属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ConstructDTO }
     *     
     */
    public ConstructDTO getConstruct() {
        return construct;
    }

    /**
     * 设置construct属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ConstructDTO }
     *     
     */
    public void setConstruct(ConstructDTO value) {
        this.construct = value;
    }

    /**
     * 获取personality属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PersonalityDTO }
     *     
     */
    public PersonalityDTO getPersonality() {
        return personality;
    }

    /**
     * 设置personality属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PersonalityDTO }
     *     
     */
    public void setPersonality(PersonalityDTO value) {
        this.personality = value;
    }

    /**
     * Gets the value of the limit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the limit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLimit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LimitDTO }
     * 
     * 
     */
    public List<LimitDTO> getLimit() {
        if (limit == null) {
            limit = new ArrayList<LimitDTO>();
        }
        return this.limit;
    }

    /**
     * Gets the value of the visa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the visa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVisa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VisaDTO }
     * 
     * 
     */
    public List<VisaDTO> getVisa() {
        if (visa == null) {
            visa = new ArrayList<VisaDTO>();
        }
        return this.visa;
    }

    /**
     * Gets the value of the plan property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plan property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlan().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanDTO }
     * 
     * 
     */
    public List<PlanDTO> getPlan() {
        if (plan == null) {
            plan = new ArrayList<PlanDTO>();
        }
        return this.plan;
    }

    /**
     * Gets the value of the engage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the engage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEngage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EngageDTO }
     * 
     * 
     */
    public List<EngageDTO> getEngage() {
        if (engage == null) {
            engage = new ArrayList<EngageDTO>();
        }
        return this.engage;
    }

    /**
     * 获取agent属性的值。
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
     * 设置agent属性的值。
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
     * 获取vatInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VatInfoDTO }
     *     
     */
    public VatInfoDTO getVatInfo() {
        return vatInfo;
    }

    /**
     * 设置vatInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VatInfoDTO }
     *     
     */
    public void setVatInfo(VatInfoDTO value) {
        this.vatInfo = value;
    }

}
