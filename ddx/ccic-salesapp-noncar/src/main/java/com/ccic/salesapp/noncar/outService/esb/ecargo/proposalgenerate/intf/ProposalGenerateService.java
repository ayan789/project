package com.ccic.salesapp.noncar.outService.esb.ecargo.proposalgenerate.intf;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.0
 * 2017-07-24T12:45:32.459+08:00
 * Generated source version: 2.7.0
 * 
 */
@WebServiceClient(name = "ProposalGenerateService", 
                  wsdlLocation = "ProposalGenerateService.wsdl",
                  targetNamespace = "http://service.ccic.com/ecargo/proposalgenerate/intf") 
public class ProposalGenerateService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://service.ccic.com/ecargo/proposalgenerate/intf", "ProposalGenerateService");
    public final static QName ProposalGenerateEndpoint = new QName("http://service.ccic.com/ecargo/proposalgenerate/intf", "ProposalGenerateEndpoint");
    static {
        URL url = ProposalGenerateService.class.getResource("ProposalGenerateService.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ProposalGenerateService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "ProposalGenerateService.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public ProposalGenerateService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ProposalGenerateService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ProposalGenerateService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
//    public ProposalGenerateService(WebServiceFeature ... features) {
//        super(WSDL_LOCATION, SERVICE, features);
//    }
//
//    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
//    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
//    //compliant code instead.
//    public ProposalGenerateService(URL wsdlLocation, WebServiceFeature ... features) {
//        super(wsdlLocation, SERVICE, features);
//    }
//
//    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
//    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
//    //compliant code instead.
//    public ProposalGenerateService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
//        super(wsdlLocation, serviceName, features);
//    }

    /**
     *
     * @return
     *     returns ProposalGeneratePort
     */
    @WebEndpoint(name = "ProposalGenerateEndpoint")
    public ProposalGeneratePort getProposalGenerateEndpoint() {
        return super.getPort(ProposalGenerateEndpoint, ProposalGeneratePort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ProposalGeneratePort
     */
    @WebEndpoint(name = "ProposalGenerateEndpoint")
    public ProposalGeneratePort getProposalGenerateEndpoint(WebServiceFeature... features) {
        return super.getPort(ProposalGenerateEndpoint, ProposalGeneratePort.class, features);
    }

}
