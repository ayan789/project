
package com.ccic.salesapp.noncar.outService.esb.PaymentNoApply;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PaymentNoApply", targetNamespace = "http://service.ccic.com/payment/paymentnoapply/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PaymentNoApply {


    /**
     * 
     * @param paymentNoApplyRequest
     * @return
     *     returns com.esb.PaymentNoApplyResponse
     */
    @WebMethod(action = "/payment/paymentNoApply")
    @WebResult(name = "PaymentNoApplyResponse", targetNamespace = "http://service.ccic.com/payment/paymentnoapply/bean", partName = "PaymentNoApplyResponse")
    public PaymentNoApplyResponse paymentNoApply(
        @WebParam(name = "PaymentNoApplyRequest", targetNamespace = "http://service.ccic.com/payment/paymentnoapply/bean", partName = "PaymentNoApplyRequest")
        PaymentNoApplyRequest paymentNoApplyRequest);

}