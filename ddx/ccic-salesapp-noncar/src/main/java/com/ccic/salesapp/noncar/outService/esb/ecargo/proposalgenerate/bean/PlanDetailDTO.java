
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PlanDetailDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="PlanDetailDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serialNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="vatrateType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="planFee" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="tniplanFee" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="vatplanFee" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="flag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanDetailDTO", propOrder = {
    "serialNo",
    "vatrateType",
    "planFee",
    "tniplanFee",
    "vatplanFee",
    "flag"
})
public class PlanDetailDTO {

    protected int serialNo;
    @XmlElement(required = true)
    protected String vatrateType;
    protected Double planFee;
    protected Double tniplanFee;
    protected Double vatplanFee;
    protected String flag;

    /**
     * ��ȡserialNo���Ե�ֵ��
     * 
     */
    public int getSerialNo() {
        return serialNo;
    }

    /**
     * ����serialNo���Ե�ֵ��
     * 
     */
    public void setSerialNo(int value) {
        this.serialNo = value;
    }

    /**
     * ��ȡvatrateType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatrateType() {
        return vatrateType;
    }

    /**
     * ����vatrateType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatrateType(String value) {
        this.vatrateType = value;
    }

    /**
     * ��ȡplanFee���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPlanFee() {
        return planFee;
    }

    /**
     * ����planFee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPlanFee(Double value) {
        this.planFee = value;
    }

    /**
     * ��ȡtniplanFee���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTniplanFee() {
        return tniplanFee;
    }

    /**
     * ����tniplanFee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTniplanFee(Double value) {
        this.tniplanFee = value;
    }

    /**
     * ��ȡvatplanFee���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVatplanFee() {
        return vatplanFee;
    }

    /**
     * ����vatplanFee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVatplanFee(Double value) {
        this.vatplanFee = value;
    }

    /**
     * ��ȡflag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlag() {
        return flag;
    }

    /**
     * ����flag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlag(String value) {
        this.flag = value;
    }

}
