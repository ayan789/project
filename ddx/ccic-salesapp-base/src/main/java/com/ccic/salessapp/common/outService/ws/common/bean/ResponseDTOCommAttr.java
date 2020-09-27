
package com.ccic.salessapp.common.outService.ws.common.bean;

import com.ccic.salessapp.common.outService.draw.bean.RenewalDrawResponseDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ResponseDTOCommAttr complex type的 Java 类。
 *
 * <p>以下模式片段指定包含在此类中的预期内容。
 *
 * <pre>
 * &lt;complexType name="ResponseDTOCommAttr">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="currentPage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalPage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseDTOCommAttr", propOrder = {
        "pageSize",
        "currentPage",
        "totalSize",
        "totalPage"
})
@XmlSeeAlso({
        RenewalDrawResponseDTO.class
})
public class ResponseDTOCommAttr {

    @XmlElement(defaultValue = "10")
    protected Integer pageSize;
    @XmlElement(defaultValue = "1")
    protected Integer currentPage;
    protected Integer totalSize;
    protected Integer totalPage;

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

    /**
     * 获取totalSize属性的值。
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getTotalSize() {
        return totalSize;
    }

    /**
     * 设置totalSize属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setTotalSize(Integer value) {
        this.totalSize = value;
    }

    /**
     * 获取totalPage属性的值。
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * 设置totalPage属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setTotalPage(Integer value) {
        this.totalPage = value;
    }

}
