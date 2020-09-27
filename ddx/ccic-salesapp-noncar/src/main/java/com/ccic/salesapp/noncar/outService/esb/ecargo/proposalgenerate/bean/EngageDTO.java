
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EngageDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="EngageDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serialNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lineNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="clauseCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clauses" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EngageDTO", propOrder = {
    "serialNo",
    "lineNo",
    "clauseCode",
    "clauses"
})
public class EngageDTO {

    protected int serialNo;
    protected int lineNo;
    @XmlElement(required = true)
    protected String clauseCode;
    @XmlElement(required = true)
    protected String clauses;

    /**
     * ��ȡserialNo���Ե�ֵ��
     * 
     */
    public int getSerialNo() {
        return serialNo;
    }

    /**
     * ����serialNo���Ե�ֵ��
     * 
     */
    public void setSerialNo(int value) {
        this.serialNo = value;
    }

    /**
     * ��ȡlineNo���Ե�ֵ��
     * 
     */
    public int getLineNo() {
        return lineNo;
    }

    /**
     * ����lineNo���Ե�ֵ��
     * 
     */
    public void setLineNo(int value) {
        this.lineNo = value;
    }

    /**
     * ��ȡclauseCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClauseCode() {
        return clauseCode;
    }

    /**
     * ����clauseCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClauseCode(String value) {
        this.clauseCode = value;
    }

    /**
     * ��ȡclauses���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClauses() {
        return clauses;
    }

    /**
     * ����clauses���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClauses(String value) {
        this.clauses = value;
    }

}
