
package com.ccic.salessapp.common.outService.ws.common.bean;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salessapp.common.outService.query.bean.RenewalQueryRequestDTO;


/**
 * <p>RequestDTOCommAttr complex type的 Java 类。
 *
 * <p>以下模式片段指定包含在此类中的预期内容。
 *
 * <pre>
 * &lt;complexType name="RequestDTOCommAttr">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="currentPage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestDTOCommAttr", propOrder = {
        "pageSize",
        "currentPage"
})
@XmlSeeAlso({
        RenewalQueryRequestDTO.class
})
public class RequestDTOCommAttr {

    @XmlElement(defaultValue = "10")
    protected Integer pageSize;
    @XmlElement(defaultValue = "1")
    protected Integer currentPage;

    /**
     * 获取pageSize属性的值。
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置pageSize属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPageSize(Integer value) {
        this.pageSize = value;
    }

    /**
     * 获取currentPage属性的值。
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置currentPage属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setCurrentPage(Integer value) {
        this.currentPage = value;
    }

}
