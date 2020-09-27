
package com.ccic.salesapp.noncar.outService.esb.quotetoproposal.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/car/quotetoproposal/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/car/quotetoproposal/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;����������ս����Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>LoanEDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="LoanEDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loanContractNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repaidType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoanEDTO", propOrder = {
    "loanContractNo",
    "loanBankCode",
    "loanStartDate",
    "loanEndDate",
    "repaidType",
    "loanAmount"
})
public class LoanEDTO {

    protected String loanContractNo;
    protected String loanBankCode;
    protected String loanStartDate;
    protected String loanEndDate;
    protected String repaidType;
    protected Double loanAmount;

    /**
     * ��ȡloanContractNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanContractNo() {
        return loanContractNo;
    }

    /**
     * ����loanContractNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanContractNo(String value) {
        this.loanContractNo = value;
    }

    /**
     * ��ȡloanBankCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanBankCode() {
        return loanBankCode;
    }

    /**
     * ����loanBankCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanBankCode(String value) {
        this.loanBankCode = value;
    }

    /**
     * ��ȡloanStartDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanStartDate() {
        return loanStartDate;
    }

    /**
     * ����loanStartDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanStartDate(String value) {
        this.loanStartDate = value;
    }

    /**
     * ��ȡloanEndDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanEndDate() {
        return loanEndDate;
    }

    /**
     * ����loanEndDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanEndDate(String value) {
        this.loanEndDate = value;
    }

    /**
     * ��ȡrepaidType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepaidType() {
        return repaidType;
    }

    /**
     * ����repaidType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepaidType(String value) {
        this.repaidType = value;
    }

    /**
     * ��ȡloanAmount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLoanAmount() {
        return loanAmount;
    }

    /**
     * ����loanAmount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLoanAmount(Double value) {
        this.loanAmount = value;
    }

}
