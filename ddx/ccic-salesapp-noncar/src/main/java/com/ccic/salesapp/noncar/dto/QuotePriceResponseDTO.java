package com.ccic.salesapp.noncar.dto;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/lifePolicy/quotePrice/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/lifePolicy/quotePrice/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;返回消息体&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>QuotePriceResponseDTO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="QuotePriceResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proposal" type="{http://service.ccic.com/lifePolicy/quotePrice/bean}ProposalDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuotePriceResponseDTO", propOrder = {
    "proposal"
})
public class QuotePriceResponseDTO {

    protected ProposalDTO proposal;

    /**
     * 获取proposal属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ProposalDTO }
     *     
     */
    public ProposalDTO getProposal() {
        return proposal;
    }

    /**
     * 设置proposal属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ProposalDTO }
     *     
     */
    public void setProposal(ProposalDTO value) {
        this.proposal = value;
    }

}
