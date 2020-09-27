package com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;name xmlns="http://service.ccic.com/agent/planFee/bean" xmlns:bean="http://service.ccic.com/agent/planFee/bean" xmlns:common="http://service.ccic.com/common/bean" xmlns:intf="http://service.ccic.com/agent/planFee/intf" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣ��&lt;/name&gt;
 * </pre>
 * 
 * 
 * <p>PlanFeeResponseDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="PlanFeeResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="planFeeInfo" type="{http://service.ccic.com/agent/planFee/bean}PlanFeeDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanFeeResponseDTO", propOrder = {
    "planFeeInfo",
    "size"
})
public class PlanFeeResponseDTO {

    protected List<com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeDTO> planFeeInfo;
    protected int size;

    /**
     * Gets the value of the planFeeInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the planFeeInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlanFeeInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanFeeDTO }
     * 
     * 
     */
    public List<PlanFeeDTO> getPlanFeeInfo() {
        if (planFeeInfo == null) {
            planFeeInfo = new ArrayList<PlanFeeDTO>();
        }
        return this.planFeeInfo;
    }

    /**
     * ��ȡsize���Ե�ֵ��
     * 
     */
    public int getSize() {
        return size;
    }

    /**
     * ����size���Ե�ֵ��
     * 
     */
    public void setSize(int value) {
        this.size = value;
    }

}
