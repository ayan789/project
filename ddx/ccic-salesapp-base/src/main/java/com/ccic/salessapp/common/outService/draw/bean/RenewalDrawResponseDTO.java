
package com.ccic.salessapp.common.outService.draw.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RenewalDrawResponseDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="RenewalDrawResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="successCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="failedCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RenewalDrawResponseDTO", propOrder = {
    "successCount",
    "failedCount",
    "totalCount"
})
public class RenewalDrawResponseDTO {

    protected int successCount;
    protected int failedCount;
    protected int totalCount;

    /**
     * ��ȡsuccessCount���Ե�ֵ��
     * 
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     * ����successCount���Ե�ֵ��
     * 
     */
    public void setSuccessCount(int value) {
        this.successCount = value;
    }

    /**
     * ��ȡfailedCount���Ե�ֵ��
     * 
     */
    public int getFailedCount() {
        return failedCount;
    }

    /**
     * ����failedCount���Ե�ֵ��
     * 
     */
    public void setFailedCount(int value) {
        this.failedCount = value;
    }

    /**
     * ��ȡtotalCount���Ե�ֵ��
     * 
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * ����totalCount���Ե�ֵ��
     * 
     */
    public void setTotalCount(int value) {
        this.totalCount = value;
    }

}
