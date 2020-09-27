
package com.ccic.salesapp.noncar.outService.esb.quoteprice.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ns0:name xmlns:ns0="http://service.ccic.com/lifePolicy/quotePrice/bean" xmlns:ns1="http://service.ccic.com/common/bean" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://service.ccic.com/lifePolicy/quotePrice/intf" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣ��&lt;/ns0:name&gt;
 * </pre>
 * 
 * 
 * <p>QuotePriceResponseDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡproposal���Ե�ֵ��
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
     * ����proposal���Ե�ֵ��
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
