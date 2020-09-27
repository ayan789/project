
package com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EntBaseInfoInquiryResponseDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="EntBaseInfoInquiryResponseDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="entCustBaseInfo" type="{http://service.ccic.com/ecif/entbaseinfoinquiry/bean}EntCustBaseInfoDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntBaseInfoInquiryResponseDTO", propOrder = {
    "entCustBaseInfo"
})
public class EntBaseInfoInquiryResponseDTO {

    protected EntCustBaseInfoDTO entCustBaseInfo;

    /**
     * ��ȡentCustBaseInfo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link EntCustBaseInfoDTO }
     *     
     */
    public EntCustBaseInfoDTO getEntCustBaseInfo() {
        return entCustBaseInfo;
    }

    /**
     * ����entCustBaseInfo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link EntCustBaseInfoDTO }
     *     
     */
    public void setEntCustBaseInfo(EntCustBaseInfoDTO value) {
        this.entCustBaseInfo = value;
    }

}
