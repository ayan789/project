
package com.ccic.salesapp.noncar.outService.esb.perBaseInfoInquiry.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PerBaseInfoInquiryResponseDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="PerBaseInfoInquiryResponseDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="perCustBaseInfo" type="{http://service.ccic.com/ecif/perbaseinfoinquiry/bean}PerCustBaseInfoDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PerBaseInfoInquiryResponseDTO", propOrder = {
    "perCustBaseInfo"
})
public class PerBaseInfoInquiryResponseDTO {

    protected PerCustBaseInfoDTO perCustBaseInfo;

    /**
     * ��ȡperCustBaseInfo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link PerCustBaseInfoDTO }
     *     
     */
    public PerCustBaseInfoDTO getPerCustBaseInfo() {
        return perCustBaseInfo;
    }

    /**
     * ����perCustBaseInfo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link PerCustBaseInfoDTO }
     *     
     */
    public void setPerCustBaseInfo(PerCustBaseInfoDTO value) {
        this.perCustBaseInfo = value;
    }

}
