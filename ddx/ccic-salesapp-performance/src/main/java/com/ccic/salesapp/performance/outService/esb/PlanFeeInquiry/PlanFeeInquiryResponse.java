package com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://service.ccic.com/agent/planFee/bean}responseHead"/>
 *         &lt;element ref="{http://service.ccic.com/agent/planFee/bean}responseBody" minOccurs="0"/>
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
@XmlRootElement(name = "PlanFeeInquiryResponse")
public class PlanFeeInquiryResponse {

    @XmlElement(required = true)
    protected ResponseHeadDTO responseHead;
    protected PlanFeeResponseDTO responseBody;

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
     *     {@link PlanFeeResponseDTO }
     *     
     */
    public PlanFeeResponseDTO getResponseBody() {
        return responseBody;
    }

    /**
     * ����responseBody���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link PlanFeeResponseDTO }
     *     
     */
    public void setResponseBody(PlanFeeResponseDTO value) {
        this.responseBody = value;
    }

}
