
package com.ccic.salesapp.noncar.outService.esb.withholdstatus;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * 预扣单状态通知
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WithholdStatusService", targetNamespace = "http://service.ccic.com/pss/withholdStatus/intf", wsdlLocation = "file:/Users/yanshuai/Desktop/qqqw/a.wsdl")
public class WithholdStatusService
    extends Service
{

    private final static URL WITHHOLDSTATUSSERVICE_WSDL_LOCATION;
    private final static WebServiceException WITHHOLDSTATUSSERVICE_EXCEPTION;
    private final static QName WITHHOLDSTATUSSERVICE_QNAME = new QName("http://service.ccic.com/pss/withholdStatus/intf", "WithholdStatusService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/Users/yanshuai/Desktop/qqqw/a.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WITHHOLDSTATUSSERVICE_WSDL_LOCATION = url;
        WITHHOLDSTATUSSERVICE_EXCEPTION = e;
    }

    public WithholdStatusService() {
        super(__getWsdlLocation(), WITHHOLDSTATUSSERVICE_QNAME);
    }

    public WithholdStatusService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WITHHOLDSTATUSSERVICE_QNAME, features);
    }

    public WithholdStatusService(URL wsdlLocation) {
        super(wsdlLocation, WITHHOLDSTATUSSERVICE_QNAME);
    }

    public WithholdStatusService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WITHHOLDSTATUSSERVICE_QNAME, features);
    }

    public WithholdStatusService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WithholdStatusService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WithholdStatusPort
     */
    @WebEndpoint(name = "WithholdStatusEndpoint")
    public WithholdStatusPort getWithholdStatusEndpoint() {
        return super.getPort(new QName("http://service.ccic.com/pss/withholdStatus/intf", "WithholdStatusEndpoint"), WithholdStatusPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WithholdStatusPort
     */
    @WebEndpoint(name = "WithholdStatusEndpoint")
    public WithholdStatusPort getWithholdStatusEndpoint(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.ccic.com/pss/withholdStatus/intf", "WithholdStatusEndpoint"), WithholdStatusPort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WITHHOLDSTATUSSERVICE_EXCEPTION!= null) {
            throw WITHHOLDSTATUSSERVICE_EXCEPTION;
        }
        return WITHHOLDSTATUSSERVICE_WSDL_LOCATION;
    }

}
