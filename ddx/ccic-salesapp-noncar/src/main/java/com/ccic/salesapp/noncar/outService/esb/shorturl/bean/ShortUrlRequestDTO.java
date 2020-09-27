
package com.ccic.salesapp.noncar.outService.esb.shorturl.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ShortUrlRequestDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ShortUrlRequestDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expandList" type="{http://service.ccic.com/netMarketing/shortUrl/bean}ExpandDTO" maxOccurs="unbounded"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShortUrlRequestDTO", propOrder = {
    "expandList",
    "type"
})
public class ShortUrlRequestDTO {

    @XmlElement(required = true)
    protected List<ExpandDTO> expandList;
    @XmlElement(required = true)
    protected String type;

    /**
     * Gets the value of the expandList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expandList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpandList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExpandDTO }
     * 
     * 
     */
    public List<ExpandDTO> getExpandList() {
        if (expandList == null) {
            expandList = new ArrayList<ExpandDTO>();
        }
        return this.expandList;
    }

    /**
     * ��ȡtype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * ����type���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
