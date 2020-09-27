
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AgentDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="AgentDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="agreementNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="agreementName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="commission" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="topCommission" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="originCommission" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AgentDTO", propOrder = {
    "agentCode",
    "agreementNo",
    "agreementName",
    "commission",
    "topCommission",
    "originCommission"
})
public class AgentDTO {

    @XmlElement(required = true)
    protected String agentCode;
    @XmlElement(required = true)
    protected String agreementNo;
    @XmlElement(required = true)
    protected String agreementName;
    protected double commission;
    protected double topCommission;
    protected double originCommission;

    /**
     * ��ȡagentCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentCode() {
        return agentCode;
    }

    /**
     * ����agentCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentCode(String value) {
        this.agentCode = value;
    }

    /**
     * ��ȡagreementNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementNo() {
        return agreementNo;
    }

    /**
     * ����agreementNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementNo(String value) {
        this.agreementNo = value;
    }

    /**
     * ��ȡagreementName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementName() {
        return agreementName;
    }

    /**
     * ����agreementName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementName(String value) {
        this.agreementName = value;
    }

    /**
     * ��ȡcommission���Ե�ֵ��
     * 
     */
    public double getCommission() {
        return commission;
    }

    /**
     * ����commission���Ե�ֵ��
     * 
     */
    public void setCommission(double value) {
        this.commission = value;
    }

    /**
     * ��ȡtopCommission���Ե�ֵ��
     * 
     */
    public double getTopCommission() {
        return topCommission;
    }

    /**
     * ����topCommission���Ե�ֵ��
     * 
     */
    public void setTopCommission(double value) {
        this.topCommission = value;
    }

    /**
     * ��ȡoriginCommission���Ե�ֵ��
     * 
     */
    public double getOriginCommission() {
        return originCommission;
    }

    /**
     * ����originCommission���Ե�ֵ��
     * 
     */
    public void setOriginCommission(double value) {
        this.originCommission = value;
    }

}
