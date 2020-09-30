package com.ccic.salesapp.noncar.outService.esb.paysuccess;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.0
 * 2018-12-19T11:21:58.603+08:00
 * Generated source version: 2.7.0
 * 
 */
@WebServiceClient(name = "SendSuccessfulPaymentService", 
                  wsdlLocation = "file:/E:/CXF/SendSuccessfulPaymentService.wsdl",
                  targetNamespace = "http://service.ccic.com/payment/paysuccess/intf") 
public class SendSuccessfulPaymentService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://service.ccic.com/payment/paysuccess/intf", "SendSuccessfulPaymentService");
    public final static QName PaySuccess = new QName("http://service.ccic.com/payment/paysuccess/intf", "PaySuccess");
    static {
        URL url = null;
        try {
            url = new URL("file:/E:/CXF/SendSuccessfulPaymentService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SendSuccessfulPaymentService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/E:/CXF/SendSuccessfulPaymentService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SendSuccessfulPaymentService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SendSuccessfulPaymentService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SendSuccessfulPaymentService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns PaySuccess
     */
    @WebEndpoint(name = "PaySuccess")
    public PaySuccess getPaySuccess() {
        return super.getPort(PaySuccess, PaySuccess.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PaySuccess
     */
    @WebEndpoint(name = "PaySuccess")
    public PaySuccess getPaySuccess(WebServiceFeature... features) {
        return super.getPort(PaySuccess, PaySuccess.class, features);
    }

}