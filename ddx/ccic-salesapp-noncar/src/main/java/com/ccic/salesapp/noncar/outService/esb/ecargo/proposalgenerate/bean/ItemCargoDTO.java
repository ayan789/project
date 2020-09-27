
package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ItemCargoDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ItemCargoDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="itemNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lineNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="marks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marksQuantity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemCargoDTO", propOrder = {
    "itemNo",
    "lineNo",
    "marks",
    "marksQuantity",
    "itemDescription"
})
public class ItemCargoDTO {

    protected int itemNo;
    protected int lineNo;
    protected String marks;
    protected String marksQuantity;
    protected String itemDescription;

    /**
     * ��ȡitemNo���Ե�ֵ��
     * 
     */
    public int getItemNo() {
        return itemNo;
    }

    /**
     * ����itemNo���Ե�ֵ��
     * 
     */
    public void setItemNo(int value) {
        this.itemNo = value;
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
     * ��ȡmarks���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarks() {
        return marks;
    }

    /**
     * ����marks���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarks(String value) {
        this.marks = value;
    }

    /**
     * ��ȡmarksQuantity���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarksQuantity() {
        return marksQuantity;
    }

    /**
     * ����marksQuantity���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarksQuantity(String value) {
        this.marksQuantity = value;
    }

    /**
     * ��ȡitemDescription���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * ����itemDescription���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemDescription(String value) {
        this.itemDescription = value;
    }

}
