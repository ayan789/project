
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
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/car/quotetoproposal/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/car/quotetoproposal/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;��Ӧ��Ϣ��&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>QuoteToProposalResponseDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="QuoteToProposalResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proposalNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proposalNoBI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proposalNoCI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quotationNoBI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quotationNoCI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proposalNoE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comboProposalNoList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ChannelDTO" type="{http://service.ccic.com/car/quotetoproposal/bean}ChannelDTO" minOccurs="0"/>
 *         &lt;element name="check" type="{http://service.ccic.com/car/quotetoproposal/bean}CheckDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extendInfo" type="{http://service.ccic.com/car/quotetoproposal/bean}ExtendInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuoteToProposalResponseDTO", propOrder = {
    "proposalNo",
    "proposalNoBI",
    "proposalNoCI",
    "quotationNoBI",
    "quotationNoCI",
    "proposalNoE",
    "comboProposalNoList",
    "channelDTO",
    "check",
    "extendInfo"
})
public class QuoteToProposalResponseDTO {

    protected String proposalNo;
    protected String proposalNoBI;
    protected String proposalNoCI;
    protected String quotationNoBI;
    protected String quotationNoCI;
    protected String proposalNoE;
    protected List<String> comboProposalNoList;
    @XmlElement(name = "ChannelDTO")
    protected ChannelDTO channelDTO;
    protected List<CheckDTO> check;
    protected List<ExtendInfoDTO> extendInfo;

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
     * ��ȡproposalNoBI���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProposalNoBI() {
        return proposalNoBI;
    }

    /**
     * ����proposalNoBI���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProposalNoBI(String value) {
        this.proposalNoBI = value;
    }

    /**
     * ��ȡproposalNoCI���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProposalNoCI() {
        return proposalNoCI;
    }

    /**
     * ����proposalNoCI���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProposalNoCI(String value) {
        this.proposalNoCI = value;
    }

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
     * ��ȡproposalNoE���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProposalNoE() {
        return proposalNoE;
    }

    /**
     * ����proposalNoE���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProposalNoE(String value) {
        this.proposalNoE = value;
    }

    /**
     * Gets the value of the comboProposalNoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comboProposalNoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComboProposalNoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getComboProposalNoList() {
        if (comboProposalNoList == null) {
            comboProposalNoList = new ArrayList<String>();
        }
        return this.comboProposalNoList;
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

}
