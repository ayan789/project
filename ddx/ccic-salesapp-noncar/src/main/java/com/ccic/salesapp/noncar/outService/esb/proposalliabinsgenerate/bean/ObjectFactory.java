
package com.ccic.salesapp.noncar.outService.esb.proposalliabinsgenerate.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccic.service.liabinsurance.proposalliabinsgenerate.bean package. 
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

    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean", "requestBody");
    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean", "requestHead");
    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean", "responseBody");
    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean", "responseHead");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccic.service.liabinsurance.proposalliabinsgenerate.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProposalGenerateRequestDTO }
     * 
     */
    public ProposalGenerateRequestDTO createProposalGenerateRequestDTO() {
        return new ProposalGenerateRequestDTO();
    }

    /**
     * Create an instance of {@link ProposalGenerateResponseDTO }
     * 
     */
    public ProposalGenerateResponseDTO createProposalGenerateResponseDTO() {
        return new ProposalGenerateResponseDTO();
    }

    /**
     * Create an instance of {@link ProposalGenerateRequest }
     * 
     */
    public ProposalGenerateRequest createProposalGenerateRequest() {
        return new ProposalGenerateRequest();
    }

    /**
     * Create an instance of {@link ProposalGenerateResponse }
     * 
     */
    public ProposalGenerateResponse createProposalGenerateResponse() {
        return new ProposalGenerateResponse();
    }

    /**
     * Create an instance of {@link PayPlanDTO }
     * 
     */
    public PayPlanDTO createPayPlanDTO() {
        return new PayPlanDTO();
    }

    /**
     * Create an instance of {@link MainDTO }
     * 
     */
    public MainDTO createMainDTO() {
        return new MainDTO();
    }

    /**
     * Create an instance of {@link LimitDTO }
     * 
     */
    public LimitDTO createLimitDTO() {
        return new LimitDTO();
    }

    /**
     * Create an instance of {@link ChannelDTO }
     * 
     */
    public ChannelDTO createChannelDTO() {
        return new ChannelDTO();
    }

    /**
     * Create an instance of {@link AgentDTO }
     * 
     */
    public AgentDTO createAgentDTO() {
        return new AgentDTO();
    }

    /**
     * Create an instance of {@link ExpandDTO }
     * 
     */
    public ExpandDTO createExpandDTO() {
        return new ExpandDTO();
    }

    /**
     * Create an instance of {@link InsuredDTO }
     * 
     */
    public InsuredDTO createInsuredDTO() {
        return new InsuredDTO();
    }

    /**
     * Create an instance of {@link ItemKindDTO }
     * 
     */
    public ItemKindDTO createItemKindDTO() {
        return new ItemKindDTO();
    }

    /**
     * Create an instance of {@link ItemDTO }
     * 
     */
    public ItemDTO createItemDTO() {
        return new ItemDTO();
    }

    /**
     * Create an instance of {@link AgriSubsidyDTO }
     * 
     */
    public AgriSubsidyDTO createAgriSubsidyDTO() {
        return new AgriSubsidyDTO();
    }

    /**
     * Create an instance of {@link ExchangeDTO }
     * 
     */
    public ExchangeDTO createExchangeDTO() {
        return new ExchangeDTO();
    }

    /**
     * Create an instance of {@link AddressDTO }
     * 
     */
    public AddressDTO createAddressDTO() {
        return new AddressDTO();
    }

    /**
     * Create an instance of {@link EngageDTO }
     * 
     */
    public EngageDTO createEngageDTO() {
        return new EngageDTO();
    }

    /**
     * Create an instance of {@link CoinsDTO }
     * 
     */
    public CoinsDTO createCoinsDTO() {
        return new CoinsDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProposalGenerateRequestDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean", name = "requestBody")
    public JAXBElement<ProposalGenerateRequestDTO> createRequestBody(ProposalGenerateRequestDTO value) {
        return new JAXBElement<ProposalGenerateRequestDTO>(_RequestBody_QNAME, ProposalGenerateRequestDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProposalGenerateResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean", name = "responseBody")
    public JAXBElement<ProposalGenerateResponseDTO> createResponseBody(ProposalGenerateResponseDTO value) {
        return new JAXBElement<ProposalGenerateResponseDTO>(_ResponseBody_QNAME, ProposalGenerateResponseDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/liabinsurance/proposalLiabInsGenerate/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

}
