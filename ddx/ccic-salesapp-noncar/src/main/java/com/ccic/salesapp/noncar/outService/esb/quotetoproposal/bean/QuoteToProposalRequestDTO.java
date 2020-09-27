
package com.ccic.salesapp.noncar.outService.esb.quotetoproposal.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/car/quotetoproposal/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/car/quotetoproposal/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣ��&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>QuoteToProposalRequestDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="QuoteToProposalRequestDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="quotationNoBI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quotationNoCI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proposalNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sumPremiun" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="mergePrint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privy" type="{http://service.ccic.com/car/quotetoproposal/bean}PrivyDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="engage" type="{http://service.ccic.com/car/quotetoproposal/bean}EngageDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="phoneSales" type="{http://service.ccic.com/car/quotetoproposal/bean}PhoneSalesDTO" minOccurs="0"/>
 *         &lt;element name="sales" type="{http://service.ccic.com/car/quotetoproposal/bean}SalesDTO" minOccurs="0"/>
 *         &lt;element name="rela" type="{http://service.ccic.com/car/quotetoproposal/bean}RelaDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="check" type="{http://service.ccic.com/car/quotetoproposal/bean}CheckDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="carTax" type="{http://service.ccic.com/car/quotetoproposal/bean}CartaxDTO" minOccurs="0"/>
 *         &lt;element name="commission" type="{http://service.ccic.com/car/quotetoproposal/bean}CommissionDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ChannelDTO" type="{http://service.ccic.com/car/quotetoproposal/bean}ChannelDTO" minOccurs="0"/>
 *         &lt;element name="extendInfo" type="{http://service.ccic.com/car/quotetoproposal/bean}ExtendInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="personE" type="{http://service.ccic.com/car/quotetoproposal/bean}PersonEDTO" minOccurs="0"/>
 *         &lt;element name="loanE" type="{http://service.ccic.com/car/quotetoproposal/bean}LoanEDTO" minOccurs="0"/>
 *         &lt;element name="kindE" type="{http://service.ccic.com/car/quotetoproposal/bean}KindEDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="projectChannel" type="{http://service.ccic.com/car/quotetoproposal/bean}ProjectChannelDTO" minOccurs="0"/>
 *         &lt;element name="comboProductList" type="{http://service.ccic.com/car/quotetoproposal/bean}ComboProductDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="vatInfo" type="{http://service.ccic.com/car/quotetoproposal/bean}VatInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuoteToProposalRequestDTO", propOrder = {
    "quotationNoBI",
    "quotationNoCI",
    "proposalNo",
    "sumPremiun",
    "mergePrint",
    "privy",
    "engage",
    "phoneSales",
    "sales",
    "rela",
    "check",
    "carTax",
    "commission",
    "channelDTO",
    "extendInfo",
    "personE",
    "loanE",
    "kindE",
    "projectChannel",
    "comboProductList",
    "vatInfo"
})
public class QuoteToProposalRequestDTO {

    protected String quotationNoBI;
    protected String quotationNoCI;
    protected String proposalNo;
    protected double sumPremiun;
    protected String mergePrint;
    protected List<PrivyDTO> privy;
    protected List<EngageDTO> engage;
    protected PhoneSalesDTO phoneSales;
    protected SalesDTO sales;
    protected List<RelaDTO> rela;
    protected List<CheckDTO> check;
    protected CartaxDTO carTax;
    protected List<CommissionDTO> commission;
    @XmlElement(name = "ChannelDTO")
    protected ChannelDTO channelDTO;
    protected List<ExtendInfoDTO> extendInfo;
    protected PersonEDTO personE;
    protected LoanEDTO loanE;
    protected List<KindEDTO> kindE;
    protected ProjectChannelDTO projectChannel;
    protected List<ComboProductDTO> comboProductList;
    protected List<VatInfoDTO> vatInfo;

    /**
     * ��ȡquotationNoBI���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuotationNoBI() {
        return quotationNoBI;
    }

    /**
     * ����quotationNoBI���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuotationNoBI(String value) {
        this.quotationNoBI = value;
    }

    /**
     * ��ȡquotationNoCI���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuotationNoCI() {
        return quotationNoCI;
    }

    /**
     * ����quotationNoCI���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuotationNoCI(String value) {
        this.quotationNoCI = value;
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
     * ��ȡsumPremiun���Ե�ֵ��
     * 
     */
    public double getSumPremiun() {
        return sumPremiun;
    }

    /**
     * ����sumPremiun���Ե�ֵ��
     * 
     */
    public void setSumPremiun(double value) {
        this.sumPremiun = value;
    }

    /**
     * ��ȡmergePrint���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMergePrint() {
        return mergePrint;
    }

    /**
     * ����mergePrint���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMergePrint(String value) {
        this.mergePrint = value;
    }

    /**
     * Gets the value of the privy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the privy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrivy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrivyDTO }
     * 
     * 
     */
    public List<PrivyDTO> getPrivy() {
        if (privy == null) {
            privy = new ArrayList<PrivyDTO>();
        }
        return this.privy;
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
     * ��ȡphoneSales���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link PhoneSalesDTO }
     *     
     */
    public PhoneSalesDTO getPhoneSales() {
        return phoneSales;
    }

    /**
     * ����phoneSales���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneSalesDTO }
     *     
     */
    public void setPhoneSales(PhoneSalesDTO value) {
        this.phoneSales = value;
    }

    /**
     * ��ȡsales���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link SalesDTO }
     *     
     */
    public SalesDTO getSales() {
        return sales;
    }

    /**
     * ����sales���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link SalesDTO }
     *     
     */
    public void setSales(SalesDTO value) {
        this.sales = value;
    }

    /**
     * Gets the value of the rela property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rela property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRela().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelaDTO }
     * 
     * 
     */
    public List<RelaDTO> getRela() {
        if (rela == null) {
            rela = new ArrayList<RelaDTO>();
        }
        return this.rela;
    }

    /**
     * Gets the value of the check property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the check property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCheck().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CheckDTO }
     * 
     * 
     */
    public List<CheckDTO> getCheck() {
        if (check == null) {
            check = new ArrayList<CheckDTO>();
        }
        return this.check;
    }

    /**
     * ��ȡcarTax���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link CartaxDTO }
     *     
     */
    public CartaxDTO getCarTax() {
        return carTax;
    }

    /**
     * ����carTax���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link CartaxDTO }
     *     
     */
    public void setCarTax(CartaxDTO value) {
        this.carTax = value;
    }

    /**
     * Gets the value of the commission property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commission property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommission().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommissionDTO }
     * 
     * 
     */
    public List<CommissionDTO> getCommission() {
        if (commission == null) {
            commission = new ArrayList<CommissionDTO>();
        }
        return this.commission;
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

    /**
     * Gets the value of the extendInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extendInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtendInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtendInfoDTO }
     * 
     * 
     */
    public List<ExtendInfoDTO> getExtendInfo() {
        if (extendInfo == null) {
            extendInfo = new ArrayList<ExtendInfoDTO>();
        }
        return this.extendInfo;
    }

    /**
     * ��ȡpersonE���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link PersonEDTO }
     *     
     */
    public PersonEDTO getPersonE() {
        return personE;
    }

    /**
     * ����personE���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link PersonEDTO }
     *     
     */
    public void setPersonE(PersonEDTO value) {
        this.personE = value;
    }

    /**
     * ��ȡloanE���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link LoanEDTO }
     *     
     */
    public LoanEDTO getLoanE() {
        return loanE;
    }

    /**
     * ����loanE���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link LoanEDTO }
     *     
     */
    public void setLoanE(LoanEDTO value) {
        this.loanE = value;
    }

    /**
     * Gets the value of the kindE property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the kindE property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKindE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KindEDTO }
     * 
     * 
     */
    public List<KindEDTO> getKindE() {
        if (kindE == null) {
            kindE = new ArrayList<KindEDTO>();
        }
        return this.kindE;
    }

    /**
     * ��ȡprojectChannel���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ProjectChannelDTO }
     *     
     */
    public ProjectChannelDTO getProjectChannel() {
        return projectChannel;
    }

    /**
     * ����projectChannel���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ProjectChannelDTO }
     *     
     */
    public void setProjectChannel(ProjectChannelDTO value) {
        this.projectChannel = value;
    }

    /**
     * Gets the value of the comboProductList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comboProductList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComboProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComboProductDTO }
     * 
     * 
     */
    public List<ComboProductDTO> getComboProductList() {
        if (comboProductList == null) {
            comboProductList = new ArrayList<ComboProductDTO>();
        }
        return this.comboProductList;
    }

    /**
     * Gets the value of the vatInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vatInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVatInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VatInfoDTO }
     * 
     * 
     */
    public List<VatInfoDTO> getVatInfo() {
        if (vatInfo == null) {
            vatInfo = new ArrayList<VatInfoDTO>();
        }
        return this.vatInfo;
    }

}
