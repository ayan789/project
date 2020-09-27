
package com.ccic.salesapp.noncar.outService.esb.quotetoproposal.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/car/quotetoproposal/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/car/quotetoproposal/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;�������۱�����Ʒ��Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>ComboProductDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ComboProductDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comboRiskCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comboMergePrint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstComboBusinessNature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondComboBusinessNature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondComboHandlerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondComboHandlerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comboStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comboStartHour" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="comboEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comboEndHour" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="comSumAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="comboSumPremium" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="comboRiskKind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comboEPolicyFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commission" type="{http://service.ccic.com/car/quotetoproposal/bean}CommissionDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="engage" type="{http://service.ccic.com/car/quotetoproposal/bean}EngageDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comboRationList" type="{http://service.ccic.com/car/quotetoproposal/bean}ComboRationDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="privy" type="{http://service.ccic.com/car/quotetoproposal/bean}PrivyDTO" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ComboProductDTO", propOrder = {
    "comboRiskCode",
    "comboMergePrint",
    "firstComboBusinessNature",
    "secondComboBusinessNature",
    "secondComboHandlerCode",
    "secondComboHandlerName",
    "comboStartDate",
    "comboStartHour",
    "comboEndDate",
    "comboEndHour",
    "comSumAmount",
    "comboSumPremium",
    "comboRiskKind",
    "comboEPolicyFlag",
    "commission",
    "engage",
    "comboRationList",
    "privy",
    "extendInfo"
})
public class ComboProductDTO {

    protected String comboRiskCode;
    protected String comboMergePrint;
    protected String firstComboBusinessNature;
    protected String secondComboBusinessNature;
    protected String secondComboHandlerCode;
    protected String secondComboHandlerName;
    protected String comboStartDate;
    protected Integer comboStartHour;
    protected String comboEndDate;
    protected Integer comboEndHour;
    protected Double comSumAmount;
    protected Double comboSumPremium;
    protected String comboRiskKind;
    protected String comboEPolicyFlag;
    protected List<CommissionDTO> commission;
    protected List<EngageDTO> engage;
    protected List<ComboRationDTO> comboRationList;
    protected List<PrivyDTO> privy;
    protected List<ExtendInfoDTO> extendInfo;

    /**
     * ��ȡcomboRiskCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComboRiskCode() {
        return comboRiskCode;
    }

    /**
     * ����comboRiskCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComboRiskCode(String value) {
        this.comboRiskCode = value;
    }

    /**
     * ��ȡcomboMergePrint���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComboMergePrint() {
        return comboMergePrint;
    }

    /**
     * ����comboMergePrint���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComboMergePrint(String value) {
        this.comboMergePrint = value;
    }

    /**
     * ��ȡfirstComboBusinessNature���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstComboBusinessNature() {
        return firstComboBusinessNature;
    }

    /**
     * ����firstComboBusinessNature���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstComboBusinessNature(String value) {
        this.firstComboBusinessNature = value;
    }

    /**
     * ��ȡsecondComboBusinessNature���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondComboBusinessNature() {
        return secondComboBusinessNature;
    }

    /**
     * ����secondComboBusinessNature���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondComboBusinessNature(String value) {
        this.secondComboBusinessNature = value;
    }

    /**
     * ��ȡsecondComboHandlerCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondComboHandlerCode() {
        return secondComboHandlerCode;
    }

    /**
     * ����secondComboHandlerCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondComboHandlerCode(String value) {
        this.secondComboHandlerCode = value;
    }

    /**
     * ��ȡsecondComboHandlerName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondComboHandlerName() {
        return secondComboHandlerName;
    }

    /**
     * ����secondComboHandlerName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondComboHandlerName(String value) {
        this.secondComboHandlerName = value;
    }

    /**
     * ��ȡcomboStartDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComboStartDate() {
        return comboStartDate;
    }

    /**
     * ����comboStartDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComboStartDate(String value) {
        this.comboStartDate = value;
    }

    /**
     * ��ȡcomboStartHour���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getComboStartHour() {
        return comboStartHour;
    }

    /**
     * ����comboStartHour���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setComboStartHour(Integer value) {
        this.comboStartHour = value;
    }

    /**
     * ��ȡcomboEndDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComboEndDate() {
        return comboEndDate;
    }

    /**
     * ����comboEndDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComboEndDate(String value) {
        this.comboEndDate = value;
    }

    /**
     * ��ȡcomboEndHour���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getComboEndHour() {
        return comboEndHour;
    }

    /**
     * ����comboEndHour���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setComboEndHour(Integer value) {
        this.comboEndHour = value;
    }

    /**
     * ��ȡcomSumAmount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getComSumAmount() {
        return comSumAmount;
    }

    /**
     * ����comSumAmount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setComSumAmount(Double value) {
        this.comSumAmount = value;
    }

    /**
     * ��ȡcomboSumPremium���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getComboSumPremium() {
        return comboSumPremium;
    }

    /**
     * ����comboSumPremium���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setComboSumPremium(Double value) {
        this.comboSumPremium = value;
    }

    /**
     * ��ȡcomboRiskKind���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComboRiskKind() {
        return comboRiskKind;
    }

    /**
     * ����comboRiskKind���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComboRiskKind(String value) {
        this.comboRiskKind = value;
    }

    /**
     * ��ȡcomboEPolicyFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComboEPolicyFlag() {
        return comboEPolicyFlag;
    }

    /**
     * ����comboEPolicyFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComboEPolicyFlag(String value) {
        this.comboEPolicyFlag = value;
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
     * Gets the value of the comboRationList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comboRationList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComboRationList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComboRationDTO }
     * 
     * 
     */
    public List<ComboRationDTO> getComboRationList() {
        if (comboRationList == null) {
            comboRationList = new ArrayList<ComboRationDTO>();
        }
        return this.comboRationList;
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
