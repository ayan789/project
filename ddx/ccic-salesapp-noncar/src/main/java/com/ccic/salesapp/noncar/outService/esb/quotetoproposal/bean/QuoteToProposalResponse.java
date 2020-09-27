
package com.ccic.salesapp.noncar.outService.esb.quotetoproposal.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;

/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://service.ccic.com/car/quotetoproposal/bean}responseHead"/>
 *         &lt;element ref="{http://service.ccic.com/car/quotetoproposal/bean}responseBody" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "responseHead",
    "responseBody"
})
@XmlRootElement(name = "QuoteToProposalResponse")
public class QuoteToProposalResponse {

    @XmlElement(required = true)
    protected ResponseHeadDTO responseHead;
    protected QuoteToProposalResponseDTO responseBody;

    /**
     * ��ȡresponseHead���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ResponseHeadDTO }
     *     
     */
    public ResponseHeadDTO getResponseHead() {
        return responseHead;
    }

    /**
     * ����responseHead���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseHeadDTO }
     *     
     */
    public void setResponseHead(ResponseHeadDTO value) {
        this.responseHead = value;
    }

    /**
     * ��ȡresponseBody���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link QuoteToProposalResponseDTO }
     *     
     */
    public QuoteToProposalResponseDTO getResponseBody() {
        return responseBody;
    }

    /**
     * ����responseBody���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link QuoteToProposalResponseDTO }
     *     
     */
    public void setResponseBody(QuoteToProposalResponseDTO value) {
        this.responseBody = value;
    }

}
