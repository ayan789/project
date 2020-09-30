package com.ccic.salesapp.performance.outService.esb.Withdrawals;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;name xmlns="http://service.ccic.com/agent/withdrawals/bean" xmlns:bean="http://service.ccic.com/agent/withdrawals/bean" xmlns:common="http://service.ccic.com/common/bean" xmlns:intf="http://service.ccic.com/agent/withdrawals/intf" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣ��&lt;/name&gt;
 * </pre>
 * 
 * 
 * <p>WithdrawalsRequestDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="WithdrawalsRequestDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="settleType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="certiIdList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="settleCycle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WithdrawalsRequestDTO", propOrder = {
    "settleType",
    "agentCode",
    "certiIdList",
    "settleCycle",
    "validStatus"
})
public class WithdrawalsRequestDTO {

    @XmlElement(required = true)
    protected String settleType;
    @XmlElement(required = true)
    protected String agentCode;
    protected List<String> certiIdList;
    protected String settleCycle;
    protected String validStatus;

    /**
     * ��ȡsettleType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettleType() {
        return settleType;
    }

    /**
     * ����settleType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettleType(String value) {
        this.settleType = value;
    }

    /**
     * ��ȡagentCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentCode() {
        return agentCode;
    }

    /**
     * ����agentCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentCode(String value) {
        this.agentCode = value;
    }

    /**
     * Gets the value of the certiIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certiIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertiIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCertiIdList() {
        if (certiIdList == null) {
            certiIdList = new ArrayList<String>();
        }
        return this.certiIdList;
    }

    /**
     * ��ȡsettleCycle���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettleCycle() {
        return settleCycle;
    }

    /**
     * ����settleCycle���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettleCycle(String value) {
        this.settleCycle = value;
    }

    /**
     * ��ȡvalidStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidStatus() {
        return validStatus;
    }

    /**
     * ����validStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidStatus(String value) {
        this.validStatus = value;
    }

}