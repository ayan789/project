package com.ccic.salesapp.noncar.outService.esb.InvoiceLink.intf;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "InvoiceLinkQueryService", targetNamespace = "http://service.ccic.com/payment/InvoiceLinkQuery/intf", wsdlLocation = "file:/C:/Users/lenovo/InvoiceLinkQueryService.wsdl")
public class InvoiceLinkQueryService
    extends Service
{

    private final static URL INVOICELINKQUERYSERVICE_WSDL_LOCATION;
    private final static WebServiceException INVOICELINKQUERYSERVICE_EXCEPTION;
    private final static QName INVOICELINKQUERYSERVICE_QNAME = new QName("http://service.ccic.com/payment/InvoiceLinkQuery/intf", "InvoiceLinkQueryService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/lenovo/InvoiceLinkQueryService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        INVOICELINKQUERYSERVICE_WSDL_LOCATION = url;
        INVOICELINKQUERYSERVICE_EXCEPTION = e;
    }

    public InvoiceLinkQueryService() {
        super(__getWsdlLocation(), INVOICELINKQUERYSERVICE_QNAME);
    }

    public InvoiceLinkQueryService(WebServiceFeature... features) {
        super(__getWsdlLocation(), INVOICELINKQUERYSERVICE_QNAME, features);
    }

    public InvoiceLinkQueryService(URL wsdlLocation) {
        super(wsdlLocation, INVOICELINKQUERYSERVICE_QNAME);
    }

    public InvoiceLinkQueryService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, INVOICELINKQUERYSERVICE_QNAME, features);
    }

    public InvoiceLinkQueryService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public InvoiceLinkQueryService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns InvoiceLinkQueryPort
     */
    @WebEndpoint(name = "InvoiceLinkQueryPort")
    public InvoiceLinkQueryPort getInvoiceLinkQueryPort() {
        return super.getPort(new QName("http://service.ccic.com/payment/InvoiceLinkQuery/intf", "InvoiceLinkQueryPort"), InvoiceLinkQueryPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns InvoiceLinkQueryPort
     */
    @WebEndpoint(name = "InvoiceLinkQueryPort")
    public InvoiceLinkQueryPort getInvoiceLinkQueryPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.ccic.com/payment/InvoiceLinkQuery/intf", "InvoiceLinkQueryPort"), InvoiceLinkQueryPort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (INVOICELINKQUERYSERVICE_EXCEPTION!= null) {
            throw INVOICELINKQUERYSERVICE_EXCEPTION;
        }
        return INVOICELINKQUERYSERVICE_WSDL_LOCATION;
    }

}