
package com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;name xmlns="http://service.ccic.com/h5img/h5imgQueryDown/bean" xmlns:bean="http://service.ccic.com/h5img/h5imgQueryDown/bean" xmlns:common="http://service.ccic.com/common/bean" xmlns:intf="http://service.ccic.com/h5img/h5imgQueryDown/intf" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;��Ӧ��Ϣ��&lt;/name&gt;
 * </pre>
 * 
 * 
 * <p>H5imgQueryDownResponseDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="H5imgQueryDownResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="imageNodes" type="{http://service.ccic.com/h5img/h5imgQueryDown/bean}ImageNodeDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "H5imgQueryDownResponseDTO", propOrder = {
    "size",
    "imageNodes"
})
public class H5ImgQueryDownResponseDTO {

    protected int size;
    protected List<ImageNodeDTO> imageNodes;

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

    /**
     * Gets the value of the imageNodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imageNodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImageNodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImageNodeDTO }
     * 
     * 
     */
    public List<ImageNodeDTO> getImageNodes() {
        if (imageNodes == null) {
            imageNodes = new ArrayList<ImageNodeDTO>();
        }
        return this.imageNodes;
    }

}
