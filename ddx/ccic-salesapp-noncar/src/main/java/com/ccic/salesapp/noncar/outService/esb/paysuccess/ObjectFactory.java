
package com.ccic.salesapp.noncar.outService.esb.paysuccess;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccic.service.payment.paysuccess.bean package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CertiDTODebitTime_QNAME = new QName("http://service.ccic.com/payment/paysuccess/bean", "DebitTime");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccic.service.payment.paysuccess.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PaySuccessResponse }
     * 
     */
    public PaySuccessResponse createPaySuccessResponse() {
        return new PaySuccessResponse();
    }

    /**
     * Create an instance of {@link PaySuccessRequest }
     * 
     */
    public PaySuccessRequest createPaySuccessRequest() {
        return new PaySuccessRequest();
    }

    /**
     * Create an instance of {@link PaySuccessRequestDTO }
     * 
     */
    public PaySuccessRequestDTO createPaySuccessRequestDTO() {
        return new PaySuccessRequestDTO();
    }

    /**
     * Create an instance of {@link CertiDTO }
     * 
     */
    public CertiDTO createCertiDTO() {
        return new CertiDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/payment/paysuccess/bean", name = "DebitTime", scope = CertiDTO.class)
    public JAXBElement<String> createCertiDTODebitTime(String value) {
        return new JAXBElement<String>(_CertiDTODebitTime_QNAME, String.class, CertiDTO.class, value);
    }

}
