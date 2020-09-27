
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ReCargoDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ReCargoDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="riskKind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ladingNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="kilometre" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="plusRate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="creditNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bargainNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conveyance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="carryBillNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="blName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="blNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="voyageNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preserveInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tonCount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="startSiteCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startSiteName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="viaSiteCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="viaSiteName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endSiteCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endSiteName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endDetailName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="checkAgentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="claimSite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReCargoDTO", propOrder = {
    "riskKind",
    "ladingNo",
    "invoiceNo",
    "invoiceCurrency",
    "invoiceAmount",
    "kilometre",
    "plusRate",
    "creditNo",
    "bargainNo",
    "conveyance",
    "carryBillNo",
    "blName",
    "blNo",
    "voyageNo",
    "preserveInfo",
    "tonCount",
    "startSiteCode",
    "startSiteName",
    "viaSiteCode",
    "viaSiteName",
    "endSiteCode",
    "endSiteName",
    "endDetailName",
    "checkAgentCode",
    "claimSite"
})
public class ReCargoDTO {

    protected String riskKind;
    protected String ladingNo;
    protected String invoiceNo;
    protected String invoiceCurrency;
    protected Double invoiceAmount;
    protected Double kilometre;
    protected Double plusRate;
    protected String creditNo;
    protected String bargainNo;
    protected String conveyance;
    protected String carryBillNo;
    protected String blName;
    protected String blNo;
    protected String voyageNo;
    protected String preserveInfo;
    protected Double tonCount;
    protected String startSiteCode;
    protected String startSiteName;
    protected String viaSiteCode;
    protected String viaSiteName;
    protected String endSiteCode;
    protected String endSiteName;
    protected String endDetailName;
    protected String checkAgentCode;
    protected String claimSite;

    /**
     * ��ȡriskKind���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiskKind() {
        return riskKind;
    }

    /**
     * ����riskKind���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiskKind(String value) {
        this.riskKind = value;
    }

    /**
     * ��ȡladingNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLadingNo() {
        return ladingNo;
    }

    /**
     * ����ladingNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLadingNo(String value) {
        this.ladingNo = value;
    }

    /**
     * ��ȡinvoiceNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * ����invoiceNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceNo(String value) {
        this.invoiceNo = value;
    }

    /**
     * ��ȡinvoiceCurrency���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceCurrency() {
        return invoiceCurrency;
    }

    /**
     * ����invoiceCurrency���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceCurrency(String value) {
        this.invoiceCurrency = value;
    }

    /**
     * ��ȡinvoiceAmount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    /**
     * ����invoiceAmount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setInvoiceAmount(Double value) {
        this.invoiceAmount = value;
    }

    /**
     * ��ȡkilometre���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getKilometre() {
        return kilometre;
    }

    /**
     * ����kilometre���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setKilometre(Double value) {
        this.kilometre = value;
    }

    /**
     * ��ȡplusRate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPlusRate() {
        return plusRate;
    }

    /**
     * ����plusRate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPlusRate(Double value) {
        this.plusRate = value;
    }

    /**
     * ��ȡcreditNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditNo() {
        return creditNo;
    }

    /**
     * ����creditNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditNo(String value) {
        this.creditNo = value;
    }

    /**
     * ��ȡbargainNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBargainNo() {
        return bargainNo;
    }

    /**
     * ����bargainNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBargainNo(String value) {
        this.bargainNo = value;
    }

    /**
     * ��ȡconveyance���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConveyance() {
        return conveyance;
    }

    /**
     * ����conveyance���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConveyance(String value) {
        this.conveyance = value;
    }

    /**
     * ��ȡcarryBillNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarryBillNo() {
        return carryBillNo;
    }

    /**
     * ����carryBillNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarryBillNo(String value) {
        this.carryBillNo = value;
    }

    /**
     * ��ȡblName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlName() {
        return blName;
    }

    /**
     * ����blName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlName(String value) {
        this.blName = value;
    }

    /**
     * ��ȡblNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlNo() {
        return blNo;
    }

    /**
     * ����blNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlNo(String value) {
        this.blNo = value;
    }

    /**
     * ��ȡvoyageNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoyageNo() {
        return voyageNo;
    }

    /**
     * ����voyageNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoyageNo(String value) {
        this.voyageNo = value;
    }

    /**
     * ��ȡpreserveInfo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreserveInfo() {
        return preserveInfo;
    }

    /**
     * ����preserveInfo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreserveInfo(String value) {
        this.preserveInfo = value;
    }

    /**
     * ��ȡtonCount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTonCount() {
        return tonCount;
    }

    /**
     * ����tonCount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTonCount(Double value) {
        this.tonCount = value;
    }

    /**
     * ��ȡstartSiteCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartSiteCode() {
        return startSiteCode;
    }

    /**
     * ����startSiteCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartSiteCode(String value) {
        this.startSiteCode = value;
    }

    /**
     * ��ȡstartSiteName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartSiteName() {
        return startSiteName;
    }

    /**
     * ����startSiteName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartSiteName(String value) {
        this.startSiteName = value;
    }

    /**
     * ��ȡviaSiteCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViaSiteCode() {
        return viaSiteCode;
    }

    /**
     * ����viaSiteCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViaSiteCode(String value) {
        this.viaSiteCode = value;
    }

    /**
     * ��ȡviaSiteName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViaSiteName() {
        return viaSiteName;
    }

    /**
     * ����viaSiteName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViaSiteName(String value) {
        this.viaSiteName = value;
    }

    /**
     * ��ȡendSiteCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndSiteCode() {
        return endSiteCode;
    }

    /**
     * ����endSiteCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndSiteCode(String value) {
        this.endSiteCode = value;
    }

    /**
     * ��ȡendSiteName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndSiteName() {
        return endSiteName;
    }

    /**
     * ����endSiteName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndSiteName(String value) {
        this.endSiteName = value;
    }

    /**
     * ��ȡendDetailName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDetailName() {
        return endDetailName;
    }

    /**
     * ����endDetailName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDetailName(String value) {
        this.endDetailName = value;
    }

    /**
     * ��ȡcheckAgentCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckAgentCode() {
        return checkAgentCode;
    }

    /**
     * ����checkAgentCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckAgentCode(String value) {
        this.checkAgentCode = value;
    }

    /**
     * ��ȡclaimSite���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimSite() {
        return claimSite;
    }

    /**
     * ����claimSite���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimSite(String value) {
        this.claimSite = value;
    }

}
