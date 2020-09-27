
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AddressDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="AddressDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="addressCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addressName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provinceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prefectureCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressDTO", propOrder = {
    "addressNo",
    "addressCode",
    "addressName",
    "provinceCode",
    "prefectureCode",
    "countyCode"
})
public class AddressDTO {

    protected int addressNo;
    @XmlElement(required = true)
    protected String addressCode;
    @XmlElement(required = true)
    protected String addressName;
    @XmlElement(required = true)
    protected String provinceCode;
    @XmlElement(required = true)
    protected String prefectureCode;
    @XmlElement(required = true)
    protected String countyCode;

    /**
     * ��ȡaddressNo���Ե�ֵ��
     * 
     */
    public int getAddressNo() {
        return addressNo;
    }

    /**
     * ����addressNo���Ե�ֵ��
     * 
     */
    public void setAddressNo(int value) {
        this.addressNo = value;
    }

    /**
     * ��ȡaddressCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressCode() {
        return addressCode;
    }

    /**
     * ����addressCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressCode(String value) {
        this.addressCode = value;
    }

    /**
     * ��ȡaddressName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * ����addressName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressName(String value) {
        this.addressName = value;
    }

    /**
     * ��ȡprovinceCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * ����provinceCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceCode(String value) {
        this.provinceCode = value;
    }

    /**
     * ��ȡprefectureCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefectureCode() {
        return prefectureCode;
    }

    /**
     * ����prefectureCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefectureCode(String value) {
        this.prefectureCode = value;
    }

    /**
     * ��ȡcountyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     * ����countyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyCode(String value) {
        this.countyCode = value;
    }

}
