package com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>InvoiceLinkQueryResponseDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="InvoiceLinkQueryResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="invoiceLinkList" type="{http://service.ccic.com/payment/InvoiceLinkQuery/bean}InvoiceLink" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceLinkQueryResponseDTO", propOrder = {
    "invoiceLinkList"
})
public class InvoiceLinkQueryResponseDTO {

    protected List<InvoiceLink> invoiceLinkList;

    /**
     * Gets the value of the invoiceLinkList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceLinkList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoiceLinkList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvoiceLink }
     * 
     * 
     */
    public List<InvoiceLink> getInvoiceLinkList() {
        if (invoiceLinkList == null) {
            invoiceLinkList = new ArrayList<InvoiceLink>();
        }
        return this.invoiceLinkList;
    }

}
