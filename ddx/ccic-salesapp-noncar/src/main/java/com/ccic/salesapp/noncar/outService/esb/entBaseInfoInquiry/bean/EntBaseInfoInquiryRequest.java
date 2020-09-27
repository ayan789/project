
package com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.bean;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="requestHead" type="{http://service.ccic.com/common/bean}RequestHeadDTO"/&gt;
 *         &lt;element name="requestBody" type="{http://service.ccic.com/ecif/entbaseinfoinquiry/bean}EntBaseInfoInquiryRequestDTO"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestHead",
    "requestBody"
})
@XmlRootElement(name = "EntBaseInfoInquiryRequest")
public class EntBaseInfoInquiryRequest {

    @XmlElement(required = true)
    protected RequestHeadDTO requestHead;
    @XmlElement(required = true)
    protected EntBaseInfoInquiryRequestDTO requestBody;

    /**
     * ��ȡrequestHead���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link RequestHeadDTO }
     *     
     */
    public RequestHeadDTO getRequestHead() {
        return requestHead;
    }

    /**
     * ����requestHead���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link RequestHeadDTO }
     *     
     */
    public void setRequestHead(RequestHeadDTO value) {
        this.requestHead = value;
    }

    /**
     * ��ȡrequestBody���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link EntBaseInfoInquiryRequestDTO }
     *     
     */
    public EntBaseInfoInquiryRequestDTO getRequestBody() {
        return requestBody;
    }

    /**
     * ����requestBody���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link EntBaseInfoInquiryRequestDTO }
     *     
     */
    public void setRequestBody(EntBaseInfoInquiryRequestDTO value) {
        this.requestBody = value;
    }

}
