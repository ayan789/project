
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ChannelDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ChannelDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="channelCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channelComCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channelComName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channelProductCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channelOperateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channelTradeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channelTradeSerialNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channelRelationNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChannelDTO", propOrder = {
    "channelCode",
    "channelName",
    "channelComCode",
    "channelComName",
    "channelProductCode",
    "channelOperateCode",
    "channelTradeCode",
    "channelTradeSerialNo",
    "channelRelationNo",
    "sourceType"
})
public class ChannelDTO {

    @XmlElement(required = true)
    protected String channelCode;
    protected String channelName;
    @XmlElement(required = true)
    protected String channelComCode;
    protected String channelComName;
    protected String channelProductCode;
    protected String channelOperateCode;
    protected String channelTradeCode;
    protected String channelTradeSerialNo;
    protected String channelRelationNo;
    protected String sourceType;

    /**
     * ��ȡchannelCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * ����channelCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelCode(String value) {
        this.channelCode = value;
    }

    /**
     * ��ȡchannelName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * ����channelName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelName(String value) {
        this.channelName = value;
    }

    /**
     * ��ȡchannelComCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelComCode() {
        return channelComCode;
    }

    /**
     * ����channelComCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelComCode(String value) {
        this.channelComCode = value;
    }

    /**
     * ��ȡchannelComName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelComName() {
        return channelComName;
    }

    /**
     * ����channelComName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelComName(String value) {
        this.channelComName = value;
    }

    /**
     * ��ȡchannelProductCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelProductCode() {
        return channelProductCode;
    }

    /**
     * ����channelProductCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelProductCode(String value) {
        this.channelProductCode = value;
    }

    /**
     * ��ȡchannelOperateCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelOperateCode() {
        return channelOperateCode;
    }

    /**
     * ����channelOperateCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelOperateCode(String value) {
        this.channelOperateCode = value;
    }

    /**
     * ��ȡchannelTradeCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelTradeCode() {
        return channelTradeCode;
    }

    /**
     * ����channelTradeCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelTradeCode(String value) {
        this.channelTradeCode = value;
    }

    /**
     * ��ȡchannelTradeSerialNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelTradeSerialNo() {
        return channelTradeSerialNo;
    }

    /**
     * ����channelTradeSerialNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelTradeSerialNo(String value) {
        this.channelTradeSerialNo = value;
    }

    /**
     * ��ȡchannelRelationNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelRelationNo() {
        return channelRelationNo;
    }

    /**
     * ����channelRelationNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelRelationNo(String value) {
        this.channelRelationNo = value;
    }

    /**
     * ��ȡsourceType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * ����sourceType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceType(String value) {
        this.sourceType = value;
    }

}
