
package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom.SalesAgentMsgDto;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom.SalesAgreementMsgDto;


/**
 * <p>AgentAgreeInquiryResponseDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="AgentAgreeInquiryResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salesAgentMsgDto" type="{http://service.ccic.com/sales/agentAgree/bean}SalesAgentMsgDto"/>
 *         &lt;element name="salesAgreementMsgList" type="{http://service.ccic.com/sales/agentAgree/bean}SalesAgreementMsgDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AgentAgreeInquiryResponseDTO", propOrder = {
    "salesAgentMsgDto",
    "salesAgreementMsgList"
})
public class AgentAgreeInquiryResponseDTO {

    @XmlElement(required = true)
    protected SalesAgentMsgDto salesAgentMsgDto;
    protected List<SalesAgreementMsgDto> salesAgreementMsgList;

    /**
     * ��ȡsalesAgentMsgDto���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link SalesAgentMsgDto }
     *     
     */
    public SalesAgentMsgDto getSalesAgentMsgDto() {
        return salesAgentMsgDto;
    }

    /**
     * ����salesAgentMsgDto���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link SalesAgentMsgDto }
     *     
     */
    public void setSalesAgentMsgDto(SalesAgentMsgDto value) {
        this.salesAgentMsgDto = value;
    }

    /**
     * Gets the value of the salesAgreementMsgList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the salesAgreementMsgList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalesAgreementMsgList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesAgreementMsgDto }
     * 
     * 
     */
    public List<SalesAgreementMsgDto> getSalesAgreementMsgList() {
        if (salesAgreementMsgList == null) {
            salesAgreementMsgList = new ArrayList<SalesAgreementMsgDto>();
        }
        return this.salesAgreementMsgList;
    }

}
