
package com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;name xmlns="http://service.ccic.com/h5img/h5imgQueryDown/bean" xmlns:bean="http://service.ccic.com/h5img/h5imgQueryDown/bean" xmlns:common="http://service.ccic.com/common/bean" xmlns:intf="http://service.ccic.com/h5img/h5imgQueryDown/intf" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣ��&lt;/name&gt;
 * </pre>
 * 
 * 
 * <p>H5imgQueryDownRequestDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="H5imgQueryDownRequestDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="baseData" type="{http://service.ccic.com/h5img/h5imgQueryDown/bean}BaseDataDTO" minOccurs="0"/>
 *         &lt;element name="metaData" type="{http://service.ccic.com/h5img/h5imgQueryDown/bean}MetaDataDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "H5imgQueryDownRequestDTO", propOrder = {
    "baseData",
    "metaData"
})
public class H5ImgQueryDownRequestDTO {

    protected BaseDataDTO baseData;
    @XmlElement(required = true)
    protected MetaDataDTO metaData;

    /**
     * ��ȡbaseData���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BaseDataDTO }
     *     
     */
    public BaseDataDTO getBaseData() {
        return baseData;
    }

    /**
     * ����baseData���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BaseDataDTO }
     *     
     */
    public void setBaseData(BaseDataDTO value) {
        this.baseData = value;
    }

    /**
     * ��ȡmetaData���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link MetaDataDTO }
     *     
     */
    public MetaDataDTO getMetaData() {
        return metaData;
    }

    /**
     * ����metaData���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link MetaDataDTO }
     *     
     */
    public void setMetaData(MetaDataDTO value) {
        this.metaData = value;
    }

}
