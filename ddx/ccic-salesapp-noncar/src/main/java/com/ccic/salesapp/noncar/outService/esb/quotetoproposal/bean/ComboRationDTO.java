
package com.ccic.salesapp.noncar.outService.esb.quotetoproposal.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/car/quotetoproposal/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/car/quotetoproposal/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;�������۷�����Ϣ&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>ComboRationDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ComboRationDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rationQty" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="kindE" type="{http://service.ccic.com/car/quotetoproposal/bean}KindEDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rationExtList" type="{http://service.ccic.com/car/quotetoproposal/bean}ExtendInfoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComboRationDTO", propOrder = {
    "rationCode",
    "rationQty",
    "kindE",
    "rationExtList"
})
public class ComboRationDTO {

    protected String rationCode;
    protected Integer rationQty;
    protected List<KindEDTO> kindE;
    protected List<ExtendInfoDTO> rationExtList;

    /**
     * ��ȡrationCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRationCode() {
        return rationCode;
    }

    /**
     * ����rationCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRationCode(String value) {
        this.rationCode = value;
    }

    /**
     * ��ȡrationQty���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRationQty() {
        return rationQty;
    }

    /**
     * ����rationQty���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRationQty(Integer value) {
        this.rationQty = value;
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
     * Gets the value of the rationExtList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rationExtList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRationExtList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtendInfoDTO }
     * 
     * 
     */
    public List<ExtendInfoDTO> getRationExtList() {
        if (rationExtList == null) {
            rationExtList = new ArrayList<ExtendInfoDTO>();
        }
        return this.rationExtList;
    }

}
