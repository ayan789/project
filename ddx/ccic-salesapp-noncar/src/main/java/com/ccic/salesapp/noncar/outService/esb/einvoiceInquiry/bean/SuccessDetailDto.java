
package com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>successDetailDto complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="successDetailDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="receiptNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="policyNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="invoiceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="invoiceNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="planFee" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="inviocePayfee" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "successDetailDto", propOrder = {
    "receiptNo",
    "policyNo",
    "invoiceCode",
    "invoiceNo",
    "planFee",
    "inviocePayfee"
})
public class SuccessDetailDto {

    @XmlElement(required = true)
    protected String receiptNo;
    @XmlElement(required = true)
    protected String policyNo;
    @XmlElement(required = true)
    protected String invoiceCode;
    @XmlElement(required = true)
    protected String invoiceNo;
    @XmlElement(required = true)
    protected BigDecimal planFee;
    @XmlElement(required = true)
    protected BigDecimal inviocePayfee;

    /**
     * ��ȡreceiptNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiptNo() {
        return receiptNo;
    }

    /**
     * ����receiptNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiptNo(String value) {
        this.receiptNo = value;
    }

    /**
     * ��ȡpolicyNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyNo() {
        return policyNo;
    }

    /**
     * ����policyNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyNo(String value) {
        this.policyNo = value;
    }

    /**
     * ��ȡinvoiceCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceCode() {
        return invoiceCode;
    }

    /**
     * ����invoiceCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceCode(String value) {
        this.invoiceCode = value;
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
     * ��ȡplanFee���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPlanFee() {
        return planFee;
    }

    /**
     * ����planFee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPlanFee(BigDecimal value) {
        this.planFee = value;
    }

    /**
     * ��ȡinviocePayfee���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInviocePayfee() {
        return inviocePayfee;
    }

    /**
     * ����inviocePayfee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInviocePayfee(BigDecimal value) {
        this.inviocePayfee = value;
    }

}
